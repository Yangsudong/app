<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberUpdate.jsp</title>
</head>
<body>
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
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
 
<script>
	$(function() {
		//초기화
		$("[name=gender]").val(["${member.gender}"]);
		$("[name=hobby]").val("${member.hobby}".replace(/\[|\]/g,"").split("/"));
		$("[name=mailYN]").val(["${member.mailYN}"]);
		$("#frm [name=job]").val("${member.job}");
	});
</script>

</head>
<body>
<h3>회원수정</h3>
${error}
<form action = "${pageContext.request.contextPath}/memberSearch.do">
	<input type="hidden" name="job" value="update"> 
	id : <input name="id">
	<button>검색</button>	
</form>	
<c:if test="${not empty member}">	
<h3>검색결과</h3>
<form method="post" name="frm" id="frm"
	  action = "${pageContext.request.contextPath}/memberUpdate.do"
	  onsubmit="return inputCheck()">
	<div>
		<label for="id">id</label>
		<input name="id" id="id" value="${member.id}" read="readonly"><br>
		<label for="pass">pass</label>	
		<input name="pass" id="pass" value="${member.pass}"><br>
	</div>			
	<div>
		<label> 성별</label>  
		<input type="radio" name="gender" value=man>남자 
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
		<input type="checkbox" name="mailYN" value="y">동의
	</div><br>
	<div>
		<label for="hobby">취미</label>	
		<input type="checkbox" name="hobby" value="read">독서
		<input type="checkbox" name="hobby" value="game">게임
		<input type="checkbox" name="hobby" value="ski">스키
	</div><br>
	<div>
		<label> 가입동기</label><br>
		<textarea id = "reason" name="reason">${member.reason}</textarea>
	</div>
	<div>
	<button type="reset">초기화</button>
	<button>등록</button>
	</div>
</form>
</c:if>
</body>
</html>