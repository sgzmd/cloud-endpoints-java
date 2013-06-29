package com.sgzmd.examples.cloudendpoints.android;

import java.util.List;

import javax.annotation.Nullable;

import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Lists;
import com.google.api.services.monitoring.Monitoring;
import com.google.api.services.monitoring.model.Room;
import com.google.common.base.Function;
import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import com.sgzmd.examples.cloudendpoints.android.model.RoomParcelable;

public class MonitoringProvider {
  private static Monitoring MONITORING = null;

  static final String TAG = "MonitoringProvider";

  public static void initialise(GoogleAccountCredential credential) {
    Log.i(TAG, "initialise");
    if (null == MONITORING) {
      MONITORING = new Monitoring.Builder(AndroidHttp.newCompatibleTransport(),
          new GsonFactory(), credential).setApplicationName(
          "MonitoringAndroidClient").build();
    } else {
      throw new IllegalStateException("Monitoring was already initialised");
    }
  }

  public static Monitoring get() {
    try {
      Log.i(TAG, "Monitoring.get()");
      
      // blocking for service object to be initialised
      while (MONITORING == null) {
        Thread.sleep(1000);
      }

      return MONITORING;
    } catch (InterruptedException e) {
      throw Throwables.propagate(e);
    }
  }

  public static List<RoomParcelable> getRoomParcelables(
      @Nullable List<Room> rooms) {
    return Lists.newArrayList(Iterables.transform(rooms,
        new Function<Room, RoomParcelable>() {
          @Override
          @Nullable
          public RoomParcelable apply(@Nullable Room arg0) {
            return RoomParcelable.fromRoom(arg0);
          }
        }));
  }
}
