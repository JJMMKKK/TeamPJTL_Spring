package com.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.MemberDTO;
import com.service.member.LoginService;
import com.service.member.RegisterService;

@Controller
public class RegisterController {

	@Autowired
	LoginService loginServ;

	@Autowired
	RegisterService serv;

	//회원가입
	@RequestMapping(value = "/InsertData", method = RequestMethod.POST)
	public void InsertData(HttpServletRequest request, MemberDTO dto, HttpSession session) {
		System.out.println(dto);
		
	
	}
}
