package com.esprit.pidev2022.Dto;

import java.util.Date;

public class PostRequest {

    Long id;
    String title;
    Date dateCreated;
    String contained ;
    Long idForum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getContained() {
        return contained;
    }

    public void setContained(String contained) {
        this.contained = contained;
    }

    public Long getIdForum() {
        return idForum;
    }

    public void setIdForum(Long idForum) {
        this.idForum = idForum;
    }
}
