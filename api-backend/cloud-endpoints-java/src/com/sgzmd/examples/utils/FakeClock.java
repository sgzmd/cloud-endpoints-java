package com.sgzmd.examples.utils;

import org.joda.time.Instant;

public class FakeClock implements Clock {
  private Instant now;

  public FakeClock(Instant now) {
    this.now = now;
  }

  @Override
  public Instant now() {
    return now;
  }
  
  public void setNow(Instant now) {
    this.now = now;
  }
}
