package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "harvest", schema = "public")
public class Harvest implements com.oopproject.wineryapplication.access.entities.entity.Entity {
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

    public Integer getId() {
        return id;
    }

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

}