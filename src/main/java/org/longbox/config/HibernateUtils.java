package org.longbox.config;

import org.longbox.domainobjects.entity.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        //Singleton
        if (sessionFactory == null) {
            Configuration configuration = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(ComicBook.class)
                    .addAnnotatedClass(ComicBookFavoritesList.class)
                    .addAnnotatedClass(ComicBookReadingList.class)
                    .addAnnotatedClass(ComicBookFinishedList.class)
                    .addAnnotatedClass(Comment.class)
                    .addAnnotatedClass(StarRating.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
