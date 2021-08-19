package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {

  private  OrderService orderService;
  private  MemberService memberService;

  @BeforeEach
  public void beforeEach(){
    AppConfig appConfig = new AppConfig();
    orderService = appConfig.getOrderService();
    memberService = appConfig.getMemberService();
  }

  @Test
  public void createOrder(){
    //given
    Member member = new Member(1L, "test", Grade.VIP);
    memberService.join(member);

    //when
    Order order = orderService.createOrder(member.getId(), "oranda", 3000);

    //then
    assertThat(order.actuallyPrice()).isEqualTo(2000);
    assertThat(order.getMemberId()).isEqualTo(1L);
    assertThat(order.getItemName()).isEqualTo("oranda");
    assertThat(order.getItemPrice()).isEqualTo(3000);
    assertThat(order.getDiscountPrice()).isEqualTo(1000);
  }
}
