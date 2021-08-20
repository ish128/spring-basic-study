package hello.core.xmlconfig;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlConfigApplicationContextTest {

  @Test
  void xmlConfigTest(){
    ApplicationContext ac = new GenericXmlApplicationContext("application-context.xml");
    MemberService memberService = ac.getBean(MemberService.class);
    OrderService orderService = ac.getBean(OrderService.class);
    MemberRepository memberRepository = ac.getBean(MemoryMemberRepository.class);
    DiscountPolicy discountPolicy = ac.getBean("discountPolicy", DiscountPolicy.class);

    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    assertThat(orderService).isInstanceOf(OrderService.class);
    assertThat(memberRepository).isInstanceOf(MemoryMemberRepository.class);
    assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
  }
}
