//$('.message a').click(function(){
//    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
//});
//
//$('#login-page').slideDown('fast');
//
//$( "#login-form" ).click(function() {
//    $( "#register-form" ).animate({
//        opacity: 0.25,
//        left: "+=50",
//        height: "toggle"
//    }, 5000, function() {
//        // Animation complete.
//    });
//});

$('#register-form').animate({opacity: 0, height: "toggle"}, 1000, function () {});


$('#login-register').click(function () {

    $('#login-form').animate({opacity: 0.25, height: "toggle"}, 1000, function () {});
    $('#register-form').animate({opacity: 0.25, height: "toggle"}, 1000, function () {});

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
