package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.ProgressDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "progress", schema = "public")
public class Progress extends com.oopproject.wineryapplication.access.entities.entity.Entity {
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

    @Override
    public String toString() {
        return super.toString()+"["+status+"]";
    }

    @Override
    public Dao<Progress> getDao() {
        return new ProgressDao();
    }
}