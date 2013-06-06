package com.sgzmd.examples.cloudendpoints;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

import org.joda.time.format.ISODateTimeFormat;

import com.google.appengine.api.datastore.Key;

/**
 * JDO containing sensor information
 * 
 * @author me@romankirillov.info [Roman "sgzmd" Kirillov"]
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Sensor {
  public enum SensorType {
    MOTION,
    TEMPERATURE
  }

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;

  @Persistent @Unique private String networkId;

  @Persistent private Boolean active;

  @Persistent private Long lastActive = 0L;

  @Persistent private SensorType sensorType;
  
  @Persistent private String sensorName;
  
  public Sensor() {}

  public Sensor(String networkId, Boolean active, SensorType type) {
    this.networkId = networkId;
    this.active = active;
    this.lastActive = 0L;
    this.sensorType = type;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Key getKey() {
    return key;
  }
  
  public long getId() {
    if (key != null) {
      return key.getId();
    } else {
      return -1;
    }
  }

  public String getNetworkId() {
    return networkId;
  }

  public SensorType getSensorType() {
    return sensorType;
  }

  public void setLastActive(long instant) {
    lastActive = instant;
  }

  public Long getLastActive() {
    if (lastActive != null) {
      return lastActive;
    } else {
      return null;
    }
  }
  
  public String getSensorName() {
    return sensorName;
  }

  public void setSensorName(String sensorName) {
    this.sensorName = sensorName;
  }

  public String getLastActiveUtc() {
    if (lastActive != null) {
      return ISODateTimeFormat.basicDateTimeNoMillis().withZoneUTC().print(lastActive);
    } else {
      return "";
    }
  }

  @Override
  public String toString() {
    return "Sensor [key=" + key + ", networkId=" + networkId + ", active=" + active
        + ", lastActive=" + lastActive + ", sensorType=" + sensorType + "]";
  }
}
