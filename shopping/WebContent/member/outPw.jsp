<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function outConfirm(){
	if(confirm("정말, 사실, 리얼, 진짜로 탈퇴할꺼냐?")){
		document.frm.submit();
	}else{
		return false;
	}
}
</script>
</head>
<body>
<form action="memOutOk.mem" method="post" onsubmit="return couConfirm()" name="frm">
비밀번호 : <input type="pasword" name="memPw" />
<span>${pwFail }</span><br/>
<input type="submit" value="탈퇴" />

</form>

</body>
</html>