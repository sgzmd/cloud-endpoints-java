package com.sgzmd.examples.cloudendpoints;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * API entry point.
 * 
 * @author me@romankirillov.info [Roman "sgzmd" Kirillov"]
 */
@Api(name = "monitoring", version = "v1")
public class ApiBackend {
  private static final Logger logger = Logger.getLogger(ApiBackend.class.getSimpleName());

  /**
   * Adds a {@link Room} to collection.
   * 
   * @param room New {@link Room} to be added. Must not exist.
   * 
   * @return an instance of {@link Room} added, with Key assigned.
   */
  @ApiMethod(name = "addRoom", httpMethod = "POST", path = "rooms")
  public Room addRoom(Room room) {
    log("addRoom({0})", room);
    Room created = getPM().makePersistent(room);
    log("created: {0}", created);
    return created;
  }

  /**
   * Updates a room with specified ID.
   * 
   * @param roomId {@link Room} ID to update
   * @param updatedRoom Updated {@link Room} contents.
   * 
   * @return updated {@link Room}.
   */
  @ApiMethod(name = "updateRoom", httpMethod = "PUT", path = "rooms/{room}")
  public Room updateRoom(@Named("room") long roomId, Room updatedRoom) {
    log("updateRoom({0}, {1})", roomId, updatedRoom);
    PersistenceManager pm = getPM();
    try {
      Room room = (Room) pm.getObjectById(
          Room.class,
          KeyFactory.createKey(Room.class.getSimpleName(), roomId));
      room.updateFrom(updatedRoom);
      return room;
    } finally {
      pm.close();
    }
  }

  /**
   * Deletes {@link Room} from collection.
   * 
   * @param roomId {@link Room} ID to be deleted.
   */
  @ApiMethod(name = "deleteRoom", httpMethod = "DELETE", path = "rooms/{room}")
  public void deleteRoom(@Named("room") long roomId) {
    log("deleteRoom({0})", roomId);
    PersistenceManager pm = getPM();
    Room room = (Room) pm.getObjectById(
        Room.class,
        KeyFactory.createKey(Room.class.getSimpleName(), roomId));
    pm.deletePersistent(room);
  }

  /**
   * Lists all {@link Room} in collection, along with their linked {@link Sensor}.
   * @return {@link List} of {@link Room}.
   */
  @SuppressWarnings("unchecked")
  @ApiMethod(name = "listRooms", httpMethod = "GET", path = "rooms")
  public List<Room> list() {
    log("list()");
    PersistenceManager pm = getPM();
    Query query = pm.newQuery(Room.class);
    return (List<Room>) pm.newQuery(query).execute();
  }

  /**
   * Adds a new {@link Sensor} to an existing {@link Room}.
   * 
   * @param roomId An identifier of a {@link Room} sensor should be added to.
   * @param sensor New {@link Sensor} to be added.
   * 
   * @return Updated {@link Room} with added {@link Sensor}.
   */
  @ApiMethod(name = "addSensor", httpMethod = "POST", path = "rooms/{room}")
  public Room addSensorToRoom(@Named("room") long roomId, Sensor sensor) {
    PersistenceManager pm = getPM(); 
    Room room;
    try {
      room = (Room) pm.getObjectById(
          Room.class,
          KeyFactory.createKey(Room.class.getSimpleName(), roomId));
      room.addSensor(sensor);
    } finally {
      pm.close();
    }
    
    return room;
  }

  private void log(String format, Object... objects) {
    logger.log(Level.INFO, format, objects);
  }

  private static PersistenceManager getPM() {
    return PMF.get().getPersistenceManager();
  }
}
