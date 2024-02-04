package dao.imp;

import bean.user;
import dao.SearchDao;
import src.Conn;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDaoImp implements SearchDao {

    private Connection conn = Conn.getconn();

    public  List<user> getAllUser(user user1){

        String sql = "select latitude,longitude,type from users where hidden !='1' ";
        StringBuffer sb = new StringBuffer(sql);
        sql = sb.toString();
        PreparedStatement pstmt = null;
        List<user> list=null;
        try {
            pstmt = conn.prepareStatement(sql);
            // 给参数赋值
            ResultSet rs = pstmt.executeQuery();
            list =new ArrayList<user>();
            int i=0;
            while(rs.next()) {
                String latitude = rs.getString("latitude");
                String longitude = rs.getString("longitude");
                String type = rs.getString("type");
                user  user_  = new user(latitude,longitude,type);

                list.add(user_);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Conn.closeConn(null, null, pstmt, null);
        }

        return list;

    }
    @Override
    public String searchopenid(user user1) {
        String sql = "select type from users where nickname = ?";
        PreparedStatement pstmt = null;
        String type =null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user1.getNickname());

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                type = rs.getString("type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Conn.closeConn(null, null, pstmt, null);
        }
        return type;
    }
    public int updateUser(user user1) {
        String sql = "update users set hidden= '0',latitude=?,longitude=? where nickname =?";
        int i = 0;

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user1.getLatitude());
            pstmt.setString(2, user1.getLongitude());
            pstmt.setString(3, user1.getNickname());
            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Conn.closeConn(null, null, pstmt, null);
        }
        return i;
    }

    @Override
    public int insert(user user1) {
        String sql = "insert into users(nickname,latitude,longitude,type) values(?,?,?,?)";
        int i=0;
        PreparedStatement pstmt = null;
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,user1.getNickname());
            pstmt.setString(2,user1.getLatitude());
            pstmt.setString(3,user1.getLongitude());
            pstmt.setString(4,user1.getType());
            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            Conn.closeConn(null, null, pstmt, null);
        }
        return i;
    }

    @Override
    public int delete(user user1) {
        String sql = "update users set hidden='1' where nickname =?";
        int i = 0;
        System.out.println("delet的name"+user1.getNickname());
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user1.getNickname());
            i = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Conn.closeConn(null, null, pstmt, null);
        }
        return i;
    }

}
