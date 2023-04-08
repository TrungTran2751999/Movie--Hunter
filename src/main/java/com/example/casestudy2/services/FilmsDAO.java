package com.example.casestudy2.services;
import com.example.casestudy2.models.Films;
import javafx.scene.input.DataFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilmsDAO implements IFilmsDAO {
    private ConnectDAO connectDAO;
    private static String INSERT_FILMS = "INSERT INTO FILMS(ID, NAME, IMAGE, TYPE, VIEW, CREATE_AT, UPDATE_AT) VALUES (?,?,?,?,?,?,?)";
    private static String SELECT_MAX_ID = "SELECT MAX(ID) FROM FILMS";
    private static String REMOVE_FILMS = "DELETE FROM FILMS WHERE ID = ?";
    private static String SELECT_ALL_FILMS = "SELECT films.id, films.name, films.image, films.view, DATE_FORMAT(films.create_at,'%e-%M-%Y %H:%i:%s') as create_at, DATE_FORMAT(films.update_at,'%e-%M-%Y %H:%i:%s') as update_at, `type`.`name`as type FROM films join `type` on films.type = type.id ORDER BY update_at DESC";
    private static String SELECT_FILMS_BY_ID = "SELECT * FROM FILMS WHERE ID = ?";
    private static String SELECT_FILMS_BY_CATEGORY = "SELECT FILMS.* , SERIES.CATEGORY_ID AS CATEGORY FROM FILMS JOIN SERIES ON FILMS.ID = SERIES.FILMS_ID WHERE SERIES.CATEGORY_ID = ? ORDER BY FILMS.UPDATE_AT DESC";
    private static String SELECT_NAME_CATEGORY = "SELECT NAME FROM CATEGORY WHERE ID = ?";
    private static String SELECT_FILMS_BY_MOVIE = "SELECT FILMS.ID, FILMS.NAME, FILMS.IMAGE, MOVIES.LINK, FILMS.CREATE_AT, FILMS.UPDATE_AT, FILMS.VIEW FROM FILMS JOIN MOVIES ON FILMS.ID = MOVIES.FILMS_ID ORDER BY UPDATE_AT DESC;";
    private static String SELECT_FILMS_BY_MOVIE_ID = "SELECT FILMS.NAME FROM FILMS JOIN MOVIES ON FILMS.ID = MOVIES.FILMS_ID WHERE MOVIES.ID = ?";
    private static String SELECT_FILMS_BY_EPISODE_ID = "SELECT EPISODES.NAME_EPISODE FROM EPISODES WHERE EPISODES.ID = ?";
    private static String INSERT_FILMS_BY_CATEGORY = "INSERT SERIES(ID, FILMS_ID, CATEGORY_ID) VALUES (?,?,?)";
    private static String SELECT_MAX_CATEGORY = "SELECT MAX(ID) FROM SERIES";
    private static String UPDATE_UPDATE_AT_FILM = "UPDATE FILMS SET UPDATE_AT = ? WHERE ID = ?";
    private static String INSERT_MOVIE = "INSERT INTO MOVIES(ID, LINK, FILMS_ID) VALUES (?,?,?)";
    private static String SELECT_MAX_MOVIE = "SELECT MAX(ID) FROM MOVIES";
    private static String UPDATE_MOVIE_AT_FILMS = "UPDATE FILMS SET NAME = ?, IMAGE = ?, UPDATE_AT = ? WHERE ID = ?";
    private static String UPDATE_MOVIE_AT_MOVIES = "UPDATE MOVIES SET LINK = ? WHERE FILMS_ID = ?";
    private static String SELECT_MOVIE_AT_FILMS = "SELECT * FROM FILMS WHERE ID = ?";
    private static String SELECT_MOVIE_AT_MOVIES = "SELECT * FROM MOVIES WHERE FILMS_ID = ?";
    private static String DELETE_MOVIE_AT_FILMS_AT_ID = "DELETE FROM FILMS WHERE ID = ?";
    private static String DELETE_MOVIE_AT_MOVIES_AT_FILMS_ID = "DELETE FROM MOVIES WHERE FILMS_ID = ?";
    private static String SEARCH_FILM = "SELECT * FROM FILMS WHERE NAME LIKE ";
    private static String SELECT_FILM_LIMIT = "SELECT * FROM FILMS ORDER BY UPDATE_AT DESC LIMIT ?,?";
    private static String UPDATE_SERIES = "UPDATE SERIES SET NAME=?, IMAGE=?, UPDATE_AT=? WHERE ID=?";
    private static String UPDATE_VIEW_MOVIE = "UPDATE FILMS SET VIEW = ? WHERE ID = ?";
    private static String UPDATE_VIEW_EPISODE = "UPDATE EPISODES SET VIEW=? WHERE ID=?";
    private static String DELETE_ALL_EPISODE_BY_FILM_ID = "DELETE FROM EPISODES WHERE FILMS_ID=?";
    private static String DELETE_SERIES = "DELETE FROM SERIES WHERE FILMS_ID = ?";
    private static String DELETE_SERIES_AT_FILMS = "DELETE FROM FILMS WHERE ID = ?";
    public FilmsDAO() {
        connectDAO = new ConnectDAO();
    }
    public int getMaxId() {
        int max = 0;
        try (Connection connection = connectDAO.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAX_ID)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                max = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return max;
    }

    @Override
    public void addFilms(Films film) {
        try (Connection connection = connectDAO.getConnection();
             PreparedStatement preparedStatementFilms = connection.prepareStatement(INSERT_FILMS)) {
            preparedStatementFilms.setInt(1, getMaxId() + 1);
            preparedStatementFilms.setString(2, film.getName());
            preparedStatementFilms.setString(3, film.getImage());
            preparedStatementFilms.setInt(4, film.getTypeId());
            preparedStatementFilms.setLong(5, film.getViews());
            preparedStatementFilms.setString(6, film.getCreateAt());
            preparedStatementFilms.setString(7, film.getUpdateAt());
            preparedStatementFilms.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFilmsByCategory(int filmId, int categoryId) {
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatementInsert = connection.prepareStatement(INSERT_FILMS_BY_CATEGORY);
            PreparedStatement preparedStatementMaxId = connection.prepareStatement(SELECT_MAX_CATEGORY)) {

            ResultSet rs = preparedStatementMaxId.executeQuery();
            int max = 0;
            while (rs.next()){
                max = rs.getInt(1);
            }
            preparedStatementInsert.setInt(1,max+1);
            preparedStatementInsert.setInt(2, filmId);
            preparedStatementInsert.setInt(3,categoryId);
            preparedStatementInsert.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeFilms(int id) {
        try (Connection connection = connectDAO.getConnection();
             PreparedStatement preparedStatementFilms = connection.prepareStatement(REMOVE_FILMS)) {
            preparedStatementFilms.setInt(1, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Films> findAllFilms() {
        List<Films> listFilms = new ArrayList<>();
        try (Connection connection = connectDAO.getConnection();
             PreparedStatement preparedStatementFilms = connection.prepareStatement(SELECT_ALL_FILMS)) {
            ResultSet rs = preparedStatementFilms.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                long view = rs.getLong("view");
                String type = rs.getString("type");
                String createAt = rs.getString("create_at");
                String updateAt = rs.getString("update_at");
                listFilms.add(new Films(id, name, image, type, view, createAt, updateAt));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listFilms;
    }

    @Override
    public Films findOneFilmById(int id) {
        Films film = null;
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatementFilms = connection.prepareStatement(SELECT_FILMS_BY_ID);) {
            preparedStatementFilms.setInt(1,id);
            ResultSet rs = preparedStatementFilms.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                String image = rs.getString("image");
                String type = rs.getString("type");
                long view = rs.getLong("view");
                String createAt = rs.getString("create_at");
                String updateAt = rs.getString("update_at");
                film = new Films(id, name, image, type, view, createAt, updateAt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return film;
    }
    @Override
    public List<Films> findListFilmByCategory(int category_id) {
        List<Films> listFilms = new ArrayList<>();
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatementFilms = connection.prepareStatement(SELECT_FILMS_BY_CATEGORY)) {
            preparedStatementFilms.setInt(1,category_id);
            ResultSet rs = preparedStatementFilms.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                String type = rs.getString("type");
                long view = rs.getLong("view");
                int categoryId = rs.getInt("category");
                String createAt = rs.getString("create_at");
                String updateAt = rs.getString("update_at");
                listFilms.add(new Films(id, name, image, type, view, createAt, updateAt, categoryId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listFilms;
    }

    @Override
    public List<Films> findListFilmByMovie() {
        List<Films> listFilms = new ArrayList<>();
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatementFilms = connection.prepareStatement(SELECT_FILMS_BY_MOVIE);) {
            ResultSet rs = preparedStatementFilms.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                String link = rs.getString("link");
                long view = rs.getLong("view");
                String createAt = rs.getString("create_at");
                String updateAt = rs.getString("update_at");
                listFilms.add(new Films(id, name, image, view, link, createAt, updateAt));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(listFilms.get(0));
        return listFilms;
    }

    @Override
    public String findNameCategory(int id) {
        String name = "";
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NAME_CATEGORY)) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                name = rs.getString(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public String findNameMovieByMovieId(int movieId) {
        String name = null;
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FILMS_BY_MOVIE_ID)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                name = rs.getString(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public String findNameEpisodeByEpisodeId(int episodeId) {
        String name = null;
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FILMS_BY_EPISODE_ID)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                name = rs.getString(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public void updateUpdateAtFilm(String updateAt, int filmId) {
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_UPDATE_AT_FILM)){
            System.out.println(preparedStatement);
            preparedStatement.setString(1, updateAt);
            preparedStatement.setInt(2, filmId);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertMovie(Films films) {
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatementSelectMaxId = connection.prepareStatement(SELECT_MAX_MOVIE);
            PreparedStatement preparedStatementInsertMovie = connection.prepareStatement(INSERT_MOVIE)){

            connection.setAutoCommit(false);
            int maxId = 0;
            ResultSet rs = preparedStatementSelectMaxId.executeQuery();
            while(rs.next()){
                maxId = rs.getInt(1);
            }

            preparedStatementInsertMovie.setInt(1,maxId+1);
            preparedStatementInsertMovie.setString(2, films.getLink());
            preparedStatementInsertMovie.setInt(3,  getMaxId());
            preparedStatementInsertMovie.executeUpdate();

            connection.commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void editMovie(Films films) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        String updateAt = dateFormat.format(new Date());
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatementUpdateMovieAtFilms = connection.prepareStatement(UPDATE_MOVIE_AT_FILMS);
            PreparedStatement preparedStatementUpdateMovieAtMovies = connection.prepareStatement(UPDATE_MOVIE_AT_MOVIES)){

            preparedStatementUpdateMovieAtFilms.setString(1, films.getName());
            preparedStatementUpdateMovieAtFilms.setString(2, films.getImage());
            preparedStatementUpdateMovieAtFilms.setString(3, updateAt);
            preparedStatementUpdateMovieAtFilms.setInt(4, films.getId());
            preparedStatementUpdateMovieAtFilms.executeUpdate();

            preparedStatementUpdateMovieAtMovies.setString(1, films.getLink());
            preparedStatementUpdateMovieAtMovies.setInt(2, films.getId());
            preparedStatementUpdateMovieAtMovies.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Films selectMovie(int filmsId) {
        Films films = null;
        try (Connection connection = connectDAO.getConnection();
             PreparedStatement preparedStatementMovieAtFilms = connection.prepareStatement(SELECT_MOVIE_AT_FILMS);
             PreparedStatement preparedStatementMovieAtMovie = connection.prepareStatement(SELECT_MOVIE_AT_MOVIES);
             ) {

             preparedStatementMovieAtFilms.setInt(1, filmsId);
             preparedStatementMovieAtMovie.setInt(1,filmsId);

             ResultSet rsMovieAtFilms = preparedStatementMovieAtFilms.executeQuery();
             ResultSet rsMovieAtMovies =preparedStatementMovieAtMovie.executeQuery();

             int idFilms = 0;
             String nameMovie = null;
             String image = null;
             String link = null;
             while (rsMovieAtFilms.next()){
                 idFilms = rsMovieAtFilms.getInt("id");
                 nameMovie = rsMovieAtFilms.getString("name");
                 image = rsMovieAtFilms.getString("image");
             }
             while (rsMovieAtMovies.next()){
                 link = rsMovieAtMovies.getString("link");
             }
             films = new Films(idFilms, nameMovie, link, image);
             return films;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
    }

    @Override
    public void deleteMovie(int id) {
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatementDeleteMovieFromFilms = connection.prepareStatement(DELETE_MOVIE_AT_FILMS_AT_ID);
            PreparedStatement preparedStatementDeleteMovieFromMovies = connection.prepareStatement(DELETE_MOVIE_AT_MOVIES_AT_FILMS_ID)){
            preparedStatementDeleteMovieFromMovies.setInt(1, id);
            preparedStatementDeleteMovieFromFilms.setInt(1,id);

            preparedStatementDeleteMovieFromMovies.executeUpdate();
            preparedStatementDeleteMovieFromFilms.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Films> searchFilm(String query) {
        List<Films> listFilms= new ArrayList<>();
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_FILM + query)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                long view = rs.getLong("view");
                String type = rs.getString("type");
                String createAt = rs.getString("create_at");
                String updateAt = rs.getString("update_at");
                listFilms.add(new Films(id, name, image, type, view, createAt, updateAt));
            }
            return listFilms;
        }catch (Exception e){
            e.printStackTrace();
        }
        return listFilms;
    }

    @Override
    public List<Films> paginateFilm(int start,int end) {
        List<Films> listFilms = new ArrayList<>();
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FILM_LIMIT)){
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2,end);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                long view = rs.getLong("view");
                String type = rs.getString("type");
                String createAt = rs.getString("create_at");
                String updateAt = rs.getString("update_at");
                listFilms.add(new Films(id, name, image, type, view, createAt, updateAt));
            }
            return listFilms;
        }catch (Exception e){
            e.printStackTrace();
        }
        return listFilms;
    }


    @Override
    public void editSeries(Films series) {
        DateFormat dateFormat = new SimpleDateFormat("YYYY:MM:dd HH:mm:ss");
        String updateAt = dateFormat.format(new Date());
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERIES)){
            preparedStatement.setString(1, series.getName());
            preparedStatement.setString(2, series.getImage());
            preparedStatement.setString(3, updateAt);
            preparedStatement.setInt(4, series.getId());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateViewMovie(int id, int view) {
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VIEW_MOVIE)){
           preparedStatement.setInt(1, view);
           preparedStatement.setInt(2,id);
           preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateViewEpisode(int id, int view) {
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VIEW_EPISODE)){
            preparedStatement.setInt(1, view);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSeries(int id) {
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatementDeleteAllEpisode = connection.prepareStatement(DELETE_ALL_EPISODE_BY_FILM_ID);
            PreparedStatement preparedStatementDeleteSeriesAtSeries = connection.prepareStatement(DELETE_SERIES);
            PreparedStatement preparedStatementDeleteSeriesAtFilms = connection.prepareStatement(DELETE_SERIES_AT_FILMS);){

            connection.setAutoCommit(false);

            preparedStatementDeleteAllEpisode.setInt(1,id);
            preparedStatementDeleteSeriesAtSeries.setInt(1,id);
            preparedStatementDeleteSeriesAtFilms.setInt(1,id);

            preparedStatementDeleteAllEpisode.executeUpdate();
            preparedStatementDeleteSeriesAtSeries.executeUpdate();
            preparedStatementDeleteSeriesAtFilms.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
