function validateForm() {
  var error = document.getElementById("error");
  var username = document.getElementById("username").value;
  var password = document.getElementById("password").value;

  if(username == "" || username == null) {
    error.innerHTML = "Please enter a username"
    return false;
  }
  if(password == "" || password == null) {
    error.innerHTML = "Incorrect Username or Password"
    return false;
  }
  return true;
}
