package com.oopproject.wineryapplication.access.daos.dao;


import com.oopproject.wineryapplication.access.entities.*;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Абстрактна имплементация на интерфейса {@link Dao} за управление на обекти чрез Hibernate.
 *
 * <p>Този клас предоставя базова имплементация за DAO класове, които управляват обекти. Той конфигурира
 * Hibernate {@code SessionFactory} и осигурява функционалност за създаване на сесии. Конкретните
 * DAO имплементации трябва да наследят този клас, за да използват неговото поведение и при необходимост
 * да презапишат методи.</p>
 *
 * @param <T> типът на обекта, управляван от този DAO
 */
public abstract class EntityDao<T extends Entity> implements Dao<T> {
    /**
     * Hibernate {@code SessionFactory}, използван за управление на сесиите с базата данни.
     */
    protected SessionFactory sessionFactory;

    /**
     * Създава {@code EntityDao} и инициализира Hibernate {@code SessionFactory}.
     *
     * <p>Този конструктор конфигурира Hibernate, използвайки файла {@code hibernate.cfg.xml}, и
     * регистрира класовете на обектите, необходими за приложението. Ако конфигурацията не успее,
     * се хвърля {@code RuntimeException}.</p>
     *
     * @throws RuntimeException ако {@code SessionFactory} не може да бъде създаден
     */
    public EntityDao() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Act.class)
                .addAnnotatedClass(Answer.class)
                .addAnnotatedClass(Batch.class)
                .addAnnotatedClass(BatchStoridge.class)
                .addAnnotatedClass(Behavior.class)
                .addAnnotatedClass(BottleType.class)
                .addAnnotatedClass(Bottle.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(ClientsOrder.class)
                .addAnnotatedClass(Company.class)
                .addAnnotatedClass(Container.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(FaultCode.class)
                .addAnnotatedClass(Harvest.class)
                .addAnnotatedClass(MachineType.class)
                .addAnnotatedClass(Machine.class)
                .addAnnotatedClass(Mix.class)
                .addAnnotatedClass(Occupation.class)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Progress.class)
                .addAnnotatedClass(Sort.class)
                .addAnnotatedClass(Sweetness.class)
                .addAnnotatedClass(WineType.class);

        try {
            this.sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException e) {
            System.out.println("Hibernate Exception: " + e.getMessage());
            System.out.println("Cause: " + e.getCause());
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>Тази имплементация използва конфигурирания {@code SessionFactory} за създаване на нова сесия.
     * Ако възникне {@code HibernateException} по време на създаването на сесията, се хвърля
     * {@code RuntimeException}.</p>
     *
     * @return нова {@code Session} за взаимодействие с базата данни
     * @throws RuntimeException ако сесията не може да бъде създадена
     */
    @Override
    public Session createSession() {
        try {
            return sessionFactory.openSession();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}

