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

  // gapi.client.monitoring.rooms.
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
    height: 300,
    width: 350,
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
};

$(document).ready(initUI);