package hjh.mag;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hjh.mag.domain.Member;
import hjh.mag.domain.MemberRoll;
import hjh.mag.repository.MemberRepository;

@Transactional
@SpringBootTest
public class RollTest {

  @Autowired
  private MemberRepository memberRepository;

  @DisplayName("enum roll type 데이터 적용 확인: User")
  @Test
  public void MemberRollUser() {
    Member member = new Member();
    member.setLoginId("1234");
    member.setPassword("1234");
    member.setEmail("1234");
    member.setRoll(MemberRoll.User);

    Member savedMember = memberRepository.save(member);

    assertEquals(MemberRoll.User, savedMember.getRoll());
  }

  @DisplayName("enum roll type 데이터 적용 확인: Admin")
  @Test
  public void MemberRollAdmin() {
    Member member = new Member();
    member.setLoginId("1234");
    member.setPassword("1234");
    member.setEmail("1234");
    member.setRoll(MemberRoll.Admin);

    Member savedMember = memberRepository.save(member);

    assertEquals(MemberRoll.Admin, savedMember.getRoll());
  }

  @DisplayName("roll을 설정 안하면?")
  @Test
  public void MemberNoRoll() {
    Member member = new Member();
    member.setLoginId("1234");
    member.setPassword("1234");
    member.setEmail("1234");

    assertDoesNotThrow(() -> memberRepository.save(member));
  }
}
