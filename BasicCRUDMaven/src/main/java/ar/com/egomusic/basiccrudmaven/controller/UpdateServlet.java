package ar.com.egomusic.basiccrudmaven.controller;

import ar.com.egomusic.basiccrudmaven.model.Friend;
import ar.com.egomusic.basiccrudmaven.persistence.FriendDAO;
import ar.com.egomusic.basiccrudmaven.persistence.FriendPersistFactory;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class UpdateServlet extends CreateServlet {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FriendDAO persistenceManager = FriendPersistFactory.getFriendDAO(persistenceMechanism);
        String formerName = request.getParameter("formerName");
        Friend friend = super.generateFriendFromRequest(request);
        if (persistenceManager.updateFriend(formerName, friend)) {
            request.setAttribute("friend", friend);
            request.setAttribute("message", "foi modificado com sucesso");
            gotoURL(successForm, request, response);
        } else {
            gotoURL(errorForm, request, response);
        }
    }
}
