package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.MachineTypeDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "machine_type", schema = "public")
public class MachineType extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_type_id_gen")
    @SequenceGenerator(name = "machine_type_id_gen", sequenceName = "machine_type_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "machine_type", nullable = false, length = 12)
    private String machineType;

    @OneToMany(mappedBy = "machineType")
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

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
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
    public String toString() {
        return super.toString()+"["+machineType+"]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<MachineType> getDao() {
        return new MachineTypeDao();
    }

}