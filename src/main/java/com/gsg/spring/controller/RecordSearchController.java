package com.gsg.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView result(String summoner, String server) {
		ModelAndView mv = new ModelAndView();

		SummonerDto summonerDto = recordSearchService.getSummonerInfo(summoner, server);
		
		return mv;
	}
	
	

}
