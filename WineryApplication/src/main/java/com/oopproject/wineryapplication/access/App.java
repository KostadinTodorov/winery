package com.oopproject.wineryapplication.access;

import com.oopproject.wineryapplication.access.daos.ActDao;
import com.oopproject.wineryapplication.access.entities.Act;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ActDao actDao = new ActDao();
        Act act = new Act();
        actDao.delete(12);
        actDao.delete(13);
        System.out.println( "Hello World!" );
    }
}
