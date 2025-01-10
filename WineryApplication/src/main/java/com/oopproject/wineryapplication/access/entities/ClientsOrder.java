package com.oopproject.wineryapplication.access.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "clients_orders", schema = "public")
public class ClientsOrder implements com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clients_orders_id_gen")
    @SequenceGenerator(name = "clients_orders_id_gen", sequenceName = "clients_orders_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "progress_id")
    private com.oopproject.wineryapplication.access.entities.Progress progress;

    @Column(name = "end_price", nullable = false, precision = 7, scale = 3)
    private BigDecimal endPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wine_type_id", nullable = false)
    private com.oopproject.wineryapplication.access.entities.WineType wineType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public com.oopproject.wineryapplication.access.entities.Progress getProgress() {
        return progress;
    }

    public void setProgress(com.oopproject.wineryapplication.access.entities.Progress progress) {
        this.progress = progress;
    }

    public BigDecimal getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(BigDecimal endPrice) {
        this.endPrice = endPrice;
    }

    public com.oopproject.wineryapplication.access.entities.WineType getWineType() {
        return wineType;
    }

    public void setWineType(com.oopproject.wineryapplication.access.entities.WineType wineType) {
        this.wineType = wineType;
    }

}