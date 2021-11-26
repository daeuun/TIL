## HandlerMapping
HandlerMapping은 클라이언트 요청에 해당하는 Controller가 어떤것인지를 결정 한다.  
즉, 클라이언트 요청 URL과 Controller를 Mapping한다.  
Spring MVC는 다수의 HandlerMapping설정이 가능하며, 이런 경우 order프로퍼티를 이용하여 사용순서를 정할 수 있다

```
mvc-dispatcher-servlet.xml

<!-- BeanNameUrlHandlerMapping --> 
<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"> 
    <property name="order" value="1" /> 
</bean> 

<!-- SimpleUrlHandlerMapping --> 
<bean id="handler" class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping"> 
    <property name="mappings"> 
        <props> 
            <prop key="/emp/list.do">employeeList</prop> 
        </props> 
    </property> 
    <property name="order" value="2" /> 
</bean> 
```

### HandlerMapping의 종류
- SimpleUrlHandlerMapping : URL과 컨트롤러 이름을 직접 매핑 한다.
- BeanNameUrlHandlerMapping : URL과 일치하는 이름을 갖는 빈을 컨트롤러로 사용한다.
- ControllerClassNameHandlerMapping : URL과 매칭되는 클래스 이름을 갖는 빈을 컨트롤러로 사용한다.
- DefaultAnnotationHandlerMapping: @RequestMapping 어노테이션을 이용하여 컨트롤러와 매핑한다.

<br>

## SimpleUrlHandlerMapping
- URL과 컨트롤러 빈 name을 직접 매핑 
- 손쉽게 사용할 수 있기 때문에 많이 사용한다
- bean 등록 후 URL 매핑을 별도로 설정해야 한다.

```
<bean id="handler" class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping"> 
    <property name="mappings"> 
        <props> 
            <prop key="/emp/list.do">employeeList</prop> 
            <prop key="/emp/form.do">employeeRegisterForm</prop> 
            <prop key="/upload/form.do">fileUploadController</prop> 
       </props> 
    </property> 
</bean> 

<bean id="employeeList" class="net.gurubee.web.emp.controller.EmployeeListController"> 
    <property name="employeeBO" ref="employeeBO" /> 
</bean> 

<bean id="employeeRegisterForm" class="net.gurubee.web.emp.controller.EmployeeRegisterFormController" /> 
```

## BeanNameUrlHandlerMapping
- Controller bean 등록시 name에 URL을 바로 지정한다.
- URL과 일치하는 이름을 갖는 빈을 컨트롤러로 사용한다.

```
<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"> 
    <property name="order" value="1" /> 
</bean> 

<bean name="/emp/list.do" class="net.gurubee.web.emp.controller.EmployeeListController"/> 

<bean name="/emp/form.do" class="net.gurubee.web.emp.controller.EmployeeRegisterFormController" /> 
```

## DefaultAnnotationHandlerMapping
- @RequestMapping 어노테이션을 이용하여 매핑한다.@RequestMapping("/emp/list.do")

```
@Controller 
@RequestMapping("/emp/list.do") 
public class EmpListController { 
    
    @RequestMapping(method = RequestMethod.GET) 
    public ModelAndView list() { 
        ModelAndView mv = new ModelAndView(); 
        mv.setViewName("emp/list"); 
        mv.addObject("list", empBO.getEmpList()); 
        return mv; 
    } 
}
```
