package com.example.casestudy2.controllers;

import com.example.casestudy2.models.Episodes;
import com.example.casestudy2.models.Films;
import com.example.casestudy2.services.EpisodesDAO;
import com.example.casestudy2.services.FilmsDAO;
import com.example.casestudy2.services.MoreDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SeriesServlet", value = "/admin/episode")
public class EpisodeServlet extends HttpServlet {
    private EpisodesDAO episodesDAO;
    private FilmsDAO filmsDAO;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(MoreDAO.checkLogin(request,response)){
            String action = request.getParameter("action");
            if(action == null){
                action = "";
            }
            switch (action){
                case "create":
                    directCreateEpisode(request,response);
                    break;
                case "edit":
                    directEditEpisode(request, response);
                    break;
                case "delete":
                    deleteEpisode(request,response);
                default:
                    showEpisode(request, response);
            }
        }else{
            response.sendRedirect("/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createEpisode(request,response);
                break;
            case "edit":
                updateEditEpisode(request,response);
                break;
            default:
                showEpisode(request, response);
        }
    }
    private void showEpisode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        episodesDAO = new EpisodesDAO();
        filmsDAO = new FilmsDAO();

        Films films = filmsDAO.findOneFilmById(id);
        List<Episodes> listEpisode = episodesDAO.findAllEpisode(id);
        request.setAttribute("nameFilm", films.getName());
        request.setAttribute("listEpisode",listEpisode);
        request.setAttribute("id", id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("episode.jsp");
        requestDispatcher.forward(request,response);
    }
    private void directCreateEpisode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        FilmsDAO filmsDAO = new FilmsDAO();
        Films films = filmsDAO.findOneFilmById(id);
        request.setAttribute("id",id);
        request.setAttribute("name", films.getName());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create-episode.jsp");
        requestDispatcher.forward(request,response);
    }
    private void createEpisode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String link = request.getParameter("link");
        int id = Integer.parseInt(request.getParameter("id"));

        episodesDAO = new EpisodesDAO();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createAt = dateFormat.format(new Date());
        String updateAt = dateFormat.format(new Date());
//        String nameEpisode, String linkEpisode, int views, String createAt, String updateAt
        episodesDAO.addEpisodeByFilmId(new Episodes(name, link, 0, createAt, updateAt), id);
        request.setAttribute("message", "Add episode success");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create-episode.jsp");
        requestDispatcher.forward(request, response);
    }
    private void directEditEpisode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int episodeId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("episodeId",episodeId);
        filmsDAO = new FilmsDAO();
        EpisodesDAO episodesDAO = new EpisodesDAO();
        Episodes episode = episodesDAO.findOneEpisode(episodeId);
        int filmsId = episode.getFilmId();
        Films films = filmsDAO.findOneFilmById(filmsId);
        request.setAttribute("name", films.getName());
        request.setAttribute("episode",episode);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit-episode.jsp");
        requestDispatcher.forward(request,response);
    }
    private void updateEditEpisode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String link = request.getParameter("link");
        boolean success = episodesDAO.editEpisode(new Episodes(id, name, link));
        if(success){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit-episode.jsp");
            request.setAttribute("message","Update success");
            requestDispatcher.forward(request,response);
        }
    }
    private void deleteEpisode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int filmId = Integer.parseInt(request.getParameter("filmId"));
        boolean success = episodesDAO.deleteEpisode(id);
        if(success){
            request.setAttribute("message", "Delete success");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/episode?id="+filmId);
            requestDispatcher.forward(request,response);
        }
    }
}
