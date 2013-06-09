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

/**
 * Helper function which updates error messages in forms.
 */
var updateTips = function(t) {
  var tips = $('#validateTips');
  tips.text(t).addClass("ui-state-highlight");
  setTimeout(function () {
    tips.removeClass("ui-state-highlight", 1500);
  }, 500);
}

/**
 * Helper function to validate the length of the field and set an error state if applicable.
 */
var checkLength = function (o, n, min, max) {
  if (o.val().length > max || o.val().length < min) {
    o.addClass("ui-state-error");
    updateTips("Length of " + n + " must be between " +
        min + " and " + max + ".");
    return false;
  } else {
    return true;
  }
}

/**
 * Starts delete room flow
 * @param roomId An ID of the Room to be deleted
 */
var deleteRoom = function(roomId) {
  console.log("Requesting room to be deleted", roomId);

  $("#dialog-confirm").data('roomId', roomId);
  $("#dialog-confirm").dialog("open");
}

/**
 * Adds a room with a specified name
 *
 * @param name A name of the newly created Room.
 */
var addRoom = function(name) {
  var room = {
    'name': name
  };

  console.log("Will be adding a room");
  console.log(room);

  gapi.client.monitoring.addRoom(room).execute(function(resp){
    console.log(resp);

    if (gLocalApi) {
      // adding a bit of latency for local API. Probably some
      // sort of bug, but without it it only returns added
      // room on the second call, I wonder why...
      setTimeout(function(){reloadAllData(true);}, 500);
    } else {
      reloadAllData(true);
    }
  });
};

/**
 * Starts add sensor UI flow
 * @param roomId Room ID the sensor will be added to
 */
var addSensorDialog = function(roomId) {
  $("#create-sensor-form").data('roomId', roomId);
  $("#create-sensor-form").dialog("open");
};

/**
 * Adds a sensor into a specified room
 *
 * @param sensor Sensor object
 * @param roomId An ID of the Room sensor to be added to
 */
var addSensor = function(sensor, roomId) {
  console.log("Will be adding sensor to room " + roomId);

  // Note, that next line is an only way I am aware at the moment of
  // writing which allows to combine named parameters in API
  // (such as those specified using @Named("name") annotation
  // and a request body which is normally sent as REST request POST body.
  sensor['room'] = roomId;

  console.log("Final object sending to the API", sensor);

  gapi.client.monitoring.addSensor(sensor).execute(function(resp){
    if (resp) {
      reloadAllData(true);
    } else {
      alert("There was a problem adding sensor");
    }
  });
};

/**
 * Initialises all forms, dialogs and buttons
 */
var initUI = function () {
  var name = $("#name");

  $("#create-room")
      .button()
      .click(function () {
        $("#dialog-form").dialog("open");
      });

  $("#dialog-form").dialog({
    autoOpen: false,
    height: 'auto',
    width: 'auto',
    modal: true,
    buttons: {
      "Add new room": function () {
        var name = $('#name');
        var bValid = true;
        name.removeClass("ui-state-error");

        bValid = bValid && checkLength(name, "username", 3, 16);

        if (bValid) {
          addRoom(name.val());
          $(this).dialog("close");
        }
      },
      'Cancel': function () {
        $(this).dialog("close");
      }
    },
    close: function () {
      name.val("").removeClass("ui-state-error");
    }
  });

  $("#create-sensor-form").dialog({
    autoOpen: false,
    height: 'auto',
    width: 'auto',
    modal: true,
    buttons: {
      "Add new sensor": function () {
        var roomId = $("#create-sensor-form").data('roomId');

        var name = $('#sensor-name');
        var bValid = true;
        name.removeClass("ui-state-error");

        bValid = bValid && checkLength(name, "username", 3, 16);

        var network = $('#sensor-network-id');
        network.removeClass("ui-state-error");

        bValid = bValid && checkLength(network, "username", 3, 16);


        if (bValid) {
          var sensor = {
            'sensorName': name.val(),
            'networkId': network.val(),
            'sensorType': $('#sensor-type').val(),
            'active': $('#sensor-active').val()
          };
          addSensor(sensor, roomId);
          $(this).dialog("close");
        }
      },
      'Cancel': function () {
        $(this).dialog("close");
      }
    },
    close: function () {
      name.val("").removeClass("ui-state-error");
    }
  });

  $( "#dialog-confirm" ).dialog({
    resizable: false,
    autoOpen: false,
    height:300,
    width: 450,
    modal: true,
    buttons: {
      "Kill all humans!": function() {
        $( this ).dialog( "close" );
        var roomId = $("#dialog-confirm").data('roomId');
        gapi.client.monitoring.deleteRoom({'room' : roomId}).execute(function(resp){
          reloadAllData(true);
        });
      },
      Cancel: function() {
        $( this ).dialog( "close" );
      }
    }
  });


};

$(document).ready(initUI);