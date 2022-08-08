package com.gsg.spring.entity;

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
@Table(name="TEST_TABLE")
@SequenceGenerator(
		 name = "ID_SEQ_GENERATOR",
		 sequenceName = "ID_SEQ", //매핑할 데이터베이스 시퀀스 이름
		 initialValue = 1, allocationSize = 1)
public class TestTable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_GENERATOR")
	private String id;
	
	private String text;
}
