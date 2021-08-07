package com.example.scan.Bean;


import java.util.*;

import org.springframework.stereotype.Component;


/**
 * 注册这个组件为全局唯一
 */

@Component
public class UserMap {
    private static Map<String, User> loginmap = new HashMap<String, User>();
    private static Set<User> users = new HashSet<>();

    private static Object object = new Object();
    private static volatile UserMap userMap;

    private UserMap(){}

    static {
        // 模拟：数据库中存在两个用户
        users.add(new User("123", "qwe"));
        users.add(new User("345", "ert"));
    }

    // 这个单例没有用到
    public static UserMap getInstance(){
        if(userMap == null){
            synchronized (object){
                if(userMap == null){
                    userMap = new UserMap();
                }
            }
        }
        return userMap;
    }

    public static boolean loginUser(String uuid, User user){
        if(loginmap.containsKey(uuid)){
            return false;
        }else{
            loginmap.put(uuid, user);
        }
        return true;
    }

    public static User getScanUserByUUID(String uuid){
        return loginmap.getOrDefault(uuid, null);
    }

    public static Set<User> getDataBaseUsers(){
        return users;
    }
}
