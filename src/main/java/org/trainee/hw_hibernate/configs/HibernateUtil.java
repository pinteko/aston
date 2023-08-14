package org.trainee.hw_hibernate.configs;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.trainee.hw_hibernate.entities.Group;
import org.trainee.hw_hibernate.entities.Student;
import org.trainee.hw_hibernate.entities.Teacher;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static void initializeSessionFactory() {
        try {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure() // Использует hibernate.cfg.xml по умолчанию
                    .build();

            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
            metadataSources.addAnnotatedClass(Student.class);
            metadataSources.addAnnotatedClass(Group.class);
            metadataSources.addAnnotatedClass(Teacher.class);

            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
