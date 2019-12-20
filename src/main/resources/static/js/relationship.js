$(document).ready(function(e) {
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
        error: function() {
            alert("No such user, or wrong password!");
        }
    });

    // friend list
    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback", 
        url: "http://localhost:8084/loadData/loadFriendCompleteList",
        success: function(callback) {
            console.log(callback);
            
            $("#friendList").empty();
            for (var i = 0; i < callback.resultObj.length; i++) {
                var tempRowHTML = "<li><div class='call-chat'><button class='btn btn-info btn-circle btn-lg' type='button'><i class='fa fa-comments-o'></i></button><button class='btn btn-danger btn-circle btn-lg' type='button'><i class='fa fa-times'></i></button></div><a href='profile_display.html'><img src='" + callback.resultObj[i].photo + "' alt='user-img' class='img-circle'> <span>" + callback.resultObj[i].uname + "</span></a></li>";

                $("#friendList").append(tempRowHTML);
            }
        },
        error: function() {
            alert("Load messages error!");
        }
    });

    // neighbor list
    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback", 
        url: "http://localhost:8084/loadData/loadNeighborCompleteList",
        success: function(callback) {
            console.log(callback);
            
            $("#neighborList").empty();
            for (var i = 0; i < callback.resultObj.length; i++) {
                var tempRowHTML = "<li><div class='call-chat'><button class='btn btn-info btn-circle btn-lg' type='button'><i class='fa fa-comments-o'></i></button><button class='btn btn-danger btn-circle btn-lg' type='button'><i class='fa fa-times'></i></button></div><a href='profile_display.html'><img src='" + callback.resultObj[i].photo + "' alt='user-img' class='img-circle'> <span>" + callback.resultObj[i].uname + "</span></a></li>";

                $("#neighborList").append(tempRowHTML);
            }
        },
        error: function() {
            alert("Load messages error!");
        }
    });

    // Pending Applications
    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback",
        url: "http://localhost:8084/notify/notifyNewFiendApplicationAsRecipient",
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

