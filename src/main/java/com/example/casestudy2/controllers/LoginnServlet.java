package com.example.casestudy2.controllers;

import com.example.casestudy2.services.MoreDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginnServlet", value = "/admin/login")
public class LoginnServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        RequestDispatcher requestDispatcher = null;
        switch (action){
            case "logout":
                logoutAdmin(request,response);
                break;
            default:
                if(!MoreDAO.checkLogin(request,response)){
                    requestDispatcher = request.getRequestDispatcher("login.jsp");
                }else{
                    requestDispatcher = request.getRequestDispatcher("/admin");
                }
                requestDispatcher.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "logout":
                logoutAdmin(request,response);
                break;
            default:
                loginAdmin(request,response);
        }
    }
    private void loginAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("user");
        String pass = request.getParameter("password");
        if (name.equals("admin") && pass.equals("admin")) {
            Cookie cookieName = new Cookie("username", name);
            Cookie cookiePass = new Cookie("password", pass);
            response.addCookie(cookieName);
            response.addCookie(cookiePass);
            response.sendRedirect("/admin");
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request,response);
        }
    }
    private void logoutAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                Cookie cookie1 = new Cookie("username", "");
                cookie1.setMaxAge(0);
                response.addCookie(cookie1);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
                requestDispatcher.forward(request,response);
            }
        }
    }
}
