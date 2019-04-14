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
        <h2> WebChecker Players: </h2>
        <#list players as player>
            <#if !currentUser.equals(player)>
                <form action="./game" method="GET">
                    <h3> ${player.name}'s Game </t>

                    <#if !player.isPlaying()>
                        <input type="hidden" name="opponent" value="${player.name}">
                        <button type="submit">Play Game</button>
                    <#else>
                        <input type="hidden" name="opponent" value="${player.name}">
                        <button type="submit">Spectate Game</button>
                    </#if>

                    </h3>
                </form>
            </#if>
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
