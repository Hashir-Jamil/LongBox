package org.longbox;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.longbox.domainobjects.dto.ComicBookDTO;
import org.longbox.persistence.entity.User;
import org.longbox.persistence.stubdatabase.ComicBookStubDB;
import org.longbox.persistence.stubdatabase.UserStubDB;
import org.longbox.utils.HibernateUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // This entry point is used to test methods informally and
        // build stub database entries for the JSON files used with GSON
        // sample code is shown below in the two comment blocks.

        /* UserStubDB userStubDB = new UserStubDB();
        userStubDB.loadUsers();
        userStubDB.serializeComicBookStubDB();

        ComicBookStubDB comicBookStubDB = new ComicBookStubDB();
        comicBookStubDB.loadComicBooks();
        comicBookStubDB.serializeComicBookStubDB(); */
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM User where id = 1");
        List<User> userList = query.list();
        System.out.println(userList.get(0).getUserName());

    }
}