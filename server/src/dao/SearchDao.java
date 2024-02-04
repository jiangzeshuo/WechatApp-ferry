package dao;

import bean.user;

import java.util.List;

public interface SearchDao {
    /**
     * 获取所有用户
     * @param user
     * @return list
     */
    public  List<user> getAllUser(user user1);
    /**
     * 用户登录
     * @param user
     * @return uid:=0不存在
     */
    public String searchopenid(user user1);
    /**
     * 修改用户
     * @param user
     * @return int
     */
    public int updateUser(user user1);
    /**
     * 新加用户
     * @param user
     * @return int
     */
    public int insert(user user1);

    /**
     * 删除用户
     * @param user
     * @return int
     */
    public int delete(user user1);
}
