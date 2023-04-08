package com.example.casestudy2.controllers;

import com.example.casestudy2.models.Films;
import com.example.casestudy2.services.FilmsDAO;
import com.example.casestudy2.services.MoreDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MainServlet", value = "")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int page = 1;
            if(request.getParameter("page") != null){
                page = Integer.parseInt(request.getParameter("page"));
            }
            int max = 15;
            FilmsDAO filmsDAO = new FilmsDAO();
            List<Films> listFilm = filmsDAO.findAllFilms();
            List<Films> films = filmsDAO.paginateFilm((page-1)*max, max);
            request.setAttribute("films", films);
            request.setAttribute("page",page);
            request.setAttribute("max", Math.ceil(listFilm.size()/max)+1);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
