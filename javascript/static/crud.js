/**
 * Created with IntelliJ IDEA.
 * User: kirillov
 * Date: 06/06/2013
 * Time: 21:50
 * To change this template use File | Settings | File Templates.
 */

function updateTips(t) {
  var tips = $('#validateTips');
  tips.text(t).addClass("ui-state-highlight");
  setTimeout(function () {
    tips.removeClass("ui-state-highlight", 1500);
  }, 500);
}

function checkLength(o, n, min, max) {
  if (o.val().length > max || o.val().length < min) {
    o.addClass("ui-state-error");
    updateTips("Length of " + n + " must be between " +
        min + " and " + max + ".");
    return false;
  } else {
    return true;
  }
}

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

var addSensorDialog = function(roomId) {
  $("#create-sensor-form").data('roomId', roomId);
  $("#create-sensor-form").dialog("open");
};

var addSensor = function(sensor, roomId) {
  console.log("Will be adding sensor to room " + roomId);

  sensor['room'] = roomId;

  console.log(sensor);

  gapi.client.monitoring.addSensor(sensor).execute(function(resp){
    if (resp) {
      reloadAllData(true);
    } else {
      alert("There was a problem adding sensor");
    }
  });
};

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

//  $("#create-sensor").button().click(function() {
//    $("#create-sensor-form").dialog("open");
//  });

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


};

$(document).ready(initUI);