import bean.user;
import com.alibaba.fastjson.JSON;
import service.imp.searchServiceImp;
import service.searchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "Servlet", urlPatterns = "/Servlet")
public class Servlet extends HttpServlet {
    private searchService usersearch = new searchServiceImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        /*设置响应头允许ajax跨域访问*/
        response.setHeader("Access-Control-Allow-Origin", "*");

        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        //获取微信小程序传递的参数值并打印
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String delet = request.getParameter("delet");
        String type = request.getParameter("type");
        String name = request.getParameter("name");

        user upload = new user(delet,name,latitude,longitude,type);

        //先查询跟据查询结果决定创建还是更新还要有个时间如果好久不更新就删除
        if(upload.getOpenid().equals("1"))
        {
            System.out.println("删除！！！！！！！！！！");
            System.out.println("delet的值"+upload.getOpenid());
            if(usersearch.deleteUserById(upload)){

            }
        }
        else if (!upload.getNickname().equals("")){
            System.out.println("进入且delet的值"+upload.getOpenid());
            if (usersearch.searchUser(upload)){
                    if(usersearch.updateUser(upload))
                        System.out.println("更新成功");
            }
            else{
                usersearch.newuser(upload);
            }
        }
//        像小程序端传递数据
        List<user> result  = usersearch.getAllusers(upload);

        String str= JSON.toJSONString(result);

        Writer out = response.getWriter();
        out.write(str);
        out.flush();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
