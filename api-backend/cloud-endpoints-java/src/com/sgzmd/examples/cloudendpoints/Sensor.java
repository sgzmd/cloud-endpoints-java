package com.sgzmd.examples.cloudendpoints;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * JDO containing sensor information
 * 
 * @author me@romankirillov.info [Roman "sgzmd" Kirillov"]
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Sensor {
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;

  @Persistent
  private Boolean active;

  @Persistent
  private String name;

  public Sensor(String name, Boolean active) {
    this.name = name;
    this.active = active;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
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
}
