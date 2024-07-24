package com.enigmacamp.simperpus.service;

import com.enigmacamp.simperpus.model.dto.response.MemberResponse;
import com.enigmacamp.simperpus.model.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    List<MemberResponse> findAll();
    Optional<MemberResponse> findById(String id);
    MemberResponse save(Member member);
    void deleteById(String id);
    List<MemberResponse> findByDateJoined(String joinDate);

    MemberResponse update(String id, Member member);
}