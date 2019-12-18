$("#form").submit(function (e) {
    var InputName = $('#InputName').val();
    var InputPassword = $('#InputPassword').val();

    if (InputName == "" || InputPassword == "") {
        alert("Username or password cannot be null");
        e.preventDefault();
        return;
    }

    var jsonObj = {
        "uname": InputName,
        "pass": InputPassword,
    };

    $.ajax({
        type: 'get',
        dataType: "jsonp",
        jsonp: "callback", 
        data: jsonObj,
        url: "http://localhost:8084/user/loginIn?uname=" + InputName + "&pass" + InputPassword,
        success: function(callback) {
            console.log(callback);
            console.log(callback.uname)
            window.location.href ="dashboard.html";
        },
        error: function() {
            alert("No such user, or wrong password!");
        }
    });
    e.preventDefault();
})