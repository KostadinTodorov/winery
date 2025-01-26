package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;
import javafx.scene.Node;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "act", schema = "public")
public class Act extends com.oopproject.wineryapplication.access.entities.entity.Entity {
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
    private Set<com.oopproject.wineryapplication.access.entities.Behavior> behaviors = new LinkedHashSet<>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Dao<Act> getDao() {
        return new ActDao();
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

    public Set<com.oopproject.wineryapplication.access.entities.Behavior> getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(Set<com.oopproject.wineryapplication.access.entities.Behavior> behaviors) {
        this.behaviors = behaviors;
    }

    @Override
    public String toString() {
        return super.toString()+"["+name+"]";
    }

}