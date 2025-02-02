package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.HarvestDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "harvest", schema = "public")
public class Harvest extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "harvest_id_gen")
    @SequenceGenerator(name = "harvest_id_gen", sequenceName = "harvest_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sort_id", nullable = false)
    private com.oopproject.wineryapplication.access.entities.Sort sort;

    @OneToMany(mappedBy = "harvest")
    private Set<com.oopproject.wineryapplication.access.entities.Mix> mixes = new LinkedHashSet<>();

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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public com.oopproject.wineryapplication.access.entities.Sort getSort() {
        return sort;
    }

    public void setSort(com.oopproject.wineryapplication.access.entities.Sort sort) {
        this.sort = sort;
    }

    public Set<com.oopproject.wineryapplication.access.entities.Mix> getMixes() {
        return mixes;
    }

    public void setMixes(Set<com.oopproject.wineryapplication.access.entities.Mix> mixes) {
        this.mixes = mixes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()+"["+sort.getName()+"]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Harvest> getDao() {
        return new HarvestDao();
    }
}