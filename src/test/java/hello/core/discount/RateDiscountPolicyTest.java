package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
  RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

  @Test
  @DisplayName("VIP멤버는 항상 10% 할인된다.")
  public void vip_o(){
    //given
    Member member = new Member(1L, "tester1", Grade.VIP);

    //when
    int result = rateDiscountPolicy.discount(member, 10000);

    //then
    assertThat(result).isEqualTo(1000);

  }

  @Test
  @DisplayName("VIP멤버가 아닌 경우는 할인되지 않는다.")
  public void vip_x(){
    //given
    Member member = new Member(1L, "tester1", Grade.BASIC);

    //when
    int result = rateDiscountPolicy.discount(member, 10000);

    //then
    assertThat(result).isEqualTo(0);

  }

}