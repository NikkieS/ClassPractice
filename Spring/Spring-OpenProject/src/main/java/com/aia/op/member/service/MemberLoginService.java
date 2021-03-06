package com.aia.op.member.service;

//import java.sql.Connection;
//import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.aia.op.jdbc.ConnectionProvider;
//import com.aia.op.member.dao.MemberDao;
import com.aia.op.member.dao.MemberDaoInterface;
import com.aia.op.member.model.LoginInfo;
import com.aia.op.member.model.LoginRequest;
import com.aia.op.member.model.Member;
import com.aia.op.util.CookieBox;

@Service
public class MemberLoginService {
	
//	@Autowired
//	MemberDao dao;
	
	private MemberDaoInterface dao;
	
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public String memberLogin(LoginRequest loginRequest, HttpSession session, HttpServletResponse response) {
		
		// Interface의 Mapper 객체 생성 - Interface의 메소드 Mapper로 정의
		dao = sessionTemplate.getMapper(MemberDaoInterface.class);
		
		String loginResult = "";
		
		// mybatis 수정 0805
		//Connection conn = null;
		
		// 로그인 처리
		Member member = null;
		
			// mybatis 수정 0805
			//conn = ConnectionProvider.getConn();
			//member = dao.selectByIdPw(conn, loginRequest.getUid(), loginRequest.getUpw());
			member = dao.selectByIdPw(loginRequest.getUid(), loginRequest.getUpw());
			
			// 2020.08.11 member.getVerify() 추가
			if(member != null && member.getVerify() == 'Y') {
				LoginInfo loginInfo = new LoginInfo(member.getUid(), member.getUname(), member.getUphoto());
				
				session.setAttribute("loginInfo", loginInfo);
				
				//쿠키 설정에 사용한 변수
				String cookieName = "uid";
				String cookiepath = session.getServletContext().getContextPath();
				
				// 회원 아이디 쿠키 설정
				if(loginRequest.getRemember() != null) {
					response.addCookie(CookieBox.createCookie(cookieName, loginRequest.getUid(), cookiepath, 60*60*24*365));
				} else {
					response.addCookie(CookieBox.createCookie(cookieName, loginRequest.getUid(), cookiepath, 0));
				}
				
				// 로그인이 필요했던 이전 페이지
				loginResult = "<script>" + "alert('로그인되었습니다.');"+"location.href='" + loginRequest.getRedirUri() + "'" + "</script>";
				
			} else if(member != null && member.getVerify() == 'N') {
				loginResult = "<script>";
				loginResult = "    if(confirm('회운 가입 후 메일인증이 안되었습니다. 인증메일을 다시 보내시겠습니까?')) {";
				loginResult = "        $.ajax(\'";
			} else {
				loginResult = "<script>" + "alert('아이디 또는 비밀번호가 틀립니다.');"+"history.go(-1);" + "</script>";
			}
		
		return loginResult;
	}
}
