var handleCheckbox = function(cb, networkId) {
    alert(networkId);
}

var disarmRoom = function(roomId) {

}

/**
 * It's ugly as a sin, but it works. It will produce something like this:
 *
 * <div>
 *  <input onclick="handleCheckbox(this, "DddEeeFff");"
 *         type="checkbox"
 *         id="sensor_0_DddEeeFff">
 *      <label for="sensor_0_DddEeeFff">DddEeeFff</label>
 * </div>
 */
var makeCheckBox = function(roomId, name, networkId, checked, orderNumber) {
    return "<div class='cb'><input disabled onclick='handleCheckbox(this, \"" + networkId + "\");' type='checkbox'" +
        "id='sensor_" + roomId + "_" + networkId + "' " + checked + "/>" +
        "<label for='sensor_" + roomId + "_" + networkId + "'>" + name +
        "</label></div>";

}

function makeRoomHtml(room, roomId) {
    var html = "";
    for (var item in room.sensors) {
        var sensor = room.sensors[item];
        var checked = sensor.active ? "checked" : ""

        html += makeCheckBox(
            roomId,
            sensor.networkId,
             sensor.networkId,
             checked,
             item);
    }

    html += "<div class='room-buttons'><button>Disarm room</button>&nbsp;<button>Arm room</button></div>";

    return html;
}

function init() {
    var ROOT = 'https://cloud-endpoints-example.appspot.com/_ah/api';
    gapi.client.load('monitoring', 'v1', function () {
        gapi.client.monitoring.listRooms().execute(function (resp) {
            console.log(resp);

            $(document).ready(function () {
                $("div#tabs").tabs();

                for (var item in resp.items) {
                    var room = resp.items[item];
                    var num_tabs = $("div#tabs ul li").length + 1;
                    $("div#tabs").append(
                        "<div id='tab" + num_tabs + "'>" + makeRoomHtml(room, item) + "</div>"
                        );

                    $("div#tabs ul").append(
                        "<li><a href='#tab" + num_tabs + "'>" + room.name + "</a></li>"
                        );

                }
                $("div#tabs").tabs("refresh");
            });
        });
    }, ROOT);


}

//$(document).ready(function () {
//    $("div#tabs").tabs();
//
//    $("button#add-tab").click(function () {
//
//        var num_tabs = $("div#tabs ul li").length + 1;
//
//        $("div#tabs ul").append(
//            "<li><a href='#tab" + num_tabs + "'>#" + num_tabs + "</a></li>"
//        );
//        $("div#tabs").tabs("refresh");
//    });
//});
