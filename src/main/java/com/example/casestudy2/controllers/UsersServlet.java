package com.example.casestudy2.controllers;

import com.example.casestudy2.models.Users;
import com.example.casestudy2.services.FilmsDAO;
import com.example.casestudy2.services.UsersDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UsersServlet", value = "/admin/users")
public class UsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action ==null){
            action="";
        }
        switch (action){
            case "history":
                getHistoryWatch(request,response);
                break;
            default:
                getUsers(request,response);
        }
    }
    private void getUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersDAO usersDAO = new UsersDAO();
        List<Users> listUser = usersDAO.findAllUser();
        request.setAttribute("listUser",listUser);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user.jsp");
        requestDispatcher.forward(request,response);
    }
    private void getHistoryWatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        UsersDAO usersDAO = new UsersDAO();
        List<Users> listHistory = usersDAO.findFilmWatcherByUser(userId);
        Users user = usersDAO.findOneUser(userId);
        request.setAttribute("user",user);
        request.setAttribute("listHistory",listHistory);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("history.jsp");
        requestDispatcher.forward(request,response);
    }

}
