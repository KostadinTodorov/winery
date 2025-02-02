package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.EmployeeDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * Represents an Employee entity in the system, corresponding to the "employee" table in the database.
 * This class extends the base entity class to inherit common functionalities and adds
 * specific attributes and relationships for the "Employee" concept.
 * <p>
 * Each instance of Employee is identified by a unique ID,
 * is associated with a person, has a password, and has an occupation.
 * Employees can also be related to a collection of behaviors.
 * <p>
 * The Employee class implements methods for retrieving and setting its properties,
 * interacting with a DAO, and overriding the default string representation.
 * <p>
 * Fields:
 * - id: A unique identifier for the Employee entity.
 * - person: An associated Person entity representing the individual linked to this Employee (many-to-one relationship).
 * - password: A string representing the password of the Employee (maximum length 30).
 * - occupation: An associated Occupation entity indicating the job role of the Employee (many-to-one relationship).
 * - behaviors: A set of behaviors associated with this Employee, forming a one-to-many relationship.
 * <p>
 * Methods:
 * - getId(): Retrieves the ID of the Employee.
 * - setId(Integer id): Sets the ID of the Employee.
 * - getPerson(): Retrieves the associated Person entity.
 * - setPerson(Person person): Sets the associated Person entity.
 * - getPassword(): Retrieves the password of the Employee.
 * - setPassword(String password): Sets the password of the Employee.
 * - getOccupation(): Retrieves the associated Occupation entity.
 * - setOccupation(Occupation occupation): Sets the associated Occupation entity.
 * - getBehaviors(): Retrieves the set of behaviors associated with the Employee.
 * - setBehaviors(Set<Behavior> behaviors): Sets the behaviors associated with the Employee.
 * - toString(): Returns a string representation of the Employee entity including its base representation and person's name.
 * - getDao(): Provides a DAO implementation specific to Employee for interacting with the database.
 * <p>
 * Relationships:
 * - Many-to-one relationship with the Person entity, linked by the "person_id" column.
 * - Many-to-one relationship with the Occupation entity, linked by the "occupation_id" column.
 * - One-to-many relationship with the Behavior entity, mapped by the "employee" field in Behavior.
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