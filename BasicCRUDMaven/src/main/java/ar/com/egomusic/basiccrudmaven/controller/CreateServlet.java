package ar.com.egomusic.basiccrudmaven.controller;

import ar.com.egomusic.basiccrudmaven.model.Friend;
import ar.com.egomusic.basiccrudmaven.persistence.FriendDAO;
import ar.com.egomusic.basiccrudmaven.persistence.FriendPersistFactory;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class CreateServlet extends MyCoolServlet {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FriendDAO persistenceManager = FriendPersistFactory.getFriendDAO(persistenceMechanism);
        Friend friend = generateFriendFromRequest(request);
        if (friend != null && persistenceManager.createFriend(friend)) {
            request.setAttribute("friend", friend);
            request.setAttribute("message", "foi criado com sucesso");
            gotoURL(successForm, request, response);
        } else {
            gotoURL(errorForm, request, response);
        }
    }

    Friend generateFriendFromRequest(HttpServletRequest request) throws NumberFormatException {
        Friend friend = new Friend();
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String comments = request.getParameter("comments");
        String ageString = request.getParameter("age").trim();
        String friendshipLevelString = request.getParameter("friendshipLevel");
        boolean valid = valiateName(name) && valiatePhone(phone)
                && valiateAge(ageString) && valiateFriendshipLevel(friendshipLevelString);
        if (!valid) {
            return null;
        }
        int age = Integer.parseInt(ageString);
        int friendshipLevel = Integer.parseInt(friendshipLevelString);
        friend.setName(name);
        friend.setPhone(phone);
        friend.setAddress(address);
        friend.setComments(comments);
        friend.setAge(age);
        friend.setFriendshipLevel(friendshipLevel);
        return friend;
    }

    private boolean valiateName(String name) {
        String namePattern = "[A-Z]([a-zA-ZñÑáéíóúÁÉÍÓÚ ])*";
        return name.matches(namePattern);
    }

    private boolean valiatePhone(String phone) {
        boolean isGoodLength = checkLength(phone, 9, 15);
        return phone.matches("([0-9  +])*") && isGoodLength;
    }

    private boolean valiateAge(String age) {
        boolean isGoodLength = checkLength(age, 1, 3);
        if (age.matches("([0-9])*") && isGoodLength) {
            int ageInt = Integer.valueOf(age);
            return ageInt < 120;
        }
        return false;
    }

    private boolean valiateFriendshipLevel(String friendshipLevel) {
        return friendshipLevel.matches("[0-9]") || friendshipLevel.endsWith("10");
    }

    private boolean checkLength(String data, int minAceptable, int maxAceptable) {
        int length = data.length();
        boolean isGoodLength = length >= minAceptable && length <= maxAceptable;
        return isGoodLength;
    }
}
