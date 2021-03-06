<!DOCTYPE html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <title>${title} | Web Checkers</title>
  <link rel="stylesheet" href="/css/style.css">
  <link rel="stylesheet" href="/css/game.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script>
  </script>
</head>
<body>
  <div class="page">
    <h1>Web Checkers | Game View</h1>

    <#include "nav-bar.ftl">

    <div class="body">

      <div id="help_text" class="INFO"></div>

      </div>

    </div>
  </div>

  <audio id="audio" src="http://www.soundjay.com/button/beep-07.mp3" autostart="false" ></audio>

  <script data-main="/js/game/index" src="/js/require.js"></script>

</body>
</html>
