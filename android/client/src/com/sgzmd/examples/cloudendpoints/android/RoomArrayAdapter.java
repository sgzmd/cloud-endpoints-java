package com.sgzmd.examples.cloudendpoints.android;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.sgzmd.examples.cloudendpoints.android.model.RoomParcelable;

public class RoomArrayAdapter extends ArrayAdapter<RoomParcelable> {
  // we probably should use Joda or something like PrettyTime
  @SuppressLint("SimpleDateFormat")
  private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm");

  private final LayoutInflater inflater;
  private final List<RoomParcelable> backingList;

  public RoomArrayAdapter(Context context, int textViewResourceId, List<RoomParcelable> objects) {
    super(context, 0, objects);
    this.backingList = objects;
    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    final RoomParcelable room = this.getItem(position);
    View view = inflater.inflate(R.layout.room_list_item, parent, false);

    ((TextView) view.findViewById(R.id.room_list_item_name)).setText(room.getName());
    CheckBox checkBox = (CheckBox) view.findViewById(R.id.room_list_item_enabled);
    checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
        new AsyncTask<Void, Void, Void>() {
          @Override
          protected Void doInBackground(Void... params) {
            try {
              MonitoringProvider.get().arm(isChecked).set("room", room.getId()).execute();
            } catch (IOException e) {
              Log.w(RoomArrayAdapter.class.getSimpleName(), e);
            }
            return null;
          }
        }.execute();
      }
    });
    checkBox.setChecked(true);

    String lastActive = SDF.format(new Date(room.getLastActiveMs()));
    ((TextView) view.findViewById(R.id.room_list_item_last_active)).setText(lastActive);

    return view;
  }

  public void refreshBackingList(List<RoomParcelable> objects) {
    backingList.clear();
    backingList.addAll(objects);
    notifyDataSetChanged();
  }
}
