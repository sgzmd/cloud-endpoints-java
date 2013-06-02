package com.sgzmd.examples.monitoringclient.android;

import java.io.IOException;
import java.util.List;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Throwables;
import com.google.api.services.monitoring.Monitoring;
import com.google.api.services.monitoring.model.Room;

public class NetworkMonitoringProvider implements MonitoringDataProvider {
  Monitoring monitoring = new Monitoring(
      AndroidHttp.newCompatibleTransport(), 
      new GsonFactory(), 
      null);
  
  @Override
  public List<Room> getRooms() {
    try {
      return monitoring.listRooms().execute().getItems();
    } catch (IOException e) {
      throw Throwables.propagate(e);
    }
  }
}
