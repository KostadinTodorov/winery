package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.ContainerDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents a Container entity in the system, corresponding to the "container" table in the database.
 * This class extends the base entity class to inherit common functionalities and adds
 * specific attributes and relationships for the "Container" concept.
 * <p>
 * Each instance of Container is identified by a unique ID,
 * has a name, a specific storage space, and is associated with a collection of batch storage records.
 * <p>
 * The Container class also implements methods for retrieving and setting its properties,
 * interacting with a DAO, and overriding the default string representation.
 * <p>
 * Fields:
 * - id: A unique identifier for the Container entity.
 * - name: A string representing the name of the Container (maximum length 30).
 * - space: An integer representing the storage capacity of the Container.
 * - batchStoridges: A set of batch storage records associated with this Container.
 * <p>
 * Methods:
 * - getId(): Retrieves the ID of the Container.
 * - setId(Integer id): Sets the ID of the Container.
 * - getDao(): Provides a DAO implementation specific to Container for interacting with the database.
 * - getSpace(): Retrieves the storage capacity of the Container.
 * - setSpace(Integer space): Sets the storage capacity of the Container.
 * - getName(): Retrieves the name of the Container.
 * - setName(String name): Sets the name of the Container.
 * - getBatchStoridges(): Retrieves the set of batch storage records associated with the Container.
 * - setBatchStoridges(Set<BatchStoridge> batchStoridges): Sets the batch storage records associated with the Container.
 * - toString(): Returns a string representation of the Container entity including its base representation and name.
 * <p>
 * Relationships:
 * - One-to-many relationship with the BatchStoridge entity, mapped by the "container" field in BatchStoridge.
 */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()+"["+name+"]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Container> getDao() {
        return new ContainerDao();
    }
}