package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.BottleDao;
import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * Represents a BottleType entity in the system, corresponding to the "bottle_type" table in the database.
 * This class extends the base entity class to inherit common functionality and adds
 * specific attributes and relationships for the "BottleType" concept.
 * <p>
 * Each instance of BottleType is identified by a unique ID, has a name, a description,
 * and a stock quantity. Additionally, it is associated with a collection of bottles.
 * <p>
 * The BottleType class provides methods for retrieving and setting its properties,
 * interacting with a DAO, and overriding the default string representation.
 * <p>
 * Fields:
 * - id: A unique identifier for the BottleType entity.
 * - name: A string representing the name of the BottleType (maximum length 35).
 * - description: A string representing a brief description of the BottleType (maximum length 200).
 * - stock: An integer representing the stock quantity available for this BottleType.
 * - bottles: A set of Bottle entities associated with this BottleType, forming a one-to-many relationship.
 * <p>
 * Methods:
 * - getId(): Retrieves the ID of the BottleType.
 * - setId(Integer id): Sets the ID of the BottleType.
 * - getDao(): Provides a DAO implementation specific to BottleType for interacting with the database.
 * - getName(): Retrieves the name of the BottleType.
 * - setName(String name): Sets the name of the BottleType.
 * - getDescription(): Retrieves the description of the BottleType.
 * - setDescription(String description): Sets the description of the BottleType.
 * - getStock(): Retrieves the stock quantity of the BottleType.
 * - setStock(Integer stock): Sets the stock quantity of the BottleType.
 * - getBottles(): Retrieves the set of bottles associated with this BottleType.
 * - setBottles(Set<Bottle> bottles): Sets the bottles associated with the BottleType.
 * - toString(): Returns a string representation of the BottleType entity that includes its base representation and name.
 * <p>
 * Relationships:
 * - One-to-many relationship with the Bottle entity, mapped by the "bottleType" field in Bottle.
 */
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
    public Dao<BottleType> getDao() {
        return new BottleTypeDao();
    }
}