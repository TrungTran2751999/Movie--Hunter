package com.example.casestudy2.controllers;

import com.example.casestudy2.models.Films;
import com.example.casestudy2.services.FilmsDAO;
import com.example.casestudy2.services.MoreDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServerlet", urlPatterns = {"/admin"})
public class HomeServerlet extends HttpServlet {
    FilmsDAO filmsDAO = new FilmsDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(MoreDAO.checkLogin(request,response)){
            RequestDispatcher requestDispatcher = null;
            if(response.getStatus() == 404){
                requestDispatcher = request.getRequestDispatcher("admin/404.jsp");
            }else{
                List<Films> films = filmsDAO.findAllFilms();
                request.setAttribute("films", films);
                requestDispatcher = request.getRequestDispatcher("admin/index.jsp");
            }
            requestDispatcher.forward(request,response);
        }else{
            response.sendRedirect("/error");
        }
    }
}
