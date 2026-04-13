"use strict"


// 11/18/2023
window.addEventListener('load', function(){
  //alert('test');
  var s_email = this.sessionStorage.getItem("username"), 
      s_password = this.sessionStorage.getItem("password"),
      login_form = document.getElementById("login-form")

  console.log(login_form);  

  if(login_form == null){
    if(s_email == null && s_password == null) {
      this.alert('Expired Session');
      this.location.assign('login.php');
    }
  }

})


var logout_btn = document.getElementById('logout');

if(logout_btn != null ){
  logout_btn.addEventListener('click', function(){
    var logout = confirm("Do you really want to logout?");

    if(logout){
      window.sessionStorage.removeItem("username");
      window.sessionStorage.removeItem("password");
      window.location.assign('login.php');
    }
  })
}




function checkLogin() {
  var email = document.getElementById('login-email')
  var password = document.getElementById('login-password')

  var validate_email = ValidateEmail(email)
  var validate_password = ValidatePassword(password)

  if(validate_email && validate_password ){
    alert('Email Address is: '+ email.value +'\n\nPassword is: '+ password.value);
    window.sessionStorage.setItem("username", email.value);
    window.sessionStorage.setItem("password", password.value);

    window.location.assign('index.php');
  }
}



function ValidateEmail(el) {
  //var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
  var re = /\S+@\S+\.\S+/;

  var loginEmailMsg = document.getElementById("login-email-msg")


  if(!el.value.length) {
    //alert("Invalid email address!");
    loginEmailMsg.innerHTML = "Required Field"
    loginEmailMsg.className = "invalid-feedback"
    loginEmailMsg.style.display = "block";

    if(el.classList.contains('is-valid')){
      el.classList.add('is-invalid')
      el.classList.remove('is-valid')
    } else {
      el.classList.add('is-invalid')
    }

    return false;

  }

  if (el.value.match(re)) {
  //if(re.test(input)) {
    //alert("Valid email address!"); 
    loginEmailMsg.innerHTML = "Valid email Address!"
    loginEmailMsg.className = "valid-feedback"
    loginEmailMsg.style.display = "block";

    if(el.classList.contains('is-invalid')){
      el.classList.add('is-valid')
      el.classList.remove('is-invalid')
    } else {
      el.classList.add('is-valid')
    }

    return true;
  } else {
    //alert("Invalid email address!");
    loginEmailMsg.innerHTML = "Invalid email address!"
    loginEmailMsg.className = "invalid-feedback"
    loginEmailMsg.style.display = "block";

    if(el.classList.contains('is-valid')){
      el.classList.add('is-invalid')
      el.classList.remove('is-valid')
    } else {
      el.classList.add('is-invalid')
    }

    return false;

  }

}

function ValidatePassword(input){

  var loginPasswordMsg = document.getElementById("login-password-msg")

  console.log(input.value.length)

  if(!input.value.length){
    loginPasswordMsg.innerHTML = "Required Field"
    loginPasswordMsg.className = "invalid-feedback"
    loginPasswordMsg.style.display = "block";

    if(input.classList.contains('is-valid')){
      input.classList.add('is-invalid')
      input.classList.remove('is-valid')
    } else {
      input.classList.add('is-invalid')
    }

    return false
  }

  if (input.value.length == 8) {
  //if(re.test(input)) {

    //alert("Valid email address!");

    loginPasswordMsg.innerHTML = "Valid Password!"
    loginPasswordMsg.className = "valid-feedback"
    loginPasswordMsg.style.display = "block";

    if(input.classList.contains('is-invalid')){
      input.classList.add('is-valid')
      input.classList.remove('is-invalid')
    } else {
      input.classList.add('is-valid')
    }

    return true;
  } else {
    //alert("Invalid email address!");

    loginPasswordMsg.innerHTML = "Password must be in 8 characters!"
    loginPasswordMsg.className = "invalid-feedback"
    loginPasswordMsg.style.display = "block";

    if(input.classList.contains('is-valid')){
      input.classList.add('is-invalid')
      input.classList.remove('is-valid')
    } else {
      input.classList.add('is-invalid')
    }

    return false;

  }
}