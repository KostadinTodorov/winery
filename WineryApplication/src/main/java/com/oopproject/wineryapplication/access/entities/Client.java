package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.ClientDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;



/**
 * Представлява entity Client в системата, съответстващо на таблицата "client" в базата данни.
 * Този клас разширява базовия клас entity, за да наследи общи функционалности и добавя
 * специфични атрибути и връзки за концепцията "Client".
 * <p>
 * Всяка инстанция на Client се идентифицира с уникален ID, може да бъде свързан с
 * физическо или юридическо лице и е асоцииран с множество поръчки.
 * <p>
 * Полета:
 * <ul>
 *     <li>{@code id}: Уникален идентификатор за entity Client.</li>
 *     <li>{@code person}: Информация за физическо лице, свързано с този Client (много-към-един).</li>
 *     <li>{@code company}: Данни за юридическо лице, свързано с този Client (много-към-един).</li>
 *     <li>{@code clientsOrders}: Набор от поръчки, свързани с този Client (едно-към-много).</li>
 * </ul>
 * <p>
 * Методи:
 * <ul>
 *     <li>{@code getId()}: Извлича ID на Client.</li>
 *     <li>{@code setId(Integer id)}: Задава ID на Client.</li>
 *     <li>{@code getPerson()}: Извлича свързаното физическо лице.</li>
 *     <li>{@code setPerson(Person person)}: Задава свързаното физическо лице.</li>
 *     <li>{@code getCompany()}: Извлича свързаното юридическо лице.</li>
 *     <li>{@code setCompany(Company company)}: Задава свързаното юридическо лице.</li>
 *     <li>{@code getClientsOrders()}: Извлича поръчките, свързани с този Client.</li>
 *     <li>{@code setClientsOrders(Set<ClientsOrder> clientsOrders)}: Задава поръчките, свързани с този Client.</li>
 *     <li>{@code getDao()}: Предоставя DAO имплементация, специфична за Client, за взаимодействие с базата данни.</li>
 *     <li>{@code toString()}: Връща string представяне на entity Client, включвайки базовото представяне и името на свързаното лице.</li>
 * </ul>
 * <p>
 * Връзки:
 * - Много-към-един с {@code Person entity}.
 * - Много-към-един с {@code Company entity}.
 * - Едно-към-много с {@code ClientsOrder entity}.
 */
@Entity
@Table(name = "client", schema = "public")
public class Client extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_gen")
    @SequenceGenerator(name = "client_id_gen", sequenceName = "client_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private com.oopproject.wineryapplication.access.entities.Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private com.oopproject.wineryapplication.access.entities.Company company;

    @OneToMany(mappedBy = "client")
    private Set<com.oopproject.wineryapplication.access.entities.ClientsOrder> clientsOrders = new LinkedHashSet<>();

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

    public com.oopproject.wineryapplication.access.entities.Person getPerson() {
        return person;
    }

    public void setPerson(com.oopproject.wineryapplication.access.entities.Person person) {
        this.person = person;
    }

    public com.oopproject.wineryapplication.access.entities.Company getCompany() {
        return company;
    }

    public void setCompany(com.oopproject.wineryapplication.access.entities.Company company) {
        this.company = company;
    }

    public Set<com.oopproject.wineryapplication.access.entities.ClientsOrder> getClientsOrders() {
        return clientsOrders;
    }

    public void setClientsOrders(Set<com.oopproject.wineryapplication.access.entities.ClientsOrder> clientsOrders) {
        this.clientsOrders = clientsOrders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()+"["+person.getPersonName()+"]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Client> getDao() {
        return new ClientDao();
    }
}