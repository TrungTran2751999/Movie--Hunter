package com.example.casestudy2.services;

import com.example.casestudy2.models.Users;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends ConnectDAO implements IUsersDAO {
    private static String SELECT_ALL_USER = "SELECT * FROM USERS";
    private static String SELECT_USER_BY_ID = "SELECT * FROM USERS WHERE ID = ?";
    private static String SELECT_FILM_WATCH_BY_ID = "{CALL film_watch_by_user(?)}";
    @Override
    public void addUser(Users users) {

    }

    @Override
    public Users findOneUser(int id) {
        Users users = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                String email = rs.getString("email");
                String createAt = rs.getString("create_at");
                users = new Users(id, name, email, createAt);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<Users> findAllUser() {
        List<Users> listUsers = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String createAt = rs.getString("create_at");
                listUsers.add(new Users(id, name, email, createAt));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listUsers;
    }

    @Override
    public List<Users> findFilmWatcherByUser(int userId) {
        List<Users> listHistory = new ArrayList<>();
        try(Connection connection = getConnection();
            CallableStatement callableStatement = connection.prepareCall(SELECT_FILM_WATCH_BY_ID)){
            callableStatement.setInt(1,userId);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()){
                int movieId = rs.getInt("movie_id");
                String movieName = rs.getString("movie_name");
                int episodeId = rs.getInt("episode_id");
                String episodeName = rs.getString("name_episode");
                String timeWatch = rs.getString("time");
                listHistory.add(new Users(episodeId, movieId, episodeName, movieName,timeWatch));
                System.out.println(new Users(episodeId, movieId, episodeName, movieName,timeWatch));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listHistory;
    }
}
