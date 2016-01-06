<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>FileDownLoad</title>
</head>
<body>
	<c:forEach var="file" items="${files}" varStatus="status" >
		<a href="file_download?fileName=${file}">${file}</a><br>
	</c:forEach>
</body>
</html>