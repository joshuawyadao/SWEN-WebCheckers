<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl">

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl">

    <#if currentUser??>
        <h2> Previous WebChecker Games: </h2>

        <#if hasPreviousGames>
            <#list previousGames as game>
                <form action="./replay/game" method="GET">
                    <h3> Game: ${game.getRedPlayer()} Vs. ${game.getWhitePlayer()} </br>
                         Time Ended: ${game.getGameEndTime()} </br>

                    <input type="hidden" name="gameId" value="${game.getGameId()}">
                    <button type="submit">View Game Replay</button>

                    </h3>
                </form>
            </#list>
        <#else>
            <h3> No Games have been played yet... Play one to view it here later on! </h3>
        </#if>
    </#if>

  </div>

</div>
</body>

</html>
