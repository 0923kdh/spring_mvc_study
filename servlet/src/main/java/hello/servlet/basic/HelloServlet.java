package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    //http://localhost:8080/hello
    //서블릿이 호출되면 service 메소드가 호출된다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");

        //톰캣과 같은 여러 WAS들이 서블릿 표준 스펙을 구현, 구현체임
        System.out.println("request = " + request); //request = org.apache.catalina.connector.RequestFacade@~
        System.out.println("response = " + response); //response = org.apache.catalina.connector.ResponseFacade@~

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        //응답 헤더에 담아 보낼 정보
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        //응답 바디에 담아 보낼 정보
        response.getWriter().write("hello " + username);
    }
}