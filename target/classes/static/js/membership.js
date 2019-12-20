$(document).ready(function(e) {
    
    function convertTime(time = +new Date()) {
        var date = new Date(time + 8 * 3600 * 1000);
        return date.toJSON().substr(0, 19).replace('T', ' ').replace(/-/g, '.');
    }
    
    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback", 
        url: "http://localhost:8084/user/currentUserInfo",
        success: function(callback) {
            console.log(callback);
            if (document.cookie == "" || callback.resultCode == "USER_NOT_LOGIN_IN") {
                window.location.href ="login.html";
            }
            else {
                $("#upper_right_name").html(callback.resultObj.uname);
                $("#photo").attr("src", callback.resultObj.photo);
            }
        },
        error: function(e) {
            console.log(e);
            alert("No such user, or wrong password!");
        }
    });

    // block
    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback",
        url: "http://localhost:8084/membership/getCurrentBlockInfo",
        success: function(callback) {
            console.log(callback);
    
            if (callback.resultObj != null) {
                $("#currentBlockID").html("Block ID: " + callback.resultObj.bid);
                $("#currentBlockName").html("Block Name: " + callback.resultObj.bname);
            }
           else {
                $("#currentBlockInformation").empty();
                $("#PendingBlockApplications").empty();
                $("#blockMembers").empty();
                $("#hoodMembers").empty();
                $("#leaveBlock").empty();
           }
        },
        error: function(e) {
            console.log(e);
            alert("Error!");
        }
    });

    // hood
    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback",
        url: "http://localhost:8084/membership/getCurrentHoodInfo",
        success: function(callback) {
            console.log(callback);
            $("#currentHood").html("Hood Name: " + callback.resultObj.hname);
        },
        error: function(e) {
            console.log(e);
            alert("Error!");
        }
    });


    // Pending Applications
    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback",
        url: "http://localhost:8084/notify/notifyNewBlockApplicationAsRecipient",
        success: function(callback) {
            console.log(callback);

            if (callback.resultObj != null) {
                $("#allApplications").empty();
                

                for (var i = 0; i < callback.resultObj.length; i++) {
                    var uphoto, uname;
                    var applicant = callback.resultObj[i].applicant;
                    var appliTime = convertTime(callback.resultObj[i].baTime);
                    var appliTXT = callback.resultObj[i].txt;
                    
                    myajax = $.ajax({
                        type: 'get',
                        dataType: "jsonp",
                        jsonp: "callback",
                        async: false,
                        url: "http://localhost:8084/user/getUserByUid?uid=" + applicant,
                        success: function(callback) {
                            console.log(callback);
                            uname = callback.resultObj.fName + "    " + callback.resultObj.lName;
                            uphoto = callback.resultObj.photo;
                        },
                        error: function(e) {
                            console.log(e);
                            alert("Error!");
                        }
                    });

                    $.when(myajax).done(function(){
                        var tempRowHTML1 = "<div class='user-img'> <a href='profile_display.html'><img src=" + uphoto + " alt='user' class='img-circle'></a></div>"

                        var tempRowHTML2 = "<div class='mail-contnet'><h5>" + uname + "</h5><span class='time'>" + appliTime
                         + "</span><br/><span class='mail-desc'>" + appliTXT + "</span> <a href='#' class='btn btn btn-rounded btn-default btn-outline m-r-5'><i class='ti-check text-success m-r-5'></i>Approve</a><a href='#' class='btn-rounded btn btn-default btn-outline'><i class='ti-close text-danger m-r-5'></i> Reject</a></div>";
    
                        $("#allApplications").append(tempRowHTML1 + tempRowHTML2);
                    });
                }
            }
            else $("#PendingBlockApplications").empty();

        },
        error: function(e) {
            console.log(e);
            alert("Error!");
        }
    });


    // block members
    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback",
        url: "http://localhost:8084/loadData/loadAllUsersFromSameBlock",
        success: function(callback) {
            console.log(callback);

            $("#blockMembersTable").empty();

            for (var i = 0; i < callback.resultObj.length; i++) {
                var id = callback.resultObj[i].uid;
                var fName = callback.resultObj[i].fName;
                var lName = callback.resultObj[i].lName;
                var name = fName + "     " + lName;
                var uName = callback.resultObj[i].uname;

                var tempRowHTML = "<tr><td>" + id + "</td><td>" + name + "</td><td><a href='profile_display.html'>@" + uName + "</a></td></tr>";
                $("#blockMembersTable").append(tempRowHTML);
            }
        },
        error: function(e) {
            console.log(e);
            alert("Error!");
        }
    });

    // hood members
    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback",
        url: "http://localhost:8084/loadData/loadAllUsersFromSameHood",
        success: function(callback) {
            console.log(callback);

            $("#hoodMembersTable").empty();

            for (var i = 0; i < callback.resultObj.length; i++) {
                var id = callback.resultObj[i].uid;
                var fName = callback.resultObj[i].fName;
                var lName = callback.resultObj[i].lName;
                var name = fName + "     " + lName;
                var uName = callback.resultObj[i].uname;

                var tempRowHTML = "<tr><td>" + id + "</td><td>" + name + "</td><td><a href='profile_display.html'>@" + uName + "</a></td></tr>";
                $("#hoodMembersTable").append(tempRowHTML);
            }
        },
        error: function(e) {
            console.log(e);
            alert("Error!");
        }
    });

    // blocks available
    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback",
        url: "http://localhost:8084/membership/blocksAvailable",
        success: function(callback) {
            console.log(callback);

            $("#blockAvailable").empty();

            for (var i = 0; i < callback.resultObj.length; i++) {
                var tempRowHTML = "<tr><td>" + callback.resultObj[i].bid + "</td><td>" + callback.resultObj[i].bname + "</td><td><div class='form-group'><div class='col-sm-12'><button class='btn btn-success'>Send</button></div></div></td></tr>";

                $("#blockAvailable").append(tempRowHTML);
            }
        },
        error: function(e) {
            console.log(e);
            alert("Error!");
        }
    });

    // leave block
    $("#leaveBlock").on("click", function() {
        $.ajax({
            type: 'get',
            dataType: "jsonp",
            jsonp: "callback",
            url: "http://localhost:8084/membership/exitCurrentBlock",
            success: function(callback) {
                console.log(callback);
            },
            error: function(e) {
                console.log(e);
                alert("Error!");
            }
        });
    });


    $("#logout").on("click", function() {
        function clearAllCookie() {
            var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
            if (keys) {
                for (var i = keys.length; i--;)
                    document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
            }
        }
        clearAllCookie();
    })
});

