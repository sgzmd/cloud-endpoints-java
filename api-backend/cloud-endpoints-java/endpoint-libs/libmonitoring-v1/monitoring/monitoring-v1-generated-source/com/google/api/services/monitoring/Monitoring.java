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
 * (build: 2013-06-05 16:09:48 UTC)
 * on 2013-06-15 at 14:46:23 UTC
 * Modify at your own risk.
 */

package com.google.api.services.monitoring;

/**
 * Service definition for Monitoring (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link MonitoringRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Monitoring extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.15.0-rc of the  library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://cloud-endpoints-example.appspot.com/_ah/api/";
//  public static final String DEFAULT_ROOT_URL = "https://localhost:/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "monitoring/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Monitoring(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Monitoring(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "addRoom".
   *
   * This request holds the parameters needed by the the monitoring server.  After setting any
   * optional parameters, call the {@link AddRoom#execute()} method to invoke the remote operation.
   *
   * @param content the {@link com.google.api.services.monitoring.model.Room}
   * @return the request
   */
  public AddRoom addRoom(com.google.api.services.monitoring.model.Room content) throws java.io.IOException {
    AddRoom result = new AddRoom(content);
    initialize(result);
    return result;
  }

  public class AddRoom extends MonitoringRequest<com.google.api.services.monitoring.model.Room> {

    private static final String REST_PATH = "rooms";

    /**
     * Create a request for the method "addRoom".
     *
     * This request holds the parameters needed by the the monitoring server.  After setting any
     * optional parameters, call the {@link AddRoom#execute()} method to invoke the remote operation.
     * <p> {@link
     * AddRoom#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
     * be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.google.api.services.monitoring.model.Room}
     * @since 1.13
     */
    protected AddRoom(com.google.api.services.monitoring.model.Room content) {
      super(Monitoring.this, "POST", REST_PATH, content, com.google.api.services.monitoring.model.Room.class);
    }

    @Override
    public AddRoom setAlt(java.lang.String alt) {
      return (AddRoom) super.setAlt(alt);
    }

    @Override
    public AddRoom setFields(java.lang.String fields) {
      return (AddRoom) super.setFields(fields);
    }

    @Override
    public AddRoom setKey(java.lang.String key) {
      return (AddRoom) super.setKey(key);
    }

    @Override
    public AddRoom setOauthToken(java.lang.String oauthToken) {
      return (AddRoom) super.setOauthToken(oauthToken);
    }

    @Override
    public AddRoom setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (AddRoom) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public AddRoom setQuotaUser(java.lang.String quotaUser) {
      return (AddRoom) super.setQuotaUser(quotaUser);
    }

    @Override
    public AddRoom setUserIp(java.lang.String userIp) {
      return (AddRoom) super.setUserIp(userIp);
    }

    @Override
    public AddRoom set(String parameterName, Object value) {
      return (AddRoom) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "addSensor".
   *
   * This request holds the parameters needed by the the monitoring server.  After setting any
   * optional parameters, call the {@link AddSensor#execute()} method to invoke the remote operation.
   *
   * @param room
   * @param content the {@link com.google.api.services.monitoring.model.Sensor}
   * @return the request
   */
  public AddSensor addSensor(java.lang.Long room, com.google.api.services.monitoring.model.Sensor content) throws java.io.IOException {
    AddSensor result = new AddSensor(room, content);
    initialize(result);
    return result;
  }

  public class AddSensor extends MonitoringRequest<com.google.api.services.monitoring.model.Room> {

    private static final String REST_PATH = "rooms/{room}";

    /**
     * Create a request for the method "addSensor".
     *
     * This request holds the parameters needed by the the monitoring server.  After setting any
     * optional parameters, call the {@link AddSensor#execute()} method to invoke the remote
     * operation. <p> {@link
     * AddSensor#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param room
     * @param content the {@link com.google.api.services.monitoring.model.Sensor}
     * @since 1.13
     */
    protected AddSensor(java.lang.Long room, com.google.api.services.monitoring.model.Sensor content) {
      super(Monitoring.this, "POST", REST_PATH, content, com.google.api.services.monitoring.model.Room.class);
      this.room = com.google.api.client.util.Preconditions.checkNotNull(room, "Required parameter room must be specified.");
    }

    @Override
    public AddSensor setAlt(java.lang.String alt) {
      return (AddSensor) super.setAlt(alt);
    }

    @Override
    public AddSensor setFields(java.lang.String fields) {
      return (AddSensor) super.setFields(fields);
    }

    @Override
    public AddSensor setKey(java.lang.String key) {
      return (AddSensor) super.setKey(key);
    }

    @Override
    public AddSensor setOauthToken(java.lang.String oauthToken) {
      return (AddSensor) super.setOauthToken(oauthToken);
    }

    @Override
    public AddSensor setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (AddSensor) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public AddSensor setQuotaUser(java.lang.String quotaUser) {
      return (AddSensor) super.setQuotaUser(quotaUser);
    }

    @Override
    public AddSensor setUserIp(java.lang.String userIp) {
      return (AddSensor) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long room;

    /**

     */
    public java.lang.Long getRoom() {
      return room;
    }

    public AddSensor setRoom(java.lang.Long room) {
      this.room = room;
      return this;
    }

    @Override
    public AddSensor set(String parameterName, Object value) {
      return (AddSensor) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "arm".
   *
   * This request holds the parameters needed by the the monitoring server.  After setting any
   * optional parameters, call the {@link Arm#execute()} method to invoke the remote operation.
   *
   * @param state
   * @return the request
   */
  public Arm arm(java.lang.Boolean state) throws java.io.IOException {
    Arm result = new Arm(state);
    initialize(result);
    return result;
  }

  public class Arm extends MonitoringRequest<com.google.api.services.monitoring.model.RoomCollection> {

    private static final String REST_PATH = "reset";

    /**
     * Create a request for the method "arm".
     *
     * This request holds the parameters needed by the the monitoring server.  After setting any
     * optional parameters, call the {@link Arm#execute()} method to invoke the remote operation. <p>
     * {@link Arm#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param state
     * @since 1.13
     */
    protected Arm(java.lang.Boolean state) {
      super(Monitoring.this, "GET", REST_PATH, null, com.google.api.services.monitoring.model.RoomCollection.class);
      this.state = com.google.api.client.util.Preconditions.checkNotNull(state, "Required parameter state must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public Arm setAlt(java.lang.String alt) {
      return (Arm) super.setAlt(alt);
    }

    @Override
    public Arm setFields(java.lang.String fields) {
      return (Arm) super.setFields(fields);
    }

    @Override
    public Arm setKey(java.lang.String key) {
      return (Arm) super.setKey(key);
    }

    @Override
    public Arm setOauthToken(java.lang.String oauthToken) {
      return (Arm) super.setOauthToken(oauthToken);
    }

    @Override
    public Arm setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (Arm) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public Arm setQuotaUser(java.lang.String quotaUser) {
      return (Arm) super.setQuotaUser(quotaUser);
    }

    @Override
    public Arm setUserIp(java.lang.String userIp) {
      return (Arm) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Boolean state;

    /**

     */
    public java.lang.Boolean getState() {
      return state;
    }

    public Arm setState(java.lang.Boolean state) {
      this.state = state;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Long sensor;

    /**

     */
    public java.lang.Long getSensor() {
      return sensor;
    }

    public Arm setSensor(java.lang.Long sensor) {
      this.sensor = sensor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Long room;

    /**

     */
    public java.lang.Long getRoom() {
      return room;
    }

    public Arm setRoom(java.lang.Long room) {
      this.room = room;
      return this;
    }

    @Override
    public Arm set(String parameterName, Object value) {
      return (Arm) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "deleteRoom".
   *
   * This request holds the parameters needed by the the monitoring server.  After setting any
   * optional parameters, call the {@link DeleteRoom#execute()} method to invoke the remote operation.
   *
   * @param room
   * @return the request
   */
  public DeleteRoom deleteRoom(java.lang.Long room) throws java.io.IOException {
    DeleteRoom result = new DeleteRoom(room);
    initialize(result);
    return result;
  }

  public class DeleteRoom extends MonitoringRequest<Void> {

    private static final String REST_PATH = "rooms/{room}";

    /**
     * Create a request for the method "deleteRoom".
     *
     * This request holds the parameters needed by the the monitoring server.  After setting any
     * optional parameters, call the {@link DeleteRoom#execute()} method to invoke the remote
     * operation. <p> {@link
     * DeleteRoom#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param room
     * @since 1.13
     */
    protected DeleteRoom(java.lang.Long room) {
      super(Monitoring.this, "DELETE", REST_PATH, null, Void.class);
      this.room = com.google.api.client.util.Preconditions.checkNotNull(room, "Required parameter room must be specified.");
    }

    @Override
    public DeleteRoom setAlt(java.lang.String alt) {
      return (DeleteRoom) super.setAlt(alt);
    }

    @Override
    public DeleteRoom setFields(java.lang.String fields) {
      return (DeleteRoom) super.setFields(fields);
    }

    @Override
    public DeleteRoom setKey(java.lang.String key) {
      return (DeleteRoom) super.setKey(key);
    }

    @Override
    public DeleteRoom setOauthToken(java.lang.String oauthToken) {
      return (DeleteRoom) super.setOauthToken(oauthToken);
    }

    @Override
    public DeleteRoom setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (DeleteRoom) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public DeleteRoom setQuotaUser(java.lang.String quotaUser) {
      return (DeleteRoom) super.setQuotaUser(quotaUser);
    }

    @Override
    public DeleteRoom setUserIp(java.lang.String userIp) {
      return (DeleteRoom) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long room;

    /**

     */
    public java.lang.Long getRoom() {
      return room;
    }

    public DeleteRoom setRoom(java.lang.Long room) {
      this.room = room;
      return this;
    }

    @Override
    public DeleteRoom set(String parameterName, Object value) {
      return (DeleteRoom) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "deleteSensor".
   *
   * This request holds the parameters needed by the the monitoring server.  After setting any
   * optional parameters, call the {@link DeleteSensor#execute()} method to invoke the remote
   * operation.
   *
   * @param sensor
   * @param room
   * @return the request
   */
  public DeleteSensor deleteSensor(java.lang.Long sensor, java.lang.Long room) throws java.io.IOException {
    DeleteSensor result = new DeleteSensor(sensor, room);
    initialize(result);
    return result;
  }

  public class DeleteSensor extends MonitoringRequest<com.google.api.services.monitoring.model.RoomCollection> {

    private static final String REST_PATH = "sensors/{sensor}";

    /**
     * Create a request for the method "deleteSensor".
     *
     * This request holds the parameters needed by the the monitoring server.  After setting any
     * optional parameters, call the {@link DeleteSensor#execute()} method to invoke the remote
     * operation. <p> {@link
     * DeleteSensor#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param sensor
     * @param room
     * @since 1.13
     */
    protected DeleteSensor(java.lang.Long sensor, java.lang.Long room) {
      super(Monitoring.this, "DELETE", REST_PATH, null, com.google.api.services.monitoring.model.RoomCollection.class);
      this.sensor = com.google.api.client.util.Preconditions.checkNotNull(sensor, "Required parameter sensor must be specified.");
      this.room = com.google.api.client.util.Preconditions.checkNotNull(room, "Required parameter room must be specified.");
    }

    @Override
    public DeleteSensor setAlt(java.lang.String alt) {
      return (DeleteSensor) super.setAlt(alt);
    }

    @Override
    public DeleteSensor setFields(java.lang.String fields) {
      return (DeleteSensor) super.setFields(fields);
    }

    @Override
    public DeleteSensor setKey(java.lang.String key) {
      return (DeleteSensor) super.setKey(key);
    }

    @Override
    public DeleteSensor setOauthToken(java.lang.String oauthToken) {
      return (DeleteSensor) super.setOauthToken(oauthToken);
    }

    @Override
    public DeleteSensor setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (DeleteSensor) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public DeleteSensor setQuotaUser(java.lang.String quotaUser) {
      return (DeleteSensor) super.setQuotaUser(quotaUser);
    }

    @Override
    public DeleteSensor setUserIp(java.lang.String userIp) {
      return (DeleteSensor) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long sensor;

    /**

     */
    public java.lang.Long getSensor() {
      return sensor;
    }

    public DeleteSensor setSensor(java.lang.Long sensor) {
      this.sensor = sensor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Long room;

    /**

     */
    public java.lang.Long getRoom() {
      return room;
    }

    public DeleteSensor setRoom(java.lang.Long room) {
      this.room = room;
      return this;
    }

    @Override
    public DeleteSensor set(String parameterName, Object value) {
      return (DeleteSensor) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "disarm".
   *
   * This request holds the parameters needed by the the monitoring server.  After setting any
   * optional parameters, call the {@link Disarm#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public Disarm disarm() throws java.io.IOException {
    Disarm result = new Disarm();
    initialize(result);
    return result;
  }

  public class Disarm extends MonitoringRequest<Void> {

    private static final String REST_PATH = "disarm";

    /**
     * Create a request for the method "disarm".
     *
     * This request holds the parameters needed by the the monitoring server.  After setting any
     * optional parameters, call the {@link Disarm#execute()} method to invoke the remote operation.
     * <p> {@link
     * Disarm#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
     * be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected Disarm() {
      super(Monitoring.this, "GET", REST_PATH, null, Void.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public Disarm setAlt(java.lang.String alt) {
      return (Disarm) super.setAlt(alt);
    }

    @Override
    public Disarm setFields(java.lang.String fields) {
      return (Disarm) super.setFields(fields);
    }

    @Override
    public Disarm setKey(java.lang.String key) {
      return (Disarm) super.setKey(key);
    }

    @Override
    public Disarm setOauthToken(java.lang.String oauthToken) {
      return (Disarm) super.setOauthToken(oauthToken);
    }

    @Override
    public Disarm setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (Disarm) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public Disarm setQuotaUser(java.lang.String quotaUser) {
      return (Disarm) super.setQuotaUser(quotaUser);
    }

    @Override
    public Disarm setUserIp(java.lang.String userIp) {
      return (Disarm) super.setUserIp(userIp);
    }

    @Override
    public Disarm set(String parameterName, Object value) {
      return (Disarm) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getRoom".
   *
   * This request holds the parameters needed by the the monitoring server.  After setting any
   * optional parameters, call the {@link GetRoom#execute()} method to invoke the remote operation.
   *
   * @param room
   * @return the request
   */
  public GetRoom getRoom(java.lang.Long room) throws java.io.IOException {
    GetRoom result = new GetRoom(room);
    initialize(result);
    return result;
  }

  public class GetRoom extends MonitoringRequest<com.google.api.services.monitoring.model.Room> {

    private static final String REST_PATH = "rooms/{room}";

    /**
     * Create a request for the method "getRoom".
     *
     * This request holds the parameters needed by the the monitoring server.  After setting any
     * optional parameters, call the {@link GetRoom#execute()} method to invoke the remote operation.
     * <p> {@link
     * GetRoom#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
     * be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param room
     * @since 1.13
     */
    protected GetRoom(java.lang.Long room) {
      super(Monitoring.this, "GET", REST_PATH, null, com.google.api.services.monitoring.model.Room.class);
      this.room = com.google.api.client.util.Preconditions.checkNotNull(room, "Required parameter room must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetRoom setAlt(java.lang.String alt) {
      return (GetRoom) super.setAlt(alt);
    }

    @Override
    public GetRoom setFields(java.lang.String fields) {
      return (GetRoom) super.setFields(fields);
    }

    @Override
    public GetRoom setKey(java.lang.String key) {
      return (GetRoom) super.setKey(key);
    }

    @Override
    public GetRoom setOauthToken(java.lang.String oauthToken) {
      return (GetRoom) super.setOauthToken(oauthToken);
    }

    @Override
    public GetRoom setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetRoom) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetRoom setQuotaUser(java.lang.String quotaUser) {
      return (GetRoom) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetRoom setUserIp(java.lang.String userIp) {
      return (GetRoom) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long room;

    /**

     */
    public java.lang.Long getRoom() {
      return room;
    }

    public GetRoom setRoom(java.lang.Long room) {
      this.room = room;
      return this;
    }

    @Override
    public GetRoom set(String parameterName, Object value) {
      return (GetRoom) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listRooms".
   *
   * This request holds the parameters needed by the the monitoring server.  After setting any
   * optional parameters, call the {@link ListRooms#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListRooms listRooms() throws java.io.IOException {
    ListRooms result = new ListRooms();
    initialize(result);
    return result;
  }

  public class ListRooms extends MonitoringRequest<com.google.api.services.monitoring.model.RoomCollection> {

    private static final String REST_PATH = "rooms";

    /**
     * Create a request for the method "listRooms".
     *
     * This request holds the parameters needed by the the monitoring server.  After setting any
     * optional parameters, call the {@link ListRooms#execute()} method to invoke the remote
     * operation. <p> {@link
     * ListRooms#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListRooms() {
      super(Monitoring.this, "GET", REST_PATH, null, com.google.api.services.monitoring.model.RoomCollection.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListRooms setAlt(java.lang.String alt) {
      return (ListRooms) super.setAlt(alt);
    }

    @Override
    public ListRooms setFields(java.lang.String fields) {
      return (ListRooms) super.setFields(fields);
    }

    @Override
    public ListRooms setKey(java.lang.String key) {
      return (ListRooms) super.setKey(key);
    }

    @Override
    public ListRooms setOauthToken(java.lang.String oauthToken) {
      return (ListRooms) super.setOauthToken(oauthToken);
    }

    @Override
    public ListRooms setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListRooms) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListRooms setQuotaUser(java.lang.String quotaUser) {
      return (ListRooms) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListRooms setUserIp(java.lang.String userIp) {
      return (ListRooms) super.setUserIp(userIp);
    }

    @Override
    public ListRooms set(String parameterName, Object value) {
      return (ListRooms) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "logSensorUpdate".
   *
   * This request holds the parameters needed by the the monitoring server.  After setting any
   * optional parameters, call the {@link LogSensorUpdate#execute()} method to invoke the remote
   * operation.
   *
   * @param networkId
   * @return the request
   */
  public LogSensorUpdate logSensorUpdate(java.lang.String networkId) throws java.io.IOException {
    LogSensorUpdate result = new LogSensorUpdate(networkId);
    initialize(result);
    return result;
  }

  public class LogSensorUpdate extends MonitoringRequest<Void> {

    private static final String REST_PATH = "sensors/{network_id}";

    /**
     * Create a request for the method "logSensorUpdate".
     *
     * This request holds the parameters needed by the the monitoring server.  After setting any
     * optional parameters, call the {@link LogSensorUpdate#execute()} method to invoke the remote
     * operation. <p> {@link LogSensorUpdate#initialize(com.google.api.client.googleapis.services.Abst
     * ractGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param networkId
     * @since 1.13
     */
    protected LogSensorUpdate(java.lang.String networkId) {
      super(Monitoring.this, "GET", REST_PATH, null, Void.class);
      this.networkId = com.google.api.client.util.Preconditions.checkNotNull(networkId, "Required parameter networkId must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public LogSensorUpdate setAlt(java.lang.String alt) {
      return (LogSensorUpdate) super.setAlt(alt);
    }

    @Override
    public LogSensorUpdate setFields(java.lang.String fields) {
      return (LogSensorUpdate) super.setFields(fields);
    }

    @Override
    public LogSensorUpdate setKey(java.lang.String key) {
      return (LogSensorUpdate) super.setKey(key);
    }

    @Override
    public LogSensorUpdate setOauthToken(java.lang.String oauthToken) {
      return (LogSensorUpdate) super.setOauthToken(oauthToken);
    }

    @Override
    public LogSensorUpdate setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (LogSensorUpdate) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public LogSensorUpdate setQuotaUser(java.lang.String quotaUser) {
      return (LogSensorUpdate) super.setQuotaUser(quotaUser);
    }

    @Override
    public LogSensorUpdate setUserIp(java.lang.String userIp) {
      return (LogSensorUpdate) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key("network_id")
    private java.lang.String networkId;

    /**

     */
    public java.lang.String getNetworkId() {
      return networkId;
    }

    public LogSensorUpdate setNetworkId(java.lang.String networkId) {
      this.networkId = networkId;
      return this;
    }

    @Override
    public LogSensorUpdate set(String parameterName, Object value) {
      return (LogSensorUpdate) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateRoom".
   *
   * This request holds the parameters needed by the the monitoring server.  After setting any
   * optional parameters, call the {@link UpdateRoom#execute()} method to invoke the remote operation.
   *
   * @param room
   * @param content the {@link com.google.api.services.monitoring.model.Room}
   * @return the request
   */
  public UpdateRoom updateRoom(java.lang.Long room, com.google.api.services.monitoring.model.Room content) throws java.io.IOException {
    UpdateRoom result = new UpdateRoom(room, content);
    initialize(result);
    return result;
  }

  public class UpdateRoom extends MonitoringRequest<com.google.api.services.monitoring.model.Room> {

    private static final String REST_PATH = "rooms/{room}";

    /**
     * Create a request for the method "updateRoom".
     *
     * This request holds the parameters needed by the the monitoring server.  After setting any
     * optional parameters, call the {@link UpdateRoom#execute()} method to invoke the remote
     * operation. <p> {@link
     * UpdateRoom#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param room
     * @param content the {@link com.google.api.services.monitoring.model.Room}
     * @since 1.13
     */
    protected UpdateRoom(java.lang.Long room, com.google.api.services.monitoring.model.Room content) {
      super(Monitoring.this, "PUT", REST_PATH, content, com.google.api.services.monitoring.model.Room.class);
      this.room = com.google.api.client.util.Preconditions.checkNotNull(room, "Required parameter room must be specified.");
    }

    @Override
    public UpdateRoom setAlt(java.lang.String alt) {
      return (UpdateRoom) super.setAlt(alt);
    }

    @Override
    public UpdateRoom setFields(java.lang.String fields) {
      return (UpdateRoom) super.setFields(fields);
    }

    @Override
    public UpdateRoom setKey(java.lang.String key) {
      return (UpdateRoom) super.setKey(key);
    }

    @Override
    public UpdateRoom setOauthToken(java.lang.String oauthToken) {
      return (UpdateRoom) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateRoom setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateRoom) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateRoom setQuotaUser(java.lang.String quotaUser) {
      return (UpdateRoom) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateRoom setUserIp(java.lang.String userIp) {
      return (UpdateRoom) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long room;

    /**

     */
    public java.lang.Long getRoom() {
      return room;
    }

    public UpdateRoom setRoom(java.lang.Long room) {
      this.room = room;
      return this;
    }

    @Override
    public UpdateRoom set(String parameterName, Object value) {
      return (UpdateRoom) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Monitoring}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Monitoring}. */
    @Override
    public Monitoring build() {
      return new Monitoring(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link MonitoringRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setMonitoringRequestInitializer(
        MonitoringRequestInitializer monitoringRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(monitoringRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
