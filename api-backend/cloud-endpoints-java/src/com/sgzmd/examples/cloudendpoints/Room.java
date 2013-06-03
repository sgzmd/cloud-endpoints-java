package com.sgzmd.examples.cloudendpoints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Nullable;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.joda.time.Instant;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.labs.repackaged.com.google.common.base.Predicate;
import com.google.appengine.labs.repackaged.com.google.common.collect.Iterables;
import com.google.appengine.labs.repackaged.com.google.common.collect.Lists;

/**
 * JDO for Room.
 * 
 * @author me@romankirillov.info [Roman "sgzmd" Kirillov"]
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Room {
  private static final Instant NEVER = new Instant(0);

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;

  @Persistent
  private String name;

  @Persistent
  @Element(dependent = "true")
  private List<Sensor> sensors;

  public Room() {
  }

  public Room(String name, List<Sensor> sensors) {
    this.name = name;
    this.sensors = sensors;
  }

  public Room(String name) {
    this.name = name;
    this.sensors = new ArrayList<Sensor>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Key getKey() {
    return key;
  }

  public List<Sensor> getSensors() {
    return sensors;
  }

  public void setSensors(List<Sensor> sensors) {
    this.sensors = sensors;
  }

  public void addSensor(Sensor sensor) {
    this.sensors.add(sensor);
  }

  @Override
  public String toString() {
    return "Room [key=" + key + ", name=" + name + "]";
  }

  public Room updateFrom(Room room) {
    if (room.getName() != null) {
      this.name = room.name;
    }
    if (room.getSensors() != null) {
      this.sensors = room.sensors;
    }

    return this;
  }

  public Instant getLastActive() {
    if (sensors != null && sensors.size() > 0) {
      return Collections.max(sensors, new Comparator<Sensor>() {
        @Override
        public int compare(Sensor o1, Sensor o2) {
          return o1.getLastActive().compareTo(o2.getLastActive());
        }
      }).getLastActive();
    } else {
      return NEVER;
    }
  }

  /* default */ void deleteSensor(final Long sensorId) {
    // TODO(sgzmd): this is not how it is expected to work.
    this.sensors = Lists.newArrayList(Iterables.filter(sensors,
        new Predicate<Sensor>() {
          @Override
          public boolean apply(@Nullable Sensor sensor) {
            return sensor.getKey().getId() != sensorId;
          }
        }));
  }
}
