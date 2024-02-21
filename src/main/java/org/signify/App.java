package org.signify;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.text.SimpleDateFormat;
import java.util.Date;

public class App 
{
    public static void main( String[] args )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction txn = null;
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Address.class);
        ServiceRegistry srvcReg = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        sessionFactory = configuration.buildSessionFactory(srvcReg);
        try {
            session = sessionFactory.openSession();
            txn = session.beginTransaction();

            Address add = new Address();
            add.setUnitNumber("123");
            add.setStreetnumber("12");
            add.setAddressLine1("John City");
            add.setAddressLine2("ck123");
            add.setState("karnataka");
            add.setPostalCode("560097");

            String date_string = "26-09-1989";

            //Instantiating the simpledateFormat class
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

            //Parsing the given String to data subject
            Date date = formatter.parse(date_string);

//            User user = new User("John Doe", "john.doe@sjdf.com", "1234567890", true, date);
//            user.setAddress(add);
//
//            session.persist(user);
//            txn.commit();

            User user = session.get(User.class, 2);
            System.out.println(user);

        }catch (Exception e) {
            if (txn != null)
                txn.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
