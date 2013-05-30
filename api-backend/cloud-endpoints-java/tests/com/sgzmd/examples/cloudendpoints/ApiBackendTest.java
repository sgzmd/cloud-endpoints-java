package com.sgzmd.examples.cloudendpoints;

import static org.junit.Assert.*;

import java.util.List;

import javax.jdo.PersistenceManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.labs.repackaged.com.google.common.collect.Iterables;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

@SuppressWarnings("unchecked")
public class ApiBackendTest {
  private static final String SENSOR_NETWORK_ID = "Sensor Network Id";
  private static final String NEW_ROOM = "New Room";
  private static final String TEST_ROOM = "Test Room";
  
  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
  private ApiBackend api;
  private PersistenceManager pm;

  @Before
  public void setUp() throws Exception {
    helper.setUp();
    this.api = new ApiBackend();
    this.pm = PMF.get().getPersistenceManager();
  }
  
  @After
  public void tearDown() {
    helper.tearDown();
  }

  @Test
  public void testAddRoom() {
    createTestRoom();
    List<Room> rooms = (List <Room>)pm.newQuery(pm.newQuery(Room.class)).execute();
    Room foundRoom = Iterables.getOnlyElement(rooms);
    assertEquals(TEST_ROOM, foundRoom.getName());
  }

  @Test
  public void testDeleteRoom() {
    Room room = createTestRoom();
    api.deleteRoom(room.getKey().getId());
    List<Room> rooms = (List <Room>)pm.newQuery(pm.newQuery(Room.class)).execute();
    assertTrue(rooms.isEmpty());
  }
  
  @Test
  public void testListRooms() {
    createTestRoom();
    createTestRoom();
    createTestRoom();
    
    List<Room> rooms = (List <Room>)pm.newQuery(pm.newQuery(Room.class)).execute();
    assertEquals(3, rooms.size());
  }
  
  @Test
  public void testUpdateRoom() {
    Room old = createTestRoom();
    Room updated = new Room(NEW_ROOM);
    
    api.updateRoom(old.getKey().getId(), updated);
    
    List<Room> rooms = (List <Room>)pm.newQuery(pm.newQuery(Room.class)).execute();
    Room foundRoom = Iterables.getOnlyElement(rooms);
    assertEquals(NEW_ROOM, foundRoom.getName());
  }
  
  @Test
  public void testAddSensorToRoom() {
    Room room = createTestRoom();
    Sensor sensor = new Sensor(SENSOR_NETWORK_ID, true);
    api.addSensorToRoom(room.getKey().getId(), sensor);
    
    List<Room> rooms = (List <Room>)pm.newQuery(pm.newQuery(Room.class)).execute();
    Room foundRoom = Iterables.getOnlyElement(rooms);
    Sensor foundSensor = Iterables.getOnlyElement(foundRoom.getSensors());
    assertEquals(SENSOR_NETWORK_ID, foundSensor.getNetworkId());    
  }
  
  private Room createTestRoom() {
    Room room = new Room(TEST_ROOM);
    return api.addRoom(room);
  }
}
