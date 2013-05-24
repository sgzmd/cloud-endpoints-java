package com.sgzmd.examples.cloudendpoints;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

/**
 * API class exporting collection of {@link Room} objects.
 * 
 * @author me@romankirillov.info [Roman "sgzmd" Kirillov"]
 */
@Api(
  name = "rooms",
  version = "v1"
)
public class Rooms {
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
  
  private static PersistenceManager getPersistenceManager() {
    return PMF.get().getPersistenceManager();
  }
}
