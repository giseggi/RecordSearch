package com.gsg.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="MEMBER_INFO")
@SequenceGenerator(
		 name = "MEMBER_NO_SEQ_GENERATOR",
		 sequenceName = "MEMBER_NO_SEQ", 
		 initialValue = 1, allocationSize = 1)
public class MemberInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
    				generator = "MEMBER_NO_SEQ_GENERATOR")
	@Column(name="MEM_NO")
	private Long memNo;
	
	@Column
	private String id;
	
	@Column
	private String password;
	
	@Column
	private String name;
	
	@Column(name="MAIL_ADDR")
	private String mailAddr;
	
	@Column
	private String note;
}
