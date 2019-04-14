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
        <h2> Waiting to Play </h2>
            <#list players as player>
                <#if !currentUser.equals(player)>
                    <form action="./game" method="GET">
                        <#if !player.isPlaying()>
                            <h3>  ${player.name}'s Game </t>
                            <input type="hidden" name="opponent" value="${player.name}">
                            <button type="submit">Play Game</button>
                            </h3>
                        </#if>
                    </form>
                </#if>
            </#list>
        <h2> Currently Playing </h2>
            <#list currentGames as game>
                <form action="./spectator/game" method="GET">
                    <h3> Game: ${game.getRedPlayer().name} Vs. ${game.getWhitePlayer().name} </t>
                    <input type="hidden" name="gameID" value="${game.getGameId()}">
                    <button type="submit">Spectate Game</button>
                    </h3>
                </form>
            </#list>
    <#else>
        <h3> ${numOfPlayersMsg} </h3>
    </#if>

    <!-- TODO: future content on the Home:
            to start games,
            spectating active games,
            or replay archived games
    -->

  </div>

</div>
</body>

</html>
