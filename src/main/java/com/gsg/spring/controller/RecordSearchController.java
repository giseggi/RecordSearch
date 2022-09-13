package com.gsg.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.ModelAndView;

import com.gsg.spring.dto.MatchDto;
import com.gsg.spring.dto.SummonerDto;
import com.gsg.spring.entity.MemberInfo;
import com.gsg.spring.service.MemberService;
import com.gsg.spring.service.RecordSearchService;

@Controller
public class RecordSearchController {
	
	@Autowired
	private RecordSearchService recordSearchService;
	
	private final MemberService memberService;
	
	@Autowired
	public RecordSearchController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "index";
	}
	

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration() {
		return "member_registration";
	}
	
	@RequestMapping(value = "/registrationDone", method = RequestMethod.POST)
	public String registrationDone(MemberInfo memberInfo) {
		memberService.save(memberInfo);
		
		return "registration_done";
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public ModelAndView result(String summoner, String server, int count, String darkFlg) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		try {
			SummonerDto summonerInfo = recordSearchService.getSummonerInfo(summoner, server);
									
			List<String> matchesId = recordSearchService.getMatchId(summonerInfo.getPuuid(), server, count);
			List<MatchDto> matchInfoList = new ArrayList<MatchDto>();
			
			for(String matchId : matchesId) {
				MatchDto matchInfo = recordSearchService.getMatchInfo(matchId, server, summoner);
				matchInfoList.add(matchInfo);
			}
			
			String latestVersion = recordSearchService.getLatestVersion();
			
			mv.addObject("latestVersion", latestVersion);
			mv.addObject("summoner", summonerInfo);
			mv.addObject("matchInfoList", matchInfoList);
			mv.addObject("darkFlg",darkFlg);
			mv.setViewName("result");
			
		} catch(WebClientResponseException e) {
			mv.setViewName("no_search_result");
		}
		

		return mv;
	}
	
	

}
