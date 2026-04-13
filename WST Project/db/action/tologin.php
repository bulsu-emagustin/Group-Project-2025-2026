<?php
    require "../../config.php";

    session_start();
    if (isset($_SESSION["login_data"]))
    {
      header("Location: $url/index.php");
      exit();
    }

    if(isset($_POST["email"]) && isset($_POST["password"])) {

      try{
        $user = $_POST["email"];
        $pass = md5($_POST["password"]);

        require 'dbconfig.php';

        $datacheck = "SELECT * FROM login 
        WHERE username='$user' OR email='$user' 
        AND password='$pass'" ;
          
        $query = $conn->query($datacheck);
        $result = $query->fetchAll(PDO::FETCH_ASSOC);

        // echo "<pre>"; print_r($result); echo "</pre>";
        // die();
        
        if (count($result) >= 1)
        {
          $result = array_shift($result); //$result[0];

          $_SESSION["login_data"] = $result;

          header("Location: $url/index.php");
        }
        else
        {
          echo "Login Failed. You will be redirected in 3 sec.";
          
          header("refresh: 1; url = $url/login.php");
        }

        $conn = null;

      } catch (PDOException $e) {
        throw($e);
        die("connection error");
      }
    } else {
        echo "No values found.";
        header("refresh: 1; url = $url/login.php");
    }
?>