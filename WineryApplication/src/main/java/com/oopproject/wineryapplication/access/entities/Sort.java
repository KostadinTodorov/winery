package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.SortDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Класът {@code Sort} представлява модел за сорта на грозде в системата.
 * Този клас е entity, което е свързано с базата данни чрез JPA анотации,
 * и наследява функционалността на {@link com.oopproject.wineryapplication.access.entities.entity.Entity}.
 *
 * Класът включва следните основни атрибути:
 * - {@code id}: Уникален идентификатор за сорта.
 * - {@code name}: Името на сорта.
 * - {@code harvests}: Колекция от реколти, свързани с този сорт.
 *
 * Чрез този модел се управляват сортовете, съхранявани в базата данни.
 */
@Entity
@Table(name = "sort", schema = "public")
public class Sort extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sort_id_gen")
    @SequenceGenerator(name = "sort_id_gen", sequenceName = "sort_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "sort")
    private Set<Harvest> harvests = new LinkedHashSet<>();

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

    public Set<Harvest> getHarvests() {
        return harvests;
    }

    public void setHarvests(Set<Harvest> harvests) {
        this.harvests = harvests;
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
    public Dao<Sort> getDao() {
        return new SortDao();
    }
}