package com.basic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.basic.service.BbsService;

@Controller
public class BbsViewController {

	@Autowired
	private BbsService bbsService;
	
	@GetMapping("bbs")
	public ModelAndView selectBbsList(@RequestParam Map<String, Object> param) {
		List<Map<String, Object>> bbsList = bbsService.selectBbsList(param);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bbsList");
		mv.setStatus(HttpStatus.valueOf(200));
		mv.addObject("bbs", bbsList);
		return mv;
	}
	
	@GetMapping("bbs-view")
	public ModelAndView selectBbsView(@RequestParam Map<String, Object> param) {
		Map<String, Object> result = bbsService.selectBbs(param);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bbsDetail");
		mv.setStatus(HttpStatus.valueOf(200));
		mv.addObject("bbs", result);
		return mv;
	}
	
	
	/* 127.0.0.1:8080/bbs/1?abc=123
	 * ?전: URL / ?후: 파라미터 
	 * URL에 있는 1을 꺼낼 때 end-point에 {}를 사용. 즉,{bbsNo} -> 1
	 * 
	 * */
	@GetMapping("bbs/{bbsNo}")
	public ModelAndView selectBbs(@RequestParam Map<String, Object> param, @PathVariable Object bbsNo) {
		param.put("bbsNo", bbsNo);
		Map<String, Object> result = bbsService.selectBbs(param);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bbsDetail");
		mv.setStatus(HttpStatus.valueOf(200));
		mv.addObject("bbs", result);
		return mv;
	}
}
