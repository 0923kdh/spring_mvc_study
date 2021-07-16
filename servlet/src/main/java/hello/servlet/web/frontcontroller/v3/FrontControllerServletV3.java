package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {

        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override //1.reuquest 요청이 들어옴.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //getRequestURI를 하면 /front-controller/v3/* 에 해당하는 정보가 들어옴
        String requestURI = request.getRequestURI();

        //2. Controller 조회
        ControllerV3 controller = controllerMap.get(requestURI);
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //3. Controller 호출
        Map<String, String> paramMap = creatParamMap(request);

        //4. Controller는 FrontController한테 ModelView 반환
        ModelView mv = controller.process(paramMap);
        String viewName = mv.getViewName(); //new-from 같은 논리이름임.

        //5. FrontController가 viewResolver 호출
        //6. viewResolver가 FrontController한테 MyView 반환
        MyView view = viewResolver(viewName);

        //7. render() 호출
        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {

        return new MyView("/WEB-INF/views/" + viewName + ".jsp");

    }

    private Map<String, String> creatParamMap(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName->paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;

    }

}