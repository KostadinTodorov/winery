package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.ProgressDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Представлява entity "Progress", което отразява прогреса на даден процес или състояние в системата.
 * Класът е свързан с таблицата "progress" в schema "public" в базата данни и е основна част от ORM модела.
 *
 * Основни характеристики на класа Progress:
 * - Идентификационен номер ({@code id}), автоматично генериран чрез {@link SequenceGenerator}.
 * - Състояние ({@code status}), съхранено като текстов низ с ограничена дължина.
 * - Едно към много връзка с {@link ClientsOrder}, представляваща всички поръчки на клиенти,
 *   свързани с дадено състояние на прогреса.
 *
 * Класът разширява {@link com.oopproject.wineryapplication.access.entities.entity.Entity},
 * осигурявайки общата функционалност на entity в системата.
 */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()+"["+status+"]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Progress> getDao() {
        return new ProgressDao();
    }
}