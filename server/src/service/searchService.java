package service;

import bean.user;

import java.util.List;

public interface searchService {
    public List<user> getAllusers(user user1);
    /**
     * 添加模块
     * @param web
     * @return true:success
     */
    public boolean newuser(user user1);
    /**
     * 逻辑删除用户
     * @param uid
     * @return int
     */
    public boolean deleteUserById(user user1);
    /**
     * 修改用户
     * @param user
     * @return true:success
     */
    public boolean updateUser(user user1);
    /**
     * 查找用户
     * @param user
     * @return true:success
     */
    public boolean searchUser(user user1);
}
