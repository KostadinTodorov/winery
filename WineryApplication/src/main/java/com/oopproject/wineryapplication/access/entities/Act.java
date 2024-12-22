package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "act", schema = "public")
public class Act {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "act_id_gen")
    @SequenceGenerator(name = "act_id_gen", sequenceName = "act_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @OneToMany(mappedBy = "act")
    private Set<Behavior> behaviors = new LinkedHashSet<>();

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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Set<Behavior> getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(Set<Behavior> behaviors) {
        this.behaviors = behaviors;
    }

}