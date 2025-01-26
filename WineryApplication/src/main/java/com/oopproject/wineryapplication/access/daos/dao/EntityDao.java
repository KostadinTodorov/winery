package com.oopproject.wineryapplication.access.daos.dao;


import com.oopproject.wineryapplication.access.entities.*;
import com.oopproject.wineryapplication.access.entities.entity.Entity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Abstract implementation of the {@link Dao} interface for entity management using Hibernate.
 *
 * <p>This class provides a base implementation for DAO classes that manage entities. It sets up
 * the Hibernate {@code SessionFactory} and provides functionality to create sessions. Concrete
 * DAO implementations should extend this class to inherit its behavior and optionally override
 * methods.</p>
 *
 * @param <T> the type of entity managed by this DAO
 */
public abstract class EntityDao<T extends Entity> implements Dao<T> {
    /**
     * The Hibernate {@code SessionFactory} used to manage database sessions.
     */
    protected SessionFactory sessionFactory;

    /**
     * Constructs an {@code EntityDao} and initializes the Hibernate {@code SessionFactory}.
     *
     * <p>This constructor configures Hibernate using the {@code hibernate.cfg.xml} file and
     * registers entity classes required for the application. If the configuration fails,
     * a {@code RuntimeException} is thrown.</p>
     *
     * @throws RuntimeException if the Hibernate {@code SessionFactory} cannot be created
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
     * <p>This implementation uses the configured {@code SessionFactory} to create a new session.
     * If a {@code HibernateException} occurs during session creation, a {@code RuntimeException}
     * is thrown.</p>
     *
     * @return a new {@code Session} for database interactions
     * @throws RuntimeException if the session cannot be created
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

