package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.BatchStoridgeDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;


/**
 * Представлява entity BatchStoridge в системата, съответстващо на таблицата "batch_storidge" в базата данни.
 * Този клас разширява базовия клас entity, за да наследи общи функционалности и добавя
 * специфични атрибути и връзки за концепцията "BatchStoridge".
 * <p>
 * Всяка инстанция на BatchStoridge се идентифицира с уникален ID,
 * свързана с даден Batch, съдържателен контейнер и обемът на съхранената течност.
 * <p>
 * Класът BatchStoridge също имплементира методи за извличане и задаване на неговите свойства и
 * взаимодействие с DAO.
 * <p>
 * Полета:
 * <ul>
 *     <li>{@code id}: Уникален идентификатор за entity BatchStoridge.</li>
 *     <li>{@code batch}: Референция към обекта {@code Batch}, представляваща връзка много-към-един.</li>
 *     <li>{@code container}: Референция към обекта {@code Container}, представляваща връзка много-към-един.</li>
 *     <li>{@code volumeStored}: Цяло число, представляващо обема на съхранената течност.</li>
 * </ul>
 * <p>
 * Методи:
 * <ul>
 *     <li>{@code getId()}: Извлича ID на BatchStoridge.</li>
 *     <li>{@code setId(Integer id)}: Задава ID на BatchStoridge.</li>
 *     <li>{@code getBatch()}: Извлича референцията към {@code Batch} обекта.</li>
 *     <li>{@code setBatch(Batch batch)}: Задава референция към {@code Batch} обекта.</li>
 *     <li>{@code getContainer()}: Извлича референцията към {@code Container} обекта.</li>
 *     <li>{@code setContainer(Container container)}: Задава референция към {@code Container} обекта.</li>
 *     <li>{@code getVolumeStored()}: Извлича съхранения обем.</li>
 *     <li>{@code setVolumeStored(Integer volumeStored)}: Задава съхранения обем.</li>
 *     <li>{@code getDao()}: Предоставя DAO имплементация, специфична за BatchStoridge, за взаимодействие с базата данни.</li>
 * </ul>
 */
@Entity
@Table(name = "batch_storidge", schema = "public")
public class BatchStoridge extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "batch_storidge_id_gen")
    @SequenceGenerator(name = "batch_storidge_id_gen", sequenceName = "batch_storidge_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "batch_id", nullable = false)
    private Batch batch;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "container_id", nullable = false)
    private com.oopproject.wineryapplication.access.entities.Container container;

    @Column(name = "volume_stored", nullable = false)
    private Integer volumeStored;

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

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public com.oopproject.wineryapplication.access.entities.Container getContainer() {
        return container;
    }

    public void setContainer(com.oopproject.wineryapplication.access.entities.Container container) {
        this.container = container;
    }

    public Integer getVolumeStored() {
        return volumeStored;
    }

    public void setVolumeStored(Integer volume_stored) {
        this.volumeStored = volume_stored;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<BatchStoridge> getDao() {
        return new BatchStoridgeDao();
    }
}