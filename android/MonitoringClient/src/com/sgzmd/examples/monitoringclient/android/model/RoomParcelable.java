package com.sgzmd.examples.monitoringclient.android.model;

import java.util.Arrays;

import com.google.api.services.monitoring.model.Room;

import android.os.Parcel;
import android.os.Parcelable;

public class RoomParcelable implements Parcelable {
  private String name;
  private SensorParcelable[] sensors;

  public static RoomParcelable fromRoom(Room room) {
    RoomParcelable parcelable = new RoomParcelable();

    parcelable.name = room.getName();
    if (room.getSensors() != null) {
      parcelable.sensors = SensorParcelable.CREATOR.newArray(room.getSensors()
          .size());
      for (int i = 0; i < room.getSensors().size(); ++i) {
        parcelable.sensors[i] = SensorParcelable.fromSensor(room.getSensors()
            .get(i));
      }
    }

    return parcelable;
  }

  public static final Parcelable.Creator<RoomParcelable> CREATOR = new Parcelable.Creator<RoomParcelable>() {
    public RoomParcelable createFromParcel(Parcel in) {
      return new RoomParcelable(in);
    }

    public RoomParcelable[] newArray(int size) {
      return new RoomParcelable[size];
    }
  };

  private RoomParcelable() {
  }

  public RoomParcelable(Parcel in) {
    name = in.readString();
    sensors = (SensorParcelable[]) in.readParcelableArray(getClass()
        .getClassLoader());
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeParcelableArray(sensors, flags);
  }

  public String getName() {
    return name;
  }

  public SensorParcelable[] getSensors() {
    if (sensors != null) {
      return sensors;
    } else {
      return new SensorParcelable[0];
    }
  }
  
  @Override
  public String toString() {
    return "RoomParcelable [name=" + name + ", sensors="
        + Arrays.toString(sensors) + "]";
  }
}
