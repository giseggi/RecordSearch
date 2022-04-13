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
import com.gsg.spring.service.RecordSearchService;

@Controller
public class RecordSearchController {
	
	@Autowired
	private RecordSearchService recordSearchService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "index";
	}
	

	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public ModelAndView result(String summoner, String server) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		try {
			SummonerDto summonerInfo = recordSearchService.getSummonerInfo(summoner, server);
									
			List<String> matchesId = recordSearchService.getMatchId(summonerInfo.getPuuid(), server);
			List<MatchDto> matchInfoList = new ArrayList<MatchDto>();
			
			for(String matchId : matchesId) {
				MatchDto matchInfo = recordSearchService.getMatchInfo(matchId, server, summoner);
				matchInfoList.add(matchInfo);
			}
			mv.addObject("summoner", summonerInfo);
			mv.addObject("matchInfoList", matchInfoList);	
			mv.setViewName("result");
			
		} catch(WebClientResponseException e) {
			mv.setViewName("no_search_result");
		}
		

		return mv;
	}
	
	

}
