package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "progress", schema = "public")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "progress_id_gen")
    @SequenceGenerator(name = "progress_id_gen", sequenceName = "progress_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "status", nullable = false, length = 13)
    private String status;

    @OneToMany(mappedBy = "progress")
    private Set<ClientsOrder> clientsOrders = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<ClientsOrder> getClientsOrders() {
        return clientsOrders;
    }

    public void setClientsOrders(Set<ClientsOrder> clientsOrders) {
        this.clientsOrders = clientsOrders;
    }

}