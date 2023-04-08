package com.example.casestudy2.services;

import com.example.casestudy2.models.Users;

import java.util.List;

public interface IUsersDAO {
    void addUser(Users users);

    Users findOneUser(int id);

    List<Users> findAllUser();

    List<Users> findFilmWatcherByUser(int userId);

}
