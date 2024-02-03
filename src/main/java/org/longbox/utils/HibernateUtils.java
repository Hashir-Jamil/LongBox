package org.longbox.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.longbox.authentication.User;
import org.longbox.comiccollection.ComicBookList;
import org.longbox.comics.ComicBook;
import org.longbox.comments.Comment;

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
