$(document).ready(function(e) {
    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback", 
        url: "http://localhost:8084/user/getUserInfo",
        success: function(callback) {
            console.log(callback);
            if (document.cookie == "") {
                window.location.href ="login.html";
            }
            else {
                $("#upper_right_name").html(callback.resultObj.uname);
            }
        },
        error: function() {
            alert("No such user, or wrong password!");
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

