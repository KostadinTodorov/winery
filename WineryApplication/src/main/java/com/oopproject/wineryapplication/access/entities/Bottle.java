package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.BottleDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

@Entity
@Table(name = "bottles", schema = "public")
public class Bottle extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bottles_id_gen")
    @SequenceGenerator(name = "bottles_id_gen", sequenceName = "bottles_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "batch_id", nullable = false)
    private Batch batch;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sweetness_id", nullable = false)
    private Sweetness sweetness;

    @Column(name = "residual_sugar", nullable = false)
    private Short residualSugar;

    @Column(name = "filled", nullable = false)
    private Integer filled;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bottle_type", nullable = false)
    private BottleType bottleType;

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

    public Sweetness getSweetness() {
        return sweetness;
    }

    public void setSweetness(Sweetness sweetness) {
        this.sweetness = sweetness;
    }

    public Short getResidualSugar() {
        return residualSugar;
    }

    public void setResidualSugar(Short residualSugar) {
        this.residualSugar = residualSugar;
    }

    public Integer getFilled() {
        return filled;
    }

    public void setFilled(Integer filled) {
        this.filled = filled;
    }

    public BottleType getBottleType() {
        return bottleType;
    }

    public void setBottleType(BottleType bottleType) {
        this.bottleType = bottleType;
    }

    @Override
    public Dao<Bottle> getDao() {
        return new BottleDao();
    }
}