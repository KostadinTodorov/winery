package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.BottleDao;
import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bottle_type", schema = "public")
public class BottleType extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bottle_type_id_gen")
    @SequenceGenerator(name = "bottle_type_id_gen", sequenceName = "bottle_type_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 35)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "bottleType")
    private Set<com.oopproject.wineryapplication.access.entities.Bottle> bottles = new LinkedHashSet<>();

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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Set<com.oopproject.wineryapplication.access.entities.Bottle> getBottles() {
        return bottles;
    }

    public void setBottles(Set<com.oopproject.wineryapplication.access.entities.Bottle> bottles) {
        this.bottles = bottles;
    }

    @Override
    public String toString() {
        return super.toString()+"["+name+"]";
    }

    @Override
    public Dao<BottleType> getDao() {
        return new BottleTypeDao();
    }
}