package hello.servlet.domain.member;

/**
 * 1. 동시성 문제를 고려하지 않았음. 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 * 2. 프로젝트가 크면 별도의 레포 관리 패키지로 빼는게 좋다.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    //싱글톤 패턴이기때문에 아래와 같이 해도 상관없다.
    //private Map<Long, Member> store = new HashMap<>();
    //private long sequence = 0L;

    //싱글톤 패턴 적용
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

    //생성자는 꼭 private으로
    private MemberRepository() {
    }
}