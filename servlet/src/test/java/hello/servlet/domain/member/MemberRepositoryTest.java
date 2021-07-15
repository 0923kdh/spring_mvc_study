package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    //Spring을 쓰면 Spring이 싱글톤을 보장해주기때문에 싱글톤 패턴을 만들지 않아도 된다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    //test 이후에 clearStore 하기위함
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Member member = new Member("hello", 20);

        //when
        Member savedMember = memberRepository.save(member);
        
        //then
        Member findMember = memberRepository.findById(savedMember.getId());

        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);

        //then
        List<Member> result = memberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1,member2);
    }
}