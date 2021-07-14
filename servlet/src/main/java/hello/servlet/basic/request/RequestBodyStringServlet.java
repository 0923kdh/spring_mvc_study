package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Message Body 의 내용을 바이트 코드로 바로 얻을 수 있다.
        ServletInputStream inputStream = request.getInputStream();

        //바이트를 문자로 변환할때에는 인코딩 정보가 꼭 필요함.
        //문자를 바이트로 변환할 때에도 인코딩 정보가 꼭 필요함.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //바이트를 문자로 변환
        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
    }
}