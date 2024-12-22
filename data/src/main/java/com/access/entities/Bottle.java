package com.access.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "bottles", schema = "public")
public class Bottle extends com.access.entities.entity.Entity {
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
    private com.access.entities.Sweetness sweetness;

    @Column(name = "residual_sugar", nullable = false)
    private Short residualSugar;

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

    public com.access.entities.Sweetness getSweetness() {
        return sweetness;
    }

    public void setSweetness(com.access.entities.Sweetness sweetness) {
        this.sweetness = sweetness;
    }

    public Short getResidualSugar() {
        return residualSugar;
    }

    public void setResidualSugar(Short residualSugar) {
        this.residualSugar = residualSugar;
    }

}