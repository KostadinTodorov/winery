package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

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

    public Integer getId() {
        return id;
    }

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

}