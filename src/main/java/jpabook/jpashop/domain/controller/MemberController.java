package jpabook.jpashop.domain.controller;

// 변경 후 (스프링 부트 2.x)
import javax.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


//jpa->mybatis 변환후
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        if(!model.containsAttribute("memberForm")) {
            model.addAttribute("memberForm", new MemberForm());
        }
        return "members/createMemberForm";
    }


    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        try {
            Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
            Member member = new Member();
            member.setName(form.getName());
            member.setAddress(address);
            member.setPassword(form.getPassword()); // 비밀번호 세팅
            System.out.println("폼 비밀번호: " + form.getPassword());

            memberService.join(member); // 서비스에서 암호화 후 저장

        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/members/new";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
