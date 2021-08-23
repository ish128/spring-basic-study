package hello.core.singleton;

import hello.core.member.Member;
import hello.core.member.MemberService;

public class SingletonMemberService implements MemberService {

  private static SingletonMemberService singletonService = new SingletonMemberService();

  public static SingletonMemberService getInstance(){
    return singletonService;
  }
  private SingletonMemberService(){

  }

  @Override
  public void join(Member member) {

  }

  @Override
  public Member findMember(Long memberId) {
    return null;
  }
}
