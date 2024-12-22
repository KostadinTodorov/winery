package com.access.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "machine_type", schema = "public")
public class MachineType extends com.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_type_id_gen")
    @SequenceGenerator(name = "machine_type_id_gen", sequenceName = "machine_type_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "machine_type", nullable = false, length = 12)
    private String machineType;

    @OneToMany(mappedBy = "machineType")
    private Set<com.access.entities.Machine> machines = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public Set<com.access.entities.Machine> getMachines() {
        return machines;
    }

    public void setMachines(Set<com.access.entities.Machine> machines) {
        this.machines = machines;
    }

}