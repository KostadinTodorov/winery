package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;
import javafx.scene.Node;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents an Act entity in the system, corresponding to the "act" table in the database.
 * This class extends the base entity class to inherit common functionalities and adds
 * specific attributes and relationships for the "Act" concept.
 *
 * Each instance of Act is identified by a unique ID,
 * has a name, a weight, and is associated with a collection of behaviors.
 *
 * The Act class also implements methods for retrieving and setting its properties,
 * interacting with a DAO, and overriding the default string representation.
 *
 * Fields:
 * - id: A unique identifier for the Act entity.
 * - name: A string representing the name of the Act (maximum length 100).
 * - weight: An integer representing the weight of the Act.
 * - behaviors: A set of behaviors associated with this Act, forming a one-to-many relationship.
 *
 * Methods:
 * - getId(): Retrieves the ID of the Act.
 * - setId(Integer id): Sets the ID of the Act.
 * - getDao(): Provides a DAO implementation specific to Act for interacting with the database.
 * - getName(): Retrieves the name of the Act.
 * - setName(String name): Sets the name of the Act.
 * - getWeight(): Retrieves the weight of the Act.
 * - setWeight(Integer weight): Sets the weight of the Act.
 * - getBehaviors(): Retrieves the set of behaviors associated with the Act.
 * - setBehaviors(Set<Behavior> behaviors): Sets the behaviors associated with the Act.
 * - toString(): Returns a string representation of the Act entity including its base representation and name.
 *
 * Relationships:
 * - One-to-many relationship with the Behavior entity, mapped by the "act" field in Behavior.
 */
@Entity
@Table(name = "act", schema = "public")
public class Act extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "act_id_gen")
    @SequenceGenerator(name = "act_id_gen", sequenceName = "act_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @OneToMany(mappedBy = "act")
    private Set<com.oopproject.wineryapplication.access.entities.Behavior> behaviors = new LinkedHashSet<>();

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

    /**
     * Retrieves the DAO instance for the {@link Act} entity.
     *
     * @return a {@code Dao<Act>} instance for performing CRUD operations on the {@link Act} entity
     */
    @Override
    public Dao<Act> getDao() {
        return new ActDao();
    }

    /**
     * Retrieves the name of the Act entity.
     *
     * @return the name of the entity as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the entity.
     *
     * @param name the name to be assigned to the entity
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the weight of the entity.
     *
     * @return the weight of the entity as an Integer
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the Act entity.
     *
     * @param weight the weight to be assigned to the entity
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * Retrieves the set of behaviors associated with this entity.
     *
     * @return a Set of Behavior entities associated with the current entity
     */
    public Set<com.oopproject.wineryapplication.access.entities.Behavior> getBehaviors() {
        return behaviors;
    }

    /**
     * Sets the set of behaviors associated with this entity.
     *
     * @param behaviors the set of Behavior entities to be associated with this entity
     */
    public void setBehaviors(Set<com.oopproject.wineryapplication.access.entities.Behavior> behaviors) {
        this.behaviors = behaviors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()+"["+name+"]";
    }

}