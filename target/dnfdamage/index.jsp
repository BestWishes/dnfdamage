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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/esayui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<title>DNF 伤害值计算</title>
</head>
<body>
	<div align="center">
		<select id="zhiyexuanzhe" class="easyui-combobox" name="zhiyexuanzhe" style="width:200px;" data-options="required:true,editable:false,panelHeight:'auto'">   
			<option value="baifenbizhiye">百分比职业</option>
			<option value="dulizhiye">固伤职业</option>
		</select>
	</div>
	<form id="baifenbiform" method="get" action="Damage">
		<div align="center">
			<table>
				<tr>
					<th align="left"><label class="">原有面板基础攻击</label></th>
					<td><input class="easyui-numberbox" type="text" name="oldgongji" id="oldgongji" data-options="required:true,min:1" /></td>
					<th rowspan="9">
						<img id="rb" width="50px" height="50px" alt="a" src="${pageContext.request.contextPath}/image/rr.png" ><br>
						<img id="lb" width="50px" height="50px" alt="a" src="${pageContext.request.contextPath}/image/ll.png" >
					</th>
					<th align="left"><label>面板基础攻击</label></th>
					<td><input class="easyui-numberbox" type="text" name="gongji" id="gongji" data-options="required:true,min:1" /></td>
					<td> <img id='imggongji'  width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"><label>原有无视攻击</label></th>
					<td><input class="easyui-numberbox" type="text" name="oldwushi" id="oldwushi" data-options="required:true,min:0" /></td>
					<th align="left"><label>无视攻击</label></th>
					<td><input class="easyui-numberbox" type="text" name="wushi" id="wushi" data-options="required:true,min:0" /></td>
					<td> <img id='imgwushi' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"><label>原有属性强化值</label> </th>
					<td> <input class="easyui-numberbox" type="text" name="oldshuxin" id="oldshuxin" data-options="required:true" /></td>
					<th align="left"><label>属性强化值</label> </th>
					<td> <input class="easyui-numberbox" type="text" name="shuxin"  id="shuxin" data-options="required:true" /></td>
					<td> <img id='imgshuxin' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"> <label>原有附加伤害</label></th>
					<td> <input class="easyui-numberbox" type="text" name="oldfujia" id="oldfujia" data-options="required:true,min:0" />%</td>
					<th align="left"> <label>附加伤害</label></th>
					<td> <input class="easyui-numberbox" type="text" name="fujia" id="fujia" data-options="required:true,min:0" />%</td>
					<td> <img id='imgfujia' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"> <label>原有增加伤害</label></th>
					<td> <input class="easyui-numberbox" type="text" name="oldzengjia" id="oldzengjia" data-options="required:true" />%</td>
					<th align="left"> <label>增加伤害</label></th>
					<td> <input class="easyui-numberbox" type="text" name="zengjia" id="zengjia" data-options="required:true" />%</td>
					<td> <img  id='imgzengjia' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"> <label>原有暴击增加伤害</label></th>
					<td> <input class="easyui-numberbox" type="text" name="oldbaoji" id="oldbaoji" data-options="required:true" />%</td>
					<th align="left"> <label>暴击增加伤害</label></th>
					<td> <input class="easyui-numberbox" type="text" name="baoji" id="baoji" data-options="required:true" />%</td>
					<td> <img  id='imgbaoji' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"> <label>原有暴击率</label></th>
					<td> <input class="easyui-numberbox" type="text" name="oldbaojilv" id="oldbaojilv" data-options="required:true,precision:2,max:100,min:0"/>%</td>
					<th align="left"> <label>暴击率</label></th>
					<td> <input class="easyui-numberbox" type="text" name="baojilv" id="baojilv" data-options="required:true,precision:2,max:100,min:0" />%</td>
					<td> <img  id='imgbaojilv' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"> <label>原有怪物属性抗性</label></th>
					<td> <input class="easyui-numberbox" type="text" name="oldkangxing" id="oldkangxing" data-options="required:true" /></td>
					<th align="left"> <label>怪物属性抗性</label></th>
					<td> <input class="easyui-numberbox" type="text" name="kangxing" id="kangxing" data-options="required:true" /></td>
					<td> <img  id='imgkangxing' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"> <label>原有怪物减伤</label></th>
					<td> <input class="easyui-numberbox" type="text" name="oldjianshang" id="oldjianshang" data-options="required:true,precision:2,max:99.99" />%</td>
					<th align="left"> <label>怪物减伤</label></th>
					<td> <input class="easyui-numberbox" type="text" name="jianshang" id="jianshang" data-options="required:true,precision:2,max:99.99" />%</td>
					<td> <img  id='imgjianshang' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
			</table>
			
		</div>
		<div align="center">
			<input type="submit" class="easyui-linkbutton" id="do" name="do" value="submit"></input>
			<label>比原来提升了：</label><input id="result" name="result" readonly="readonly"><label>的伤害</label>
			<td> <img id='imgresult' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
		</div>
	</form>
	<form id="duliform" method="post" action="Damage">
		<div align="center">
			<table>
				<tr>
					<th align="left"><label>原有力量(智力)</label></th>
					<td><input id="oldpower" class="easyui-numberbox" type="text" name="oldpower" data-options="required:true,min:1" /></td>
					<th rowspan="9">
						<img id="rd" width="50px" height="50px" alt="a" src="${pageContext.request.contextPath}/image/rr.png" ><br>
						<img id="ld" width="50px" height="50px" alt="a" src="${pageContext.request.contextPath}/image/ll.png" >
					</th>
					<th align="left"><label>力量(智力)</label></th>
					<td><input class="easyui-numberbox" type="text" name="power" id="power" data-options="required:true,min:1" /></td>
					<td> <img  id='imgpower' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"><label>原有独立</label></th>
					<td><input class="easyui-numberbox" type="text" name="oldduli" id="oldduli" data-options="required:true,min:0" /></td>
					<th align="left"><label>独立</label></th>
					<td><input class="easyui-numberbox" type="text" name="duli" id="duli" data-options="required:true,min:0" /></td>
					<td> <img  id='imgduli' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"><label>原有属性强化值</label> </th>
					<td> <input class="easyui-numberbox" type="text" name="oldshuxin" id="oldshuxin1" data-options="required:true,precision:0" /></td>
					<th align="left"><label>属性强化值</label> </th>
					<td> <input class="easyui-numberbox" type="text" name="shuxin" id="shuxin1" data-options="required:true,precision:0" /></td>
					<td> <img  id='imgshuxin1' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"> <label>原有附加伤害</label></th>
					<td> <input class="easyui-numberbox" type="text" name="oldfujia" id="oldfujia1" data-options="required:true,min:0" />%</td>
					<th align="left"> <label>附加伤害</label></th>
					<td> <input class="easyui-numberbox" type="text" name="fujia" id="fujia1" data-options="required:true,min:0" />%</td>
					<td> <img  id='imgfujia1' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"> <label>原有增加伤害</label></th>
					<td> <input class="easyui-numberbox" type="text" name="oldzengjia" id="oldzengjia1" data-options="required:true" />%</td>
					<th align="left"> <label>增加伤害</label></th>
					<td> <input class="easyui-numberbox" type="text" name="zengjia" id="zengjia1" data-options="required:true" />%</td>
					<td> <img  id='imgzengjia1' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"> <label>原有暴击增加伤害</label></th>
					<td> <input class="easyui-numberbox" type="text" name="oldbaoji" id="oldbaoji1" data-options="required:true" />%</td>
					<th align="left"> <label>暴击增加伤害</label></th>
					<td> <input class="easyui-numberbox" type="text" name="baoji" id="baoji1" data-options="required:true" />%</td>
					<td> <img  id='imgbaoji1' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"> <label>原有暴击率</label></th>
					<td> <input class="easyui-numberbox" type="text" name="oldbaojilv" id="oldbaojilv1" data-options="required:true,precision:2,max:100,min:0" />%</td>
					<th align="left"> <label>暴击率</label></th>
					<td> <input class="easyui-numberbox" type="text" name="baojilv" id="baojilv1" data-options="required:true,precision:2,max:100,min:0" />%</td>
					<td> <img  id='imgbaojilv1' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
				<tr>
					<th align="left"> <label>原有怪物属性抗性</label></th>
					<td> <input class="easyui-numberbox" type="text" name="oldkangxing" id="oldkangxing1" data-options="required:true" /></td>
					<th align="left"> <label>怪物属性抗性</label></th>
					<td> <input class="easyui-numberbox" type="text" name="kangxing" id="kangxing1" data-options="required:true" /></td>
					<td><img id='imgkangxing1'  width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png"></td>
				</tr>
				<tr>
					<th align="left"> <label>原有怪物减伤</label></th>
					<td> <input class="easyui-numberbox" type="text" name="oldjianshang" id="oldjianshang1" data-options="required:true,precision:2,max:99.99" />%</td>
					<th align="left"> <label>怪物减伤</label></th>
					<td> <input class="easyui-numberbox" type="text" name="jianshang" id="jianshang1" data-options="required:true,precision:2,max:99.99" />%</td>
					<td> <img  id='imgjianshang1' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
				</tr>
			</table>
		</div>
		<div align="center">
			<input type="submit" class="easyui-linkbutton" id="do1" name="do1" value="submit"></input>
			<label>比原来提升了：</label><input id="result1" name="result1" readonly="readonly"><label>的伤害</label>
			<td> <img  id='imgresult1' width="20px" height="20px" alt="a" src="${pageContext.request.contextPath}/image/equals.png" > </td>
		</div>
	</form>
	<div align="center">
		<a href="http://www.miitbeian.gov.cn/">蜀ICP备15036081号</a>
	</div>
</body>
</html>