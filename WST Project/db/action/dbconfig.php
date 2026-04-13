<?php

$dbservername = "localhost";
$dbusername = "root";
$dbpassword = "";
$dbname= "project_wst";

$conn = new PDO("mysql:host=$dbservername;port=3306", $dbusername, $dbpassword);
$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
$conn->setAttribute(PDO::MYSQL_ATTR_USE_BUFFERED_QUERY, true);

$sql = "use ". $dbname;
$conn->exec($sql);

