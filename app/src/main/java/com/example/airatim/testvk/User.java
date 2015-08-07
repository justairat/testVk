package com.example.airatim.testvk;

/**
 * Created by Airat Im on 07.08.2015.
 */
public class User {
    String name;
    String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
