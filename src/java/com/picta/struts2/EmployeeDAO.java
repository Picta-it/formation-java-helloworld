package com.picta.struts2;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class EmployeeDAO {
    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();

        Properties properties = configuration.getProperties();

        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Employee> getEmployees()
    {

        Session session = null;
        Transaction tx=null;

        List<Employee> Employees = new ArrayList<Employee>();

        try
        {
            // Configure the session factory
            configureSessionFactory();

            session = sessionFactory.openSession();

            Employees = session.createQuery("from Employee").list();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return Employees;
    }

    public void addEmployee(Employee Employee)
    {
        // Configure the session factory
        configureSessionFactory();

        Session session = null;
        Transaction tx=null;

        try {

            tx = session.beginTransaction();
            session.save(Employee);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();

            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
            tx.rollback();
        } finally{
            if(session != null) {
                session.close();
            }
        }
    }
}
