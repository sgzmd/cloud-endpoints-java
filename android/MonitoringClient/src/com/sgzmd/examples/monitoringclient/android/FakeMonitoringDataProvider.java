package com.sgzmd.examples.monitoringclient.android;

import java.util.List;
import java.util.Random;

import com.google.api.client.util.Lists;
import com.google.api.services.monitoring.model.Room;
import com.google.api.services.monitoring.model.Sensor;


public class FakeMonitoringDataProvider implements MonitoringDataProvider {
  @Override
  public List<Room> getRooms() {
    List<Room> rooms = Lists.newArrayList();
    
    rooms.add(makeRoom("Living Room", 5));
    rooms.add(makeRoom("Bedroom", 3));
    rooms.add(makeRoom("Hall", 6));
    rooms.add(makeRoom("Guest Bedroom", 2));
    
    return rooms;
  } 
  
  private Room makeRoom(String name, int numSensors) {
    Room room = new Room();
    room.setName(name);
    List<Sensor> sensors = Lists.newArrayList();
    for (int i = 0; i < numSensors; ++i) {
      Sensor s = new Sensor();
      s.setNetworkId(Long.toHexString(new Random(i).nextLong()));
      s.setSensorType("MOTION");
      s.setActive(true);
      sensors.add(s);
    }
    room.setSensors(sensors);
    return room;
  }
}
