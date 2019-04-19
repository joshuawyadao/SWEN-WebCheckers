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
        <h2> Waiting to Play: </h2>
            <#if hasPlayers>
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
            <#else>
                <h3> No Players are currently waiting to play a game. </h3>
            </#if>
        <h2> Currently Playing: </h2>
            <#if hasCurrentGames>
                <#list currentGames as game>
                    <#if game.arePlayersInGame()>
                        <h3> Game: ${game.getRedPlayer().name} Vs. ${game.getWhitePlayer().name} </h3>

                        <form action="./spectator/game" method="GET">
                            <h2> Red Player: ${game.getRedPlayer().name}
                            <input type="hidden" name="gameID" value="${game.getGameId()}">
                            <input type="hidden" name="isRed" value="true">
                            <button type="submit">View Perspective</button>
                            </h2>
                        </form>

                        <form action="./spectator/game" method="GET">
                            <h2> White Player: ${game.getWhitePlayer().name}
                            <input type="hidden" name="gameID" value="${game.getGameId()}">
                            <input type="hidden" name="isRed" value="false">
                            <button type="submit">View Perspective</button>
                            </h2>
                        </form>
                        </br>
                    </#if>
                </#list>
            <#else>
                <h3> No Games are currently being played. </h3>
            </#if>
    <#else>
        <h3> ${numOfPlayersMsg} </h3>
    </#if>

  </div>

</div>
</body>

</html>
