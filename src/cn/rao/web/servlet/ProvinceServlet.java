package cn.rao.web.servlet;

import cn.rao.domain.Province;
import cn.rao.service.ProvinceService;
import cn.rao.service.impl.ProvinceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      /*//1调用service查询
        ProvinceService service=new ProvinceServiceImpl();
        List<Province> list=service.findAll();
        //2序列化list为json
        ObjectMapper mapper=new ObjectMapper();

        String json = mapper.writeValueAsString(list);*/
        ProvinceService service=new ProvinceServiceImpl();
        String json = service.findAllJson();


        System.out.println(json);
        //3响应结果
        response.setContentType("application/json;charset=utf-8");
         response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
