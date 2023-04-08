package com.example.casestudy2.models;

public class Episodes {
    private int idEpisodes;
    private int episodeNumber;
    private String nameEpisode;
    private String linkEpisode;
    private int views;
    private int filmId;
    private String createAt;
    private String updateAt;

    public Episodes(String nameEpisode, String linkEpisode, int views, String createAt, String updateAt) {
        this.nameEpisode = nameEpisode;
        this.linkEpisode = linkEpisode;
        this.views = views;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
    public Episodes(int id, int filmId, String nameEpisode, int episodeNumber, String linkEpisode) {
        this.idEpisodes = id;
        this.filmId = filmId;
        this.nameEpisode = nameEpisode;
        this.linkEpisode = linkEpisode;
        this.episodeNumber= episodeNumber;
    }

    public Episodes(int idEpisodes, String nameEpisode, String linkEpisode) {
        this.idEpisodes = idEpisodes;
        this.nameEpisode = nameEpisode;
        this.linkEpisode = linkEpisode;
    }

    public Episodes(int idEpisodes, int episodeNumber, String nameEpisode, String linkEpisode, int views, String createAt, String updateAt) {
        this.idEpisodes = idEpisodes;
        this.episodeNumber = episodeNumber;
        this.nameEpisode = nameEpisode;
        this.linkEpisode = linkEpisode;
        this.views = views;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getIdEpisodes() {
        return idEpisodes;
    }

    public void setIdEpisodes(int idEpisodes) {
        this.idEpisodes = idEpisodes;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getNameEpisode() {
        return nameEpisode;
    }

    public void setNameEpisode(String nameEpisode) {
        this.nameEpisode = nameEpisode;
    }

    public String getLinkEpisode() {
        return linkEpisode;
    }

    public void setLinkEpisode(String linkEpisode) {
        this.linkEpisode = linkEpisode;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {

        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String convertDate(String date){
        String[] dateTime = date.split(" ")[0].split("-");
        String result = dateTime[2]+"-"+dateTime[1]+"-"+dateTime[0]+" "+date.split(" ")[1];
        return result;
    }
    public String getConvertCreateAt(){
        return convertDate(createAt);
    }
    public String getConvertUpdateAt(){
        return convertDate(updateAt);
    }

}
