package org.longbox.businesslogic.utils;

public class GenreUtils {

    public static String[] genreStringToList(String genres) {
        return genres.split(",\\s*");
    }

    public static String genreListToString(String[] genresList) {
        String genres = "";
        if (genresList == null) {
            return genres;
        }
        if (genresList.length == 0) {
            return genres;
        }
        if (genresList.length == 1) {
            return genresList[0];
        }
        for (int i = 0; i < genresList.length - 1; i++) {
            genres = genres + genresList[i] + ", ";
        }
        genres = genres + genresList[genresList.length - 1];
        return genres;
    }
}
