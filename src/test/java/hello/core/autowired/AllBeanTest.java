package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AllBeanTest {

  @Test
  void test(){
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
    DiscountService discountService = ac.getBean(DiscountService.class);
    Member member = new Member(1L, "userA", Grade.VIP);
    int result1 = discountService.discount(member, 20000, FixedDiscountPolicy.class);
    Assertions.assertThat(result1).isEqualTo(1000);

    int result2 = discountService.discount(member, 20000, RateDiscountPolicy.class);
    Assertions.assertThat(result2).isEqualTo(2000);
  }

  static class DiscountService{
    private final Map<String, DiscountPolicy> discountPolicyMap;
    private final List<DiscountPolicy> discountPolicies;

    public DiscountService(Map<String, DiscountPolicy> discountPolicyMap, List<DiscountPolicy> discountPolicies) {
      this.discountPolicyMap = discountPolicyMap;
      this.discountPolicies = discountPolicies;

      System.out.println("discountPolicyMap:" + discountPolicyMap);
      System.out.println("discountPolicies:" + discountPolicies);
    }

    public int discount(Member member, int price, Class<? extends DiscountPolicy> clazz){
      System.out.println("DiscountService.discount:" + clazz);
      DiscountPolicy discountPolicy = discountPolicy(clazz);
      System.out.println("DiscountService.discount:" + discountPolicy);
      return discountPolicy(clazz).discount(member, price);
    }

    private DiscountPolicy discountPolicy(final Class<? extends DiscountPolicy> clazz){
     return discountPolicies.stream()
          .filter(i -> {
            System.out.println("DiscountService.discountPolicy::" + i.getClass());
            return clazz.isAssignableFrom(i.getClass());
          })
          .findAny()
          .get();
    }
  }


}
