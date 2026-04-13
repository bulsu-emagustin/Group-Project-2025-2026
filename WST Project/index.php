<?php
require 'config.php';
session_start();

if (!isset($_SESSION["login_data"])) {
    session_destroy();
    header('Location: login.php');
    exit();
}

$user_data = $_SESSION["login_data"];
$userid = $user_data["id"];

try {
    require 'db/action/dbconfig.php';

    $stmt = $conn->prepare("SELECT * FROM login WHERE id = ?");
    $stmt->execute([$userid]);
    $user_data = $stmt->fetch(PDO::FETCH_ASSOC);

} catch (PDOException $e) {
    die("DB Error: " . $e->getMessage());
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>

/* ===== BODY ===== */
body {
    background-color: #000;
    color: #fff;
}

/* ===== NAVBAR ===== */
.custom-navbar {
    background-color: #111;
    border-bottom: 2px solid red;
}

.navbar-brand {
    font-weight: bold;
}

/* SEARCH */
.custom-navbar input {
    border-radius: 20px;
    padding: 8px 15px;
}

/* SUB NAV */
.sub-nav {
    display: flex;
    justify-content: center;
    gap: 40px;
    background-color: #0a0a0a;
    padding: 15px 0;
    border-bottom: 1px solid #222;
}

.sub-nav a {
    text-decoration: none;
    color: #aaa;
    position: relative;
}

/* ACTIVE TAB */
.sub-nav a.active {
    color: white;
}

.sub-nav a.active::after {
    content: "";
    position: absolute;
    width: 100%;
    height: 2px;
    background: red;
    bottom: -8px;
    left: 0;
}

/* HOVER */
.sub-nav a:hover {
    color: red;
}

/* HERO */
.hero {
    position: relative;
    height: 400px;
    background: url('images/demonslayer.jpg') center/cover no-repeat;
    display: flex;
    align-items: center;
    padding-left: 50px;
}

/* OVERLAY */
.hero::before {
    content: "";
    position: absolute;
    inset: 0;
    background: rgba(0,0,0,0.6);
}

.hero-content {
    position: relative;
    max-width: 500px;
}

.hero h1 {
    font-size: 40px;
    font-weight: bold;
}

.hero span {
    color: red;
}

/* CARDS */
.card {
    background-color: #111;
    color: white;
    border: 1px solid #222;
}

.card img {
    height: 200px;
    object-fit: cover;
}

</style>
</head>

<body>

<!-- ===== NAVBAR ===== -->
<nav class="navbar navbar-expand-lg navbar-dark custom-navbar">
  <div class="container-fluid">

    <a class="navbar-brand" href="#">
      MANGA<span class="text-danger">QUILLA</span>
    </a>

    <form class="d-flex mx-auto w-50">
      <input class="form-control bg-dark border-0 text-white" type="search" placeholder="Search...">
    </form>

    <div>
      <img src="<?php echo $user_data['img_url']; ?>" class="rounded-circle" width="40">
    </div>

  </div>
</nav>

<!-- ===== SUB NAV ===== -->
<div class="sub-nav">
  <a class="active" href="#">HOME</a>
  <a href="#">PRODUCTS</a>
  <a href="#">NEW ARRIVALS</a>
  <a href="#">ABOUT US</a>
</div>

<!-- ===== HERO ===== -->
<div class="hero">
  <div class="hero-content">
    <h1>
      DEMON SLAYER:<br>
      <span>KIMETSU NO YAIBA</span>
    </h1>

    <p class="small text-secondary mt-3">
      Demon Slayer: Kimetsu no Yaiba is a Japanese anime series that follows the journey of Tanjiro Kamado.
    </p>
  </div>
</div>

<!-- ===== CONTENT ===== -->
<div class="container my-5">
  <div class="row">

<?php
$books = $conn->query("SELECT * FROM books")->fetchAll(PDO::FETCH_ASSOC);

foreach ($books as $row) {
?>

    <div class="col-md-4 mb-4">
      <div class="card">
        <img src="<?php echo $row['image']; ?>">
        <div class="card-body">
          <h5><?php echo $row['title']; ?></h5>
          <p><?php echo $row['excerpt']; ?></p>
          <a href="<?php echo $url; ?>/updatebooks.php?id=<?php echo $row['id']; ?>" class="btn btn-danger">Update</a>
        </div>
      </div>
    </div>

<?php } ?>

  </div>
</div>

</body>
</html>