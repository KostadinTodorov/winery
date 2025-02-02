package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.daos.AnswerDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents an Answer entity in the system, corresponding to the "answers" table in the database.
 * This class extends the base entity class to inherit common functionalities and adds
 * specific attributes and relationships for the "Answer" concept.
 *
 * Each instance of Answer is identified by a unique ID,
 * includes a string representing the answer, and is associated with a collection of machines.
 *
 * The Answer class also implements methods for retrieving and setting its properties,
 * interacting with a DAO, and overriding the default string representation.
 *
 * Fields:
 * - id: A unique identifier for the Answer entity.
 * - answers: A string representing the answer (maximum length 20).
 * - machines: A set of machines associated with this Answer, forming a one-to-many relationship.
 *
 * Methods:
 * - getId(): Retrieves the ID of the Answer.
 * - setId(Integer id): Sets the ID of the Answer.
 * - getDao(): Provides a DAO implementation specific to Answer for interacting with the database.
 * - getAnswers(): Retrieves the string representing the answer.
 * - setAnswers(String answers): Sets the string representing the answer.
 * - getMachines(): Retrieves the set of machines associated with the Answer.
 * - setMachines(Set<Machine> machines): Sets the machines associated with the Answer.
 *
 * Relationships:
 * - One-to-many relationship with the Machine entity, mapped by the "answer" field in Machine.
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