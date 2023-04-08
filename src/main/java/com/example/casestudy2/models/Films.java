package com.example.casestudy2.models;

public class Films {
    private int id;
    private String name;
    private String image;
    private String type;
    private long views;
    private String createAt;
    private String updateAt;
    private String link;
    private int typeId;
    private int categoryId;

    public Films(int id, String name, String image, String type, long views, String createAt, String updateAt) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.type = type;
        this.views = views;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Films(String name, String image, int typeId, long views, String createAt, String updateAt) {
        this.name = name;
        this.image = image;
        this.typeId = typeId;
        this.views = views;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
    public Films(String name, String image, int typeId, long views, String createAt, String updateAt, String link) {
        this.name = name;
        this.image = image;
        this.typeId = typeId;
        this.views = views;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.link = link;
    }
    public Films(int id, String name, String image, long views, String createAt, String updateAt) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.views = views;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Films(int id, String name, String image, long views, String link, String createAt, String updateAt) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.views = views;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.link = link;
    }

    public Films(int id, String name, String link, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.link = link;
    }

    public Films(int id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }

    public Films(int id, String name, String image, String type, long view, String createAt, String updateAt, int categoryId) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.type = type;
        this.views = view;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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
    @Override
    public String toString() {
        return "Films{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", type='" + type + '\'' +
                ", views=" + views +
                ", createAt='" + createAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", link='" + link + '\'' +
                ", typeId=" + typeId +
                '}';
    }
}
