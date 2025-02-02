package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.ContainerDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * Представлява entity Container в системата, съответстващо на таблицата "container" в базата данни.
 * Този клас разширява базовия entity клас, за да наследи общи функционалности, и добавя
 * специфични атрибути и връзки за концепцията "Container".
 * <p>
 * Всяка инстанция на Container съдържа уникален идентификатор, пространство и име,
 * както и връзка към набор от BatchStoridge обекти.
 * <p>
 * Полета:
 * <ul>
 *     <li>{@code id}: Уникален идентификатор за entity Container.</li>
 *     <li>{@code space}: Цяло число, представляващо пространството на Container.</li>
 *     <li>{@code name}: Низ, представляващ името на Container (максимална дължина 30).</li>
 *     <li>{@code batchStoridges}: Колекция от BatchStoridge обекти, свързани с този Container.</li>
 * </ul>
 * <p>
 * Методи:
 * <ul>
 *     <li>{@code getId()}: Извлича уникалния идентификатор на Container.</li>
 *     <li>{@code setId(Integer id)}: Задава уникалния идентификатор на Container.</li>
 *     <li>{@code getSpace()}: Извлича пространството на този Container.</li>
 *     <li>{@code setSpace(Integer space)}: Задава пространството на този Container.</li>
 *     <li>{@code getName()}: Извлича името на този Container.</li>
 *     <li>{@code setName(String name)}: Задава името на този Container.</li>
 *     <li>{@code getBatchStoridges()}: Извлича набора от BatchStoridge обекти, свързани с Container.</li>
 *     <li>{@code setBatchStoridges(Set<BatchStoridge> batchStoridges)}: Задава набора от BatchStoridge обекти, свързани с Container.</li>
 *     <li>{@code getDao()}: Предоставя DAO имплементация за взаимодействие с базата данни за Container.</li>
 *     <li>{@code toString()}: Връща String представяне на Container, включващо неговото име.</li>
 * </ul>
 * <p>
 * Връзки:
 * - Връзка един-към-много с {@code BatchStoridge entity}, свързана чрез полето "container" в BatchStoridge.
 */
@Entity
@Table(name = "container", schema = "public")
public class Container extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "container_id_gen")
    @SequenceGenerator(name = "container_id_gen", sequenceName = "container_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "space", nullable = false)
    private Integer space;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "container")
    private Set<BatchStoridge> batchStoridges = new LinkedHashSet<>();

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

    public Integer getSpace() {
        return space;
    }

    public void setSpace(Integer space) {
        this.space = space;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BatchStoridge> getBatchStoridges() {
        return batchStoridges;
    }

    public void setBatchStoridges(Set<BatchStoridge> batchStoridges) {
        this.batchStoridges = batchStoridges;
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
    public Dao<Container> getDao() {
        return new ContainerDao();
    }
}