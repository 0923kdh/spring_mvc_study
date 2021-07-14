package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Content-Type: application/json
        response.setContentType("application/json");

        //application/json은 스펙상 utf-8 형식을 사용하도록 정의되어있다.
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        //HelloData 객체를 Json string 으로 변환
        //{"username" : "kim", "age" : 20}
        String result = objectMapper.writeValueAsString(helloData);
        //getWriter() 를 사용하면 추가 파라미터를 자동으로 추가해버린다.
        //이때는 response.getOutputStream() 으로 출력하면 된다.
        response.getWriter().write(result);
    }
}