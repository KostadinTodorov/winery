package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.BottleDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;



/**
 * Представлява entity Bottle в системата, съответстващо на таблицата "bottles" в базата данни.
 * Този клас разширява базовия клас entity, за да наследи общи функционалности и добавя
 * специфични атрибути и връзки, свързани с концепцията "бутилка".
 * <p>
 * Всяка инстанция на Bottle се идентифицира с уникален ID,
 * съдържа информация за производствената партида, типа бутилка, степента на сладост,
 * остатъчната захар и дали бутилката е била напълнена.
 * <p>
 * Полета:
 * <ul>
 *     <li>{@code id}: Уникален идентификатор за entity Bottle.</li>
 *     <li>{@code batch}: Връзка с Batch обекта, представляващ производствената партида.</li>
 *     <li>{@code sweetness}: Връзка със Sweetness обекта, указващ степента на сладост.</li>
 *     <li>{@code residualSugar}: Стойност, указваща остатъчната захар в бутилката (в краткосрочен формат).</li>
 *     <li>{@code filled}: Цяло число, указващо дали бутилката е била напълнена.</li>
 *     <li>{@code bottleType}: Връзка с BottleType, представляващ типа бутилка.</li>
 * </ul>
 * <p>
 * Методи:
 * <ul>
 *     <li>{@code getId()}: Извлича ID на Bottle.</li>
 *     <li>{@code setId(Integer id)}: Задава ID на Bottle.</li>
 *     <li>{@code getBatch()}: Извлича производствената партида.</li>
 *     <li>{@code setBatch(Batch batch)}: Задава производствената партида.</li>
 *     <li>{@code getSweetness()}: Извлича степента на сладост.</li>
 *     <li>{@code setSweetness(Sweetness sweetness)}: Задава степента на сладост.</li>
 *     <li>{@code getResidualSugar()}: Извлича остатъчната захар.</li>
 *     <li>{@code setResidualSugar(Short residualSugar)}: Задава остатъчната захар.</li>
 *     <li>{@code getFilled()}: Извлича състоянието на запълване.</li>
 *     <li>{@code setFilled(Integer filled)}: Задава състоянието на запълване.</li>
 *     <li>{@code getBottleType()}: Извлича типа на бутилката.</li>
 *     <li>{@code setBottleType(BottleType bottleType)}: Задава типа на бутилката.</li>
 *     <li>{@code getDao()}: Предоставя DAO имплементация за Bottle, използвана за взаимодействие с базата данни.</li>
 * </ul>
 * <p>
 * Връзки:
 * - Много-към-един връзки с {@code Batch}, {@code Sweetness}, и {@code BottleType}.
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