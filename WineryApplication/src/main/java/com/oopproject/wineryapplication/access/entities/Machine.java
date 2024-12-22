package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "machines", schema = "public")
public class Machine {
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

    public Integer getId() {
        return id;
    }

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

}