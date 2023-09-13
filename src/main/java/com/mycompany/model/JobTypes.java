package com.mycompany.model;

import jakarta.persistence.*;

@Entity
@Table(name = "job_types")
public class JobTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 250,unique = true )
    private String name;


    public JobTypes() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

