$(document).ready(function(e) {
    function convertTime(time = +new Date()) {
        var date = new Date(time + 8 * 3600 * 1000);
        return date.toJSON().substr(0, 19).replace('T', ' ').replace(/-/g, '.');
    }

    var unread_list = [];

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

    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback", 
        url: "http://localhost:8084/user/getMsgidUnread",
        success: function(callback) {
            console.log(callback);
        },
        error: function() {
            alert("Error!");
        }
    });

    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback", 
        url: "http://localhost:8084/message/getAllMessageThreads",
        success: function(callback) {
            console.log(callback);

            if (callback.resultObj != null) {
                $("#allMessages").empty();

                message_count = callback.resultObj.length;

                uphoto_list = [];
                uname_list = [];
                author_list = [];
                mTime_list = [];
                title_list = [];
                txt_list = [];

                for (var i = 0; i < callback.resultObj.length; i++) {
                    var uphoto, uname;
                    var author = callback.resultObj[i].author;
                    var mTime = convertTime(callback.resultObj[i].mTime);
                    var title = callback.resultObj[i].title;
                    var txt = callback.resultObj[i].txt;

                    author_list.push(author);
                    mTime_list.push(mTime);
                    title_list.push(title);
                    txt_list.push(txt);
                    
                    myajax = $.ajax({
                        type: 'get',
                        dataType: "jsonp",
                        jsonp: "callback",
                        async: false,
                        url: "http://localhost:8084/user/getUserByUid?uid=" + author,
                        success: function(callback) {
                            console.log(callback);
                            uname = callback.resultObj.fName + "    " + callback.resultObj.lName;
                            uphoto = callback.resultObj.photo;

                            uphoto_list.push(uphoto);
                            uname_list.push(uname);
                        },
                        error: function(e) {
                            console.log(e);
                            alert("Error!");
                        }
                    });
                }
                
                $.when(myajax).done(function(){
                    for (var i = 0; i < message_count; i++) {
                        var tempRowHTML1 = "<div class='comment-body'><div class='user-img'> <a href='profile_display.html'><img src=" + uphoto_list[i] + " alt='user' class='img-circle'></a></div>"

                        var tempRowHTML2 = "<div class='mail-contnet'><h5>" + uname_list[i] + "</h5><span class='time'>" + mTime_list[i] + "</span><br/><span class='mail-desc'><a href = 'message_detail.html'><h5>" + title_list[i] + "</h5>" + txt_list[i] + "</div></div>";

                        $("#allMessages").append(tempRowHTML1 + tempRowHTML2);
                    }
                });
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

