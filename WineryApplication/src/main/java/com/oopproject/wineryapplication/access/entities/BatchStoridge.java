package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.BatchStoridgeDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

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

    public Integer getId() {
        return id;
    }

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

    @Override
    public Dao<BatchStoridge> getDao() {
        return new BatchStoridgeDao();
    }
}