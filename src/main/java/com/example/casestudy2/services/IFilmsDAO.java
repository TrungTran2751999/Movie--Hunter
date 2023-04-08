package com.example.casestudy2.services;

import com.example.casestudy2.models.Films;

import java.util.List;

public interface IFilmsDAO {
    void addFilms(Films film);

    void removeFilms(int id);
    void addFilmsByCategory(int filmsId, int categoryId);

    List<Films> findAllFilms();

    Films findOneFilmById(int id);
    List<Films> findListFilmByCategory(int category_id);
    List<Films> findListFilmByMovie();
    String findNameCategory(int id);
    String findNameMovieByMovieId(int movieId);
    String findNameEpisodeByEpisodeId(int episodeId);
    void updateUpdateAtFilm(String updateAt, int filmId);
    void insertMovie(Films films);
    void editMovie(Films films);
    Films selectMovie(int filmsId);
    void deleteMovie(int id);
    List<Films> searchFilm(String query);
    List<Films> paginateFilm(int start,int end);
    void editSeries(Films films);
    void updateViewMovie(int id, int view);
    void updateViewEpisode(int id, int view);
    void deleteSeries(int id);
}
