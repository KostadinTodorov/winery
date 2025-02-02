package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.SweetnessDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Класът {@code Sweetness} представлява entity, описващо категорията на сладостта
 * в контекста на система за управление на винарска изба. Това entity е свързано
 * с бутилки чрез релация "one-to-many".
 *
 * Класът наследява {@link com.oopproject.wineryapplication.access.entities.entity.Entity},
 * който предоставя базова функционалност за всички entity-та в системата.
 *
 * Анотацията {@link Entity} обозначава класа като JPA entity, а {@link Table} указва
 * неговата асоциация с таблицата "sweetness" в базата данни.
 *
 * Следните атрибути са дефинирани за класа:
 * <ul>
 *     <li>{@code id} - Уникален идентификатор на сладостта. Генерира се автоматично с помощта на {@code SequenceGenerator}.</li>
 *     <li>{@code category} - Основна категория на сладостта.</li>
 *     <li>{@code leCategory} - Допълнителна категория на сладостта.</li>
 *     <li>{@code bottles} - Сет от бутилки, които са асоциирани към дадена категория сладост.</li>
 * </ul>
 *
 * Методи на класа включват стандартни getter и setter методи за достъп до полетата,
 * както и метод за генериране на стринг представяне и достъп до съответния DAO обект.
 */
@Entity
@Table(name = "sweetness", schema = "public")
public class Sweetness extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sweetness_id_gen")
    @SequenceGenerator(name = "sweetness_id_gen", sequenceName = "sweetness_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "category", nullable = false, length = 15)
    private String category;

    @Column(name = "le_category", length = 15)
    private String leCategory;

    @OneToMany(mappedBy = "sweetness")
    private Set<Bottle> bottles = new LinkedHashSet<>();

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLeCategory() {
        return leCategory;
    }

    public void setLeCategory(String leCategory) {
        this.leCategory = leCategory;
    }

    public Set<Bottle> getBottles() {
        return bottles;
    }

    public void setBottles(Set<Bottle> bottles) {
        this.bottles = bottles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()+"["+leCategory+"("+category+")"+"]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Sweetness> getDao() {
        return new SweetnessDao();
    }
}