package com.example.casestudy2.controllers;

import com.example.casestudy2.models.Episodes;
import com.example.casestudy2.models.Films;
import com.example.casestudy2.services.EpisodesDAO;
import com.example.casestudy2.services.FilmsDAO;
import com.example.casestudy2.services.MoreDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SerieServlet", value = "/admin/series")
@MultipartConfig(fileSizeThreshold = 1024*1024*2, maxFileSize = 1024*1024*10, maxRequestSize = 1024*1024*50)
public class SerieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(MoreDAO.checkLogin(request,response)){
            String action = request.getParameter("action");
            if(action ==null){
                action = "";
            }
            switch (action){
                case "create":
                    response.sendRedirect("create-film.jsp");
                    break;
                case "edit":
                    directEditSeries(request,response);
                    break;
                case "delete":
                    deleteSeries(request,response);
                    break;
                default:
                    showAllSeries(request,response);
            }
        }else{
            response.sendRedirect("/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action ==null){
            action = "";
        }
        switch (action){
            case "create":
                createSeries(request,response);
                break;
            case "edit":
                editSeries(request,response);
                break;
            default:
                showAllSeries(request,response);
        }
    }
    private void showAllSeries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        FilmsDAO filmsDAO = new FilmsDAO();
        List<Films> listFilm = filmsDAO.findListFilmByCategory(id);
        request.setAttribute("nameCategory", filmsDAO.findNameCategory(id));
        request.setAttribute("listFilm", listFilm);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("series.jsp");
        requestDispatcher.forward(request, response);
    }
    private void createSeries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nameSeries = request.getParameter("nameSeries");
        String nameEpiosde = request.getParameter("nameEpisode");
        int category = Integer.parseInt(request.getParameter("category"));
//        String image = request.getParameter("image");
        String link = request.getParameter("link");
        int type = 1;
        int views = 0;
        String createAt = dateFormat.format(new Date());
        String updateAt = dateFormat.format(new Date());
//        String name, String image, int typeId, long views, String createAt, String updateAt
        FilmsDAO filmsDAO = new FilmsDAO();
        String imageName = nameSeries+"-"+(filmsDAO.getMaxId()+1);
        String linkImage = MoreDAO.createImage(request,response,"image",imageName);
        if(!linkImage.equals("")){
            Films films = new Films(nameSeries, linkImage, type, views, createAt, updateAt);
            filmsDAO.addFilms(films);

            Episodes episodes = new Episodes(nameEpiosde, link, views, createAt, updateAt);
            EpisodesDAO episodesDAO = new EpisodesDAO();
            episodesDAO.addEpisode(episodes);

            int filmId = filmsDAO.getMaxId();
            filmsDAO.addFilmsByCategory(filmId, category);
            request.setAttribute("message","Add series "+episodes.getNameEpisode()+" successfully");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("create-film.jsp");
            requestDispatcher.forward(request,response);
        }else{
            request.setAttribute("message","image must be .jpg, .png, .jpeg");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("create-film.jsp");
            requestDispatcher.forward(request,response);
        }

    }
    private void editSeries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Part image = request.getPart("image");
        FilmsDAO filmsDAO = new FilmsDAO();

        Films films = filmsDAO.findOneFilmById(id);
        String nameImage = films.getImage().split("/")[3].split("\\.")[0];
        String newNameImage = "series "+films.getName()+"-"+films.getId();
        String responseImage = "";
        if(image.getSubmittedFileName().equals("")){
            responseImage = films.getImage();
            Films newFilm = new Films(id, name, responseImage);
            filmsDAO.editMovie(newFilm);

            request.setAttribute("message","Edit successfully");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit-movie.jsp");
            requestDispatcher.forward(request,response);
        }else {
            responseImage = MoreDAO.createImage(request, response, "image", newNameImage);
            if (!responseImage.equals("")) {
                Films newFilm = new Films(id, name, responseImage);
                filmsDAO.editMovie(newFilm);

                request.setAttribute("message", "Edit successfully");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit-movie.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("message", "image must be .jpg, png, jpeg");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit-movie.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }
    private void directEditSeries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =Integer.parseInt(request.getParameter("id"));
        FilmsDAO filmsDAO = new FilmsDAO();
        Films film = filmsDAO.findOneFilmById(id);
        request.setAttribute("movie", film);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit-series.jsp");
        requestDispatcher.forward(request,response);
    }
    private void deleteSeries(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int type = Integer.parseInt(request.getParameter("type"));
        FilmsDAO filmsDAO = new FilmsDAO();
        filmsDAO.deleteSeries(id);
        Films films = filmsDAO.findOneFilmById(id);
        response.sendRedirect("/admin/series?id="+type);
    }

}
