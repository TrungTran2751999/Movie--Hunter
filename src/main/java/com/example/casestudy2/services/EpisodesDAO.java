package com.example.casestudy2.services;

import com.example.casestudy2.models.Episodes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EpisodesDAO implements IEpisodesDAO {
    private static String INSERT_EPISODE_BY_FILM_ID = "INSERT INTO EPISODES(ID, LINK, EPISODE, FILMS_ID, NAME_EPISODE, VIEW, CREATE_AT, UPDATE_AT) VALUES (?,?,?,?,?,?,?,?)";
    private static String SELECT_ALL_EPISODE_BY_FILM_ID = "SELECT * FROM EPISODES WHERE FILMS_ID = ?";
    private static String SELECT_MAX_ID_EPISODE = "SELECT MAX(ID) FROM EPISODES";
    private static String SELECT_MAX_EPISODE_OF_FILMS = "SELECT MAX(EPISODE) FROM EPISODES WHERE FILMS_ID = ?";
    private static String SELECT_EPISODE_BY_ID = "SELECT * FROM EPISODES WHERE ID=?";
    private static String UPDATE_EPISODE_BY_ID = "UPDATE EPISODES SET LINK=?, NAME_EPISODE=?, UPDATE_AT=? WHERE ID=?";
    private static String DELETE_EPISODE_BY_ID = "DELETE FROM EPISODES WHERE ID = ?";
    private ConnectDAO connectDAO;

    public EpisodesDAO(){connectDAO = new ConnectDAO();}
    @Override
    public void addEpisode(Episodes episodes) {
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatementInsert = connection.prepareStatement(INSERT_EPISODE_BY_FILM_ID);
            PreparedStatement preparedStatementMaxEpiosde = connection.prepareStatement(SELECT_MAX_EPISODE_OF_FILMS);
            PreparedStatement preparedStatementMaxIdEpisdose = connection.prepareStatement(SELECT_MAX_ID_EPISODE)){

            FilmsDAO filmsDAO = new FilmsDAO();
            int maxFilmId = filmsDAO.getMaxId();
            preparedStatementMaxEpiosde.setInt(1,maxFilmId);
            ResultSet rs = preparedStatementMaxEpiosde.executeQuery();
            int maxEpisode = 0;
            while (rs.next()){
                maxEpisode = rs.getInt(1);
            }
            ResultSet rsId = preparedStatementMaxIdEpisdose.executeQuery();
            int maxIdEpisode = 0;
            while (rsId.next()){
                maxIdEpisode = rsId.getInt(1);
            }
            preparedStatementInsert.setInt(1,  maxIdEpisode +1);
            preparedStatementInsert.setString(2, episodes.getLinkEpisode());
            preparedStatementInsert.setInt(3, maxEpisode+1);
            preparedStatementInsert.setInt(4, maxFilmId);
            preparedStatementInsert.setString(5, episodes.getNameEpisode());
            preparedStatementInsert.setInt(6, episodes.getViews());
            preparedStatementInsert.setString(7, episodes.getCreateAt());
            preparedStatementInsert.setString(8, episodes.getUpdateAt());
            preparedStatementInsert.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addEpisodeByFilmId(Episodes episodes, int filmsId) {
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatementInsert = connection.prepareStatement(INSERT_EPISODE_BY_FILM_ID);
            PreparedStatement preparedStatementMaxEpiosde = connection.prepareStatement(SELECT_MAX_EPISODE_OF_FILMS);
            PreparedStatement preparedStatementMaxIdEpisdose = connection.prepareStatement(SELECT_MAX_ID_EPISODE)){

            int maxFilmId = filmsId;
            preparedStatementMaxEpiosde.setInt(1,maxFilmId);
            ResultSet rs = preparedStatementMaxEpiosde.executeQuery();
            int maxEpisode = 0;
            while (rs.next()){
                maxEpisode = rs.getInt(1);
            }
            ResultSet rsId = preparedStatementMaxIdEpisdose.executeQuery();
            int maxIdEpisode = 0;
            while (rsId.next()){
                maxIdEpisode = rsId.getInt(1);
            }
            preparedStatementInsert.setInt(1,  maxIdEpisode +1);
            preparedStatementInsert.setString(2, episodes.getLinkEpisode());
            preparedStatementInsert.setInt(3, maxEpisode+1);
            preparedStatementInsert.setInt(4, maxFilmId);
            preparedStatementInsert.setString(5, episodes.getNameEpisode());
            preparedStatementInsert.setInt(6, episodes.getViews());
            preparedStatementInsert.setString(7, episodes.getCreateAt());
            preparedStatementInsert.setString(8, episodes.getUpdateAt());
            preparedStatementInsert.executeUpdate();

            FilmsDAO filmsDAO = new FilmsDAO();
            filmsDAO.updateUpdateAtFilm(episodes.getUpdateAt(), filmsId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeEpisode(int episodeId) {

    }
    @Override
    public List<Episodes> findAllEpisode(int filmId) {
        List<Episodes> listEpisodes = new ArrayList<>();
        try {
            Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EPISODE_BY_FILM_ID);
            preparedStatement.setInt(1, filmId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String link = rs.getString("link");
                int episode = rs.getInt("episode");
                int film_id = rs.getInt("films_id");
                String name_episode = rs.getString("name_episode");
                int view = rs.getInt("view");
                String createAt = rs.getString("create_at");
                String updateAt = rs.getString("update_at");
                listEpisodes.add(new Episodes(id, episode, name_episode, link, view, createAt, updateAt));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listEpisodes;
    }

    @Override
    public Episodes findOneEpisode(int episodeId) {
//        int filmId, String nameEpisode, String linkEpisode, int views, String createAt, String updateAt
        Episodes episodes = null;
        try {
            Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EPISODE_BY_ID);
            preparedStatement.setInt(1, episodeId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int filmsId = rs.getInt("films_id");
                String link = rs.getString("link");
                String nameEpisode = rs.getString("name_episode");
                int episodeNumber = rs.getInt("episode");
                episodes = new Episodes(id, filmsId, nameEpisode, episodeNumber, link);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return episodes;
    }

    @Override
    public int getMaxIdEpisode() {
        int max = 0;
        try {
            Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAX_ID_EPISODE);
            ResultSet rs = preparedStatement.executeQuery();
            max = rs.getInt(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return max;
    }

    @Override
    public boolean editEpisode(Episodes episodes) {
//        int id, int filmId, String nameEpisode, int episodeNumber, String linkEpisode
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateAt = dateFormat.format(new Date());
        boolean success = false;
        try {
            Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EPISODE_BY_ID);
            preparedStatement.setString(1, episodes.getLinkEpisode());
            preparedStatement.setString(2, episodes.getNameEpisode());
            preparedStatement.setString(3, updateAt);
            preparedStatement.setInt(4, episodes.getIdEpisodes());
            success = preparedStatement.executeUpdate() > 0;
            FilmsDAO filmsDAO = new FilmsDAO();
            int filmId = findOneEpisode(episodes.getIdEpisodes()).getFilmId();
            filmsDAO.updateUpdateAtFilm(updateAt, filmId);
            return success;
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean deleteEpisode(int id) {
        boolean success = false;
        try(Connection connection = connectDAO.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EPISODE_BY_ID)) {
            preparedStatement.setInt(1, id);
            success = preparedStatement.executeUpdate() > 0;
            return success;
        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }
}
