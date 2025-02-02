package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.CompanyDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * Представлява entity Company в системата, съответстващо на таблицата "company" в базата данни.
 * Този клас разширява базовия клас entity, за да наследи общи функционалности и добавя
 * специфични атрибути и връзки за концепцията "Company".
 * <p>
 * Всяка инстанция на Company се идентифицира с уникален ID,
 * има адрес и е свързана с колекция от клиенти.
 * <p>
 * Класът Company също имплементира методи за извличане и задаване на неговите свойства,
 * взаимодействие с DAO и предефиниране на string представянето по подразбиране.
 * <p>
 * Полета:
 * <ul>
 *     <li>{@code id}: Уникален идентификатор за entity Company.</li>
 *     <li>{@code address}: Низ, представляващ адреса на компанията (максимална дължина 200).</li>
 *     <li>{@code clients}: Набор от клиенти, свързани с тази компания, образуващи връзка един-към-много.</li>
 * </ul>
 * <p>
 * Методи:
 * <ul>
 *     <li>{@code getId()}: Извлича ID на Company.</li>
 *     <li>{@code setId(Integer id)}: Задава ID на Company.</li>
 *     <li>{@code getAddress()}: Извлича адреса на Company.</li>
 *     <li>{@code setAddress(String address)}: Задава адреса на Company.</li>
 *     <li>{@code getClients()}: Извлича набора от клиенти, свързани с Company.</li>
 *     <li>{@code setClients(Set<Client> clients)}: Задава клиентите, свързани с Company.</li>
 *     <li>{@code getDao()}: Предоставя DAO имплементация, специфична за Company, за взаимодействие с базата данни.</li>
 * </ul>
 * <p>
 * Връзки:
 * - Връзка един-към-много с {@code Client entity}, съпоставена чрез полето "company" в Client.
 */
@Entity
@Table(name = "company", schema = "public")
public class Company extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_gen")
    @SequenceGenerator(name = "company_id_gen", sequenceName = "company_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @OneToMany(mappedBy = "company")
    private Set<Client> clients = new LinkedHashSet<>();

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Company> getDao() {
        return new CompanyDao();
    }
}