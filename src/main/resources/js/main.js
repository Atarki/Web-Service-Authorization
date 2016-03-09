$(document).ready(function () {
    $('#RegisterSubmit').click(function () {
        $.ajax({
            type: 'PUT',
            url: '/users',
            data: {
                login: $.('#login').valueOf(),
                pass: $.('#pass').valueOf(),
                email: $.('#email').valueOf()
            },
            headers: {'id': '${user.id}'},

            success: function (res, status, xhr) {
                $(trID).remove();
                //alert(xhr.getResponseHeader("info"));
            }
        });
    });

    $('#LoginSubmit').click(function () {
        $.ajax({
            type: 'POST',
            url: '/users',
            data: {
                login: $.('#login').valueOf(),
                pass: $.('#pass').valueOf(),
            },
            headers: {'id': '${user.id}'},

            success: function (res, status, xhr) {
                $(trID).remove();
                //alert(xhr.getResponseHeader("info"));
            }
        });
    });



    $('#register-form').animate({opacity: 0, height: "toggle"}, 1000, function () {
    });


    $('#login-register').click(function () {

        $('#login-form').animate({opacity: 0.25, height: "toggle"}, 1000, function () {
        });
        $('#register-form').animate({opacity: 0.25, height: "toggle"}, 1000, function () {
        });

    }, function () {
        $('#login-form').animate({opacity: 0.25, height: "toggle"}, 1000);
    });

// -------------------------------------------

    $("#register-btn").click(function () {
        $("#register-form").animate({opacity: 0.25, height: "toggle"}, 1000, function () {
            // Animation complete.
        });
    }, function () {
        $("#register-form").animate({opacity: 0.25, height: "toggle"}, 1000)
    });


});
