package com.hw.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	Logger logger = Logger.getLogger("work");

	@RequestMapping("/main.do")
	public void main(HttpServletRequest request) {
		String speed = request.getParameter("speed");
		String temp = request.getParameter("temp");
		System.out.println("shit");
		logger.debug(speed+" "+temp);
		// return "main";
	}
}
