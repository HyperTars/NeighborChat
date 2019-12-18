$(document).ready(function(e) {
    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback", 
        url: "http://localhost:8084/user/getUserInfo",
        success: function(callback) {
            console.log(callback);
            if (callback.resultCode == "USER_NOT_LOGIN_IN") {
                window.location.href ="login.html";
            }
            else if (callback.resultCode == "SUCCESS") {
                $("#upper_right_name").html(callback.resultObj.uname);
            }
        },
        error: function() {
            alert("No such user, or wrong password!");
        }
    });

    // console.log($.cookie('userSession'));
});

// $('#upper_right_2').on("click", function() {
//     $.cookie("userSession", "", {expires: -1});
//     window.location.href ="dashboard.html";
// })