package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

//    @RequestMapping("/new-form")
//    public ModelAndView newForm() {
//
//        System.out.println("SpringMemberFormControllerV1.process");
//        return new ModelAndView("new-form");
//
//    }

    // 위 코드와 같음.
    // 논리주소만 리턴해도 된다.
    @GetMapping("new-form") //@RequestMapping(value = "/new-form", method = RequestMethod.GET) 랑 같음
    public String newForm() {
        return "new-form";
    }


//    @RequestMapping
//    public ModelAndView members() {
//
//        List<Member> members = memberRepository.findAll();
//
//        ModelAndView mv = new ModelAndView("members");
//        mv.addObject("members", members);
//
//        return mv;
//
//    }

    //위 코드와 같음.
    @GetMapping //@RequestMapping(method = RequestMethod.GET) 와 같음.
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";

    }

//    @RequestMapping("/save")
//    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
//
//        String username = request.getParameter("username");
//        int age = Integer.parseInt(request.getParameter("age"));
//
//        Member member = new Member(username, age);
//        memberRepository.save(member);
//
//        ModelAndView mv = new ModelAndView("save-result");
//        mv.addObject("member", member);
//
//        return mv;
//
//    }

    // 위 코드와 같음.
    // @RequestParam -> 형변환도 자동으로 해줌
    @PostMapping("/save") //@RequestMapping(value = "/save", method = RequestMethod.POST) 와 같음
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";

    }

}