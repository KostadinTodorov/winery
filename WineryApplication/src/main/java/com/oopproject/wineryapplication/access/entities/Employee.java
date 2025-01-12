package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "employee", schema = "public")
public class Employee extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_gen")
    @SequenceGenerator(name = "employee_id_gen", sequenceName = "employee_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private com.oopproject.wineryapplication.access.entities.Person person;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "occupation_id", nullable = false)
    private com.oopproject.wineryapplication.access.entities.Occupation occupation;

    @OneToMany(mappedBy = "employee")
    private Set<Behavior> behaviors = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.oopproject.wineryapplication.access.entities.Person getPerson() {
        return person;
    }

    public void setPerson(com.oopproject.wineryapplication.access.entities.Person person) {
        this.person = person;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public com.oopproject.wineryapplication.access.entities.Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(com.oopproject.wineryapplication.access.entities.Occupation occupation) {
        this.occupation = occupation;
    }

    public Set<Behavior> getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(Set<Behavior> behaviors) {
        this.behaviors = behaviors;
    }
}