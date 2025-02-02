package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;
import javafx.scene.Node;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Представлява entity Act в системата, съответстващо на таблицата "act" в базата данни.
 * Този клас разширява базовия клас entity, за да наследи общи функционалности и добавя
 * специфични атрибути и връзки за концепцията "Act".
 *
 * Всяка инстанция на Act се идентифицира с уникален ID,
 * има име, тегло и е свързана с колекция от поведения.
 *
 * Класът Act също имплементира методи за извличане и задаване на неговите свойства,
 * взаимодействие с DAO и предефиниране на string представянето по подразбиране.
 *
 * Полета:
 * <ul>
 *     <li>{@code id}: Уникален идентификатор за entity Act.</li>
 *     <li>{@code name}: Низ, представляващ името на Act (максимална дължина 100).</li>
 *     <li>{@code weight}: Цяло число, представляващо теглото на Act.</li>
 *     <li>{@code behaviors}: Набор от поведения, свързани с този Act, образуващи връзка един-към-много.</li>
 * </ul>
 *
 * Методи:
 * <ul>
 *     <li>{@code getId()}: Извлича ID на Act.</li>
 *     <li>{@code setId(Integer id)}: Задава ID на Act.</li>
 *     <li>{@code getDao()}: Предоставя DAO имплементация, специфична за Act, за взаимодействие с базата данни.</li>
 *     <li>{@code getName()}: Извлича името на Act.</li>
 *     <li>{@code setName(String name)}: Задава името на Act.</li>
 *     <li>{@code getWeight()}: Извлича теглото на Act.</li>
 *     <li>{@code setWeight(Integer weight)}: Задава теглото на Act.</li>
 *     <li>{@code setWeight(Integer weight)}: Задава теглото на Act.</li>
 *     <li>{@code setWeight(Integer weight)}: Задава теглото на Act.</li>
 *     <li>{@code setWeight(Integer weight)}: Задава теглото на Act.</li>
 *     <li>{@code getBehaviors()}: Извлича набора от поведения, свързани с Act.</li>
 *     <li>{@code setBehaviors(Set<Behavior> behaviors)}: Задава поведенията, свързани с Act.</li>
 *     <li>{@code toString()}: Връща string представяне на entity Act, включващо неговото базово представяне и име.</li>
 * <li>
 *
 * Връзки:
 * - Връзка един-към-много с {@code Behavior entity}, съпоставена чрез полето "act" в Behavior.
 */
@Entity
@Table(name = "act", schema = "public")
public class Act extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "act_id_gen")
    @SequenceGenerator(name = "act_id_gen", sequenceName = "act_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @OneToMany(mappedBy = "act")
    private Set<com.oopproject.wineryapplication.access.entities.Behavior> behaviors = new LinkedHashSet<>();

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

    /**
     * Retrieves the DAO instance for the {@link Act} entity.
     *
     * @return a {@code Dao<Act>} instance for performing CRUD operations on the {@link Act} entity
     */
    @Override
    public Dao<Act> getDao() {
        return new ActDao();
    }

    /**
     * Retrieves the name of the Act entity.
     *
     * @return the name of the entity as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the entity.
     *
     * @param name the name to be assigned to the entity
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the weight of the entity.
     *
     * @return the weight of the entity as an Integer
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the Act entity.
     *
     * @param weight the weight to be assigned to the entity
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * Retrieves the set of behaviors associated with this entity.
     *
     * @return a Set of Behavior entities associated with the current entity
     */
    public Set<com.oopproject.wineryapplication.access.entities.Behavior> getBehaviors() {
        return behaviors;
    }

    /**
     * Sets the set of behaviors associated with this entity.
     *
     * @param behaviors the set of Behavior entities to be associated with this entity
     */
    public void setBehaviors(Set<com.oopproject.wineryapplication.access.entities.Behavior> behaviors) {
        this.behaviors = behaviors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()+"["+name+"]";
    }

}