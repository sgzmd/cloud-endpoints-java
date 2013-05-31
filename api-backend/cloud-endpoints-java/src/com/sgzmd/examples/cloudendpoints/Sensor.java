package com.sgzmd.examples.cloudendpoints;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.google.appengine.api.datastore.Key;

/**
 * JDO containing sensor information
 * 
 * @author me@romankirillov.info [Roman "sgzmd" Kirillov"]
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Sensor {
  static final DateTimeFormatter UTC_DATETIME_FORMATTER = ISODateTimeFormat.basicDateTimeNoMillis().withZoneUTC();

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

  public String getNetworkId() {
    return networkId;
  }

  public SensorType getSensorType() {
    return sensorType;
  }

  public void setLastActive(Instant instant) {
    lastActive = instant.getMillis();
  }

  public Instant getLastActive() {
    if (lastActive != null) {
      return new Instant(lastActive);
    } else {
      return null;
    }
  }

  public String getLastActiveUtc() {
    if (lastActive != null) {
      return UTC_DATETIME_FORMATTER.print(lastActive);
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
