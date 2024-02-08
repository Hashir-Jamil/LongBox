package org.longbox;

import org.longbox.persistence.UserStubDB;

public class Main {
    public static void main(String[] args) {
        // This entry point is used to test methods informally and
        // build stub database entries for the JSON files used with GSON
        UserStubDB userStubDB = new UserStubDB();
        userStubDB.loadUsers();
        userStubDB.serializeUserStubDB();
    }
}