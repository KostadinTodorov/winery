package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "wine_type", schema = "public")
public class WineType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wine_type_id_gen")
    @SequenceGenerator(name = "wine_type_id_gen", sequenceName = "wine_type_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @OneToMany(mappedBy = "wineType")
    private Set<Batch> batches = new LinkedHashSet<>();

    @OneToMany(mappedBy = "wineType")
    private Set<ClientsOrder> clientsOrders = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Batch> getBatches() {
        return batches;
    }

    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }

    public Set<ClientsOrder> getClientsOrders() {
        return clientsOrders;
    }

    public void setClientsOrders(Set<ClientsOrder> clientsOrders) {
        this.clientsOrders = clientsOrders;
    }

}