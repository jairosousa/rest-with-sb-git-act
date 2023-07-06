package com.jnsdevs.app.model;

/**
 * @Autor Jairo Nascimento
 * @Created 06/07/2023 - 10:17
 */
public class Greeting {

    private Long id;

    private String name;

    public Greeting(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
