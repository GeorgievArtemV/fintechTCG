package com.example.fintechtcg.model;

import java.util.ArrayList;
import java.util.Date;

public class FilmDetail {
    public String nameRu;

    public String posterUrl;




    public String description;



    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }


    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }




    public class Country{
        public String country;
    }

    public class Genre{
        public String genre;
    }
}
