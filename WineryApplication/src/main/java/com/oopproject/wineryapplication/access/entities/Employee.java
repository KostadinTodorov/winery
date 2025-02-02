package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.EmployeeDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;



/**
 * Represents the "Employee" entity in the system, corresponding to the "employee" table in the database.
 * This class extends the base entity class to inherit common functionalities and adds specific
 * attributes and relationships for the concept of an "Employee."
 * <p>
 * Each instance of Employee is identified by a unique ID, is linked to a person, has a defined occupation,
 * and a password for authentication.
 * <p>
 * Fields:
 * <ul>
 *     <li>{@code id}: Unique identifier for the Employee entity.</li>
 *     <li>{@code person}: Represents the person associated with the employee, forming a many-to-one relationship.</li>
 *     <li>{@code password}: A string representing the password of the employee, with a maximum length of 30 characters.</li>
 *     <li>{@code occupation}: Denotes the job or role the employee holds, forming a many-to-one relationship.</li>
 *     <li>{@code behaviors}: A collection of behaviors linked to this employee, forming a one-to-many relationship.</li>
 * </ul>
 * <p>
 * Methods:
 * <ul>
 *     <li>{@code getId()}: Retrieves the ID of the Employee.</li>
 *     <li>{@code setId(Integer id)}: Sets the ID of the Employee.</li>
 *     <li>{@code getDao()}: Provides a DAO implementation specific to Employee for database interaction.</li>
 *     <li>{@code getPerson()}: Retrieves the person linked to this employee.</li>
 *     <li>{@code setPerson(Person person)}: Defines the person linked to this employee.</li>
 *     <li>{@code getPassword()}: Retrieves the employee's password.</li>
 *     <li>{@code setPassword(String password)}: Sets the employee's password.</li>
 *     <li>{@code getOccupation()}: Retrieves the occupation associated with an employee.</li>
 *     <li>{@code setOccupation(Occupation occupation)}: Sets the occupation of the employee.</li>
 *     <li>{@code getBehaviors()}: Retrieves the behaviors associated with the employee.</li>
 *     <li>{@code setBehaviors(Set<Behavior> behaviors)}: Assigns behaviors to the employee.</li>
 *     <li>{@code toString()}: Returns a string representation of the employee, including the linked person's name.</li>
 * </ul>
 * <p>
 * Relationships:
 * - Many-to-one relationship with {@code Person entity}, representing the associated person.
 * - Many-to-one relationship with {@code Occupation entity}, representing the employee's role.
 * - One-to-many relationship with {@code Behavior entity}, representing the behaviors tied to the employee.
 */
@Entity
@Table(name = "employee", schema = "public")
public class Employee extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_gen")
    @SequenceGenerator(name = "employee_id_gen", sequenceName = "employee_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private com.oopproject.wineryapplication.access.entities.Person person;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "occupation_id", nullable = false)
    private com.oopproject.wineryapplication.access.entities.Occupation occupation;

    @OneToMany(mappedBy = "employee")
    private Set<Behavior> behaviors = new LinkedHashSet<>();

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

    public com.oopproject.wineryapplication.access.entities.Person getPerson() {
        return person;
    }

    public void setPerson(com.oopproject.wineryapplication.access.entities.Person person) {
        this.person = person;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public com.oopproject.wineryapplication.access.entities.Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(com.oopproject.wineryapplication.access.entities.Occupation occupation) {
        this.occupation = occupation;
    }

    public Set<Behavior> getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(Set<Behavior> behaviors) {
        this.behaviors = behaviors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()+"["+person.getPersonName()+"]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Employee> getDao() {
        return new EmployeeDao();
    }
}