package org.longbox.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.longbox.persistence.entity.User;
import org.longbox.persistence.entity.ComicBookList;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.Comment;

public class HibernateUtils {
    private static SessionFactory sessionFactory;
    private static SessionFactory getSessionFactory() {
        //Singleton
        if (sessionFactory == null) {
            Configuration configuration = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(ComicBook.class)
                    .addAnnotatedClass(ComicBookList.class)
                    .addAnnotatedClass(Comment.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
