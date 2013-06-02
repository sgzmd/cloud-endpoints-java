package com.sgzmd.examples.monitoringclient.android;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.sgzmd.examples.monitoringclient.android.model.SensorParcelable;

public class SensorArrayAdapter extends ArrayAdapter<SensorParcelable> {

  public SensorArrayAdapter(Context context, SensorParcelable[] sensors) {
    super(context, 0, sensors);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    CheckBox checkBox = new CheckBox(parent.getContext());
    final SensorParcelable sensor = this.getItem(position);
    checkBox.setText(sensor.getName());
    checkBox.setChecked(sensor.getActive());
    checkBox.setTag(sensor);

    checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        new AlertDialog.Builder(SensorArrayAdapter.this.getContext())
            .setTitle("Sensor").setMessage(sensor.getNetworkId()).show();
      }
    });

    return checkBox;
  }
}
