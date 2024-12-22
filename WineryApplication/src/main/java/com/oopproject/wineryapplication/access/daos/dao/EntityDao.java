package com.oopproject.wineryapplication.access.daos.dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class EntityDao<T> implements Dao<T> {
    protected SessionFactory sessionFactory;

    public EntityDao() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                //.addAnnotatedClass(Act.class);
//                .addAnnotatedClass(Answer.class)
//                .addAnnotatedClass(Batch.class)
//                .addAnnotatedClass(BatchStoridge.class)
//                .addAnnotatedClass(Behavior.class)
//                .addAnnotatedClass(BottleType.class)
//                .addAnnotatedClass(Bottle.class)
//                .addAnnotatedClass(Client.class)
//                .addAnnotatedClass(ClientsOrder.class)
//                .addAnnotatedClass(Company.class)
//                .addAnnotatedClass(Container.class)
//                .addAnnotatedClass(Employee.class)
//                .addAnnotatedClass(EmptyBottle.class)
//                .addAnnotatedClass(FaultCode.class)
//                .addAnnotatedClass(Harvest.class)
//                .addAnnotatedClass(MachineType.class)
//                .addAnnotatedClass(Machine.class)
//                .addAnnotatedClass(Mix.class)
//                .addAnnotatedClass(Occupation.class)
//                .addAnnotatedClass(Person.class)
//                .addAnnotatedClass(Progress.class)
//                .addAnnotatedClass(Sort.class)
//                .addAnnotatedClass(Sweetness.class)
//                .addAnnotatedClass(WineType.class);

        try {
            this.sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException e) {
            System.out.println("Hibernate Exception: " + e.getMessage());
            System.out.println("Cause: " + e.getCause());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Session createSession() {
        try {
            return sessionFactory.openSession();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
