package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.ClientDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * Represents a Client entity in the system, corresponding to the "client" table in the database.
 * This class extends the base Entity class to inherit common functionalities and adds
 * attributes, methods, and relationships unique to the "Client" concept.
 * <p>
 * Each instance of Client is identified by a unique ID and can optionally be associated with
 * a person, a company, and a collection of orders (ClientsOrder).
 * <p>
 * The Client class also implements methods for retrieving and setting its properties,
 * interacting with a DAO, and overriding the default string representation.
 * <p>
 * Fields:
 * - id: A unique identifier for the Client entity.
 * - person: Represents the associated person entity in a many-to-one relationship.
 * - company: Represents the associated company entity in a many-to-one relationship.
 * - clientsOrders: A set of orders related to this client in a one-to-many relationship.
 * <p>
 * Methods:
 * - getId(): Retrieves the ID of the Client.
 * - setId(Integer id): Sets the ID of the Client.
 * - getDao(): Provides a DAO implementation specific to Client for interacting with the database.
 * - getPerson(): Retrieves the associated person entity.
 * - setPerson(Person person): Sets the associated person entity.
 * - getCompany(): Retrieves the associated company entity.
 * - setCompany(Company company): Sets the associated company entity.
 * - getClientsOrders(): Retrieves the set of orders linked to this client.
 * - setClientsOrders(Set<ClientsOrder> clientsOrders): Sets the set of orders linked to this client.
 * - toString(): Returns a string representation of the Client entity, including its base representation and person's name if available.
 * <p>
 * Relationships:
 * - Many-to-one relationship with the Person entity (person_id column).
 * - Many-to-one relationship with the Company entity (company_id column).
 * - One-to-many relationship with the ClientsOrder entity, mapped by the "client" field.
 */
@Entity
@Table(name = "client", schema = "public")
public class Client extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_gen")
    @SequenceGenerator(name = "client_id_gen", sequenceName = "client_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private com.oopproject.wineryapplication.access.entities.Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private com.oopproject.wineryapplication.access.entities.Company company;

    @OneToMany(mappedBy = "client")
    private Set<com.oopproject.wineryapplication.access.entities.ClientsOrder> clientsOrders = new LinkedHashSet<>();

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

    public com.oopproject.wineryapplication.access.entities.Company getCompany() {
        return company;
    }

    public void setCompany(com.oopproject.wineryapplication.access.entities.Company company) {
        this.company = company;
    }

    public Set<com.oopproject.wineryapplication.access.entities.ClientsOrder> getClientsOrders() {
        return clientsOrders;
    }

    public void setClientsOrders(Set<com.oopproject.wineryapplication.access.entities.ClientsOrder> clientsOrders) {
        this.clientsOrders = clientsOrders;
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
    public Dao<Client> getDao() {
        return new ClientDao();
    }
}