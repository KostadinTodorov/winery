package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "client", schema = "public")
public class Client extends com.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_gen")
    @SequenceGenerator(name = "client_id_gen", sequenceName = "client_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private com.access.entities.Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private com.access.entities.Company company;

    @OneToMany(mappedBy = "client")
    private Set<com.access.entities.ClientsOrder> clientsOrders = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.access.entities.Person getPerson() {
        return person;
    }

    public void setPerson(com.access.entities.Person person) {
        this.person = person;
    }

    public com.access.entities.Company getCompany() {
        return company;
    }

    public void setCompany(com.access.entities.Company company) {
        this.company = company;
    }

    public Set<com.access.entities.ClientsOrder> getClientsOrders() {
        return clientsOrders;
    }

    public void setClientsOrders(Set<com.access.entities.ClientsOrder> clientsOrders) {
        this.clientsOrders = clientsOrders;
    }

}