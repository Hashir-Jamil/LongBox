package org.longbox;

import org.longbox.persistence.dao.ComicBookDaoImpl;
import org.longbox.persistence.dao.ComicBookFinishedListDaoImpl;
import org.longbox.persistence.dao.UserDaoImpl;
import org.longbox.persistence.entity.ComicBook;
import org.longbox.persistence.entity.User;

public class Main {
    public static void main(String[] args) {
        // This entry point is used to test methods informally and
        // build stub database entries for the JSON files used with GSON
        // sample code is shown below in the two comment blocks.
        UserDaoImpl u = new UserDaoImpl();
        ComicBookDaoImpl cb = new ComicBookDaoImpl();

        ComicBook cbdto = cb.getComicBookById(5);

        User user;

        ComicBookFinishedListDaoImpl finishedListDao = new ComicBookFinishedListDaoImpl();

/*        try {
            user = u.getUserById(2);
            System.out.println(user);
        } catch (UserIDDoesNotExistException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        boolean success;


        try {
            success = finishedListDao.saveToFinished(user, cbdto);
            System.out.println(success);
        } catch (UserIDDoesNotExistException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }*/

        int result;

        try {
            finishedListDao.removeFromFinished(Long.valueOf(2),Long.valueOf(3));
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        System.out.println(finishedListDao.getUsersFinishedList(Long.valueOf(2)));

    }



}