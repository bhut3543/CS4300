$(document).ready(function() {

  $("#loginform").submit(function() {
    var username = $.trim($('#username_l').val());
    var password = $.trim($('#password_l').val());
    if(username.length == '') {
        $("#error").html("Please enter a username");
        return false;
    }
    if(password.length < 6) {
        $("#error").html("Password must be greater than 6 characters");
        return false;
    }
  });
  $("#registerform").submit(function() {
    var username = $.trim($('#username_r').val());
    var password = $.trim($('#password_r').val());
    if(username.length == '') {
        $("#error").html("Please enter a username");
        return false;
    }
    if(password.length < 6) {
        $("#error").html("Password must be greater than 6 characters");
        return false;
    }
    // $.ajax({url: "http://localhost:8080/4300Project/FinalProjServlet?action=regval&username="+username, success: function(text) {
    //     $("#error").html("Password must be greater than afdiuh characters");
    //     return false;
    // }})
  });


});
