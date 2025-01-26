package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.ContainerDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "container", schema = "public")
public class Container extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "container_id_gen")
    @SequenceGenerator(name = "container_id_gen", sequenceName = "container_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "space", nullable = false)
    private Integer space;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "container")
    private Set<BatchStoridge> batchStoridges = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpace() {
        return space;
    }

    public void setSpace(Integer space) {
        this.space = space;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BatchStoridge> getBatchStoridges() {
        return batchStoridges;
    }

    public void setBatchStoridges(Set<BatchStoridge> batchStoridges) {
        this.batchStoridges = batchStoridges;
    }

    @Override
    public String toString() {
        return super.toString()+"["+name+"]";
    }

    @Override
    public Dao<Container> getDao() {
        return new ContainerDao();
    }
}