package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bottle_type", schema = "public")
public class BottleType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bottle_type_id_gen")
    @SequenceGenerator(name = "bottle_type_id_gen", sequenceName = "bottle_type_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 35)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @OneToMany(mappedBy = "bottleType")
    private Set<com.oopproject.wineryapplication.access.entities.EmptyBottle> emptyBottles = new LinkedHashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<com.oopproject.wineryapplication.access.entities.EmptyBottle> getEmptyBottles() {
        return emptyBottles;
    }

    public void setEmptyBottles(Set<com.oopproject.wineryapplication.access.entities.EmptyBottle> emptyBottles) {
        this.emptyBottles = emptyBottles;
    }

}