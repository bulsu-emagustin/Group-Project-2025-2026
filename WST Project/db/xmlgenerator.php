<?php //require "connection.php"; ?>

<?php

/** create XML file */ 
$mysqli = new mysqli("localhost", "root", "", "Project_WST");
/* check connection */
if ($mysqli->connect_errno) {
   echo "Connect failed ".$mysqli->connect_error;
   exit();
}

$query = "SELECT id,title,category,author,publish_date,price,media,media_type FROM books";
$booksArray = array();
if ($result = $mysqli->query($query)) {
    /* fetch associative array */
    while ($row = $result->fetch_assoc()) {
       array_push($booksArray, $row);
    }
  
    if(count($booksArray)){
         createXMLfile($booksArray);
     }
    /* free result set */
    $result->free();
}
/* close connection */
$mysqli->close();

function createXMLfile($booksArray){
  
   $filePath = '../xmlfiles/book.xml';
   
   if(!file_exists($filePath)){
      $dom     = new DOMDocument('1.0', 'utf-8');

      $dtd = new DOMImplementation();

      $dom->appendChild($dtd->createDocumentType('books', '', 'Bookstore.dtd'));
      
      $root      = $dom->createElement('books'); 
      for($i=0; $i<count($booksArray); $i++){
      
      $bookId                =  $booksArray[$i]['id'];  
      $bookName              =  htmlspecialchars($booksArray[$i]['title']);
      $bookAuthor            =  $booksArray[$i]['author']; 
      $bookPrice             =  $booksArray[$i]['price']; 
      $bookPublishDate       =  $booksArray[$i]['publish_date']; 
      $bookCategory          =  $booksArray[$i]['category'];
      $bookMedia             =  $booksArray[$i]['media'];
      $bookMediaType         =  $booksArray[$i]['media_type'];
      
      //Start XML Generation
      $book      = $dom->createElement('book');
      $book->setAttribute('category', $bookCategory);

      $id        = $dom->createElement('bookId', $bookId); 
      $book->appendChild($id);

      $title      = $dom->createElement('title', $bookName); 
      $book->appendChild($title);

      $author    = $dom->createElement('author', $bookAuthor); 
      $book->appendChild($author);

      $year      = $dom->createElement('year', $bookPublishDate); 
      $book->appendChild($year);


      $price     = $dom->createElement('price', $bookPrice); 
      $book->appendChild($price);
      
      $media = $dom->createElement('media', $bookMedia); 
      $book->appendChild($media);
      $media->setAttribute('type', $bookMediaType);
   
      $root->appendChild($book);
      }

      $dom->appendChild($root); 
      $dom->save($filePath);

      echo "XML created Successfully!\n";
   } else {
      echo "XML file already exists!<br>";
   }
   
   validateXML($filePath);

 }

 /**
  * DTD Checker
  */
 function validateXML($xml){

   try{
      if(file_exists($xml)){
         $dom = new DOMDocument;
         $dom->load($xml);
         if ($dom->validate()) {
            echo "This document is valid!\n";
         } else {
            echo "This file is not valid. Check your DTD!";
         }
      } else {
         echo "Oops File not exists";
      }
   } catch(Exception $e){
      echo "Some Error in process occured". $e->getMessage();
   }

 }

 ?>