package com.example.casestudy2.models;

public class Users {
    private int id;
    private String names;
    private String emails;
    private String password;
    private String createAt;
    private int episodeId;
    private int movieId;
    private String episodeName;
    private String movieName;
    private String timeWatch;

    public Users(String names, String emails, String password) {
        this.names = names;
        this.emails = emails;
        this.password = password;
    }

    public Users(int id, String names, String emails, String createAt) {
        this.id = id;
        this.names = names;
        this.emails = emails;
        this.createAt = createAt;
    }

    public Users(int episodeId, int movieId, String episodeName, String movieName, String timeWatch) {
        this.episodeId = episodeId;
        this.movieId = movieId;
        this.episodeName = episodeName;
        this.movieName = movieName;
        this.timeWatch = timeWatch;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTimeWatch() {
        return timeWatch;
    }

    public void setTimeWatch(String timeWatch) {
        this.timeWatch = timeWatch;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", names='" + names + '\'' +
                ", emails='" + emails + '\'' +
                ", password='" + password + '\'' +
                ", createAt='" + createAt + '\'' +
                ", episodeId=" + episodeId +
                ", movieId=" + movieId +
                ", episodeName='" + episodeName + '\'' +
                ", movieName='" + movieName + '\'' +
                ", timeWatch='" + timeWatch + '\'' +
                '}';
    }
}
