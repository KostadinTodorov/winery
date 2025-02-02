package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.BottleDao;
import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;



/**
 * Представлява entity BottleType в системата, съответстващо на таблицата "bottle_type" в базата данни.
 * Този клас разширява базовия клас entity, за да наследи общи функционалности и добавя
 * специфични атрибути и връзки за концепцията "BottleType".
 * <p>
 * Всяка инстанция на BottleType се идентифицира с уникален ID,
 * има име, описание и съхраняваща наличност, както и е свързана с набор от бутилки (Bottle).
 * <p>
 * Класът BottleType също имплементира методи за извличане и задаване на неговите свойства,
 * взаимодействие с DAO и предефиниране на string представянето по подразбиране.
 * <p>
 * Полета:
 * <ul>
 *     <li>{@code id}: Уникален идентификатор за entity BottleType.</li>
 *     <li>{@code name}: Низ, представляващ името на BottleType (максимална дължина 35).</li>
 *     <li>{@code description}: Низ, представляващ описание на BottleType (максимална дължина 200).</li>
 *     <li>{@code stock}: Цяло число, представляващо наличното количество от този тип бутилка.</li>
 *     <li>{@code bottles}: Набор от бутилки, свързани с този BottleType, образуващи връзка един-към-много.</li>
 * </ul>
 * <p>
 * Методи:
 * <ul>
 *     <li>{@code getId()}: Извлича ID на BottleType.</li>
 *     <li>{@code setId(Integer id)}: Задава ID на BottleType.</li>
 *     <li>{@code getName()}: Извлича името на BottleType.</li>
 *     <li>{@code setName(String name)}: Задава името на BottleType.</li>
 *     <li>{@code getDescription()}: Извлича описанието на BottleType.</li>
 *     <li>{@code setDescription(String description)}: Задава описанието на BottleType.</li>
 *     <li>{@code getStock()}: Извлича количеството налични бутилки от този тип.</li>
 *     <li>{@code setStock(Integer stock)}: Задава количеството налични бутилки от този тип.</li>
 *     <li>{@code getBottles()}: Извлича набора от бутилки, свързани с този BottleType.</li>
 *     <li>{@code setBottles(Set<Bottle> bottles)}: Задава бутилките, свързани с този BottleType.</li>
 *     <li>{@code toString()}: Връща string представяне на BottleType, включващо неговото базово представяне и име.</li>
 *     <li>{@code getDao()}: Предоставя DAO имплементация, специфична за BottleType, за взаимодействие с базата данни.</li>
 * </ul>
 * <p>
 * Връзки:
 * - Връзка един-към-много с {@code Bottle entity}, съпоставена чрез полето "bottleType" в Bottle.
 */
@Entity
@Table(name = "bottle_type", schema = "public")
public class BottleType extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bottle_type_id_gen")
    @SequenceGenerator(name = "bottle_type_id_gen", sequenceName = "bottle_type_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 35)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "bottleType")
    private Set<com.oopproject.wineryapplication.access.entities.Bottle> bottles = new LinkedHashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Set<com.oopproject.wineryapplication.access.entities.Bottle> getBottles() {
        return bottles;
    }

    public void setBottles(Set<com.oopproject.wineryapplication.access.entities.Bottle> bottles) {
        this.bottles = bottles;
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
    public Dao<BottleType> getDao() {
        return new BottleTypeDao();
    }
}