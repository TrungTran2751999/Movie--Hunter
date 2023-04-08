package com.example.casestudy2.controllers;

import com.example.casestudy2.models.Episodes;
import com.example.casestudy2.models.Films;
import com.example.casestudy2.services.EpisodesDAO;
import com.example.casestudy2.services.FilmsDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PlayServlet", value = "/play")
public class PlayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        FilmsDAO filmsDAO = new FilmsDAO();
        Films film = filmsDAO.findOneFilmById(id);
        EpisodesDAO episodesDAO = new EpisodesDAO();
        request.setAttribute("film",film);
        if(film.getType().equals("2")){
            Films movie = filmsDAO.selectMovie(id);
            request.setAttribute("movie",movie);
            filmsDAO.updateViewMovie(id, (int) film.getViews()+1);
        }else if(film.getType().equals("1")){
            List<Episodes> listEpisode = episodesDAO.findAllEpisode(id);
            request.setAttribute("listEpisode",listEpisode);
            if(request.getParameter("episode") == null){
                Episodes firstEpisode = listEpisode.get(0);
                filmsDAO.updateViewEpisode(firstEpisode.getIdEpisodes(),firstEpisode.getViews()+1);
                filmsDAO.updateViewMovie(film.getId(), (int) film.getViews()+1);
                request.setAttribute("firstEpisode",firstEpisode);
            }else{
                int episode = Integer.parseInt(request.getParameter("episode"));
                for(Episodes episodes: listEpisode){
                    if(episodes.getIdEpisodes() == episode){
                        filmsDAO.updateViewEpisode(episodes.getIdEpisodes(),episodes.getViews()+1);
                        filmsDAO.updateViewMovie(film.getId(), (int) film.getViews()+1);
                        request.setAttribute("firstEpisode",episodes);
                        request.setAttribute("episodeIdd", episode);
                    }
                }
            }

        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("play.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
