package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class  MemoryMemberRepository implements MemberRepository{

  private static final  ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();

  @Override
  public void save(Member member) {
    store.put(member.getId(), member);
  }

  @Override
  public Member findById(Long id) {
    return store.get(id);
  }
}
