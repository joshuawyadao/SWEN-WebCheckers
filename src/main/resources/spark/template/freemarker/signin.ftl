<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="53">
  <title>Web Checkers | Sign In</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | Sign In</h1>

   <div class="navigation">
        <a href="/">my home</a>
   </div>

  <div class="body">

  <audio autoplay>
     <source src="https://s1.vocaroo.com/media/download_temp/Vocaroo_s1Dc3F6sDwyR.mp3">
  </audio>
  <!-- Provide a message to the user, if supplied. -->
  <#include "message.ftl">

  <form action="./signin" method="POST">
          Please Enter Your Name:
          <br/>
          <input name="myUsername" placeholder="Your Name"/>
          <br/><br/>
          <button type="submit">Sign In</button>
  </form>

  </div>

</div>
</body>

</html>