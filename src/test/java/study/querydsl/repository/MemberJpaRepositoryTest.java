package study.querydsl.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    void basicTest() {
        Member member = new Member("member1");
        memberJpaRepository.save(member);

        Member member1 = memberJpaRepository.findById(member.getId()).get();

        assertThat(member).isEqualTo(member1);

        List<Member> findMember = memberJpaRepository.findByAll();

        assertThat(findMember).containsExactly(member);

        List<Member> findMember2 = memberJpaRepository.findByUsername("member1");
        assertThat(findMember2).containsExactly(member);
    }


    @Test
    void basicQueryDslTest() {
        Member member = new Member("member1");
        memberJpaRepository.save(member);

        Member member1 = memberJpaRepository.findById(member.getId()).get();

        assertThat(member).isEqualTo(member1);

        List<Member> findMember = memberJpaRepository.findByAll_QueryDsl();

        assertThat(findMember).containsExactly(member);

        List<Member> findMember2 = memberJpaRepository.findByUsername_QueryDsl("member1");
        assertThat(findMember2).containsExactly(member);
    }

    @Test
    void searchQueryDsl() {

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCondition memberSearchCondition = new MemberSearchCondition();
        //memberSearchCondition.setTeamName("teamB");
        //memberSearchCondition.setAgeGoe(31);
        //memberSearchCondition.setAgeLoe(40);

        List<MemberTeamDto> result = memberJpaRepository.searchByBuilder(memberSearchCondition);
        assertThat(result).extracting("username").containsExactly("member4");
    }

    @Test
    void searchQueryDsl2() {

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        MemberSearchCondition memberSearchCondition = new MemberSearchCondition();
        memberSearchCondition.setTeamName("teamB");
        memberSearchCondition.setAgeGoe(31);
        memberSearchCondition.setAgeLoe(40);

        List<MemberTeamDto> result = memberJpaRepository.search(memberSearchCondition);
        assertThat(result).extracting("username").containsExactly("member4");
    }
}