package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.ClientsOrderDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a ClientsOrder entity in the system, corresponding to the "clients_orders" table in the database.
 * This class extends the base entity class to inherit common functionalities and adds
 * specific attributes and relationships for the "ClientsOrder" concept.
 * <p>
 * Each instance of ClientsOrder is identified by a unique ID,
 * contains details about the client, the wine type ordered, the quantity, pricing, and order progress.
 * <p>
 * The ClientsOrder class implements methods for retrieving and setting its properties,
 * interacting with its DAO, and overriding its string representation.
 * <p>
 * Fields:
 * - id: A unique identifier for the ClientsOrder entity.
 * - client: The client associated with this order, represented as a many-to-one relationship.
 * - quantity: An integer representing the amount of wine ordered.
 * - orderDate: The date when the order was placed.
 * - endDate: The desired end date for the order.
 * - completionDate: The date when the order was completed.
 * - progress: The progress status of the order, represented as a many-to-one relationship.
 * - endPrice: The final price for the order.
 * - wineType: The type of wine ordered, represented as a many-to-one relationship.
 * <p>
 * Methods:
 * - getId(): Retrieves the ID of the ClientsOrder.
 * - setId(Integer id): Sets the ID of the ClientsOrder.
 * - getClient(): Retrieves the client associated with the order.
 * - setClient(Client client): Associates a client with the order.
 * - getWineType(): Retrieves the wine type associated with the order.
 * - setWineType(WineType wineType): Associates a wine type with the order.
 * - getOrderDate(): Retrieves the order date.
 * - setOrderDate(LocalDate orderDate): Sets the order date.
 * - getEndDate(): Retrieves the desired end date of the order.
 * - setEndDate(LocalDate endDate): Sets the desired order end date.
 * - getCompletionDate(): Retrieves the actual completion date.
 * - setCompletionDate(LocalDate completionDate): Sets the order completion date.
 * - getProgress(): Retrieves the progress of the order.
 * - setProgress(Progress progress): Updates the progress of the order.
 * - getQuantity(): Retrieves the quantity of wine ordered.
 * - setQuantity(Integer quantity): Sets the quantity of wine ordered.
 * - getEndPrice(): Retrieves the final price of the order.
 * - setEndPrice(Double endPrice): Sets the final price for the order.
 * - toString(): Returns a string representation of the ClientsOrder entity.
 * <p>
 * Relationships:
 * - Many-to-one relationship with the Client entity, referencing the client who placed the order.
 * - Many-to-one relationship with the WineType entity, indicating the type of wine requested in the order.
 * - Many-to-one relationship with the Progress entity, indicating the current progress state of the order.
 */
@Entity
@Table(name = "clients_orders", schema = "public")
public class ClientsOrder extends com.oopproject.wineryapplication.access.entities.entity.Entity {
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

    @Column(name = "end_price", nullable = false)
    private Double endPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wine_type_id", nullable = false)
    private com.oopproject.wineryapplication.access.entities.WineType wineType;


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

    public Double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Double endPrice) {
        this.endPrice = endPrice;
    }

    public com.oopproject.wineryapplication.access.entities.WineType getWineType() {
        return wineType;
    }

    public void setWineType(com.oopproject.wineryapplication.access.entities.WineType wineType) {
        this.wineType = wineType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<ClientsOrder> getDao() {
        return new ClientsOrderDao();
    }
}