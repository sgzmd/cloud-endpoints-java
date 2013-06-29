/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2013-06-26 16:27:34 UTC)
 * on 2013-06-28 at 13:15:53 UTC 
 * Modify at your own risk.
 */

package com.google.api.services.monitoring.model;

/**
 * Model definition for Sensor.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the . For a detailed explanation see:
 * <a href="http://code.google.com/p/google-api-java-client/wiki/Json">http://code.google.com/p/google-api-java-client/wiki/Json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Sensor extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean active;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private Key key;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long lastActive;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String lastActiveUtc;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String networkId;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String sensorName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String sensorType;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getActive() {
    return active;
  }

  /**
   * @param active active or {@code null} for none
   */
  public Sensor setActive(java.lang.Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public Sensor setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public Key getKey() {
    return key;
  }

  /**
   * @param key key or {@code null} for none
   */
  public Sensor setKey(Key key) {
    this.key = key;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getLastActive() {
    return lastActive;
  }

  /**
   * @param lastActive lastActive or {@code null} for none
   */
  public Sensor setLastActive(java.lang.Long lastActive) {
    this.lastActive = lastActive;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLastActiveUtc() {
    return lastActiveUtc;
  }

  /**
   * @param lastActiveUtc lastActiveUtc or {@code null} for none
   */
  public Sensor setLastActiveUtc(java.lang.String lastActiveUtc) {
    this.lastActiveUtc = lastActiveUtc;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNetworkId() {
    return networkId;
  }

  /**
   * @param networkId networkId or {@code null} for none
   */
  public Sensor setNetworkId(java.lang.String networkId) {
    this.networkId = networkId;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSensorName() {
    return sensorName;
  }

  /**
   * @param sensorName sensorName or {@code null} for none
   */
  public Sensor setSensorName(java.lang.String sensorName) {
    this.sensorName = sensorName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSensorType() {
    return sensorType;
  }

  /**
   * @param sensorType sensorType or {@code null} for none
   */
  public Sensor setSensorType(java.lang.String sensorType) {
    this.sensorType = sensorType;
    return this;
  }

  @Override
  public Sensor set(String fieldName, Object value) {
    return (Sensor) super.set(fieldName, value);
  }

  @Override
  public Sensor clone() {
    return (Sensor) super.clone();
  }

}
