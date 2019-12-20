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

