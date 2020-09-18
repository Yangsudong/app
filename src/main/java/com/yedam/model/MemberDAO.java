package com.yedam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yedam.common.ConnectionManager;

public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//싱글톤
	static MemberDAO instance;
	public static MemberDAO getInstance() {
		if(instance==null)
			instance=new MemberDAO();
			return instance;
	}
	
	//전체조회
	public ArrayList<MemberVO> selectAll(MemberVO memberVO) {
		MemberVO resultVO = null;
		ArrayList<MemberVO> list = new ArrayList<MemberVO>(); 
		
		try {
			conn = ConnectionManager.getConnnect();
			String sql = " SELECT ID, PASS, GENDER, JOB, MAILYN, REASON, HOBBY, REGDATE"
					   + " FROM MEMBERS"
					   + " ORDER BY ID ";
			
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resultVO = new MemberVO();
				resultVO.setId(rs.getString(1));
				resultVO.setPass(rs.getString(2));
				resultVO.setGender(rs.getString(3));
				resultVO.setJob(rs.getString(4));
				resultVO.setMailYN(rs.getString(5));
				resultVO.setReason(rs.getString(6));
				resultVO.setHobby(rs.getString(7));
				resultVO.setRegdate(rs.getString(8));
				list.add(resultVO);
			} 
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return list;
	}
	
	
	//단건조회
	public MemberVO selectOne(MemberVO memberVO) {
		MemberVO resultVO = null;
		
		try {
			conn = ConnectionManager.getConnnect();
			String sql = " SELECT ID, PASS, GENDER, JOB, MAILYN, REASON, HOBBY, REGDATE"
					   + " FROM MEMBERS"
					   + " WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memberVO.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultVO = new MemberVO();
				resultVO.setId(rs.getString(1));
				resultVO.setPass(rs.getString(2));
				resultVO.setGender(rs.getString(3));
				resultVO.setJob(rs.getString(4));
				resultVO.setMailYN(rs.getString(5));
				resultVO.setReason(rs.getString(6));
				resultVO.setHobby(rs.getString(7));
				resultVO.setRegdate(rs.getString(8));
				
			} else {
				System.out.println("아이디가 없습니다");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return resultVO;
	}
	
	//메일수신회원수 :  select count(id) cnt from members where mailYn='y'; 
	public int getMailynCnt() {
		int cnt = 0;
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "SELECT COUNT(ID) CNT FROM MEMBERS WHERE MAILYN='Y'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			cnt = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		return cnt;
	}
	
	// 성별인원수 : select gender, count(id) cnt from members group by gender;
	public List<HashMap<String, Object>> getGenderCnt() {
		List<HashMap<String, Object>> list =
								new ArrayList<HashMap<String, Object>>();
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "SELECT GENDER, COUNT(ID) CNT FROM MEMBERS GROUP BY GENDER";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("gender", rs.getString("gender"));
				map.put("cnt", rs.getInt("cnt"));
				list.add(map);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		return list;
	}
	
	
	public void delete(MemberVO memberVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "DELETE FROM MEMBERS WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVO.getId());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 삭제됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		
	}
	
	public void update(MemberVO memberVO) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "UPDATE MEMBERS SET PASS = ?,GENDER = ?, JOB = ?, MAILYN = ?,HOBBY = ?, REASON = ? WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVO.getPass());
			pstmt.setString(2, memberVO.getGender());
			pstmt.setString(3, memberVO.getJob());
			pstmt.setString(4, memberVO.getMailYN());
			pstmt.setString(5, memberVO.getHobby());
			pstmt.setString(6, memberVO.getReason());
			pstmt.setString(7, memberVO.getId());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 입력됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
		
	}
	
	public int insert(MemberVO memberVO) {
		int r=0;
		try {
			//1.DB연결
		 conn = ConnectionManager.getConnnect();
			
			//2.sql 구문 실행
			String sql = "INSERT INTO MEMBERS VALUES(?,?,?,?,?,?,?,sysdate)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPass());	
			pstmt.setString(3, memberVO.getGender());	
			pstmt.setString(4, memberVO.getJob());	
			pstmt.setString(5, memberVO.getMailYN());	
			pstmt.setString(6, memberVO.getReason());	
			pstmt.setString(7, memberVO.getHobby());
			r = pstmt.executeUpdate();
			
			//3.결과처리
			System.out.println(r + "건이 처리됨");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//4.연결해제
			ConnectionManager.close(null, pstmt, conn);
		}
		return r;
	}
}
