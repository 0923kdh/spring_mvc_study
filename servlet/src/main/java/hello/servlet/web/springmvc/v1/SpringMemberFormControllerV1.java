package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 1. @Controller
 *  1) 스프링이 자동으로 스프링 빈으로 등록한다. 내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 된다.
 *  2) 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식한다.
 *
 * 2. @RequestMapping
 *  1) 요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출된다
 *  2) 애노테이션 기반으로 동작하기 때문에 메서드의 이름은 임의로 지으면 된다.
 *
 * 3. ModelAndView
 *  1) Model과 View 정보를 담아서 반영하면 된다.
 *
 * 4. RequestMappingHandlerMapping
 *  1) 스프링 중에서 @RequestMapping 또는 @Contrller가 클래스 레벨에 붙어 있는 경우에 매핑 정보로 인식함.
 *
 */

//@Component
//@RequestMapping

//위 코드처럼해도 된다.
@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {

        System.out.println("SpringMemberFormControllerV1.process");
        return new ModelAndView("new-form");

    }

}