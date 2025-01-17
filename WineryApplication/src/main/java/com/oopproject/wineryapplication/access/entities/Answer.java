package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

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

}