package com.sgzmd.examples.cloudendpoints.android;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.util.Throwables;
import com.google.api.services.monitoring.model.Room;
import com.google.common.collect.Lists;

/**
 * A list fragment representing a list of Rooms. This fragment also supports
 * tablet devices by allowing list items to be given an 'activated' state upon
 * selection. This helps indicate which item is currently being viewed in a
 * {@link RoomDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class RoomListFragment extends ListFragment {

  private static final String TAG = RoomListFragment.class.getSimpleName();

  /**
   * The serialization (saved instance state) Bundle key representing the
   * activated item position. Only used on tablets.
   */
  private static final String STATE_ACTIVATED_POSITION = "activated_position";

  /**
   * The fragment's current callback object, which is notified of list item
   * clicks.
   */
  private Callbacks mCallbacks = sDummyCallbacks;

  /**
   * The current activated item position. Only used on tablets.
   */
  private int mActivatedPosition = ListView.INVALID_POSITION;

  /**
   * A callback interface that all activities containing this fragment must
   * implement. This mechanism allows activities to be notified of item
   * selections.
   */
  public interface Callbacks {
    /**
     * Callback for when an item has been selected.
     */
    public void onItemSelected(Long roomId);
  }

  private RoomArrayAdapter adapter;


  /**
   * A dummy implementation of the {@link Callbacks} interface that does
   * nothing. Used only when this fragment is not attached to an activity.
   */
  private static Callbacks sDummyCallbacks = new Callbacks() {
    @Override
    public void onItemSelected(Long roomId) {
    }
  };

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public RoomListFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    Log.i(TAG, "Entering onCreate");
    super.onCreate(savedInstanceState);

    new AsyncTask<Void, Void, List<Room>>() {
      @Override
      protected void onPostExecute(List<Room> result) {
        adapter = new RoomArrayAdapter(RoomListFragment.this.getActivity(), 0,
            MonitoringProvider.getRoomParcelables(result));
        RoomListFragment.this.setListAdapter(RoomListFragment.this.adapter);
      }

      @Override
      protected List<Room> doInBackground(Void... params) {
        try {
          Log.d(TAG, "Getting data from the API...");
          return MonitoringProvider.get().listRooms().execute().getItems();
        } catch (IOException e) {
          Log.e(TAG, "IOException occurred when getting list of rooms", e);
          return Lists.newArrayList();
        }
      }
    }.execute();

    setHasOptionsMenu(true);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    // Restore the previously serialized activated item position.
    if (savedInstanceState != null
        && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
      setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
    }
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);

    // Activities containing this fragment must implement its callbacks.
    if (!(activity instanceof Callbacks)) {
      throw new IllegalStateException(
          "Activity must implement fragment's callbacks.");
    }

    mCallbacks = (Callbacks) activity;
  }

  @Override
  public void onDetach() {
    super.onDetach();

    // Reset the active callbacks interface to the dummy implementation.
    mCallbacks = sDummyCallbacks;
  }

  @Override
  public void onListItemClick(ListView listView, View view, int position,
      long id) {
    Log.i(this.getClass().getSimpleName(),
        String.format("onListItemClick: position=%d, id=%d", position, id));
    super.onListItemClick(listView, view, position, id);

    mCallbacks.onItemSelected(this.adapter.getItem(position).getId());
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    if (mActivatedPosition != ListView.INVALID_POSITION) {
      // Serialize and persist the activated item position.
      outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
    }
  }

  /**
   * Turns on activate-on-click mode. When this mode is on, list items will be
   * given the 'activated' state when touched.
   */
  public void setActivateOnItemClick(boolean activateOnItemClick) {
    // When setting CHOICE_MODE_SINGLE, ListView will automatically
    // give items the 'activated' state when touched.
    getListView().setChoiceMode(
        activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
            : ListView.CHOICE_MODE_NONE);
  }

  private void setActivatedPosition(int position) {
    if (position == ListView.INVALID_POSITION) {
      getListView().setItemChecked(mActivatedPosition, false);
    } else {
      getListView().setItemChecked(position, true);
    }

    mActivatedPosition = position;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.options_menu, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {

    case R.id.options_menu_refresh:

      new AsyncTask<Void, Void, List<Room>>() {
        @Override
        protected void onPostExecute(List<Room> result) {
          if (Log.isLoggable(TAG, Log.DEBUG)) {
            for (Room room : result) {
              try {
                Log.d(TAG, room.toPrettyString());
              } catch (UserRecoverableAuthIOException ue) {
                RoomListFragment.this.getActivity().startActivityForResult(ue.getIntent(), 2);
              } catch (IOException e) {
                // should never happen
                Throwables.propagate(e);
              }
            }
          }
          adapter.refreshBackingList(MonitoringProvider
              .getRoomParcelables(result));
        }

        @Override
        protected List<Room> doInBackground(Void... params) {
          try {
            return MonitoringProvider.get().listRooms().execute().getItems();
          } catch (IOException e) {
            return Lists.newArrayList();
          }
        }
      }.execute();
    }

    return super.onOptionsItemSelected(item);
  }
}
