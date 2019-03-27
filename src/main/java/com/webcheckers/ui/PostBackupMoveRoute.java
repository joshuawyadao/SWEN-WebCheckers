package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import spark.*;

public class PostBackupMoveRoute implements Route {

    public PostBackupMoveRoute(final PlayerLobby playerLobby, final TemplateEngine templateEngine ) {

    }

    @Override
    public Object handle(Request request, Response response) {
        return true;
    }

}
