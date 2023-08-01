package ar.com.egomusic.basiccrudmaven.controller;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

public class FrontController extends MyCoolServlet {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE,
                    "Não foi possível definir a codificação", ex);
        }
        String form = request.getParameter("form");
        if (form == null || (persistenceMechanism != null
                && persistenceMechanism.equals("none"))) {
            gotoURL(errorForm, request, response);
        } else if (form.equals("errorForm")) {
            gotoNamedResource(errorForm, request, response);
        } else if (form.equals("createServlet")) {
            gotoNamedResource(createServlet, request, response);
        } else if (form.equals("updateServlet")) {
            gotoNamedResource(updateServlet, request, response);
        } else if (form.equals("deleteServlet")) {
            gotoNamedResource(deleteServlet, request, response);
        } else if (form.equals("readServlet")) {
            gotoNamedResource(readServlet, request, response);
        } else {
            gotoURL(errorForm, request, response);
        }
    }
}
