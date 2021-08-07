package com.example.scan.Service.impl;

import com.example.scan.Bean.User;
import com.example.scan.Bean.UserMap;
import com.example.scan.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public boolean hasUser(String userId) {
        Set<User> all = UserMap.getDataBaseUsers();
        for (User user : all) {
            if(user.getUserID().equals(userId))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkUserInfo(User cur) {
        Set<User> users = UserMap.getDataBaseUsers();
        for (User user : users) {
            if(user.getUserID().equals(cur.getUserID()))
                return user.getName().equals(cur.getName());
        }
        return false;
    }
}
