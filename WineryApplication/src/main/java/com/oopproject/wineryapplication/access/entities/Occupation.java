package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.OccupationDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "occupation", schema = "public")
public class Occupation extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "occupation_id_gen")
    @SequenceGenerator(name = "occupation_id_gen", sequenceName = "occupation_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "occupation", nullable = false, length = 20)
    private String occupation;

    @OneToMany(mappedBy = "occupation")
    private Set<Employee> employees = new LinkedHashSet<>();

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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()+"["+occupation+"]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Occupation> getDao() {
        return new OccupationDao();
    }
}