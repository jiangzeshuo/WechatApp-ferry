package src;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Query {
    public static void main(String[] args) {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            //1、获取连接对象
            conn=Conn.getconn();
            //2、创建statement类对象，用来执行SQL语句！！
            st=conn.createStatement();
            //3、创建sql查询语句
            String sql="select *from users";
            //4、执行sql语句并且换回一个查询的结果集
            rs=st.executeQuery(sql);
            while(rs.next()) {  //循环遍历结果集
                String id=rs.getString("openid");
                String name=rs.getString("nickname");
                String score=rs.getString("latitude");
                System.out.println("id=" + id + "--" + "name=" + name + "--" + "score=" + score);
              }
        }
            catch (Exception e) {
            e.printStackTrace();
        }
    }
}