package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.FaultCodeDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

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

    public Integer getId() {
        return id;
    }

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

    @Override
    public Dao<FaultCode> getDao() {
        return new FaultCodeDao();
    }
}