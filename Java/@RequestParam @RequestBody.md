### HttpServletRequest 방식

```java
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView process1(){
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView process2(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        Integer age = Integer.valueOf(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member",member);
        return mv;
    }

    @RequestMapping
    public ModelAndView process3() {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members",members);
        return mv;
    }
}

```

### @RequestParam 방식

```java
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public String process1(){
        return "new-form";
    }

    @RequestMapping("/save")
    public String process2(@RequestParam("username") String username, @RequestParam("age") int age, Model model) {
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    @RequestMapping
    public String process3(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }

```

1. 더이상 ModelAndView 객체를 반환하지 않아도 된다.
2. HttpServlet 기술들을 직접 사용하지 않는다.
3. HttpServletRequest를 대체하는 @ReqeustParma을 통해 직접 사용자의 파라미터 정보를 가져온다.
4. HttpServletResponse를 대체하는 Model 객체가 존재하므로 view로 전달되는 데이터는 모두 model 객체에 담아서 넘기면 된다.
5. 메소드의 리턴타입이 String이면 뷰이름을 나타낸다.


### @RequestParam @RequestBody
- @RequestParam
    
    쿼리 파라미터를 컨트롤러의 메서드 인자로 바인딩
    1:1로 매칭하여 하나의 값만을 전달
    
- @RequestBody
    
    HttpMessageReader가 request body를 Java Object로 역직렬화한다.
    

✔ **Post 요청에 @RequestBody로 데이터를 받는다면 Setter가 필요없다?**

spring에서 Json의 역직렬화(Json을 자바 객체로 변환)를 담당하는 것은 Jackson2HttpMessageConverter입니다.  
즉, @RequestBody 로 Json데이터가 넘어오면 Json을 JavaObject로 변환해주는 것은 Jackson2HttpMessageConverter입니다.  
이런 역직렬화는 ObjectMapper의 readValue 메서드를 사용해서 변환하기 때문에 setter가 필요없는것입니다.  
(단, 기본 생성자는 대부분의 경우에 필요합니다.)

[https://jojoldu.tistory.com/407](https://jojoldu.tistory.com/407)

✔ **Get 요청에도 Setter가 필요없다??**

Get 요청의 경우 Json데이터가 아닌 Query Parameter로 받습니다.  
그래서 Jackson2HttpMessageConverter가 아닌 Spring의 WebDataBinder를 사용합니다.  
WebDataBinder는 기본 설정으로 자바빈 방식으로 값을 바인딩합니다.  
자바빈 방식이란 setter를 활용해서 값을 할당하는 것을 의미합니다.  
따라서 Get 요청에서는 Setter가 없으면 값을 받아올 수 없습니다.

[https://velog.io/@conatuseus/RequestBody에-기본-생성자는-왜-필요한가](https://velog.io/@conatuseus/RequestBody%EC%97%90-%EA%B8%B0%EB%B3%B8-%EC%83%9D%EC%84%B1%EC%9E%90%EB%8A%94-%EC%99%9C-%ED%95%84%EC%9A%94%ED%95%9C%EA%B0%80)
