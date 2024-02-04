package src;

import java.sql.*;

public class Conn {
    static String driverClass="oracle.jdbc.driver.OracleDriver"; //oracle的驱动
    static String url="jdbc:oracle:thin:@//localhost:1521/orcl";  //连接oracle路径方式 “”gfs“”是要建立连接的数据库名 1521端口
    static String user="jzs";   //user是数据库的用户名
    static String password="123456";  //用户登录密码

    public static Connection getconn() {  //为了方便下面的讲解，这里专门建立了一个用于数据库连接的一个方法
        Connection conn=null;
        try {
            //首先建立驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //驱动成功后进行连接
            conn=DriverManager.getConnection(url, user, password);

            System.out.println("连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn; //返回一个连接
    }

    // 关闭连接
    public static void closeConn(ResultSet rs, Statement st, PreparedStatement pst, Connection conn){
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(st != null){
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                try {
                    if(pst != null){
                        pst.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally{
                    try {
                        if(conn != null && !conn.isClosed()){
                            conn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

//自己在测试连接的时候可以不用定义连接方法