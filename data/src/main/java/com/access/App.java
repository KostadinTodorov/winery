package com.access;

import com.access.daos.ActDao;
import com.access.entities.Act;
import jakarta.persistence.Access;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Act act = new Act();
        act.setName("smoking on duty outside");
        act.setWeight(-75);
        ActDao actDao = new ActDao();
        actDao.add(act);
        act.setName("smoking on duty inside");
        act.setWeight(-150);
        actDao.add(act);
        System.out.println( "Hello World!" );
    }
}
