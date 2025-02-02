package com.oopproject.wineryapplication.access.entities;

import com.oopproject.wineryapplication.access.daos.BottleTypeDao;
import com.oopproject.wineryapplication.access.daos.PersonDao;
import com.oopproject.wineryapplication.access.daos.dao.Dao;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Клас, представляващ ентитет "Person" в базата данни.
 * Таблицата за този ентитет е дефинирана с име {@code person} в схемата {@code public}.
 * Класът наследява {@link com.oopproject.wineryapplication.access.entities.entity.Entity}
 * и предоставя функционалност за управление на данни, свързани с лица, в контекста на системата.
 *
 * Атрибутите на ентитета представляват информация за лицето като име, възраст, имейл,
 * пол, телефонен номер и взаимоотношения с клиенти и служители.
 *
 * Връзките {@code @OneToMany} дефинират зависимости с други ентитети:
 * {@link Client} и {@link Employee}.
 */
@Entity
@Table(name = "person", schema = "public")
public class Person extends com.oopproject.wineryapplication.access.entities.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_gen")
    @SequenceGenerator(name = "person_id_gen", sequenceName = "person_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "person_name", nullable = false, length = 30)
    private String personName;

    @Column(name = "age", nullable = false)
    private Short age;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "is_male", nullable = false)
    private Boolean isMale = false;

    @Column(name = "phone_number", nullable = false, length = 12)
    private String phoneNumber;

    @OneToMany(mappedBy = "person")
    private Set<Client> clients = new LinkedHashSet<>();

    @OneToMany(mappedBy = "person")
    private Set<Employee> employees = new LinkedHashSet<>();

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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsMale() {
        return isMale;
    }

    public void setIsMale(Boolean isMale) {
        this.isMale = isMale;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString()+"["+personName+"]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dao<Person> getDao() {
        return new PersonDao();
    }
}