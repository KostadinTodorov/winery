package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.BatchDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents a Batch entity in the system, corresponding to the "batch" table in the database.
 * This class extends the base entity class to inherit common functionalities and adds
 * specific attributes and relationships for the "Batch" concept.
 * <p>
 * Each instance of Batch is identified by a unique ID, has a volume that denotes the quantity
 * associated with the batch, and is linked to specific wine types and other related entities.
 * <p>
 * Fields:
 * - id: A unique identifier for the Batch entity.
 * - volume: An integer representing the volume associated with the batch.
 * - wineType: A reference to the WineType entity that describes the associated wine type.
 * - batchStoridges: A collection of BatchStoridge entities associated with this batch.
 * - bottles: A collection of Bottle entities produced from this batch.
 * - mixes: A collection of Mix entities to which this batch contributes.
 * <p>
 * Methods:
 * - getId(): Retrieves the unique ID of the Batch entity.
 * - setId(Integer id): Sets the unique ID of the Batch entity.
 * - getVolume(): Retrieves the volume of the Batch entity.
 * - setVolume(Integer volume): Sets the volume of the Batch entity.
 * - getWineType(): Retrieves the associated WineType entity.
 * - setWineType(WineType wineType): Links a WineType entity to this Batch.
 * - getBatchStoridges(): Retrieves the associated set of BatchStoridge entities.
 * - setBatchStoridges(Set<BatchStoridge> batchStoridges): Sets the associated BatchStoridge entities.
 * - getBottles(): Retrieves the associated set of Bottle entities.
 * - setBottles(Set<Bottle> bottles): Sets the associated Bottle entities.
 * - getMixes(): Retrieves the associated set of Mix entities.
 * - setMixes(Set<Mix> mixes): Sets the associated Mix entities.
 * - getDao(): Provides a DAO implementation specific to Batch for interacting with the database.
 * <p>
 * Relationships:
 * - Many-to-one relationship with the WineType entity, linked via the "wine_type_id" field.
 * - One-to-many relationship with the BatchStoridge entity, mapped by the "batch" field in BatchStoridge.
 * - One-to-many relationship with the Bottle entity, mapped by the "batch" field in Bottle.
 * - One-to-many relationship with the Mix entity, mapped by the "batch" field in Mix.
 */
@Entity
@Table(name = "batch", schema = "public")
public class Batch extends com.oopproject.wineryapplication.access.entities.entity.Entity {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Batch> getDao() {
        return new BatchDao();
    }
}