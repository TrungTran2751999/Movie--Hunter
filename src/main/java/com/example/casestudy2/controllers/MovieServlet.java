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
import java.util.Date;
import java.util.List;

@WebServlet(name = "MovieServlet", value = "/admin/movie")
@MultipartConfig(fileSizeThreshold = 1024*1024*2, maxFileSize = 1024*1024*10, maxRequestSize = 1024*1024*50)
public class MovieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(MoreDAO.checkLogin(request,response)){
            String action = request.getParameter("action");
            if(action==null){
                action = "";
            }
            switch (action){
                case "create":
                    directCreateMovie(request,response);
                    break;
                case "edit":
                    directEditMovie(request,response);
                    break;
                case "delete":{
                    deleteMovie(request,response);
                    break;
                }
                default:
                    showMovie(request,response);
            }
        }else{
            response.sendRedirect("/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null){
            action = "";
        }
        switch (action){
            case "create":
                createMovie(request,response);
                break;
            case "edit":
                editMovie(request,response);
                break;
            default:
                showMovie(request,response);
        }
    }
    private void showMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = null;
        try {
            FilmsDAO filmsDAO = new FilmsDAO();
            List<Films> listFilms = filmsDAO.findListFilmByMovie();
            request.setAttribute("listFilm",listFilms);
            requestDispatcher = request.getRequestDispatcher("movie.jsp");
        }catch (Exception e){
            requestDispatcher = request.getRequestDispatcher("404.jsp");
        }
        requestDispatcher.forward(request,response);
    }
    private void directCreateMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("create-movie.jsp");
        requestDispatcher.forward(request,response);
    }
    private void createMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FilmsDAO filmsDAO = new FilmsDAO();
        String link = request.getParameter("link");
        String nameMovie = request.getParameter("nameMovie");
        String imageName = "movie"+nameMovie+"-"+(filmsDAO.getMaxId()+1);
        String createAt = dateFormat.format(new Date());
        String updateAt = dateFormat.format(new Date());
        int type = 2;
        int views= 0;
        String linkImage = MoreDAO.createImage(request,response,"image",imageName);
        if(!linkImage.equals("")){
            Films films = new Films(nameMovie, linkImage, type, views, createAt, updateAt, link);
            filmsDAO.addFilms(films);
            filmsDAO.insertMovie(films);
            request.setAttribute("message","Add series "+nameMovie+" successfully");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("create-movie.jsp");
            requestDispatcher.forward(request,response);
        }else{
            request.setAttribute("message","image must be .jpg, .png, .jpeg");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("creat-movie.jsp");
            requestDispatcher.forward(request,response);
        }
    }
    private void directEditMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int filmId = Integer.parseInt(request.getParameter("filmId"));
        FilmsDAO filmsDAO = new FilmsDAO();
        Films films = filmsDAO.selectMovie(filmId);
        request.setAttribute("movie", films);
        request.setAttribute("image", films.getImage().split("/")[3]);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit-movie.jsp");
        requestDispatcher.forward(request,response);
    }
    private void editMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int filmId = Integer.parseInt(request.getParameter("filmId"));
        String nameMovie = request.getParameter("nameMovie");
        String link = request.getParameter("link");
        Part image = request.getPart("image");
        FilmsDAO filmsDAO = new FilmsDAO();

        Films films = filmsDAO.selectMovie(filmId);
        String nameImage = films.getImage().split("/")[3].split("\\.")[0];
        String responseImage = "";
        if(image.getSubmittedFileName().equals("")){
            responseImage = films.getImage();
            Films newFilm = new Films(filmId, nameMovie, link, responseImage);
            filmsDAO.editMovie(newFilm);

            request.setAttribute("message","Edit successfully");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit-movie.jsp");
            requestDispatcher.forward(request,response);
        }else{
            responseImage = MoreDAO.createImage(request,response,"image", nameImage);
            if(!responseImage.equals("")){
                Films newFilm = new Films(filmId, nameMovie, link, responseImage);
                filmsDAO.editMovie(newFilm);

                request.setAttribute("message","Edit successfully");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit-movie.jsp");
                requestDispatcher.forward(request,response);
            }else{
                request.setAttribute("message","image must be .jpg, png, jpeg");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit-movie.jsp");
                requestDispatcher.forward(request,response);
            }
        }
    }
    private void deleteMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("filmId"));
        String name = request.getParameter("name");
        MoreDAO.deleteImage(request,response,name.split("/")[3]);
        FilmsDAO filmsDAO = new FilmsDAO();
        filmsDAO.deleteMovie(id);
        request.setAttribute("message","Delete success");
        request.setAttribute("listFilm",filmsDAO.findListFilmByMovie());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("movie.jsp");
        requestDispatcher.forward(request,response);

    }
}
