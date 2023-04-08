package com.example.casestudy2.controllers;

import com.example.casestudy2.models.Films;
import com.example.casestudy2.services.FilmsDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", value = "/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = null;
        String query = request.getParameter("query");
        if(!query.equals("") || query != null){
            FilmsDAO filmsDAO = new FilmsDAO();
            List<Films> listFilm = filmsDAO.searchFilm("'%"+query+"%'");
            request.setAttribute("query", query);
            request.setAttribute("films",listFilm);
            requestDispatcher = request.getRequestDispatcher("search.jsp");
        }else{
            requestDispatcher = request.getRequestDispatcher("404.jsp");
        }
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
