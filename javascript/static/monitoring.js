/*
 * Copyright [2013] [Roman "sgzmd" Kirillov]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

var gTabs = null;
var gLocalApi = false;

// This is a very basic format function, but it will serve our goals.
String.prototype.format = function() {
  var i = 0, s = this, re;
  while(i < arguments.length) {
    re = new RegExp("\\{" + i + "\\}", 'gm');
    s = s.replace(re, arguments[i++]);
  }
  return s;
};

var CHECKBOX_TEMPLATE = '<div> \
   <input onclick="handleCheckbox({0}, {1}, this);" \
          type="checkbox" \
          id="sensor_{1}_{0}"\
          {3}> \
    <label for="sensor_{1}_{0}">\
      <a href="#" title="Click to update last-active" onclick="triggerSensor(\'{4}\');">{2}</a>\
    </label> &mdash; <small title="Last time this sensor was triggered">{5}</small>\
  </div>';

/**
 * Updates last-active-timestamp of a given.
 *
 * @param networkId
 */
var triggerSensor = function(networkId) {
  gapi.client.monitoring
      .logSensorUpdate({'network_id': networkId})
      .execute(function(resp){
        reloadAllData(true);
      });
}

/**
 * Sends an arm/disarm request to the API
 * @param request Request object to be sent
 */
var sendArmRequest = function(request) {
  console.log("Sending Arm request", request);
  gapi.client.monitoring.arm(request).execute(function (resp) {
    console.log("Reloading the data", resp);
    rebuildTabBar(resp, true);
  });
}

/**
 * Changes individual sensor state
 *
 * @param sensorId ID of the sensor to toggle
 * @param roomId ID of the room sensor is installed in
 * @param cb HTML object of the checkbox
 */
var handleCheckbox = function (sensorId, roomId, cb) {
  // if sensor was active, cb.value will be "on"
  // before the callback has finished.
  var active = cb.checked;
  var request = {
    'sensor' : sensorId,
    'room': roomId,
    'state': active
  };
  sendArmRequest(request);
}

/**
 * Toggles the monitoring state of the whole room
 *
 * @param event JQuery event
 * @param state true or false
 */
var armOrDisarmRoom = function(event, state) {
  var roomId = event.currentTarget.value;
  var request = {
    'room': roomId,
    'state': state
  };

  sendArmRequest(request);
}

/**
 * It's ugly as a sin, but it works. Nomen est omen, too.
 */
var makeCheckBox = function (roomId, name, sensorId, checked, networkId, lastUpdated) {
  return CHECKBOX_TEMPLATE.format(
      sensorId,
      roomId,
      name,
      checked ? "checked=true" : "",
      networkId,
      lastUpdated);
}


/**
 * Builds an HTML for a room tab, consisting of checkboxes for sensors.
 *
 * @param room a JSON/JS object for Room returned by the API
 *
 * @returns {string} HTML for a newly created DIV for the tab.
 */
function makeRoomHtml(room) {
  var html = "";
  for (var item in room.sensors) {
    var sensor = room.sensors[item];
    var checked = sensor.active ? "checked" : ""

    html += makeCheckBox(
        room['id'],
        sensor.sensorName,
        sensor['id'],
        sensor.active,
        sensor.networkId,
        sensor.lastActive != 0 ? new Date(parseInt(sensor.lastActive, 10)).toJSON() : "Never updated");
  }

  var lastRoomActive = room.lastActive != 0 ? new Date(parseInt(room.lastActive, 10)).toJSON() : "Never updated";

  html += "<div class='room-buttons'>";
  html += "<button id='add-sensor' value='" + room['id'] + "'>Add sensor</button>"
  html += "&nbsp;";
  html += "<button id='disarm-room' value='" + room['id'] + "'>Disarm room</button>";
  html += "&nbsp;";
  html += "<button id='arm-room' value='" + room['id'] + "'>Arm room</button>";
  html += "<div class='room-last-active' " +
      "title='Shows the last time any sensor in this room was active'>" +
      "Last activity: " + lastRoomActive + "</div>";
  html += "</div>";

  return html;
}

/**
 * Builds or re-builds tabs control from JSON-RPC response.
 *
 * @param resp JSON-RPC response payload
 * @param reload if <code>true</code> then tab bar will be reset to it's original state
 * and then rebuild; otherwise will just build the tab bar.
 */
var rebuildTabBar = function (resp, reload) {
  if (!resp  || !resp.items || resp.items.length == 0) {
    alert("No rooms created. Please add some!");
  } else {

    var selected = 0;
    if (gTabs != null) {
      selected = gTabs.tabs("option", "active");
    }

    if (reload) {
      $("div#tabs").tabs("destroy");
      $("div#tabs").html("<ul></ul>");
    }

    for (var item in resp.items) {
      var room = resp.items[item];
      var num_tabs = $("div#tabs ul li").length + 1;

      $("div#tabs").append(
          "<div id='tab" + num_tabs + "'>" + makeRoomHtml(room, item) + "</div>"
      );

      $("div#tabs ul").append(
          "<li><a href='#tab" + num_tabs + "'>" + room.name + "</a>" +
              "<a href=\"#\" onclick='deleteRoom(" + room['id'] + ");'>x</a></li>"
      );
    }

    gTabs = $("div#tabs").tabs();
    gTabs.tabs("option", "active", selected);

    $('button#add-sensor').button().click(function (event) {
      addSensorDialog(event.currentTarget.value);
    })

    $('button#disarm-room').button().click(function(event) {
      armOrDisarmRoom(event, false);
    });

    $('button#arm-room').button().click(function(event) {
      armOrDisarmRoom(event, true);
    });

    $(document).tooltip();
  }
};

/**
 * [Re]loads all the data from the API and [re]build the tab bar.
 *
 * @param reload Re-load and re-build if true.
 */
var reloadAllData = function(reload) {
  gapi.client.monitoring.listRooms().execute(function (resp) {
    console.info("Data from API:", resp);

    if (! reload ) {
      $(document).ready(function() { rebuildTabBar(resp) });
    } else {
      rebuildTabBar(resp, true);
    }
  });
}

/**
 * Called by Google JS client upon finishing to load.
 */
function init() {
//  var ROOT = 'https://cloud-endpoints-example.appspot.com/_ah/api';
  var ROOT = 'http://localhost:8888/_ah/api';
  gapi.client.load('monitoring', 'v1', reloadAllData, ROOT);

  if (ROOT.indexOf("localhost") > 0) {
    gLocalApi = true;
  }
}
