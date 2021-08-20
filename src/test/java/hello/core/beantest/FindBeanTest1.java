package hello.core.beantest;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindBeanTest1 {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("빈 이름으로 찾기")
  void test1(){
    Object memberService = ac.getBean("memberService");
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("빈 이름 없이, 빈 타입으로 찾기")
  void test2(){
    MemberService memberService = ac.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("빈 타입과 이름으로 찾기")
  void test3(){
    MemberService memberService = ac.getBean("memberService",MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("빈 구체 타입으로 찾기")
  void test4(){
    MemberServiceImpl memberService = ac.getBean( MemberServiceImpl.class); // 참고: 구체 타입으로 조회하면 변경시 유연성이 떨어진다.
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("빈 이름이 없는 경우, 예외발생하는지 확인하는 테스트")
  void test5(){
    assertThrows(NoSuchBeanDefinitionException.class, ()->ac.getBean( "xxx"));
  }
}
