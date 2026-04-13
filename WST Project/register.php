<?php 
require "config.php";

  session_start();
  if (isset($_SESSION["username"]) && isset($_SESSION["password"]))
  {
    header('Location: index.php');
    exit();
  }

$text = "Register";

$firstname_field_label = "First";
$lastname_field_label = "Last Name";
$email_field_label = "Email";
$username_field_label = "Username";
$password_field_label = "Password";
$cpassword_field_label = "Confirm Password";


?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Register User</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="stylesheet" href="css/styles.css">
  
</head>
<body>

  <!-- HEADER -->
  <header>
  </header>

  <!-- MAIN -->
  <main >
    <div class="container">
      <div class="row registration justify-content-center align-items-center">
        <div class="col-lg-4 mx-auto">
          <h2 class="text-center mb-3"><?php echo $text; ?></h2>
          <form method="post" action="<?php echo "{$url}/db/action/toregister.php"; ?>" id="register-form" enctype="multipart/form-data" novalidate>
            <div class="row">
              <div class="col border border-2 rounded rounded-3 p-5">
                <div class="mb-5">
                  <img id="uploadPreview" src="<?php echo "$url/images/avatar.png"; ?>" class="mb-3 avatar img-fluid"/><br>
                  <input type="file" id="imglink" name="imglink" accept=".jpg,.jpeg,.png" onchange="PreviewImage();"/>
                </div>
                <div class="mb-3">
                  <label for="login-email" class="form-label d-none"><?php echo $firstname_field_label; ?></label>
                  <input type="text" class="form-control" name="first_name" id="login-email" required="false" placeholder="<?php echo "Enter $firstname_field_label" ?>">
                  <div id="login-email-msg" class="form-text"></div>
                </div>
                <div class="mb-3">
                  <label for="login-email" class="form-label d-none">Last Name</label>
                  <input type="text" class="form-control" name="last_name" id="login-email" required="false" placeholder="<?php echo "Enter $lastname_field_label" ?>">
                  <div id="login-email-msg" class="form-text"></div>
                </div>
                <div class="mb-3">
                  <label for="login-email" class="form-label d-none">Email address</label>
                  <input type="text" class="form-control" name="email" id="login-email" required="false" placeholder="<?php echo "Enter $email_field_label" ?>">
                  <div id="login-email-msg" class="form-text"></div>
                </div>
                <div class="mb-3">
                  <label for="login-email" class="form-label d-none">Username</label>
                  <input type="text" class="form-control" name="username" id="login-email" required="false" placeholder="<?php echo "Enter $username_field_label" ?>">
                  <div id="login-email-msg" class="form-text"></div>
                </div>
                <div class="mb-3">
                  <label for="login-password" class="form-label d-none">Password</label>
                  <input type="password" class="form-control" name="password" id="login-password" placeholder="<?php echo "Enter $password_field_label" ?>">
                  <div id="login-password-msg"></div>
                </div>
                <div class="mb-3">
                  <label for="login-password" class="form-label d-none"><?php echo $cpassword_field_label; ?></label>
                  <input type="password" class="form-control" name="cpassword" id="login-password" placeholder="<?php echo "Enter $cpassword_field_label" ?>">
                  <div id="login-password-msg"></div>
                </div>
                <div class="d-grid mt-5">
                  <button type="submit" name="submit" value="Register" id="form-submit" class="btn btn-primary">Add New User</button>
                </div>
                <div class="mt-4 text-center">
                  <p>Already Registered? <a href="">Login</a> here</p>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>

  </main>

  <script type="text/javascript">

    function PreviewImage() {
        var oFReader = new FileReader();
        oFReader.readAsDataURL(document.getElementById("imglink").files[0]);

        oFReader.onload = function (oFREvent) {
            document.getElementById("uploadPreview").src = oFREvent.target.result;
        };
    };
	</script>

  <!-- FOOTER -->
  <footer>

  </footer>
  
  <!-- <script src="js/main.js"></script> -->

  <script>
    // var login_form = document.getElementById("login-form")
    // login_form.addEventListener("submit", function(event){
    //   event.preventDefault()
    // });
  </script>
</body>
</html>