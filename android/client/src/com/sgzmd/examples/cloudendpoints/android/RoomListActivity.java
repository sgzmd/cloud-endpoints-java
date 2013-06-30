package com.sgzmd.examples.cloudendpoints.android;

import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

/**
 * An activity representing a list of Rooms. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link RoomDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link RoomListFragment} and the item details (if present) is a
 * {@link RoomDetailFragment}.
 * <p>
 * This activity also implements the required {@link RoomListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class RoomListActivity extends FragmentActivity implements
    RoomListFragment.Callbacks {

  private SharedPreferences settings;
  private GoogleAccountCredential credential;

  static final String TAG = RoomListActivity.class.getSimpleName();

  // key for storing authenticated account preference
  static final String PREF_ACCOUNT_NAME = "account-name";
  
  static final int REQUEST_ACCOUNT_PICKER = 2;

  /**
   * Whether or not the activity is in two-pane mode, i.e. running on a tablet
   * device.
   */
  private boolean mTwoPane;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_room_list);

    // Inside your Activity class onCreate method
    this.settings = getSharedPreferences(TAG, 0);
    this.credential = GoogleAccountCredential.usingAudience(this, ClientCredentials.AUDIENCE);
    String account = settings.getString(PREF_ACCOUNT_NAME, null);
    if (account != null) {
      setAccountName(account);
    } else {
      chooseAccount();
    }

    if (findViewById(R.id.room_detail_container) != null) {
      // The detail container view will be present only in the
      // large-screen layouts (res/values-large and
      // res/values-sw600dp). If this view is present, then the
      // activity should be in two-pane mode.
      mTwoPane = true;

      // In two-pane mode, list items should be given the
      // 'activated' state when touched.
      ((RoomListFragment) getSupportFragmentManager().findFragmentById(
          R.id.room_list)).setActivateOnItemClick(true);
    }

    // TODO: If exposing deep links into your app, handle intents here.
  }

  /**
   * Callback method from {@link RoomListFragment.Callbacks} indicating that the
   * item with the given ID was selected.
   */
  public void onItemSelected(String id) {
    if (mTwoPane) {
      // In two-pane mode, show the detail view in this activity by
      // adding or replacing the detail fragment using a
      // fragment transaction.
      Bundle arguments = new Bundle();
      arguments.putString(RoomDetailFragment.ARG_ITEM_ID, id);
      RoomDetailFragment fragment = new RoomDetailFragment();
      fragment.setArguments(arguments);
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.room_detail_container, fragment).commit();

    } else {
      // In single-pane mode, simply start the detail activity
      // for the selected item ID.
      Intent detailIntent = new Intent(this, RoomDetailActivity.class);
      detailIntent.putExtra(RoomDetailFragment.ARG_ITEM_ID, id);
      startActivity(detailIntent);
    }
  }

  @Override
  public void onItemSelected(Long roomId) {
    Log.i(TAG, "onItemSelected " + roomId);
    // RoomParcelable room =
    // dataProvider.getRoomByPosition(roomPosition.intValue());
    if (mTwoPane) {
      // In two-pane mode, show the detail view in this activity by
      // adding or replacing the detail fragment using a
      // fragment transaction.
      Bundle arguments = new Bundle();
      arguments.putLong(RoomDetailFragment.ARG_ITEM_ID, roomId);
      RoomDetailFragment fragment = new RoomDetailFragment();
      fragment.setArguments(arguments);
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.room_detail_container, fragment)
          .commit();
    } else {
      // In single-pane mode, simply start the detail activity
      // for the selected item ID.
      Intent detailIntent = new Intent(this, RoomDetailActivity.class);
      detailIntent.putExtra(RoomDetailFragment.ARG_ITEM_ID, roomId);
      startActivity(detailIntent);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
    case REQUEST_ACCOUNT_PICKER:
      if (data != null && data.getExtras() != null) {
        String accountName = data.getExtras().getString(
            AccountManager.KEY_ACCOUNT_NAME);
        if (accountName != null) {
          setAccountName(accountName);
          SharedPreferences.Editor editor = settings.edit();
          editor.putString(PREF_ACCOUNT_NAME, accountName);
          editor.commit();
          Log.i(TAG, "Now have account " + accountName);
          // User is authorized.
        }
      } else {
        reportAuthenticationFailure();
      }
      break;
    }
  }

  
  @Override
  protected void onResume() {
    super.onResume();
    checkGooglePlayServicesAvailable();
  }
  
  /**
   * Pops up Account Picker dialog to select a Google account to authenticate with
   */
  protected void chooseAccount() {
    Log.i(TAG, "chooseAccount");
    startActivityForResult(
        credential.newChooseAccountIntent(),
        REQUEST_ACCOUNT_PICKER);
  }

  /**
   * Stores selected account name (e.g. username@gmail.com) to shared preferences.
   */
  private void setAccountName(String accountName) {
    SharedPreferences.Editor editor = settings.edit();
    editor.putString(PREF_ACCOUNT_NAME, accountName);
    editor.commit();
    credential.setSelectedAccountName(accountName);
    MonitoringProvider.initialise(credential);
  }
  
  /**
   * Check that Google Play services APK is installed and up to date.
   */
  private boolean checkGooglePlayServicesAvailable() {
    final int connectionStatusCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (GooglePlayServicesUtil.isUserRecoverableError(connectionStatusCode)) {
      showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
      return false;
    }
    return true;
  }

  /**
   * Called if the device does not have Google Play Services installed.
   */
  void showGooglePlayServicesAvailabilityErrorDialog(final int connectionStatusCode) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
            connectionStatusCode, RoomListActivity.this, 0);
        dialog.show();
      }
    });
  }
  
  void reportAuthenticationFailure() {
    Toast toast = Toast.makeText(this, R.string.authentication_failed, Toast.LENGTH_LONG);
    
    toast.setGravity(Gravity.CENTER, 0, 0);
    toast.show();
    
    finish();
  }
}
