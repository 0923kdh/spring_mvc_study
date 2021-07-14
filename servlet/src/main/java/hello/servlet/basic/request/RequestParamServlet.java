package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 파라미터 전송기능
 * http://localhost:8080/request-param?username=hello&age=20
 *
 * 2. 동일한 파라미터 전송 가능
 * http://localhost:8080/request-param?username=hello&username=kim&age=20
 */

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start ");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = "
                        + request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end ");
        System.out.println();

        //request.getParameter() 는 하나의 파라미터 이름에 대해서 단 하나의 값만 있을 때 사용해야 한다.
        //request.getParameter() 를 사용하면 request.getParameterValues() 의 첫 번째 값을 반환한다.
        //request.getParameter() 는 GET URL 쿼리 파라미터 형식도 지원하고 POST HTML Form 형식도 지원한다.
        System.out.println("[단일 파라미터 조회] - start ");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회] - end ");
        System.out.println();

        //중복일 때는 request.getParameterValues() 를 사용해야한다.
        System.out.println("[이름이 같은 복수 파라미터 조회] - start ");
        //http://localhost:8080/request-param?username=hello&age=20&username=hello2
        String[] usernames = request.getParameterValues("username");
        for(String name : usernames) {
            System.out.println("name = " + name);
        }
        System.out.println("[이름이 같은 복수 파라미터 조회] - end ");
        System.out.println();

        response.getWriter().write("ok");
    }
}