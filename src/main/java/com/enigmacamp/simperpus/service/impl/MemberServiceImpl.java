package com.enigmacamp.simperpus.service.impl;

import com.enigmacamp.simperpus.model.dto.response.BorrowingResponse;
import com.enigmacamp.simperpus.model.dto.response.MemberResponse;
import com.enigmacamp.simperpus.model.entity.Member;
import com.enigmacamp.simperpus.repository.MemberRepository;
import com.enigmacamp.simperpus.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public List<MemberResponse> findAll() {
        return memberRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MemberResponse> findById(String id) {
        return memberRepository.findById(id)
                .map(this::convertToResponse);
    }

    @Override
    public MemberResponse save(Member member) {
        Member savedMember = memberRepository.save(member);
        return convertToResponse(savedMember);
    }

    @Override
    public void deleteById(String id) {
        memberRepository.deleteById(id);
    }

    @Override
    public List<MemberResponse> findByDateJoined(String joinDate) {
        return memberRepository.findAllByDateJoined(joinDate).stream()
               .map(this::convertToResponse)
               .collect(Collectors.toList());
    }

    @Override
    public MemberResponse update(String id, Member member) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            Member existingMember = optionalMember.get();
            existingMember.setName(member.getName());
            existingMember.setEmail(member.getEmail());
            Member updatedMember = memberRepository.save(existingMember);
            return convertToResponse(updatedMember);
        }
        return null;
    }

    private MemberResponse convertToResponse(Member member) {
    MemberResponse response = new MemberResponse();
    response.setId(member.getId().toString());
    response.setName(member.getName());
    response.setEmail(member.getEmail());
    response.setJoinDate(java.sql.Date.valueOf(member.getJoinDate()));
    response.setBorrowings(member.getBorrowings().stream()
            .map(borrowing -> new BorrowingResponse(
                    borrowing.getId().toString(),
                    java.sql.Date.valueOf(borrowing.getBorrowedDate()),
                    java.sql.Date.valueOf(borrowing.getReturnedDate()),
                    borrowing.isReturned()
            ))
            .collect(Collectors.toSet()));
    return response;
}
}