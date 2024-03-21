package com.controller.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.MemberDTO;
import com.service.member.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService serv;

	//로그인
	@RequestMapping(value = "/Mypage", method = RequestMethod.POST)
	public String LoginToMypage(String userId, String userPw, HttpSession session) {
		MemberDTO dto = serv.login(userId, userPw);

		if (dto != null) {
			session.setAttribute("loginUser", dto);
			return "main";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}

	//로그아웃
	@RequestMapping(value = "/Logout", method = RequestMethod.POST)
	public String Logout(HttpSession session) {
		MemberDTO dto = (MemberDTO) session.getAttribute("loginUser");
		if (dto != null) {
			session.removeAttribute("loginUser");
			return "main";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}
	
	//어아다 첮가
	@RequestMapping(value = "/SearchID", method = RequestMethod.POST)
	public String SearchID(HttpServletRequest request, String userName, String ssn1, String ssn2, HttpSession session) {
		MemberDTO dto = serv.findUserId(userName, ssn1, ssn2);
		if (dto != null) {
			request.setAttribute("found_userId", dto);
			return "member/Find_Info/viewID";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}
	
	//비밀번호 찾기
	@RequestMapping(value = "/SearchPartPW", method = RequestMethod.POST)
	public String SearchPartPW(HttpServletRequest request, HttpServletResponse response, String userId, String userName, String ssn1, String ssn2, HttpSession session) {
		MemberDTO dto = serv.findUserPW(userId, userName, ssn1, ssn2);
		if (dto != null) {
			Cookie userIdCookie = new Cookie("findPW_userid",userId);
			userIdCookie.setMaxAge(30*60);
			response.addCookie(userIdCookie);
			
			request.setAttribute("foundUserPW", dto);
			return "member/Find_Info/viewPartPW";
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}
	
	//전체 비밀번호 출력용
	@RequestMapping(value = "/SearchAllPW", method = RequestMethod.POST)
	public String SearchAllPW(HttpServletRequest request, String userId, HttpSession session) {
		MemberDTO dto = serv.selectMemberData(userId);
		if (dto != null) {
			request.setAttribute("foundUserInfo", dto);
			
			//디버그 코드************************************************************************
			return "member/Find_Info/viewAllPW";
			//디버그 코드******
						
//			RequestDispatcher dis = request.getRequestDispatcher("SendEmailServlet");
//			dis.forward(request, response);
						
		} else {
			return "member/Find_Info/cantFindUserdata";
		}

	}
	
	
	
	
	
	
	
}