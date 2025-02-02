package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.CompanyDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents a Company entity in the system, corresponding to the "company" table in the database.
 * This class extends the base entity class to inherit common functionalities and adds
 * specific attributes and relationships for the "Company" concept.
 * <p>
 * Each instance of Company is identified by a unique ID,
 * has an address, and is associated with a collection of clients.
 * <p>
 * The Company class also implements methods for retrieving and setting its properties,
 * interacting with a Company-specific DAO, and overriding the default string representation.
 * <p>
 * Fields:
 * - id: A unique identifier for the Company entity.
 * - address: A string representing the address of the Company (maximum length 200).
 * - clients: A set of clients associated with this Company, forming a one-to-many relationship.
 * <p>
 * Methods:
 * - getId(): Retrieves the ID of the Company.
 * - setId(Integer id): Sets the ID of the Company.
 * - getDao(): Provides a DAO implementation specific to the Company for interacting with the database.
 * - getAddress(): Retrieves the address of the Company.
 * - setAddress(String address): Sets the address of the Company.
 * - getClients(): Retrieves the set of clients associated with the Company.
 * - setClients(Set<Client> clients): Sets the clients associated with the Company.
 * <p>
 * Relationships:
 * - One-to-many relationship with the Client entity, mapped by the "company" field in Client.
 */
@Entity
@Table(name = "company", schema = "public")
public class Company extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_gen")
    @SequenceGenerator(name = "company_id_gen", sequenceName = "company_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @OneToMany(mappedBy = "company")
    private Set<Client> clients = new LinkedHashSet<>();

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Company> getDao() {
        return new CompanyDao();
    }
}