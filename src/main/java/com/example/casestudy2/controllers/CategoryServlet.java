package com.example.casestudy2.controllers;

import com.example.casestudy2.models.Films;
import com.example.casestudy2.services.FilmsDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("categoryId") != null){
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            FilmsDAO filmsDAO = new FilmsDAO();
            List<Films> listFilm = filmsDAO.findListFilmByCategory(categoryId);
            request.setAttribute("films",listFilm);
            request.setAttribute("categoryId",categoryId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("category.jsp");
            requestDispatcher.forward(request,response);
        }else{
            FilmsDAO filmsDAO = new FilmsDAO();
            List<Films> listFilm = filmsDAO.findListFilmByMovie();
            request.setAttribute("films",listFilm);
            request.setAttribute("movie","success");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("category.jsp");
            requestDispatcher.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
