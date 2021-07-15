package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Servelt 이 Controller 역할을 한다.
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //WEB-INF 하위 리소스들은 외부에서 호출해도 바로 호출되지 않는다.
        //항상 Cotroller를 거쳐야한다.
        String viewPath = "/WEB-INF/views/new-form.jsp";

        //Cotroller에서 view로 이동할 수 있게 해준다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        //servlet 에서 jsp 를 호출해준다.
        dispatcher.forward(request, response);
    }
}