package com.enigmacamp.simperpus.repository;

import com.enigmacamp.simperpus.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    @Query(value = "SELECT * FROM members WHERE date_joined = :joinDate", nativeQuery = true)
    List<Member> findAllByDateJoined(String joinDate);
}
