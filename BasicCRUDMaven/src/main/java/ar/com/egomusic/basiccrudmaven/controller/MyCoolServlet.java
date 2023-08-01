package ar.com.egomusic.basiccrudmaven.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class MyCoolServlet extends HttpServlet {

    protected String errorForm = null;
    protected String displayForm = null;
    protected String successForm = null;
    protected String createServlet = null;
    protected String deleteServlet = null;
    protected String updateServlet = null;
    protected String readServlet = null;
    protected String persistenceMechanism = null;

    protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected void gotoNamedResource(String address, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getNamedDispatcher(address);
        dispatcher.forward(request, response);
    }

    protected void gotoURL(String address, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    public void init() {
        ServletConfig config = getServletConfig();
        ServletContext context = config.getServletContext();
        displayForm = context.getInitParameter("displayForm");
        errorForm = context.getInitParameter("errorForm");
        successForm = context.getInitParameter("successForm");
        createServlet = context.getInitParameter("createServlet");
        deleteServlet = context.getInitParameter("deleteServlet");
        readServlet = context.getInitParameter("readServlet");
        updateServlet = context.getInitParameter("updateServlet");
        persistenceMechanism = context.getInitParameter("persistenceMechanism");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
