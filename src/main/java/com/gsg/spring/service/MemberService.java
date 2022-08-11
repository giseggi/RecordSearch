package com.gsg.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsg.spring.entity.MemberInfo;
import com.gsg.spring.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public List<MemberInfo> findAll() {
		List<MemberInfo> members = new ArrayList<>();
		memberRepository.findAll().forEach(e -> members.add(e));
		return members;
	}
	
	public Optional<MemberInfo> findById(Long memNo) {
		Optional<MemberInfo> members = memberRepository.findById(memNo);
		return members;
	}
	
	public void deleteById(Long memNo) {
		memberRepository.deleteById(memNo);
	}
	
	public MemberInfo save(MemberInfo memberInfo) {
		memberRepository.save(memberInfo);
		return memberInfo;
	}
	
	public void updateById(Long memNo, MemberInfo memberInfo) {
		Optional<MemberInfo> e = memberRepository.findById(memNo);
		
		if(e.isPresent()) {
			e.get().setMemNo(memberInfo.getMemNo());
			e.get().setId(memberInfo.getId());
			e.get().setPassword(memberInfo.getPassword());
			e.get().setName(memberInfo.getName());
			e.get().setMailAddr(memberInfo.getMailAddr());
			e.get().setNote(memberInfo.getNote());
			memberRepository.save(memberInfo);			
		}
	}
	
}
