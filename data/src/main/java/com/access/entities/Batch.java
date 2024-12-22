package com.access.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "batch", schema = "public")
public class Batch extends com.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_id_gen")
    @SequenceGenerator(name = "batch_id_gen", sequenceName = "batch_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "volume", nullable = false)
    private Integer volume;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wine_type_id", nullable = false)
    private com.access.entities.WineType wineType;

    @OneToMany(mappedBy = "batch")
    private Set<com.access.entities.BatchStoridge> batchStoridges = new LinkedHashSet<>();

    @OneToMany(mappedBy = "batch")
    private Set<com.access.entities.Bottle> bottles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "batch")
    private Set<com.access.entities.Mix> mixes = new LinkedHashSet<>();

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

    public com.access.entities.WineType getWineType() {
        return wineType;
    }

    public void setWineType(com.access.entities.WineType wineType) {
        this.wineType = wineType;
    }

    public Set<com.access.entities.BatchStoridge> getBatchStoridges() {
        return batchStoridges;
    }

    public void setBatchStoridges(Set<com.access.entities.BatchStoridge> batchStoridges) {
        this.batchStoridges = batchStoridges;
    }

    public Set<com.access.entities.Bottle> getBottles() {
        return bottles;
    }

    public void setBottles(Set<com.access.entities.Bottle> bottles) {
        this.bottles = bottles;
    }

    public Set<com.access.entities.Mix> getMixes() {
        return mixes;
    }

    public void setMixes(Set<com.access.entities.Mix> mixes) {
        this.mixes = mixes;
    }

}