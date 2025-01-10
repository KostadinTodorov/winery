package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "client", schema = "public")
public class Client implements com.oopproject.wineryapplication.access.entities.entity.Entity {
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

    public Integer getId() {
        return id;
    }

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

}