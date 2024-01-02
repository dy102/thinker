package com.example.thinker.repository;

import com.example.thinker.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m WHERE m.customId=:customId")
    Member findByCustomId(@Param("customId") String customId);

    @Query("SELECT m FROM Member m WHERE m.pw=:pw")
    Member findByPw(@Param("pw") String pw);

    @Query("SELECT m FROM Member m WHERE m.name=:name")
    Member findByName(@Param("name") String name);
}
