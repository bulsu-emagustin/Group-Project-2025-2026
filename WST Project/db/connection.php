<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname= "Project_WST";



// // Create connection
// $conn = new mysqli($servername, $username, $password);

// // // Check connection
// // if ($conn->connect_error) {
// //   die("Connection failed: " . $conn->connect_error);
// // }

// // Check connection
// if (mysqli_connect_error()) {
//     die("Database connection failed: " . mysqli_connect_error());
//   }

// echo "Connected successfully";

try {
    $conn = new PDO("mysql:host=$servername;port=3306", $username, $password);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    //echo "Connected successfully";

    $sql = "CREATE DATABASE IF NOT EXISTS ". $dbname;
    // use exec() because no results are returned
    try {
        $conn->exec($sql);
    } catch (PDOException $th) {
        echo "<br> Database Already Exists";
    }

    $sql = "use ". $dbname;
    $conn->exec($sql);
    //sql to create login
    $query = "CREATE TABLE IF NOT EXISTS login (
        id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        first_name VARCHAR(200) NOT NULL,
        last_name VARCHAR(200) NOT NULL,
        username VARCHAR(100) NOT NULL,
        email VARCHAR(100) NOT NULL, 
        img_url VARCHAR(255),
        password TEXT NOT NULL,
        login_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Monitor login date',
        logout_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Monitor logout date'
        )";
    
    try {
        $conn->exec($query);
        
        $data = [
            ['Aquilla James', 'Dela Cruz', 'Akila', '2024104677@bulsu.edu.ph', "", md5('12345678')],
            ['John Edrick', 'Maigue', 'Edrik', '2024104678@bulsu.edu.ph', "", md5('12345678')],
            ['Ian Gabriel', 'San Pedro', 'Ian', '2024104679@bulsu.edu.ph', "", md5('12345678')],
            ['Bennedict', 'Garma', 'Ben', '2024104680@bulsu.edu.ph', "", md5('12345678')],
            ['Ernesto Jay-r', 'Agustin', 'Ernesto', '2024104681@bulsu.edu.ph', "", md5('12345678')],
        ];

        if ($counter == 0) {
        $query_i = $conn->prepare("INSERT INTO login (
            first_name,
            last_name, 
            username,
            email,
            img_url,
            password
        ) VALUES (?,?,?,?,?,?)");

         try {
            $conn->beginTransaction();
            foreach ($data as $row)
            {

                $check = $conn->prepare("SELECT COUNT(*) FROM login WHERE username=?");
                $check->execute([$row['0']]);

                if($check->fetchColumn() == 0){
                    $query_i->execute($row);
                }

            }

            $conn->commit();
            
        }catch (Exception $e){
            $conn->rollback();
            throw $e;
        }
        }
        

    } catch (PDOException $th) {
        echo "Error in creating Table";
        echo $th;
    }
    $query_create_books = "CREATE TABLE IF NOT EXISTS books (
        id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(100) NOT NULL,
        excerpt TEXT,
        image VARCHAR(255)
        )";
    
    try {
        $conn->exec($query_create_books);


        $data_books = [
            ['Julius Ceasar', ' Roman general and statesman known for conquering Gaul, winning a major civil war, and acting as dictator, which effectively transformed the Roman Republic into the Roman Empire.', 'images/JuliusCeasar.jpg'],
            ['Napoleon Bonaparte', 'French military and political leader who rose to prominence during the French Revolution, crowning himself Emperor of the French in 1804.', 'images/Napoleon.jpg'],
            ['Socrates', 'Philosopher known for his contributions to Western philosophy.', 'images/Socrates.jpg']
        ];

        if ($counter == 0) {
        $query_insert_books = $conn->prepare("INSERT INTO books (
            title,
            excerpt,
            image
        ) VALUES (?,?,?)");

        try {
            $conn->beginTransaction();

            foreach ($data_books as $row)
            {

                $check = $conn->prepare("SELECT COUNT(*) FROM books WHERE title=?");
                $check->execute([$row['0']]);;

                if($check->fetchColumn() == 0){
                    $query_insert_books->execute($row);
                }

            }
        $counter++;
            $conn->commit();
        }catch (Exception $e){
            $conn->rollback();
            throw $e;
        }
        }


    } catch (PDOException $th) {
        echo "Error in creating Table";
        echo $th;
    }

} catch(PDOException $e) {
    echo "Connection failed: " . $e->getMessage();
}