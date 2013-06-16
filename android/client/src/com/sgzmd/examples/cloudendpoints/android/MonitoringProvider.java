package com.sgzmd.examples.cloudendpoints.android;

import java.util.List;

import javax.annotation.Nullable;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Lists;
import com.google.api.services.monitoring.Monitoring;
import com.google.api.services.monitoring.model.Room;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.sgzmd.examples.cloudendpoints.android.model.RoomParcelable;

public class MonitoringProvider {
  private static final Monitoring MONITORING = new Monitoring(
      AndroidHttp.newCompatibleTransport(),
      new GsonFactory(),
      null);

  public static Monitoring get() {
    return MONITORING;
  }

  public static List<RoomParcelable> getRoomParcelables(@Nullable List<Room> rooms) {
    return Lists.newArrayList(Iterables.transform(rooms, new Function<Room, RoomParcelable>() {
      @Override
      @Nullable
      public RoomParcelable apply(@Nullable Room arg0) {
        return RoomParcelable.fromRoom(arg0);
      }
    }));
  }
}
