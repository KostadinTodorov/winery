package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.WineTypeDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Класът {@code WineType} представлява тип вино, използван в системата.
 * Този клас наследява основната функционалност на {@link com.oopproject.wineryapplication.access.entities.entity.Entity}
 * и служи за управление и съхранение на информация, свързана с типове вина.
 *
 * Той използва JPA анотации за взаимодействие с база данни
 * и е част от схемата {@code public} в базата данни.
 *
 * Основни характеристики:
 * <ul>
 *     <li>{@link javax.persistence.Entity}: Декларира класа като JPA entity.</li>
 *     <li>{@link javax.persistence.Table}: Задава свързаната таблица в базата данни.</li>
 * </ul>
 *
 * Връзки:
 * <ul>
 *     <li>Връзка "Едно към много" към {@link Batch} обекти чрез асоциацията {@code batches}.</li>
 *     <li>Връзка "Едно към много" към {@link ClientsOrder} обекти чрез асоциацията {@code clientsOrders}.</li>
 * </ul>
 */
@Entity
@Table(name = "wine_type", schema = "public")
public class WineType extends com.oopproject.wineryapplication.access.entities.entity.Entity {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()+"["+name+"]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<WineType> getDao() {
        return new WineTypeDao();
    }
}