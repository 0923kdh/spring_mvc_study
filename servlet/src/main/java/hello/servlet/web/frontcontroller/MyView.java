package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        modelToRequestAttribute(model, request);

        //jsp forward를 하면 jsp가 request.getAttribute를 한다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {

        //jsp는 request.getAttribute로 데이터를 조회하기 때문에
        //모델에 있는 데이터를 다 꺼내서 request에 setAttribute를 이용해서 정보를 다 담는다.
        model.forEach((key, value)-> request.setAttribute(key, value));

    }

}