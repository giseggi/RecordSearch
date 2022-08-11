package com.gsg.spring.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gsg.spring.entity.MemberInfo;
import com.gsg.spring.service.MemberService;

@RestController
@RequestMapping("member")
public class JpaRestController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<MemberInfo>> getAllmembers() {
        List<MemberInfo> member = memberService.findAll();
        return new ResponseEntity<List<MemberInfo>>(member, HttpStatus.OK);
    }
	
	@GetMapping(value = "/{memNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<MemberInfo> getMember(@PathVariable("memNo") Long memNo) {
        Optional<MemberInfo> member = memberService.findById(memNo);
        return new ResponseEntity<MemberInfo>(member.get(), HttpStatus.OK);
    }
	
	@DeleteMapping(value = "/{memNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> deleteMember(@PathVariable("memNo") Long memNo) {
        memberService.deleteById(memNo);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
	
	@PutMapping(value = "/{memNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<MemberInfo> updateMember(@PathVariable("memNo") Long memNo, MemberInfo memberInfo) {
        memberService.updateById(memNo, memberInfo);
        return new ResponseEntity<MemberInfo>(memberInfo, HttpStatus.OK);
    }
	
	@PostMapping
    public ResponseEntity<MemberInfo> save(MemberInfo memberInfo) {
        return new ResponseEntity<MemberInfo>(memberService.save(memberInfo), HttpStatus.OK);
    }
	
	@RequestMapping(value="/saveMember", method = RequestMethod.GET)
    public ResponseEntity<MemberInfo> save(HttpServletRequest req, MemberInfo memberInfo){
        return new ResponseEntity<MemberInfo>(memberService.save(memberInfo), HttpStatus.OK);
    }

}
