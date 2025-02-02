package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.BottleDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;


/**
 * Represents a Bottle entity in the system, corresponding to the "bottles" table in the database.
 * This class extends the base entity class to inherit common functionalities and adds
 * specific attributes and relationships for the "Bottle" concept.
 * <p>
 * Each instance of Bottle is identified by a unique ID,
 * is linked to a specific batch and sweetness level, and includes details about
 * its type, residual sugar content, and whether it is filled.
 * <p>
 * The Bottle class also implements methods for retrieving and setting its properties,
 * interacting with a DAO, and overriding the default string representation.
 * <p>
 * Fields:
 * - id: A unique identifier for the Bottle entity.
 * - batch: A Batch entity associated with this Bottle, forming a many-to-one relationship.
 * - sweetness: A Sweetness entity representing the sweetness level, forming a many-to-one relationship.
 * - residualSugar: A short value representing the residual sugar content of the Bottle.
 * - filled: An integer indicating whether the Bottle is filled.
 * - bottleType: A BottleType entity representing the type of Bottle, forming a many-to-one relationship.
 * <p>
 * Methods:
 * - getId(): Retrieves the ID of the Bottle.
 * - setId(Integer id): Sets the ID of the Bottle.
 * - getBatch(): Retrieves the associated Batch of the Bottle.
 * - setBatch(Batch batch): Associates a Batch with the Bottle.
 * - getSweetness(): Retrieves the sweetness level of the Bottle.
 * - setSweetness(Sweetness sweetness): Sets the sweetness level of the Bottle.
 * - getResidualSugar(): Retrieves the residual sugar content of the Bottle.
 * - setResidualSugar(Short residualSugar): Sets the residual sugar content of the Bottle.
 * - getFilled(): Retrieves the filled status of the Bottle.
 * - setFilled(Integer filled): Sets the filled status of the Bottle.
 * - getBottleType(): Retrieves the type of the Bottle.
 * - setBottleType(BottleType bottleType): Sets the type of Bottle.
 * - getDao(): Provides a DAO implementation specific to Bottle for interacting with the database.
 * - toString(): Returns a string representation of the Bottle entity including its type and state.
 * <p>
 * Relationships:
 * - Many-to-one relationship with the Batch entity, mapped by the "batch_id" field.
 * - Many-to-one relationship with the Sweetness entity, mapped by the "sweetness_id" field.
 * - Many-to-one relationship with the BottleType entity, mapped by the "bottle_type" field.
 */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Bottle> getDao() {
        return new BottleDao();
    }
}