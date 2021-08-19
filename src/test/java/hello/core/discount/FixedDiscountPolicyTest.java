package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.order.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FixedDiscountPolicyTest {

  private final FixedDiscountPolicy discountPolicy = new FixedDiscountPolicy();

  @Test
  public void discount_vip(){
    //given
    Member vipMember = new Member(1L, "tester", Grade.VIP);
    //when
    int discountAmount = discountPolicy.discount(vipMember, 10000);
    //then
    Assertions.assertThat(discountAmount).isEqualTo(1000);
  }

  @Test
  public void discount_basic(){
    //given
    Member vipMember = new Member(1L, "tester", Grade.BASIC);
    //when
    int discountAmount = discountPolicy.discount(vipMember, 10000);
    //then
    Assertions.assertThat(discountAmount).isEqualTo(0);
  }
}
