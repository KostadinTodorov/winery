module com.oopproject.wineryapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires org.postgresql.jdbc;


    opens com.oopproject.wineryapplication to javafx.fxml;
    exports com.oopproject.wineryapplication;
    exports com.oopproject.wineryapplication.controller;
    opens com.oopproject.wineryapplication.controller to javafx.fxml;
    exports com.oopproject.wineryapplication.access;
    opens com.oopproject.wineryapplication.access to org.hibernate.orm.core;
    exports com.oopproject.wineryapplication.access.daos.dao;
    opens com.oopproject.wineryapplication.access.daos.dao to org.hibernate.orm.core;
    exports com.oopproject.wineryapplication.access.daos;
    opens com.oopproject.wineryapplication.access.daos to org.hibernate.orm.core;
    exports com.oopproject.wineryapplication.access.entities;
    opens com.oopproject.wineryapplication.access.entities to javafx.fxml, org.hibernate.orm.core;
    exports com.oopproject.wineryapplication.access.entities.entity;
    opens com.oopproject.wineryapplication.access.entities.entity to javafx.fxml, org.hibernate.orm.core;
    exports com.oopproject.wineryapplication.access.entities.entity.contracts;
    opens com.oopproject.wineryapplication.access.entities.entity.contracts to javafx.fxml, org.hibernate.orm.core;
    exports com.oopproject.wineryapplication.access.entities.helper;
    opens com.oopproject.wineryapplication.access.entities.helper to javafx.fxml, org.hibernate.orm.core;

}