<?php
  require "../../config.php";

    session_start();
    if (isset($_SESSION["login_data"]))
    {
      header('Location: ../../index.php');
      exit();
    }

    // echo "<pre>"; print_r($_POST); echo "</pre>";
    // die();

    if(isset($_POST['submit']))
    {
      $firstname = $_POST['first_name'];
      $lastname = $_POST['last_name'];
      $email = $_POST['email'];
      $username= $_POST['username'];
      $password = $_POST['password'];
      $passwordhash = md5($password);
      $cpassword = $_POST['cpassword'];


      if(isset($_FILES['imglink']) || !empty($_FILES['imglink'])){
        $img_name = $_FILES['imglink']['name'];
        $img_size =$_FILES['imglink']['size'];
        $img_tmp =$_FILES['imglink']['tmp_name'];
      }

      // echo "<pre>"; print_r($_FILES); echo "</pre>";
      // var_dump($_REQUEST);
      // die();
      
      if(strcmp($password, $cpassword) == 0) //0 equal values 
      {
        try {
          require 'dbconfig.php';

          $datacheck = "SELECT * FROM login WHERE username='$username' OR email='$email'" ;
          
          $query = $conn->query($datacheck);
          $results = $query->fetchAll(PDO::FETCH_ASSOC);

          // echo "<pre>"; print_r($_REQUEST); echo "</pre>";
          // // var_dump($_REQUEST);
          // die();

          if(!empty($results)) //if has data 
          {
            // there is already a user with the same username
            echo '<script type="text/javascript"> 
              alert("User already exists.. try another username") 
            </script>';

            header("refresh: 1; url = $url/register.php");
          }
          else if(isset($target_file) && file_exists($target_file))
          {
            echo '<script type="text/javascript"> alert("Image file already exists.. Try another image file") </script>';

            header("refresh: 1; url = $url/register.php");
          }
          else if(isset($img_size) && $img_size>2097152)
          {
            echo '<script type="text/javascript"> alert("Image file size larger than 2 MB.. Try another image file") </script>';
            header("refresh: 1; url = $url/register.php");
          }
          else
          {

            // if(isset($img_tmp)){

            //   $directory = "$images_folder/users/";
            //   $originalFileName = basename($img_name);
            //   $fileType = pathinfo($originalFileName, PATHINFO_EXTENSION); 
            //   $filename = "user_{$firstname}_{$lastname}.{$fileType}";
            //   $target_file = $directory.$filename;
            //   $uploaded_file = move_uploaded_file($img_tmp,$target_file); 	
            // }
            // die();

            try {
              $conn->beginTransaction();
              
                $data = [
                  $firstname,
                  $lastname,
                  $username,
                  $email,
                  $passwordhash
                ];

                $q_adduser = $conn->prepare("INSERT INTO login (
                    first_name,
                    last_name,
                    username,
                    email,
                    password
                ) VALUES (?,?,?,?,?)");

                $q_adduser->execute($data);

                if(isset($img_tmp)){

                  $directory = "$images_folder/users/";

                  $originalFileName = basename($img_name);
                  $fileType = pathinfo($originalFileName, PATHINFO_EXTENSION); 

                  $filename = "user_{$firstname}_{$lastname}.{$fileType}";

                  $target_file = $directory.$filename;

                  $uploaded_file = move_uploaded_file($img_tmp,$target_file); 	
                }

              $conn->commit();

              echo '<script type="text/javascript"> alert("User Registered.. Go to login page to login") </script>';
              header("refresh: 1; url = $url/login.php");
            }catch (Exception $e){
                $conn->rollback();
                echo '<script type="text/javascript"> alert("Error!") </script>';
                throw $e;
                // header("refresh: 1; url = $url/login.php");
            }catch (Throwable $e){
                $conn->rollback();
                echo '<script type="text/javascript"> alert("PHP Error!") </script>';
                throw $e;
                // header("refresh: 1; url = $url/login.php");
            }

            $conn = null;
            exit();
          }	
        }catch(PDOException $e) {
          throw($e);
          die("connection error");
        }
      }
      else
      {
        echo '<script type="text/javascript"> alert("Password and confirm password does not match!")</script>';	
      }
    }

    
?>