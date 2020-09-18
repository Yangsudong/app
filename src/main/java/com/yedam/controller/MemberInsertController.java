package com.yedam.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.model.MemberDAO;
import com.yedam.model.MemberVO;

public class MemberInsertController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("회원등록");
		// 파라미터를 VO에 담기
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String gender = request.getParameter("gender");
		String job = request.getParameter("job");
		String mailYN = request.getParameter("mailYN");
		String[] hobby = request.getParameterValues("hobby");
		String reason = request.getParameter("reason");
		//유효성 체크
		
		
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPass(pass);
		member.setGender(gender);
		member.setJob(job);
		member.setMailYN(mailYN);
		member.setHobby(Arrays.toString(hobby));
		member.setReason(reason);
		
		//서비스 처리
		int r = MemberDAO.getInstance().insert(member);
		
		//결과 저장
		request.setAttribute("cnt", r);
		request.setAttribute("member", member);
		//뷰페이지로 이동
		request.getRequestDispatcher("/member/memberInsertOutput.jsp").forward(request, response);
	}	

}
