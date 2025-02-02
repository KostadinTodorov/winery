package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.MachineTypeDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Представлява entity MachineType в системата, съответстващо на таблицата "machine_type" в базата данни.
 * Този клас разширява {@link com.oopproject.wineryapplication.access.entities.entity.Entity}
 * и наследява общата функционалност от него, като добавя специфични атрибути и връзки, свързани с концепцията за "MachineType".
 *
 * MachineType е предназначен да опише типа на дадена машина и има свързани машини,
 * които са моделирани чрез връзка един-към-много.
 *
 * Полета:
 * <ul>
 *     <li>{@code id}: Уникален идентификатор за MachineType.</li>
 *     <li>{@code machineType}: Тип на машината, представен като низ с максимална дължина 12 знака.</li>
 *     <li>{@code machines}: Набор от {@link Machine}, които са свързани с този MachineType.</li>
 * </ul>
 *
 * Методи:
 * <ul>
 *     <li>{@code getId()}: Извлича идентификатора на текущото MachineType.</li>
 *     <li>{@code setId(Integer id)}: Задава идентификатора на MachineType.</li>
 *     <li>{@code getMachineType()}: Извлича типа на текущото MachineType като низ.</li>
 *     <li>{@code setMachineType(String machineType)}: Задава типа на MachineType.</li>
 *     <li>{@code getMachines()}: Извлича набора от обекти {@link Machine}, свързани с MachineType.</li>
 *     <li>{@code setMachines(Set<Machine> machines)}: Задава набора от свързани машини.</li>
 *     <li>{@code toString()}: Връща низово представяне на MachineType, включващо уникалния идентификатор
 *         и стойността на {@code machineType}. Наследява {@inheritDoc} от базовия клас {@link com.oopproject.wineryapplication.access.entities.entity.Entity}.</li>
 *     <li>{@code getDao()}: Връща {@link Dao} обект за управление на MachineType в базата данни.</li>
 * </ul>
 *
 * Връзки:
 * <ul>
 *     <li>Връзка един-към-много с {@link Machine}, осъществявана чрез полето {@code machines}.</li>
 * </ul>
 */
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