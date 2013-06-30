package com.sgzmd.examples.cloudendpoints.android;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.google.api.client.util.Throwables;
import com.google.api.services.monitoring.Monitoring.Arm;
import com.sgzmd.examples.cloudendpoints.android.model.SensorParcelable;

public class SensorArrayAdapter extends ArrayAdapter<SensorParcelable> {
  // we probably should use Joda or something like PrettyTime
  @SuppressLint("SimpleDateFormat")
  private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm");

  private static final String TAG = SensorArrayAdapter.class.getSimpleName();

  private final LayoutInflater inflater;

  public SensorArrayAdapter(Context context, SensorParcelable[] sensors) {
    super(context, 0, sensors);
    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    final SensorParcelable sensor = this.getItem(position);
    Log.d(TAG, sensor.toString());
    View view = inflater.inflate(R.layout.sensor_list_item, parent, false);

    CheckBox enabled = (CheckBox) view.findViewById(R.id.sensor_list_item_enabled);
    enabled.setChecked(sensor.getActive());
    
    enabled.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
        new AsyncTask<Void, Void, Void>() {
          @Override
          protected Void doInBackground(Void... params) {
            try {
              Arm request = MonitoringProvider.get()
                  .arm(isChecked)
                  .setSensor(sensor.getId())
                  .setRoom(sensor.getRoomId());

              Log.d(TAG, request.toString());
              request.execute();
            } catch (IOException e) {
              Throwables.propagate(e);
            }
            return null;
          }
        }.execute();
      }
    });

    TextView lastActive = (TextView) view.findViewById(R.id.sensor_list_item_last_active);

    // TODO(sgzmd): creating an extra object in heap is completely unnecessary
    // here. get rid of it!
    lastActive.setText(SDF.format(new Date(sensor.getLastActiveMillis())));

    TextView name = (TextView) view.findViewById(R.id.sensor_list_item_name);
    name.setText(sensor.getName());

    return view;
  }
}
