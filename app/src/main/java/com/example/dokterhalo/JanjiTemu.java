package com.example.dokterhalo;

import java.util.Date;

public class JanjiTemu {

    protected String title;
    protected String sinopsis;
    protected String poster;
    protected String status;
    protected int rating;
    protected String review;
    public JanjiTemu(String title, String sinopsis,String poster, String status, int rating, String review){
        this.title= title;
        this.sinopsis = sinopsis;
        this.poster= poster;
        this.status= status;
        this.rating= rating;
        this.review= review;
    }

    public void setTitle (String title){
        this.title=title;
    }

    public void setSinopsis (String sinopsis){
        this.sinopsis=sinopsis;
    }

    public void setPoster (String poster){
        this.poster=poster;
    }

    public void setStatus (String status){
        this.status=status;
    }

    public void setRating(int rating){
        this.rating=rating;
    }

    public void setReview(String review){
        this.review=review;
    }

    public String getTitle(){
        return this.title;
    }

    public String getSinopsis(){
        return this.sinopsis;
    }

    public String getPoster(){
        return this.poster;
    }

    public String getStatus(){
        return this.status;
    }

    public int getRating(){
        return this.rating;
    }

    public String getReview(){
        return this.review;
    }
}