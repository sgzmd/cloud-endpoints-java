/**
 * Created with IntelliJ IDEA.
 * User: kirillov
 * Date: 06/06/2013
 * Time: 21:50
 * To change this template use File | Settings | File Templates.
 */

var name = $("#name");
var allFields = [name];

function updateTips( t ) {
  tips
      .text( t )
      .addClass( "ui-state-highlight" );
  setTimeout(function() {
    tips.removeClass( "ui-state-highlight", 1500 );
  }, 500 );
}

function checkLength( o, n, min, max ) {
  if ( o.val().length > max || o.val().length < min ) {
    o.addClass( "ui-state-error" );
    updateTips( "Length of " + n + " must be between " +
        min + " and " + max + "." );
    return false;
  } else {
    return true;
  }
}

var initUI = function () {
  $("#create-room")
      .button()
      .click(function () {
        $("#dialog-form").dialog("open");
      });

  $( "#dialog-form" ).dialog({
    autoOpen: false,
    height: 300,
    width: 350,
    modal: true,
    buttons: {
      "Create an account": function() {
        var bValid = true;
        name.removeClass( "ui-state-error" );

        bValid = bValid && checkLength( name, "username", 3, 16 );

        if ( bValid ) {
          alert("Everything is OK, will be adding a room with name " + name.val());
//          $( "#users tbody" ).append( "<tr>" +
//              "<td>" + name.val() + "</td>" +
//              "<td>" + email.val() + "</td>" +
//              "<td>" + password.val() + "</td>" +
//              "</tr>" );
          $( this ).dialog( "close" );
        }
      },
      Cancel: function() {
        $( this ).dialog( "close" );
      }
    },
    close: function() {
      allFields.val( "" ).removeClass( "ui-state-error" );
    }
  });
};

$(document).ready(initUI);