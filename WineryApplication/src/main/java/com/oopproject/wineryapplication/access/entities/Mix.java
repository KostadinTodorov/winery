package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.MixDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

/**
 * Класът {@code Mix} представлява entity, което съхранява информация за свързването
 * между реколта ({@link Harvest}) и партида ({@link Batch}). Този клас наследява
 * {@link com.oopproject.wineryapplication.access.entities.entity.Entity} и предоставя специфична функционалност
 * за работа с данни, свързани с винопроизводството.
 *
 * Анотации:
 * <ul>
 *     <li>{@code @Entity}: Определя класа като JPA entity.</li>
 *     <li>{@code @Table}: Дефинира името на таблицата и схемата, в която се съхранява entity-то.</li>
 *     <li>{@code @Id} и {@code @Column}: Маркират полето за уникален идентификатор и неговите настройки.</li>
 *     <li>{@code @ManyToOne} и {@code @JoinColumn}: Дефинират връзките към другите entity-та
 *         ({@link Harvest} и {@link Batch}).</li>
 * </ul>
 */
@Entity
@Table(name = "mix", schema = "public")
public class Mix extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "batch_id", nullable = false)
    private Batch batch;

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

    public Harvest getHarvest() {
        return harvest;
    }

    public void setHarvest(Harvest harvest) {
        this.harvest = harvest;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Mix> getDao() {
        return new MixDao();
    }
}