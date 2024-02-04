package service.imp;

import bean.user;
import dao.SearchDao;
import dao.imp.SearchDaoImp;
import service.searchService;

import java.util.List;

public class searchServiceImp implements searchService {
    private SearchDao userDao = new SearchDaoImp();
    public List<user> getAllusers(user user1){
        List<user> list = userDao.getAllUser(user1);
        if(list.size() == 0) {
            return null;
        }

        return list;
    }

    @Override
    public boolean newuser(user user1) {
        int i = userDao.insert(user1);
        return i>0?true:false;
    }

    @Override
    public boolean deleteUserById(user user1) {
       int i = userDao.delete(user1);
       System.out.println("这是delet的i"+i);
       return i>0?true:false;
    }

    @Override
    public boolean updateUser(user user1) {
        int i = userDao.updateUser(user1);
        return i>0?true:false;
    }

    @Override
    public boolean searchUser(user user1) {
       String i= userDao.searchopenid(user1);
       if(i==null)
           return false;
       else return true;
    }

}
