package com.sgzmd.examples.cloudendpoints.android.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.api.client.util.DateTime;
import com.google.api.services.monitoring.model.Sensor;

/**
 * {@link Parcelable} representation of the {@link Sensor}, suitable for passing
 * in Bundles.
 *
 * @author kirillov
 */
public class SensorParcelable implements Parcelable {
  private String networkId;
  private String name;
  private Boolean active;
  private Long lastActiveMillis;
  private Long roomId;
  private Long id;

  public Long getRoomId() {
    return roomId;
  }

  public void setRoomId(Long roomId) {
    this.roomId = roomId;
  }

  public static SensorParcelable fromSensor(Sensor sensor) {
    SensorParcelable parcelable = new SensorParcelable();

    parcelable.id = sensor.getId();
    parcelable.active = sensor.getActive();
    parcelable.lastActiveMillis = sensor.getLastActive();
    parcelable.name = sensor.getSensorName();
    parcelable.networkId = sensor.getNetworkId();

    return parcelable;
  }


  @Override
  public String toString() {
    return "SensorParcelable [networkId=" + networkId + ", name=" + name + ", active=" + active
        + ", lastActiveMillis=" + lastActiveMillis + ", roomId=" + roomId + ", id=" + id + "]";
  }

  public static final Parcelable.Creator<SensorParcelable> CREATOR = new Parcelable.Creator<SensorParcelable>() {
    @Override
    public SensorParcelable createFromParcel(Parcel in) {
      return new SensorParcelable(in);
    }

    @Override
    public SensorParcelable[] newArray(int size) {
      return new SensorParcelable[size];
    }
  };

  public SensorParcelable(Parcel in) {
    this.networkId = in.readString();
    this.name = in.readString();
    this.active = Boolean.parseBoolean(in.readString());
    this.lastActiveMillis = in.readLong();
  }

  private SensorParcelable() {}

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(networkId);
    dest.writeString(name);
    dest.writeString(Boolean.toString(active));
    dest.writeLong(lastActiveMillis);
  }

  public String getNetworkId() {
    return networkId;
  }

  public String getName() {
    return name;
  }

  public Boolean getActive() {
    return active;
  }

  public Long getLastActiveMillis() {
    return lastActiveMillis;
  }

  public String getLastActive() {
    if (lastActiveMillis == null || lastActiveMillis.longValue() == 0L) {
      return "Never active";
    } else {
      return new DateTime(lastActiveMillis).toStringRfc3339();
    }
  }

  public Long getId() {
    return id;
  }
}