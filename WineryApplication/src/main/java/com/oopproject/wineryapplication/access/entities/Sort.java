package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "sort", schema = "public")
public class Sort {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sort_id_gen")
    @SequenceGenerator(name = "sort_id_gen", sequenceName = "sort_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "sort")
    private Set<Harvest> harvests = new LinkedHashSet<>();

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

    public Set<Harvest> getHarvests() {
        return harvests;
    }

    public void setHarvests(Set<Harvest> harvests) {
        this.harvests = harvests;
    }

}