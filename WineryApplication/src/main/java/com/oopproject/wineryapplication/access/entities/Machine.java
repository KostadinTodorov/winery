package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.MachineDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * Represents the Machine entity in the system, corresponding to the "machines" table in the database.
 * This class extends the base Entity class to inherit common functionalities and adds
 * specific attributes and relationships for the concept of a "Machine".
 * <p>
 * Each instance of Machine is identified by a unique ID, has attributes such as machine type,
 * production year, fault code, service and replacement requirements, service cost,
 * and a related answer.
 * <p>
 * The class also implements methods to retrieve and set its properties,
 * interact with the DAO, and override the default string representation.
 * <p>
 * Fields:
 * <ul>
 *     <li>{@code id}: Unique identifier for the Machine entity.</li>
 *     <li>{@code machineType}: The type of the machine, forming a many-to-one relationship with {@link MachineType}.</li>
 *     <li>{@code productionYear}: The year when the machine was produced, represented as a {@link LocalDate}.</li>
 *     <li>{@code faultCode}: A reference to the fault code, forming a many-to-one relationship with {@link FaultCode}.</li>
 *     <li>{@code needsService}: Indicates whether the machine needs servicing, represented as a boolean.</li>
 *     <li>{@code needsReplacement}: Indicates whether the machine needs replacement, represented as a boolean.</li>
 *     <li>{@code serviceCost}: The cost of servicing the machine, stored with a precision of 7 and scale of 3 as a {@link BigDecimal}.</li>
 *     <li>{@code answer}: A reference to an answer, forming a many-to-one relationship with {@link Answer}.</li>
 * </ul>
 * <p>
 * Methods:
 * <ul>
 *     <li>{@code getId()}: Retrieves the ID of the Machine.</li>
 *     <li>{@code setId(Integer id)}: Sets the ID of the Machine.</li>
 *     <li>{@code getMachineType()}: Retrieves the type of the Machine.</li>
 *     <li>{@code setMachineType(MachineType machineType)}: Sets the type of the Machine.</li>
 *     <li>{@code getProductionYear()}: Retrieves the production year of the Machine.</li>
 *     <li>{@code setProductionYear(LocalDate productionYear)}: Sets the production year of the Machine.</li>
 *     <li>{@code getFaultCode()}: Retrieves the fault code of the Machine.</li>
 *     <li>{@code setFaultCode(FaultCode faultCode)}: Sets the fault code of the Machine.</li>
 *     <li>{@code getNeedsService()}: Retrieves whether the Machine needs servicing.</li>
 *     <li>{@code setNeedsService(Boolean needsService)}: Sets whether the Machine needs servicing.</li>
 *     <li>{@code getNeedsReplacement()}: Retrieves whether the Machine needs replacement.</li>
 *     <li>{@code setNeedsReplacement(Boolean needsReplacement)}: Sets whether the Machine needs replacement.</li>
 *     <li>{@code getServiceCost()}: Retrieves the service cost of the Machine.</li>
 *     <li>{@code setServiceCost(BigDecimal serviceCost)}: Sets the service cost of the Machine.</li>
 *     <li>{@code getAnswer()}: Retrieves the related answer for the Machine.</li>
 *     <li>{@code setAnswer(Answer answer)}: Sets the related answer for the Machine.</li>
 *     <li>{@code getDao()}: Provides the DAO implementation specific to Machine for database interaction.</li>
 * </ul>
 * <p>
 * Relationships:
 * - Many-to-one relationship with {@code MachineType} defining the machine type.
 * - Many-to-one relationship with {@code FaultCode} defining fault codes associated with the Machine.
 * - Many-to-one relationship with {@code Answer} linking possible answers or resolutions.
 */
@Entity
@Table(name = "machines", schema = "public")
public class Machine extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machines_id_gen")
    @SequenceGenerator(name = "machines_id_gen", sequenceName = "machines_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_type_id")
    private MachineType machineType;

    @Column(name = "production_year", nullable = false)
    private LocalDate productionYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fault_code_id")
    private FaultCode faultCode;

    @Column(name = "needs_service", nullable = false)
    private Boolean needsService = false;

    @Column(name = "needs_replacement", nullable = false)
    private Boolean needsReplacement = false;

    @Column(name = "service_cost", nullable = false, precision = 7, scale = 3)
    private BigDecimal serviceCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

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

    public MachineType getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }

    public LocalDate getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(LocalDate productionYear) {
        this.productionYear = productionYear;
    }

    public FaultCode getFaultCode() {
        return faultCode;
    }

    public void setFaultCode(FaultCode faultCode) {
        this.faultCode = faultCode;
    }

    public Boolean getNeedsService() {
        return needsService;
    }

    public void setNeedsService(Boolean needsService) {
        this.needsService = needsService;
    }

    public Boolean getNeedsReplacement() {
        return needsReplacement;
    }

    public void setNeedsReplacement(Boolean needsReplacement) {
        this.needsReplacement = needsReplacement;
    }

    public BigDecimal getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(BigDecimal serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Machine> getDao() {
        return new MachineDao();
    }
}