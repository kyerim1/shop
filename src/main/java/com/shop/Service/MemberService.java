package com.shop.Service;

import com.shop.Dto.MemberForm;
import com.shop.Entity.Member;
import com.shop.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입폼의 내용을 데이터 베이스에 저장
    public void saveMember(MemberForm memberForm){
        Member member = memberForm.createEntity();
        // 아이디와 이메일 중복여부
        validUserIdEmail( member );
        memberRepository.save(member);
    }
    private void validUserIdEmail(Member member){
        Member find = memberRepository.findByUserId(member.getUserId());
        if( find != null){
            throw new IllegalStateException("이미 가입된 아이디 입니다.");
        }
        find = memberRepository.findByEmail(member.getEmail());
        if( find !=null){
            throw new IllegalArgumentException("이미 가입된 이메일 입니다.");
        }
    }
}
