package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.BatchDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * Представлява entity Batch в системата, съответстващо на таблицата "batch" в базата данни.
 * Този клас разширява базовия клас entity, за да наследи общи функционалности и добавя
 * специфични атрибути и връзки за концепцията "Batch".
 * <p>
 * Всяка инстанция на Batch се идентифицира с уникален ID,
 * съхранява обем, е свързана с тип вино и включва колекции на хранилища, бутилки и смеси.
 * <p>
 * Полета:
 * <ul>
 *     <li>{@code id}: Уникален идентификатор за entity Batch.</li>
 *     <li>{@code volume}: Цяло число, представляващо обема на партидата.</li>
 *     <li>{@code wineType}: Връзка към обект от тип WineType, представящ типа вино за партидата.</li>
 *     <li>{@code batchStoridges}: Набор от "BatchStoridge" обекти, свързани с партидата.</li>
 *     <li>{@code bottles}: Набор от "Bottle" обекти, свързани с партидата.</li>
 *     <li>{@code mixes}: Набор от "Mix" обекти, свързани с партидата.</li>
 * </ul>
 * <p>
 * Методи:
 * <ul>
 *     <li>{@code getId()}: Извлича уникалния идентификатор на партидата.</li>
 *     <li>{@code setId(Integer id)}: Задава уникалния идентификатор на партидата.</li>
 *     <li>{@code getVolume()}: Извлича обема на партидата.</li>
 *     <li>{@code setVolume(Integer volume)}: Задава обема на партидата.</li>
 *     <li>{@code getWineType()}: Извлича свързания тип вино.</li>
 *     <li>{@code setWineType(WineType wineType)}: Задава свързания тип вино.</li>
 *     <li>{@code getBatchStoridges()}: Извлича набора от свързани BatchStoridge обекти.</li>
 *     <li>{@code setBatchStoridges(Set<BatchStoridge> batchStoridges)}: Задава свързаните BatchStoridge обекти.</li>
 *     <li>{@code getBottles()}: Извлича набора от свързани Bottle обекти.</li>
 *     <li>{@code setBottles(Set<Bottle> bottles)}: Задава свързаните Bottle обекти.</li>
 *     <li>{@code getMixes()}: Извлича набора от свързани Mix обекти.</li>
 *     <li>{@code setMixes(Set<Mix> mixes)}: Задава свързаните Mix обекти.</li>
 *     <li>{@code getDao()}: Връща DAO обекта за взаимодействие с базата данни за този клас.</li>
 * </ul>
 * <p>
 * Връзки:
 * - Връзка един-към-много с {@code BatchStoridge entity}, съпоставена чрез полето "batch" в BatchStoridge.
 * - Връзка един-към-много с {@code Bottle entity}, съпоставена чрез полето "batch" в Bottle.
 * - Връзка един-към-много с {@code Mix entity}, съпоставена чрез полето "batch" в Mix.
 * - Връзка много-към-един с {@code WineType entity}, съпоставена чрез полето "wineType" в Batch.
 */
@Entity
@Table(name = "batch", schema = "public")
public class Batch extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_id_gen")
    @SequenceGenerator(name = "batch_id_gen", sequenceName = "batch_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "volume", nullable = false)
    private Integer volume;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wine_type_id", nullable = false)
    private com.oopproject.wineryapplication.access.entities.WineType wineType;

    @OneToMany(mappedBy = "batch")
    private Set<com.oopproject.wineryapplication.access.entities.BatchStoridge> batchStoridges = new LinkedHashSet<>();

    @OneToMany(mappedBy = "batch")
    private Set<com.oopproject.wineryapplication.access.entities.Bottle> bottles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "batch")
    private Set<com.oopproject.wineryapplication.access.entities.Mix> mixes = new LinkedHashSet<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public com.oopproject.wineryapplication.access.entities.WineType getWineType() {
        return wineType;
    }

    public void setWineType(com.oopproject.wineryapplication.access.entities.WineType wineType) {
        this.wineType = wineType;
    }

    public Set<com.oopproject.wineryapplication.access.entities.BatchStoridge> getBatchStoridges() {
        return batchStoridges;
    }

    public void setBatchStoridges(Set<com.oopproject.wineryapplication.access.entities.BatchStoridge> batchStoridges) {
        this.batchStoridges = batchStoridges;
    }

    public Set<com.oopproject.wineryapplication.access.entities.Bottle> getBottles() {
        return bottles;
    }

    public void setBottles(Set<com.oopproject.wineryapplication.access.entities.Bottle> bottles) {
        this.bottles = bottles;
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
    public Dao<Batch> getDao() {
        return new BatchDao();
    }
}