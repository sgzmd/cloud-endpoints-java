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
                        "<div id='tab" + num_tabs + "'>" + room.name + "</div>"
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