package org.longbox.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.longbox.domainobjects.UserDTO;

public class UserStubDB {

    private List<UserDTO> userStubData = new ArrayList<>();

    public UserStubDB() {
        UserDTO u1 = new UserDTO("Always_Scheming",
                "John",
                "Smith",
                new Date(1990, 12, 1),
                "email@domain.com",
                "asdifp483qradv",
                "Canada",
                new Date());
        userStubData.add(u1);

        UserDTO u2 = new UserDTO("Always_Throwing",
                "Neo",
                "Anderson",
                new Date(1929,1,1),
                "address@provider.ca",
                "asdf4308asdf=as03",
                "Indonesia",
                new Date());
        userStubData.add(u2);

        UserDTO u3 = new UserDTO("Phoenix",
                "Stan",
                "Lee",
                new Date(2000,4,31),
                "123fake@nowhere.org",
                "xlk;uu500834",
                "United Kingdom",
                new Date());
        userStubData.add(u3);
    }
}
