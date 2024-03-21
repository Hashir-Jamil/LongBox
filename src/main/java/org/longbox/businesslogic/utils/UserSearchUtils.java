package org.longbox.businesslogic.utils;

import org.longbox.domainobjects.dto.UserDto;

import java.util.List;

public class UserSearchUtils {
    public static UserDto getSearchedUser(List<UserDto> userDtoList, String query){
        UserDto user = new UserDto();
        for(UserDto u: userDtoList){
            if(u.getUserName().equalsIgnoreCase(query)){
                return u;
            }
        }
        return user;
    }
}
