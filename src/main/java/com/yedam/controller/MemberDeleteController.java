package com.yedam.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.model.MemberDAO;
import com.yedam.model.MemberVO;

public class MemberDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("회원삭제");
		//파라미터 변수에 저장
				String id = request.getParameter("id");
				
				//유효성 체크(id가 null인지)
				if(id.isEmpty()) {
					request.setAttribute("error", "ID를 입력..");
					request.getRequestDispatcher("/member/memberDelete.jsp").forward(request, response);
					System.out.println();
					return;
				} 
				
				//VO에 담기
				MemberVO member = new MemberVO();
				member.setId(id);
				
				//서비스
				MemberDAO.getInstance().delete(member);
				
				//조회결과를 저장후에 결과페이지로 포워드
				request.getRequestDispatcher("/member/memberDeleteOutput.jsp").forward(request, response);
	}

}
