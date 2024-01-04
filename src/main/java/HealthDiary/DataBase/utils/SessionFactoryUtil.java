package HealthDiary.DataBase.utils;

import HealthDiary.DataBase.models.DbDiary;
import HealthDiary.DataBase.models.DbUser;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import HealthDiary.Param;

public class SessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private SessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration cfg = new Configuration();

                //db connection properties
                cfg.setProperty("hibernate.connection.url", "jdbc:postgresql://" +
                                                            Param.DB_HOST.getVal() + ":" +
                                                            Param.DB_PORT.getVal() + "/" +
                                                            Param.DB_NAME.getVal());
                cfg.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
                cfg.setProperty("hibernate.connection.username", Param.DB_USER.getVal());
                cfg.setProperty("hibernate.connection.password", Param.DB_PASS.getVal());
                cfg.setProperty("show_sql", "true");

                //add db objects classes
                cfg.addAnnotatedClass(DbUser.class);
                cfg.addAnnotatedClass(DbDiary.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
                sessionFactory = cfg.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.print("Исключение! ");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
