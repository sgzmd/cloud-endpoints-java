package com.sgzmd.examples.cloudendpoints.android;

import java.io.IOException;

import javax.annotation.Nullable;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.api.client.util.Throwables;
import com.google.api.services.monitoring.model.Room;
import com.google.api.services.monitoring.model.RoomCollection;
import com.google.api.services.monitoring.model.Sensor;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.sgzmd.examples.cloudendpoints.android.model.RoomParcelable;

/**
 * A fragment representing a single Room detail screen. This fragment is either
 * contained in a {@link RoomListActivity} in two-pane mode (on tablets) or a
 * {@link RoomDetailActivity} on handsets.
 */
public class RoomDetailFragment extends Fragment {
  private static final String TAG = RoomDetailFragment.class.getSimpleName();
  /**
   * The fragment argument representing the item ID that this fragment
   * represents.
   */
  public static final String ARG_ITEM_ID = "item_id";

  /**
   * The dummy content this fragment is presenting.
   */
  private RoomParcelable room = null;

  private View rootView;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public RoomDetailFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Sensor sensor = new Sensor();
    sensor.setActive(true);

    if (getArguments().containsKey(ARG_ITEM_ID)) {
      final long roomId = getArguments().getLong(ARG_ITEM_ID);

      new AsyncTask<Void, Void, Room>() {
        @Override
        protected Room doInBackground(Void... params) {
          try {
            // I don't know why getRoom doesn't return sensors.
            RoomCollection rooms = MonitoringProvider.get().listRooms().execute();
            return Iterables.find(rooms.getItems(), new Predicate<Room>() {
              @Override
              public boolean apply(@Nullable Room arg0) {
                return arg0.getId() == roomId;
              }
            });
          } catch (IOException e) {
            // TODO(sgzmd): probably a bad idea
            return null;
          }
        }

        @Override
        protected void onPostExecute(Room result) {
          Log.i(TAG, "onPostExecute");
          try {
            Log.i(TAG, result.toPrettyString());
          } catch (IOException e) {
            // REALLY shouldn't happen
            Throwables.propagate(e);
          }
          RoomDetailFragment.this.room = RoomParcelable.fromRoom(result);
          RoomDetailFragment.this.populateView();
        }
      }.execute();
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    this.rootView = inflater.inflate(R.layout.fragment_room_detail, container, false);
    return rootView;
  }

  private void populateView() {
    if (room != null) {
      this.getActivity().setTitle(room.getName());
      ListView listView = (ListView) rootView;
      listView.setAdapter(new SensorArrayAdapter(getActivity(), room.getSensors()));
    }
  }
}
