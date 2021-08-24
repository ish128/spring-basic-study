package hello.core;

import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AutoAppConfigTest {

    @Test
    void autoConfigTest(){
      ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

      MemberService memberService = ac.getBean(MemberService.class);
      OrderService orderService = ac.getBean(OrderService.class);

      assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
      assertThat(orderService).isInstanceOf(OrderServiceImpl.class);
    }

}