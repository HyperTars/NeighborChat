$(document).ready(function(e) {
    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback", 
        url: "http://localhost:8084/user/currentUserInfo",
        success: function(callback) {
            console.log(callback);
            if (document.cookie == "") {
                window.location.href ="login.html";
            }
            else {
                $("#upper_right_name").html(callback.resultObj.uname);
                $("#userName").html(callback.resultObj.uname);
                $("#avatar").attr("src", callback.resultObj.photo);
                $("#photo").attr("src", callback.resultObj.photo);
                $("#password").val(callback.resultObj.passwd);
                $("#email").val(callback.resultObj.email);
                $("#fName").val(callback.resultObj.fName);
                $("#lName").val(callback.resultObj.lName);
                $("#addr1").val(callback.resultObj.addr1);
                $("#addr2").val(callback.resultObj.addr2);
                $("#intro").html(callback.resultObj.intro);

                if (callback.resultObj.notify == 1) $("#email_receive").attr("checked", "true");
                $("#nRange").get(0).selectedIndex = callback.resultObj.nRange; 
            }
        },
        error: function() {
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

