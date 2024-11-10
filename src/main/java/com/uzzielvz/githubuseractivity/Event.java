package com.uzzielvz.githubuseractivity;

import com.google.gson.annotations.SerializedName;

class Events {
    private String type;

    @SerializedName("repo")
    private Repo repo;

    // Getters y setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }
}

class Repo {
    @SerializedName("name")
    private String name;

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

