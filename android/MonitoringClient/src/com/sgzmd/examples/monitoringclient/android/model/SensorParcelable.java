package com.sgzmd.examples.monitoringclient.android.model;

import com.google.api.services.monitoring.model.Sensor;

import android.os.Parcel;
import android.os.Parcelable;

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

  public static SensorParcelable fromSensor(Sensor sensor) {
    SensorParcelable parcelable = new SensorParcelable();
    
    parcelable.active = sensor.getActive();
    parcelable.lastActiveMillis = sensor.getLastActive().getMillis();
    parcelable.name = sensor.getNetworkId();
    parcelable.networkId = sensor.getNetworkId();
    
    return parcelable;
  }
  
  public static final Parcelable.Creator<SensorParcelable> CREATOR = new Parcelable.Creator<SensorParcelable>() {
    public SensorParcelable createFromParcel(Parcel in) {
      return new SensorParcelable(in);
    }

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
}
