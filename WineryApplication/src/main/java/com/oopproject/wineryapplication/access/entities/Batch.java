package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "batch", schema = "public")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_id_gen")
    @SequenceGenerator(name = "batch_id_gen", sequenceName = "batch_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "volume", nullable = false)
    private Integer volume;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wine_type_id", nullable = false)
    private com.oopproject.wineryapplication.access.entities.WineType wineType;

    @OneToMany(mappedBy = "batch")
    private Set<com.oopproject.wineryapplication.access.entities.BatchStoridge> batchStoridges = new LinkedHashSet<>();

    @OneToMany(mappedBy = "batch")
    private Set<com.oopproject.wineryapplication.access.entities.Bottle> bottles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "batch")
    private Set<com.oopproject.wineryapplication.access.entities.Mix> mixes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public com.oopproject.wineryapplication.access.entities.WineType getWineType() {
        return wineType;
    }

    public void setWineType(com.oopproject.wineryapplication.access.entities.WineType wineType) {
        this.wineType = wineType;
    }

    public Set<com.oopproject.wineryapplication.access.entities.BatchStoridge> getBatchStoridges() {
        return batchStoridges;
    }

    public void setBatchStoridges(Set<com.oopproject.wineryapplication.access.entities.BatchStoridge> batchStoridges) {
        this.batchStoridges = batchStoridges;
    }

    public Set<com.oopproject.wineryapplication.access.entities.Bottle> getBottles() {
        return bottles;
    }

    public void setBottles(Set<com.oopproject.wineryapplication.access.entities.Bottle> bottles) {
        this.bottles = bottles;
    }

    public Set<com.oopproject.wineryapplication.access.entities.Mix> getMixes() {
        return mixes;
    }

    public void setMixes(Set<com.oopproject.wineryapplication.access.entities.Mix> mixes) {
        this.mixes = mixes;
    }

}