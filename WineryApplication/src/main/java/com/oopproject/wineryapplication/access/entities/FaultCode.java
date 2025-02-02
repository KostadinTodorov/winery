package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.FaultCodeDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Класът {@code FaultCode} представлява ентити, което съхранява информация за кодове на грешки.
 * Той е мапнат към таблица "fault_codes" в базата от данни и наследява
 * функционалности от базовия клас {@link com.oopproject.wineryapplication.access.entities.entity.Entity}.
 *
 * Основната цел на този клас е да съхранява данни за кодове на грешки,
 * които са свързани с машини в системата.
 *
 * Структурата на класа включва следните основни атрибути:
 * - {@code id}: Уникален идентификатор на кода на грешка (первично ключово поле).
 * - {@code codeName}: Име на кода на грешка, което представлява низ с ограничена дължина.
 * - {@code machines}: Набор от машини, които са свързани със съответния код на грешка.
 *
 * Освен това, класът предоставя методи за достъп и модификация на тези полета,
 * както и специфична DAO (Data Access Object) за управление на пресистентността на обектите.
 *
 * Таблична структура:
 * Таблицата "fault_codes" съдържа следните основни полета:
 * - {@code id} (основен ключ)
 * - {@code code_name} (уникално име на кода на грешка)
 */
@Entity
@Table(name = "fault_codes", schema = "public")
public class FaultCode extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fault_codes_id_gen")
    @SequenceGenerator(name = "fault_codes_id_gen", sequenceName = "fault_codes_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code_name", nullable = false, length = 12)
    private String codeName;

    @OneToMany(mappedBy = "faultCode")
    private Set<com.oopproject.wineryapplication.access.entities.Machine> machines = new LinkedHashSet<>();

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

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Set<com.oopproject.wineryapplication.access.entities.Machine> getMachines() {
        return machines;
    }

    public void setMachines(Set<com.oopproject.wineryapplication.access.entities.Machine> machines) {
        this.machines = machines;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<FaultCode> getDao() {
        return new FaultCodeDao();
    }
}