<?php 
  session_start();
  if (isset($_SESSION["username"]) && isset($_SESSION["password"]))
  {
    header('Location: index.php');
    exit();
  }

  $text = "Login";
  $email_field_label = "Please enter Email address";
  $password_field_label = "Please enter Password";
  $remember_me = false;
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="text-white" style="background: radial-gradient(circle at left, #1a0000, #000000);">

<div class="container-fluid vh-100 d-flex align-items-center">
  <div class="row w-100">

  
    <div class="col-lg-7 d-flex flex-column justify-content-center px-5">
      <h5 class="mb-4">
        <span class="fw-bold">MANGA</span><span class="text-danger fw-bold">QUILLA</span>
      </h5>

      <h1 class="display-3 fw-bold">
        MANGA?<br>
        <span class="text-danger">WE GOT YOU.</span>
      </h1>

      <p class="mt-3 text-secondary">
        Step into the world of manga where every page tells a story.
        From action-packed adventures to heartwarming romances,
        our store brings your favorite series together in one place.
      </p>
    </div>

    <!-- log in box -->
    <div class="col-lg-5 d-flex justify-content-center">
      <div class="p-5 rounded-3 shadow-lg" style="background-color: rgba(255,255,255,0.05); width: 500px;">

        <h3 class="fw-bold mb-3">
          SIGN <span class="text-danger">IN</span>
        </h3>

        <p class="text-secondary small mb-4">Welcome, Readers!</p>

        <form method="POST" action="db/action/tologin.php">

          <div class="mb-3">
            <label class="form-label small"><?= $email_field_label ?></label>
            <input type="text" name="email" class="form-control bg-dark border-0 text-white" placeholder="you@example.com">
          </div>

          <div class="mb-3">
            <label class="form-label small"><?= $password_field_label ?></label>
            <input type="password" name="password" class="form-control bg-dark border-0 text-white" placeholder="********">
          </div>

          <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" name="remember_me" <?= $remember_me ? 'checked' : '' ?>>
            <label class="form-check-label small">Remember me</label>
          </div>

          <div class="d-grid mb-3">
            <button type="submit" class="btn btn-danger"><?= $text ?></button>
          </div>
        
          </form>

        <div class="text-center text-secondary mb-2">OR</div>

        <div class="d-grid mb-3">
          <button class="btn btn-dark border text-white">
            Continue with Google
          </button>
        </div>

        <p class="text-center small">
          New here? <span class="text-danger">Create an account</span>
        </p>

      </div>
    </div>

  </div>
</div>

</body>
</html>