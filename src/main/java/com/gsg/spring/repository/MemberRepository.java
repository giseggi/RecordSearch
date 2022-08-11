package com.gsg.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsg.spring.entity.MemberInfo;

public interface MemberRepository extends JpaRepository<MemberInfo, Long> {
	
	public List<MemberInfo> findById(String id);
	
	public List<MemberInfo> findByName(String name);
	
	public List<MemberInfo> findByMailAddr(String mailAddr);
	
}
