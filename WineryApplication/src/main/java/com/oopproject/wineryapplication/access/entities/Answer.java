package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.AnswerDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * Представлява entity Answer в системата, съответстващо на таблицата "answers" в базата данни.
 * Този клас разширява базовия клас entity, за да наследи общи функционалности и добавя
 * специфични атрибути и връзки за концепцията "Answer".
 * <p>
 * Всяка инстанция на Answer се идентифицира с уникален ID,
 * има низ отговор и е свързана с колекция от машини.
 * <p>
 * Класът Answer също имплементира методи за извличане и задаване на неговите свойства,
 * взаимодействие с DAO и предефиниране на string представянето по подразбиране.
 * <p>
 * Полета:
 * <ul>
 *     <li>{@code id}: Уникален идентификатор за entity Answer.</li>
 *     <li>{@code answers}: Низ, представляващ отговора (максимална дължина 20).</li>
 *     <li>{@code machines}: Набор от машини, свързани с този Answer, образуващи връзка един-към-много.</li>
 * </ul>
 * <p>
 * Методи:
 * <ul>
 *     <li>{@code getId()}: Извлича ID на Answer.</li>
 *     <li>{@code setId(Integer id)}: Задава ID на Answer.</li>
 *     <li>{@code getDao()}: Предоставя DAO имплементация, специфична за Answer, за взаимодействие с базата данни.</li>
 *     <li>{@code getAnswers()}: Извлича отговора.</li>
 *     <li>{@code setAnswers(String answers)}: Задава отговора.</li>
 *     <li>{@code getMachines()}: Извлича набора от машини, свързани с Answer.</li>
 *     <li>{@code setMachines(Set<Machine> machines)}: Задава машините, свързани с Answer.</li>
 * </ul>
 * <p>
 * Връзки:
 * - Връзка един-към-много с {@code Machine entity}, съпоставена чрез полето "answer" в Machine.
 */
@Entity
@Table(name = "answers", schema = "public")
public class Answer extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answers_id_gen")
    @SequenceGenerator(name = "answers_id_gen", sequenceName = "answers_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "answers", nullable = false, length = 20)
    private String answers;

    @OneToMany(mappedBy = "answer")
    private Set<com.oopproject.wineryapplication.access.entities.Machine> machines = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public Set<com.oopproject.wineryapplication.access.entities.Machine> getMachines() {
        return machines;
    }

    public void setMachines(Set<com.oopproject.wineryapplication.access.entities.Machine> machines) {
        this.machines = machines;
    }

    @Override
    public Dao<Answer> getDao() {
        return new AnswerDao();
    }
}