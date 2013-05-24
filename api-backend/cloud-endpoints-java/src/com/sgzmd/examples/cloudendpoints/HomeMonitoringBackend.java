package com.sgzmd.examples.cloudendpoints;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.labs.repackaged.com.google.common.base.Predicate;
import com.google.appengine.labs.repackaged.com.google.common.collect.Collections2;


/**
 * API class exporting collection of {@link Room} objects.
 * 
 * @author me@romankirillov.info [Roman "sgzmd" Kirillov"]
 */
@Api(
    name = "home",
    version = "v1")
public class HomeMonitoringBackend {
  @ApiMethod(
      name = "rooms.list",
      httpMethod = "GET",
      path = "list")
  @SuppressWarnings("unchecked")
  public List<Room> listRooms() {
    PersistenceManager pm = getPersistenceManager();
    Query query = pm.newQuery(Room.class);
    return (List<Room>) pm.newQuery(query).execute();
  }

  @ApiMethod(name = "rooms.insert")
  public void insert(Room room) {
    PersistenceManager pm = getPersistenceManager();
    pm.makePersistent(room);
  }

  @ApiMethod(
      name = "rooms.listsensors",
      httpMethod = "GET",
      path = "rooms/{room}/sensors")
  public List<Sensor> listSensorsForRoom(@Named("room") Long roomId) {
    PersistenceManager pm = getPersistenceManager();
    Room room = pm.getObjectById(
        Room.class,
        KeyFactory.createKey(Room.class.getSimpleName(), roomId));

    return room.getSensors();
  }

  @ApiMethod(
      name = "rooms.insertsensor",
      httpMethod = "POST",
      path = "rooms/{room}/sensors/insert")
  public void insertSensorToRoom(@Named("room") Long roomId, Sensor sensor) {
    PersistenceManager pm = getPersistenceManager();
    Room room = pm.getObjectById(
        Room.class,
        KeyFactory.createKey(Room.class.getSimpleName(), roomId));
    room.addSensor(sensor);
    pm.makePersistent(room);
  }

  @ApiMethod(
    name = "rooms.deletesensor",
    httpMethod = "DELETE",
    path = "rooms/{room}/sensors/{sensor}")
  public void deleteSensorFromRoom(
      @Named("room") Long roomId,
      final @Named("sensor") Long sensorId) {
    PersistenceManager pm = getPersistenceManager();
    Room room = pm.getObjectById(
        Room.class,
        KeyFactory.createKey(Room.class.getSimpleName(), roomId));
    // there are better ways of doing this; this is done just to illustrate the
    // point of using two named parameters in the URL
    room.setSensors(new ArrayList<>(Collections2.filter(
        room.getSensors(), new Predicate<Sensor>() {
          @Override
          public boolean apply(@Nullable Sensor sensor) {
            return sensor.getKey().getId() == sensorId;
          }
        })));
    
    pm.makePersistent(room);
  }

  private static PersistenceManager getPersistenceManager() {
    return PMF.get().getPersistenceManager();
  }
}
