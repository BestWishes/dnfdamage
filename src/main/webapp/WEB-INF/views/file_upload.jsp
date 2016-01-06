<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/comm/esayui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/comm/esayui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/comm/esayui/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/esayui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/esayui/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/esayui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/file_upload.js"></script>
<title>File</title>
</head>
<body>
	welcome: ${userName }
	<div id="file_upload_div">
		<form method="post" enctype="multipart/form-data" id="file_upload_form" action="file_upload">
			<input type="file" id="file" name="file" ><img id="img" alt="已选择的图片" src=""><div id="picDiv"></div>
			<input type="text" id="userName" name="userName" >
			<input type="submit" value="提交">
		</form>
	</div>
</body>
</html>