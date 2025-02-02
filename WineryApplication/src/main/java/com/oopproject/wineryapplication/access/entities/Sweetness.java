package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.SweetnessDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "sweetness", schema = "public")
public class Sweetness extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sweetness_id_gen")
    @SequenceGenerator(name = "sweetness_id_gen", sequenceName = "sweetness_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "category", nullable = false, length = 15)
    private String category;

    @Column(name = "le_category", length = 15)
    private String leCategory;

    @OneToMany(mappedBy = "sweetness")
    private Set<Bottle> bottles = new LinkedHashSet<>();

    /**
     * {@inheritDoc}
     */
    public Integer getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLeCategory() {
        return leCategory;
    }

    public void setLeCategory(String leCategory) {
        this.leCategory = leCategory;
    }

    public Set<Bottle> getBottles() {
        return bottles;
    }

    public void setBottles(Set<Bottle> bottles) {
        this.bottles = bottles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()+"["+leCategory+"("+category+")"+"]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Sweetness> getDao() {
        return new SweetnessDao();
    }
}