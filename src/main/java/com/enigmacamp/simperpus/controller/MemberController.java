package com.enigmacamp.simperpus.controller;

import com.enigmacamp.simperpus.config.APIUrl;
import com.enigmacamp.simperpus.model.dto.request.MemberRequest;
import com.enigmacamp.simperpus.model.dto.response.MemberResponse;
import com.enigmacamp.simperpus.model.entity.Member;
import com.enigmacamp.simperpus.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.MEMBER_API)
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public List<MemberResponse> getAllMembers() {
        return memberService.findAll();
    }

    @GetMapping(path = "/{id}")
    public MemberResponse getMemberById(@PathVariable String id) {
        return memberService.findById(id).orElse(null);
    }

    @PostMapping
    public MemberResponse createMember(@RequestBody MemberRequest memberRequest) {
        Member member = convertToEntity(memberRequest);
        return memberService.save(member);
    }

    @PutMapping(path = "/{id}")
    public MemberResponse updateMember(@PathVariable String id, @RequestBody MemberRequest updatedMemberRequest) {
        Member member = convertToEntity(updatedMemberRequest);
        return memberService.update(id, member);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteMember(@PathVariable String id) {
        memberService.deleteById(id);
    }

    private Member convertToEntity(MemberRequest memberRequest) {
    Member member = new Member();
    member.setName(memberRequest.getName());
    member.setEmail(memberRequest.getEmail());
    member.setJoinDate(memberRequest.getJoinDate().toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate());
    return member;
}
}