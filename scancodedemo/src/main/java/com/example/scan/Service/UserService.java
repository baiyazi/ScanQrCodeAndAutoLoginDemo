package com.example.scan.Service;

import com.example.scan.Bean.User;

/**
 * 检查模拟数据库中用户
 */
public interface UserService {
    boolean hasUser(String userId);
    boolean checkUserInfo(User user);
}
