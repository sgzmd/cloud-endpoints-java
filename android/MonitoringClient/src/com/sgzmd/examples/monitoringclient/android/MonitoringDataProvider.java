package com.sgzmd.examples.monitoringclient.android;

import java.util.List;

import com.google.api.services.monitoring.model.Room;

public interface MonitoringDataProvider {
  List<Room> getRooms();
}
