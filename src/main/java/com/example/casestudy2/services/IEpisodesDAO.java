package com.example.casestudy2.services;
import com.example.casestudy2.models.Episodes;

import java.util.List;

public interface IEpisodesDAO {
    void addEpisode(Episodes episodes);

    void removeEpisode(int episodeId);

    List<Episodes> findAllEpisode(int filmId);

    Episodes findOneEpisode(int episodeId);
    int getMaxIdEpisode();
    void addEpisodeByFilmId(Episodes episodes, int filmsId);
    boolean editEpisode(Episodes episodes);
    boolean deleteEpisode(int id);
}