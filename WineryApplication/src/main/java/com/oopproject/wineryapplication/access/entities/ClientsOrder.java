package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.ClientsOrderDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * Представлява entity ClientsOrder в системата, съответстващо на таблицата "clients_orders" в базата данни.
 * Този клас разширява базовия клас entity, за да наследи общи функционалности и добавя
 * специфични атрибути и връзки за концепцията "ClientsOrder".
 * <p>
 * Всяка инстанция на ClientsOrder се идентифицира с уникален ID
 * и съдържа информация за клиента, поръчаното количество и дати на поръчка и завършване.
 * <p>
 * Полета:
 * <ul>
 *     <li>{@code id}: Уникален идентификатор за поръчка.</li>
 *     <li>{@code client}: Клиентът, свързан с тази поръчка.</li>
 *     <li>{@code quantity}: Количеството на поръчаните продукти.</li>
 *     <li>{@code orderDate}: Датата, на която е направена поръчката.</li>
 *     <li>{@code endDate}: Крайната дата за изпълнение на поръчката.</li>
 *     <li>{@code completionDate}: Дата, на която е завършена поръчката (ако е налична).</li>
 *     <li>{@code progress}: Статус или прогрес на изпълнение на поръчката.</li>
 *     <li>{@code endPrice}: Общо цената на поръчката.</li>
 *     <li>{@code wineType}: Типът вино, поръчан в тази поръчка.</li>
 * </ul>
 * <p>
 * Методи:
 * <ul>
 *     <li>{@code getId()}: Извлича уникалния идентификатор на поръчката.</li>
 *     <li>{@code setId(Integer id)}: Задава уникалния идентификатор на поръчката.</li>
 *     <li>{@code getClient()}: Извлича клиента, свързан с поръчката.</li>
 *     <li>{@code setClient(Client client)}: Задава клиента, свързан с поръчката.</li>
 *     <li>{@code getQuantity()}: Извлича количеството от поръчката.</li>
 *     <li>{@code setQuantity(Integer quantity)}: Задава количеството от поръчката.</li>
 *     <li>{@code getOrderDate()}: Извлича датата на поръчката.</li>
 *     <li>{@code setOrderDate(LocalDate orderDate)}: Задава датата на поръчката.</li>
 *     <li>{@code getEndDate()}: Извлича крайната дата за изпълнение на поръчката.</li>
 *     <li>{@code setEndDate(LocalDate endDate)}: Задава крайната дата за изпълнение на поръчката.</li>
 *     <li>{@code getCompletionDate()}: Извлича датата на завършване на поръчката (ако е налична).</li>
 *     <li>{@code setCompletionDate(LocalDate completionDate)}: Задава датата на завършване на поръчката.</li>
 *     <li>{@code getProgress()}: Извлича текущия прогрес/статус на поръчката.</li>
 *     <li>{@code setProgress(Progress progress)}: Задава текущия прогрес/статус на поръчката.</li>
 *     <li>{@code getEndPrice()}: Извлича крайната цена на поръчката.</li>
 *     <li>{@code setEndPrice(Double endPrice)}: Задава крайната цена на поръчката.</li>
 *     <li>{@code getWineType()}: Извлича типа вино, свързан с поръчката.</li>
 *     <li>{@code setWineType(WineType wineType)}: Задава типа вино, свързан с поръчката.</li>
 *     <li>{@code getDao()}: Предоставя DAO за изпълнение на операции с базата данни.</li>
 * </ul>
 * <p>
 * Връзки:
 * <ul>
 *     <li>Много-към-един връзка с {@code Client} (чрез полето client_id).</li>
 *     <li>Много-към-един връзка с {@code Progress} (чрез полето progress_id).</li>
 *     <li>Много-към-един връзка с {@code WineType} (чрез полето wine_type_id).</li>
 * </ul>
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