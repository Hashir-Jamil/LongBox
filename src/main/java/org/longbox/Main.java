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
    }
}