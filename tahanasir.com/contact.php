<?php
function spamcheck($field) {
  // Sanitize e-mail address
  $field=filter_var($field, FILTER_SANITIZE_EMAIL);
  // Validate e-mail address
  if(filter_var($field, FILTER_VALIDATE_EMAIL)) {
    return TRUE;
  } else {
    return FALSE;
  }
}
?>

<br>

<form role="form" method="post" action="<?php echo $_SERVER["PHP_SELF"];?>#contact" id="contact">
    <div class="form-group">
    <label for="exampleInputEmail1" style="color:#fff;">Email:</label>
    <input type="text" name="email" class="form-control" id="exampleInputEmail1" style="width:50%;">
  </div>

   <div class="form-group">
    <label for="exampleInputEmail1" style="color:#fff;">Subject:</label>
    <input type="text" name="subject" class="form-control" id="exampleInputEmail1" style="width:50%;">
  </div>

 <div class="form-group">
    <label for="exampleInputEmail1" style="color:#fff;">Message:</label>
    <textarea style="resize: none; width:50%;" name="message" class="form-control" rows="11"></textarea>
  </div>
  <button type="submit" name="submit" class="btn btn-default">Submit</button>
  <br><br>

  </form>
  
  <?php 

  if (isset($_POST["email"])) {
    // Check if "from" email address is valid
    $mailcheck = spamcheck($_POST["email"]);
    if ($mailcheck==FALSE) {
      echo '<p>Invalid input</p>';
    } else {
    // sender
      $email = $_POST["email"]; 
      $subject = $_POST["subject"];
      $message = $_POST["message"];
      // message lines should not exceed 70 characters (PHP rule), so wrap it
      $message = wordwrap($message, 70);
      // send mail
      mail("tahamnasir@gmail.com",$subject,$message,"From: $email\n");
      echo '<p>Thanks for contacting me. I\'ll get back to you shortly.</p>';
    }
  }

?>