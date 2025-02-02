package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.BehaviorDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;



/**
 * The {@code Behavior} class represents an entity in the system, corresponding to the "behavior" table in the database.
 * This class inherits from a base {@code Entity} class to gain reusable functionality and defines specific attributes
 * and relations representing the concept of a "Behavior."
 * <p>
 * Each instance of {@code Behavior} is uniquely identified by its ID, is associated with an Employee entity,
 * and establishes a many-to-one relationship with an Act entity. This relationship allows several behaviors
 * to be linked to a single act or employee.
 * <p>
 * Attributes:
 * <ul>
 *     <li>{@code id}: The unique identifier for the Behavior entity, automatically generated.</li>
 *     <li>{@code employee}: The Employee entity that this behavior is linked to; establishes a many-to-one relationship.</li>
 *     <li>{@code act}: The Act entity associated with this behavior; establishes a many-to-one relationship.</li>
 * </ul>
 * <p>
 * Methods:
 * <ul>
 *     <li>{@code getId()}: Retrieves the unique ID of the Behavior entity.</li>
 *     <li>{@code setId(Integer id)}: Assigns a unique ID to the Behavior entity.</li>
 *     <li>{@code getEmployee()}: Retrieves the Employee entity linked to this Behavior.</li>
 *     <li>{@code setEmployee(Employee employee)}: Associates this Behavior with an Employee entity.</li>
 *     <li>{@code getAct()}: Retrieves the Act entity linked to this Behavior.</li>
 *     <li>{@code setAct(Act act)}: Associates this Behavior with an Act entity.</li>
 *     <li>{@code getDao()}: Returns a DAO implementation specific to the Behavior entity for database interaction.</li>
 * </ul>
 * <p>
 * Relationships:
 * - Many-to-one relationship with {@code Employee} entity, linked through the field "employee_id."
 * - Many-to-one relationship with {@code Act} entity, linked through the field "act_id."
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