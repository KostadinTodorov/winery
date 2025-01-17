package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "behavior", schema = "public")
public class Behavior extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "behavior_id_gen")
    @SequenceGenerator(name = "behavior_id_gen", sequenceName = "behavior_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private com.oopproject.wineryapplication.access.entities.Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "act_id", nullable = false)
    private Act act;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.oopproject.wineryapplication.access.entities.Employee getEmployee() {
        return employee;
    }

    public void setEmployee(com.oopproject.wineryapplication.access.entities.Employee employee) {
        this.employee = employee;
    }

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

}