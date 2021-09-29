<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#btn1{
		border-top-left-radius: 5px;
		border-bottom-left-radius: 5px;
		margin-right: -4px;
	}
	#btn2{
		border-top-right-radius: 5px;
		border-bottom-right-radius: 5px;
		margin-left: -3px;
	}
	#btn_group button{
		border: 1px solid skyblue;
		background-color: rgba(0,0,0,0);
		color:skyblue;
		padding: 5px;
	}
	#btn_group button:hover {
		color: white;
		background-color: skyblue;
	}
	
</style>
</head>
<body>
<section>
	<div style="text-align : center;">
		<img alt="" src="errorPicture/404에러.PNG" width="40%" ><br><br><br>
		<div id="btn_group">
			<button id="btn1" type="button" onclick="location.href='index'">메인페이지로</button>
			<button id="btn2" type="button" onclick="javascript:history.back()">이전페이지로</button>
		</div>
	</div>
</section>
</body>
</html>