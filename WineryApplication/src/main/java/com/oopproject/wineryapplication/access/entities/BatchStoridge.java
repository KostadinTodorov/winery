package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.BatchStoridgeDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

/**
 * Represents a BatchStoridge entity in the system, corresponding to the "batch_storidge" table in the database.
 * This class extends the base entity class and provides attributes and relationships specific to the
 * "BatchStoridge" concept.
 * <p>
 * Each instance of BatchStoridge is identified by a unique ID, maintains a reference to a batch,
 * references a container, and keeps track of the volume stored.
 * <p>
 * The BatchStoridge class also implements methods for retrieving and setting its properties,
 * and provides a DAO implementation for interacting with the database.
 * <p>
 * Fields:
 * - id: A unique identifier for the entity.
 * - batch: A many-to-one relationship with the Batch entity, representing a specific batch.
 * - container: A many-to-one relationship with the Container entity, representing the container associated with the batch.
 * - volumeStored: An integer storing the volume of the batch stored in the container.
 * <p>
 * Methods:
 * - getId(): Retrieves the ID of the BatchStoridge entity.
 * - setId(Integer id): Sets the ID of the BatchStoridge entity.
 * - getBatch(): Retrieves the Batch associated with this entity.
 * - setBatch(Batch batch): Sets the Batch associated with this entity.
 * - getContainer(): Retrieves the Container associated with this entity.
 * - setContainer(Container container): Sets the Container associated with this entity.
 * - getVolumeStored(): Retrieves the volume stored in the container.
 * - setVolumeStored(Integer volume_stored): Sets the volume stored in the container.
 * - getDao(): Returns a DAO for interacting with the BatchStoridge entity in the database.
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