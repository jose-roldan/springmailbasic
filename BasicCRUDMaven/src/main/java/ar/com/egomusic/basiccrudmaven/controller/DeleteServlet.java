package ar.com.egomusic.basiccrudmaven.controller;

import ar.com.egomusic.basiccrudmaven.model.Friend;
import ar.com.egomusic.basiccrudmaven.persistence.FriendDAO;
import ar.com.egomusic.basiccrudmaven.persistence.FriendPersistFactory;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class DeleteServlet extends MyCoolServlet {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FriendDAO persistenceManager = FriendPersistFactory.getFriendDAO(persistenceMechanism);
        String name = request.getParameter("name");
        Friend friend = (Friend) persistenceManager.readFriend(name);
        if (friend != null && persistenceManager.deleteFriend(name)) {
            request.setAttribute("friend", friend);
            request.setAttribute("message", "foi deletado com sucesso");
            gotoURL(successForm, request, response);
        } else {
            gotoURL(errorForm, request, response);
        }
    }
}
