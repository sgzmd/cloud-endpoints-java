package com.sgzmd.examples.monitoringclient.android;

import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.api.client.util.Preconditions;
import com.google.api.services.monitoring.Monitoring;
import com.google.api.services.monitoring.model.Room;
import com.sgzmd.examples.monitoringclient.android.model.RoomParcelable;

public class MainActivity extends FragmentActivity implements
    ActionBar.TabListener {

  /**
   * The {@link android.support.v4.view.PagerAdapter} that will provide
   * fragments for each of the sections. We use a
   * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will
   * keep every loaded fragment in memory. If this becomes too memory intensive,
   * it may be best to switch to a
   * {@link android.support.v4.app.FragmentStatePagerAdapter}.
   */
  SectionsPagerAdapter mSectionsPagerAdapter;

  final MonitoringDataProvider dataProvider = new NetworkMonitoringProvider();

  /**
   * The {@link ViewPager} that will host the section contents.
   */
  ViewPager mViewPager;

  private List<Room> rooms = null;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    new AsyncTask<Void, Void, List<Room>>() {
      @Override
      protected List<Room> doInBackground(Void... params) {
        Log.i("MonitoringClient", "Trying to connect to "
            + Monitoring.DEFAULT_BASE_URL);
        return dataProvider.getRooms();
      }

      @Override
      protected void onPostExecute(List<Room> result) {
        initialisePager(result);
      }
    }.execute();

    setContentView(R.layout.activity_main);
  }

  private void initialisePager(List<Room> rooms) {
    // Create the adapter that will return a fragment for each of the three
    // primary sections
    // of the app.
    this.rooms = rooms;

    mSectionsPagerAdapter = new SectionsPagerAdapter(
        getSupportFragmentManager(), rooms);

    // Set up the action bar.
    final ActionBar actionBar = getActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    // Set up the ViewPager with the sections adapter.
    mViewPager = (ViewPager) findViewById(R.id.pager);
    mViewPager.setAdapter(mSectionsPagerAdapter);

    // When swiping between different sections, select the corresponding
    // tab.
    // We can also use ActionBar.Tab#select() to do this if we have a
    // reference
    // to the
    // Tab.
    mViewPager
        .setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
          @Override
          public void onPageSelected(int position) {
            actionBar.setSelectedNavigationItem(position);
          }
        });

    // For each of the sections in the app, add a tab to the action bar.
    for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
      actionBar.addTab(actionBar.newTab()
          .setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }

  /**
   * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one
   * of the primary sections of the app.
   */
  public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final List<Room> rooms;

    public SectionsPagerAdapter(FragmentManager fm, List<Room> rooms) {
      super(fm);

      this.rooms = rooms;
    }

    @Override
    public Fragment getItem(int i) {
      Preconditions.checkArgument(i < rooms.size());

      Fragment fragment = new DummySectionFragment();
      Bundle args = new Bundle();
      
      args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
      args.putParcelable(DummySectionFragment.ROOM, RoomParcelable.fromRoom(rooms.get(i)));
      
      fragment.setArguments(args);
      
      return fragment;
    }

    @Override
    public int getCount() {
      return rooms.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
      Preconditions.checkArgument(position < rooms.size());
      return rooms.get(position).getName();
    }
  }

  /**
   * A dummy fragment representing a section of the app, but that simply
   * displays dummy text.
   */
  public static class DummySectionFragment extends Fragment {
    public static final String ROOM = "room_json";

    private RoomParcelable room;

    public static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      
      room = getArguments().getParcelable(ROOM);
      
      LinearLayout layout = new LinearLayout(getActivity());
      layout.setGravity(Gravity.TOP);

      SensorArrayAdapter adapter = new SensorArrayAdapter(getActivity(), room.getSensors());
      ListView listView = new ListView(getActivity());
      listView.setAdapter(adapter);
      
      layout.addView(listView);

      return layout;
    }
  }

  public void onTabReselected(Tab tab, FragmentTransaction ft) {
    // TODO Auto-generated method stub

  }

  public void onTabSelected(Tab tab, FragmentTransaction ft) {
    // TODO Auto-generated method stub

  }

  public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    // TODO Auto-generated method stub

  }
}
