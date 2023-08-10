package org.trainee.hw_hibernate.configs;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class HibernateContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Создаем и инициализируем SessionFactory
        HibernateUtil.initializeSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Закрываем SessionFactory при завершении работы приложения
        HibernateUtil.getSessionFactory().close();
    }
}