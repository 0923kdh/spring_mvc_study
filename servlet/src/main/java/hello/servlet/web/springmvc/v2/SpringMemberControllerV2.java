package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
@RequestMapping은 메서드 단위로 된다.
*/

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //클래스 레벨의 @RequestMapping + 메세드 레벨의 @RequestMapping 정보가 더해짐.
    // /springmvc/v2/members/new-form 와 같음.
    @RequestMapping("/new-form")
    public ModelAndView newForm() {

        System.out.println("SpringMemberFormControllerV1.process");
        return new ModelAndView("new-form");

    }

    //클래스 레벨의 @RequestMapping + 메세드 레벨의 @RequestMapping 정보가 더해짐.
    // /springmvc/v2/members 와 같음.
    @RequestMapping
    public ModelAndView save() {

        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);

        return mv;

    }

    //클래스 레벨의 @RequestMapping + 메세드 레벨의 @RequestMapping 정보가 더해짐.
    // /springmvc/v2/members/save 와 같음.
    @RequestMapping("/save")
    public ModelAndView members(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);

        return mv;

    }
}