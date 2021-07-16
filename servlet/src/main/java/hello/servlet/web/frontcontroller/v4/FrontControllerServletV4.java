package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {

        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());

    }

    @Override //1.reuquest 요청이 들어옴.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //getRequestURI를 하면 /front-controller/v4/* 에 해당하는 정보가 들어옴
        String requestURI = request.getRequestURI();

        //2. Controller 조회
        ControllerV4 controller = controllerMap.get(requestURI);
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //3. paramMap, ViewModel 호출
        Map<String, String> paramMap = createParamMap(request);
        //모델 객체를 FrontController에서 생성해서 넘긴다.
        Map<String, Object> model = new HashMap<>();

        //4. FrontController에게 ViewName(논리 이름) 반환
        String viewName = controller.process(paramMap, model);

        //5. ViewResolver 호출
        //6. MyView 반환
        MyView view = viewResolver(viewName);
        view.render(model, request, response);

    }

    private MyView viewResolver(String viewName) {

        return new MyView("/WEB-INF/views/" + viewName + ".jsp");

    }

    private Map<String, String> createParamMap(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName->paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;

    }

}