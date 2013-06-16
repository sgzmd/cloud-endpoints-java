package com.sgzmd.examples.cloudendpoints.android.model;

import java.util.Arrays;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.api.services.monitoring.model.Room;

public class RoomParcelable implements Parcelable {
  private Long lastActive;
  private String name;
  private SensorParcelable[] sensors;
  private long id;

  public static RoomParcelable fromRoom(Room room) {
    RoomParcelable parcelable = new RoomParcelable();

    parcelable.lastActive = room.getLastActive();
    parcelable.id = room.getId();

    parcelable.name = room.getName();
    if (room.getSensors() != null) {
      parcelable.sensors = SensorParcelable.CREATOR.newArray(room.getSensors()
          .size());
      for (int i = 0; i < room.getSensors().size(); ++i) {
        parcelable.sensors[i] = SensorParcelable.fromSensor(room.getSensors()
            .get(i));
        parcelable.sensors[i].setRoomId(room.getId());
      }
    }

    return parcelable;
  }

  public static final Parcelable.Creator<RoomParcelable> CREATOR = new Parcelable.Creator<RoomParcelable>() {
    @Override
    public RoomParcelable createFromParcel(Parcel in) {
      return new RoomParcelable(in);
    }

    @Override
    public RoomParcelable[] newArray(int size) {
      return new RoomParcelable[size];
    }
  };

  private RoomParcelable() {
  }

  public RoomParcelable(Parcel in) {
    name = in.readString();
    lastActive = in.readLong();
    id = in.readLong();
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
    dest.writeLong(lastActive);
    dest.writeLong(id);
    dest.writeParcelableArray(sensors, flags);
  }

  public String getName() {
    return name;
  }

  public Long getLastActiveMs() {
    return lastActive;
  }

  public long getId() {
    return id;
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