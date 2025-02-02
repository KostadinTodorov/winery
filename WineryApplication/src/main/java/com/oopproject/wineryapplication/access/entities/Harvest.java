package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.HarvestDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * Представлява entity Harvest в системата, съответстващо на таблицата "harvest" в базата данни.
 * Този клас разширява базовия клас entity, за да наследи общи функционалности
 * и добавя специфични атрибути и връзки за концепцията "Harvest".
 * <p>
 * Всяка инстанция на Harvest се идентифицира с уникален ID, има тегло,
 * асоциира се с определен сорт и съдържа множество смеси, в които участва.
 * <p>
 * Полета:
 * <ul>
 *     <li>{@code id}: Уникален идентификатор за entity Harvest.</li>
 *     <li>{@code weight}: Цяло число, представляващо теглото на Harvest в килограми.</li>
 *     <li>{@code sort}: Сортът (grape sort), асоцииран с този Harvest.</li>
 *     <li>{@code mixes}: Набор от смеси (Mix), в които този Harvest участва.</li>
 * </ul>
 * <p>
 * Методи:
 * <ul>
 *     <li>{@code getId()}: Извлича ID на Harvest.</li>
 *     <li>{@code setId(Integer id)}: Задава ID на Harvest.</li>
 *     <li>{@code getWeight()}: Извлича теглото на Harvest.</li>
 *     <li>{@code setWeight(Integer weight)}: Задава теглото на Harvest.</li>
 *     <li>{@code getSort()}: Извлича асоциирания сорт на Harvest.</li>
 *     <li>{@code setSort(Sort sort)}: Задава сорта на Harvest.</li>
 *     <li>{@code getMixes()}: Извлича набора от смеси, в които участва Harvest.</li>
 *     <li>{@code setMixes(Set<Mix> mixes)}: Задава набора от смеси за този Harvest.</li>
 *     <li>{@code toString()}: Връща string представяне на entity Harvest, включващо неговото ID и име на сорта.</li>
 * </ul>
 * <p>
 * Връзки:
 * - Връзка много-към-един със {@code Sort} (сорт грозде), свързан чрез {@code sort_id}.
 * - Връзка един-към-много със {@code Mix} (смеси), свързани чрез полето "harvest" в Mix.
 */
@Entity
@Table(name = "harvest", schema = "public")
public class Harvest extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "harvest_id_gen")
    @SequenceGenerator(name = "harvest_id_gen", sequenceName = "harvest_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sort_id", nullable = false)
    private com.oopproject.wineryapplication.access.entities.Sort sort;

    @OneToMany(mappedBy = "harvest")
    private Set<com.oopproject.wineryapplication.access.entities.Mix> mixes = new LinkedHashSet<>();

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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public com.oopproject.wineryapplication.access.entities.Sort getSort() {
        return sort;
    }

    public void setSort(com.oopproject.wineryapplication.access.entities.Sort sort) {
        this.sort = sort;
    }

    public Set<com.oopproject.wineryapplication.access.entities.Mix> getMixes() {
        return mixes;
    }

    public void setMixes(Set<com.oopproject.wineryapplication.access.entities.Mix> mixes) {
        this.mixes = mixes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()+"["+sort.getName()+"]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Harvest> getDao() {
        return new HarvestDao();
    }
}