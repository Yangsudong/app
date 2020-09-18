<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function inputCheck() {
	//id, pw 필수입력체크
	if(frm.id.value == ""){
		window.alert("id 입력");
		frm.id.focus();
		return false; 
	}
	if(frm.pass.value == ""){
		alert("pw 입력");
		frm.pass.focus();
		return false;		
	}	
	
	//job(select태그)
	if(frm.job.value == "") {
		alert("job 선택");
		frm.job.focus();
		return false;
	}
	//radio, checkbox
	var gender =
		document.querySelectorAll("[name ='gender']:checked").length;
	if(gender == 0) {
		alert("성별 적어도 하나는 선택");
		return false;
	}
	
	//frm.submit();
	return true;
} 


</script>
</head>
<body>
<h3 class="page_title">회원등록</h3>
<%-- /frontWeb/memberInsert.do or ../memberInsert.do --%>
<form method="post" name="frm" id="frm"
	  action = "${pageContext.request.contextPath}/memberInsert.do"
	  onsubmit="return inputCheck()">
	<div class="regist">
		<label>id</label><input type="text" name="id"><br>
		<label>pass</label><input type="password" name="pass">
	</div>			
	<div>
		<label> 성별</label>  
		<input type="radio" name="gender" value=man checked>남자 
    	<input type="radio" name="gender" value=woman>여자 
    </div><br>
    
	<div>
		<p><label>직업</label></p>
		<select name="job" id="job">
			<option value="" selected>선택</option>
			<option value="devlope">개발자</option>
			<option value="dba">관리자</option>
			<option value="doctor">의사</option>
			<option value="student">학생</option>	
		</select>
	</div><br>
	<div>
		<label>메일수신여부</label>	
		<input type="checkbox" name="mailYN" value="y" checked>동의
		<!-- <input type="checkbox" name="mailYN" value="n" >미동의 -->
	</div><br>
	<div>
		<label for="hobby">취미</label>	
		<input type="checkbox" name="hobby" value="read">독서
		<input type="checkbox" name="hobby" value="game">게임
		<input type="checkbox" name="hobby" value="ski">스키
	</div><br>
	<div>
		<label> 가입동기</label><br>
		<textarea id = "reason" name="reason"></textarea>
	</div>
	<div>
	<button type="reset">초기화</button>
	<button>등록</button>
	</div>
	
	
</form>
</body>
</html>