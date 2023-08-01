package ar.com.egomusic.basiccrudmaven.controller;

import ar.com.egomusic.basiccrudmaven.model.Friend;
import ar.com.egomusic.basiccrudmaven.persistence.FriendDAO;
import ar.com.egomusic.basiccrudmaven.persistence.FriendPersistFactory;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class ReadServlet extends MyCoolServlet {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FriendDAO persistenceManager = FriendPersistFactory.getFriendDAO(persistenceMechanism);
        String name = request.getParameter("name");
        Friend friend = (Friend) persistenceManager.readFriend(name);
        if (friend != null) {
            request.setAttribute("message", "tem as seguintes informações armazenadas:");
            request.setAttribute("friend", friend);
            gotoURL(displayForm, request, response);
        } else {
            gotoURL(errorForm, request, response);
        }
    }
}
