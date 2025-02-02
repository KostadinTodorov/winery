package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.BehaviorDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;


/**
 * Represents a Behavior entity in the system, corresponding to the "behavior" table in the database.
 * This class extends the base Entity class to inherit common functionalities and defines attributes
 * and relationships unique to the "Behavior" concept.
 * <p>
 * Each instance of Behavior is identified by a unique ID,
 * and is associated with an Employee and an Act entity.
 * <p>
 * Fields:
 * - id: A unique identifier for the Behavior entity.
 * - employee: The Employee associated with this Behavior, forming a many-to-one relationship.
 * - act: The Act associated with this Behavior, forming a many-to-one relationship.
 * <p>
 * Methods:
 * - getId(): Retrieves the ID of the Behavior.
 * - setId(Integer id): Sets the ID of the Behavior.
 * - getEmployee(): Retrieves the Employee associated with the Behavior.
 * - setEmployee(Employee employee): Sets the Employee associated with the Behavior.
 * - getAct(): Retrieves the Act associated with the Behavior.
 * - setAct(Act act): Sets the Act associated with the Behavior.
 * - getDao(): Provides a DAO implementation specific to Behavior for interacting with the database.
 */
@Entity
@Table(name = "behavior", schema = "public")
public class Behavior extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    /**
     * A unique identifier for the Behavior entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "behavior_id_gen")
    @SequenceGenerator(name = "behavior_id_gen", sequenceName = "behavior_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * The Employee associated with this Behavior.
     * Establishes a many-to-one relationship where multiple Behavior entities
     * can be associated with a single Employee.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private com.oopproject.wineryapplication.access.entities.Employee employee;

    /**
     * The Act associated with this Behavior.
     * Establishes a many-to-one relationship where multiple Behavior entities
     * can be associated with a single Act.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "act_id", nullable = false)
    private Act act;

    /**
     * Retrieves the unique identifier of the Behavior entity.
     *
     * @return the unique ID of this Behavior instance
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the Behavior entity.
     *
     * @param id the unique ID to assign to this Behavior instance
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the Employee associated with this Behavior.
     *
     * @return the Employee entity linked to this Behavior
     */
    public com.oopproject.wineryapplication.access.entities.Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the Employee associated with this Behavior.
     *
     * @param employee the Employee entity to link to this Behavior
     */
    public void setEmployee(com.oopproject.wineryapplication.access.entities.Employee employee) {
        this.employee = employee;
    }

    /**
     * Retrieves the Act associated with this Behavior.
     *
     * @return the Act entity linked to this Behavior
     */
    public Act getAct() {
        return act;
    }

    /**
     * Sets the Act associated with this Behavior.
     *
     * @param act the Act entity to link to this Behavior
     */
    public void setAct(Act act) {
        this.act = act;
    }

    /**
     * Provides the DAO instance specific to the Behavior entity for database operations.
     *
     * @return a {@code Dao<Behavior>} implementation for the Behavior entity
     */
    @Override
    public Dao<Behavior> getDao() {
        return new BehaviorDao();
    }
}