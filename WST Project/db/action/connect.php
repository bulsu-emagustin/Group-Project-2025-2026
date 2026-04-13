<?php
$con = mysqli_connect("localhost","root",""); 

if(!mysqli_select_db($con, "Project_WST"))
  
{
  die("connection error");
}
?>