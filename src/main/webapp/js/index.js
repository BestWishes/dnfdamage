$(document).ready(function() {
	$("#duliform").hide();

	
	$("#zhiyexuanzhe").combobox({
		onSelect : function() {
			if ($("#zhiyexuanzhe").combobox("getValue") == "baifenbizhiye") {
				$("#duliform").hide();
				$("#baifenbiform").show();
			} else if ($("#zhiyexuanzhe").combobox("getValue") == "dulizhiye") {
				$("#duliform").show();
				$("#baifenbiform").hide();
			}
		}
	});
	$("#testAction").click(function(){
		$.post("Weixin",null,function(){
			window.location.href="http://www.baidu.com";
			});
	})
	$("#duliform").form({
		onSubmit:function(param){
			param.zhiye=$("#zhiyexuanzhe").combobox("getValue");
			return $("#duliform").form('validate');
		},
		success : function(data) {
			if(data==""){
				$("#result1").val("系统维护中...");
			}else{
				$("#result1").val(data);
				if(parseFloat(data)>0){
					$("#imgresult1").attr('src',"image/up.png");
				}else if(parseFloat(data)<0){
					$("#imgresult1").attr('src',"image/down.jpg");
				}else{
					$("#imgresult1").attr('src',"image/equals.png");
				}
			}
			
		},
		onLoadError:function(){
			$("#result1").val("系统维护中...");
		}
	})
	$("#baifenbiform").form({
		onSubmit:function(param){
			param.zhiye=$("#zhiyexuanzhe").combobox("getValue");
			return $("#baifenbiform").form('validate');
		},
		
		success : function(data) {
			if(data==""){
				$("#result").val("系统维护中...");
			}else{
				$("#result").val(data);
				if(parseFloat(data)>0){
					$("#imgresult").attr('src',"image/up.png");
				}else if(parseFloat(data)<0){
					$("#imgresult").attr('src',"image/down.jpg");
				}else{
					$("#imgresult").attr('src',"image/equals.png");
				}
			}
			
		},
		onLoadError:function(){
			$("#result1").val("系统维护中...");
		}
	})
	
	$("#rd").click(function(){
		
		$("#power").textbox('setValue',$("#oldpower").textbox('getValue'));
		$("#duli").textbox('setValue',$("#oldduli").textbox('getValue'));
		$("#shuxin1").textbox('setValue',$("#oldshuxin1").textbox('getValue'));
		$("#fujia1").textbox('setValue',$("#oldfujia1").textbox('getValue'));
		$("#zengjia1").textbox('setValue',$("#oldzengjia1").textbox('getValue'));
		$("#baoji1").textbox('setValue',$("#oldbaoji1").textbox('getValue'));
		$("#baojilv1").textbox('setValue',$("#oldbaojilv1").textbox('getValue'));
		$("#kangxing1").textbox('setValue',$("#oldkangxing1").textbox('getValue'));
		$("#jianshang1").textbox('setValue',$("#oldjianshang1").textbox('getValue'));
	})
	$("#ld").click(function(){
		
		$("#oldpower").textbox('setValue',$("#power").textbox('getValue'));
		$("#oldduli").textbox('setValue',$("#duli").textbox('getValue'));
		$("#oldshuxin1").textbox('setValue',$("#shuxin1").textbox('getValue'));
		$("#oldfujia1").textbox('setValue',$("#fujia1").textbox('getValue'));
		$("#oldzengjia1").textbox('setValue',$("#zengjia1").textbox('getValue'));
		$("#oldbaoji1").textbox('setValue',$("#baoji1").textbox('getValue'));
		$("#oldbaojilv1").textbox('setValue',$("#baojilv1").textbox('getValue'));
		$("#oldkangxing1").textbox('setValue',$("#kangxing1").textbox('getValue'));
		$("#oldjianshang1").textbox('setValue',$("#jianshang1").textbox('getValue'));
	})
	
	$("#rb").click(function(){
		
		$("#gongji").textbox('setValue',$("#oldgongji").textbox('getValue'));
		$("#wushi").textbox('setValue',$("#oldwushi").textbox('getValue'));
		$("#shuxin").textbox('setValue',$("#oldshuxin").textbox('getValue'));
		$("#fujia").textbox('setValue',$("#oldfujia").textbox('getValue'));
		$("#zengjia").textbox('setValue',$("#oldzengjia").textbox('getValue'));
		$("#baoji").textbox('setValue',$("#oldbaoji").textbox('getValue'));
		$("#baojilv").textbox('setValue',$("#oldbaojilv").textbox('getValue'));
		$("#kangxing").textbox('setValue',$("#oldkangxing").textbox('getValue'));
		$("#jianshang").textbox('setValue',$("#oldjianshang").textbox('getValue'));
	})
	$("#lb").click(function(){
		
		$("#oldgongji").textbox('setValue',$("#gongji").textbox('getValue'));
		$("#oldwushi").textbox('setValue',$("#wushi").textbox('getValue'));
		$("#oldshuxin").textbox('setValue',$("#shuxin").textbox('getValue'));
		$("#oldfujia").textbox('setValue',$("#fujia").textbox('getValue'));
		$("#oldzengjia").textbox('setValue',$("#zengjia").textbox('getValue'));
		$("#oldbaoji").textbox('setValue',$("#baoji").textbox('getValue'));
		$("#oldbaojilv").textbox('setValue',$("#baojilv").textbox('getValue'));
		$("#oldkangxing").textbox('setValue',$("#kangxing").textbox('getValue'));
		$("#oldjianshang").textbox('setValue',$("#jianshang").textbox('getValue'));
	})
	
	
	$("#oldgongji").numberbox({
		'onChange':function(){
			if($("#oldgongji").textbox('getValue')<$("#gongji").textbox('getValue')){
				$("#imggongji").attr('src',"image/up.png");
			}else if($("#oldgongji").textbox('getValue')>$("#gongji").textbox('getValue')){
				$("#imggongji").attr('src',"image/down.jpg");
			}else{
				$("#imggongji").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#gongji").numberbox({
		'onChange':function(){
			if($("#oldgongji").textbox('getValue')<$("#gongji").textbox('getValue')){
				$("#imggongji").attr('src',"image/up.png");
			}else if($("#oldgongji").textbox('getValue')>$("#gongji").textbox('getValue')){
				$("#imggongji").attr('src',"image/down.jpg");
			}else{
				$("#imggongji").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#oldwushi").numberbox({
		'onChange':function(){
			if($("#oldwushi").textbox('getValue')<$("#wushi").textbox('getValue')){
				$("#imgwushi").attr('src',"image/up.png");
			}else if($("#oldwushi").textbox('getValue')>$("#wushi").textbox('getValue')){
				$("#imgwushi").attr('src',"image/down.jpg");
			}else{
				$("#imgwushi").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#wushi").numberbox({
		'onChange':function(){
			if($("#oldwushi").textbox('getValue')<$("#wushi").textbox('getValue')){
				$("#imgwushi").attr('src',"image/up.png");
			}else if($("#oldwushi").textbox('getValue')>$("#wushi").textbox('getValue')){
				$("#imgwushi").attr('src',"image/down.jpg");
			}else{
				$("#imgwushi").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#oldshuxin").numberbox({
		'onChange':function(){
			if($("#oldshuxin").textbox('getValue')<$("#shuxin").textbox('getValue')){
				$("#imgshuxin").attr('src',"image/up.png");
			}else if($("#oldshuxin").textbox('getValue')>$("#shuxin").textbox('getValue')){
				$("#imgshuxin").attr('src',"image/down.jpg");
			}else{
				$("#imgshuxin").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#shuxin").numberbox({
		'onChange':function(){
			if($("#oldshuxin").textbox('getValue')<$("#shuxin").textbox('getValue')){
				$("#imgshuxin").attr('src',"image/up.png");
			}else if($("#oldshuxin").textbox('getValue')>$("#shuxin").textbox('getValue')){
				$("#imgshuxin").attr('src',"image/down.jpg");
			}else{
				$("#imgshuxin").attr('src',"image/equals.png");
			}
			
		}
	});
	
	
	$("#oldfujia").numberbox({
		'onChange':function(){
			if($("#oldfujia").textbox('getValue')<$("#fujia").textbox('getValue')){
				$("#imgfujia").attr('src',"image/up.png");
			}else if($("#oldfujia").textbox('getValue')>$("#fujia").textbox('getValue')){
				$("#imgfujia").attr('src',"image/down.jpg");
			}else{
				$("#imgfujia").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#fujia").numberbox({
		'onChange':function(){
			if($("#oldfujia").textbox('getValue')<$("#fujia").textbox('getValue')){
				$("#imgfujia").attr('src',"image/up.png");
			}else if($("#oldfujia").textbox('getValue')>$("#fujia").textbox('getValue')){
				$("#imgfujia").attr('src',"image/down.jpg");
			}else{
				$("#imgfujia").attr('src',"image/equals.png");
			}
			
		}
	});
	
	$("#oldzengjia").numberbox({
		'onChange':function(){
			if($("#oldzengjia").textbox('getValue')<$("#zengjia").textbox('getValue')){
				$("#imgzengjia").attr('src',"image/up.png");
			}else if($("#oldzengjia").textbox('getValue')>$("#zengjia").textbox('getValue')){
				$("#imgzengjia").attr('src',"image/down.jpg");
			}else{
				$("#imgzengjia").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#zengjia").numberbox({
		'onChange':function(){
			if($("#oldzengjia").textbox('getValue')<$("#zengjia").textbox('getValue')){
				$("#imgzengjia").attr('src',"image/up.png");
			}else if($("#oldzengjia").textbox('getValue')>$("#zengjia").textbox('getValue')){
				$("#imgzengjia").attr('src',"image/down.jpg");
			}else{
				$("#imgzengjia").attr('src',"image/equals.png");
			}
			
		}
	});
	
	
	$("#oldbaoji").numberbox({
		'onChange':function(){
			if($("#oldbaoji").textbox('getValue')<$("#baoji").textbox('getValue')){
				$("#imgbaoji").attr('src',"image/up.png");
			}else if($("#oldbaoji").textbox('getValue')>$("#baoji").textbox('getValue')){
				$("#imgbaoji").attr('src',"image/down.jpg");
			}else{
				$("#imgbaoji").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#baoji").numberbox({
		'onChange':function(){
			if($("#oldbaoji").textbox('getValue')<$("#baoji").textbox('getValue')){
				$("#imgbaoji").attr('src',"image/up.png");
			}else if($("#oldbaoji").textbox('getValue')>$("#baoji").textbox('getValue')){
				$("#imgbaoji").attr('src',"image/down.jpg");
			}else{
				$("#imgbaoji").attr('src',"image/equals.png");
			}
			
		}
	});
	
	$("#oldbaojilv").numberbox({
		'onChange':function(){
			if($("#oldbaojilv").textbox('getValue')<$("#baojilv").textbox('getValue')){
				$("#imgbaojilv").attr('src',"image/up.png");
			}else if($("#oldbaojilv").textbox('getValue')>$("#baojilv").textbox('getValue')){
				$("#imgbaojilv").attr('src',"image/down.jpg");
			}else{
				$("#imgbaojilv").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#baojilv").numberbox({
		'onChange':function(){
			if($("#oldbaojilv").textbox('getValue')<$("#baojilv").textbox('getValue')){
				$("#imgbaojilv").attr('src',"image/up.png");
			}else if($("#oldbaojilv").textbox('getValue')>$("#baojilv").textbox('getValue')){
				$("#imgbaojilv").attr('src',"image/down.jpg");
			}else{
				$("#imgbaojilv").attr('src',"image/equals.png");
			}
			
		}
	});
	

	$("#oldkangxing").numberbox({
		'onChange':function(){
			if($("#oldkangxing").textbox('getValue')<$("#kangxing").textbox('getValue')){
				$("#imgkangxing").attr('src',"image/up.png");
			}else if($("#oldkangxing").textbox('getValue')>$("#kangxing").textbox('getValue')){
				$("#imgkangxing").attr('src',"image/down.jpg");
			}else{
				$("#imgkangxing").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#kangxing").numberbox({
		'onChange':function(){
			if($("#oldkangxing").textbox('getValue')<$("#kangxing").textbox('getValue')){
				$("#imgkangxing").attr('src',"image/up.png");
			}else if($("#oldkangxing").textbox('getValue')>$("#kangxing").textbox('getValue')){
				$("#imgkangxing").attr('src',"image/down.jpg");
			}else{
				$("#imgkangxing").attr('src',"image/equals.png");
			}
			
		}
	});
	
	$("#oldjianshang").numberbox({
		'onChange':function(){
			if($("#oldjianshang").textbox('getValue')<$("#jianshang").textbox('getValue')){
				$("#imgjianshang").attr('src',"image/up.png");
			}else if($("#oldjianshang").textbox('getValue')>$("#jianshang").textbox('getValue')){
				$("#imgjianshang").attr('src',"image/down.jpg");
			}else{
				$("#imgjianshang").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#jianshang").numberbox({
		'onChange':function(){
			if($("#oldjianshang").textbox('getValue')<$("#jianshang").textbox('getValue')){
				$("#imgjianshang").attr('src',"image/up.png");
			}else if($("#oldjianshang").textbox('getValue')>$("#jianshang").textbox('getValue')){
				$("#imgjianshang").attr('src',"image/down.jpg");
			}else{
				$("#imgjianshang").attr('src',"image/equals.png");
			}
			
		}
	});
	
	$("#oldpower").numberbox({
		'onChange':function(){
			if($("#oldpower").textbox('getValue')<$("#power").textbox('getValue')){
				$("#imgpower").attr('src',"image/up.png");
			}else if($("#oldpower").textbox('getValue')>$("#power").textbox('getValue')){
				$("#imgpower").attr('src',"image/down.jpg");
			}else{
				$("#imgpower").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#power").numberbox({
		'onChange':function(){
			if($("#oldpower").textbox('getValue')<$("#power").textbox('getValue')){
				$("#imgpower").attr('src',"image/up.png");
			}else if($("#oldpower").textbox('getValue')>$("#power").textbox('getValue')){
				$("#imgpower").attr('src',"image/down.jpg");
			}else{
				$("#imgpower").attr('src',"image/equals.png");
			}
			
		}
	});
	
	$("#oldduli").numberbox({
		'onChange':function(){
			if($("#oldduli").textbox('getValue')<$("#duli").textbox('getValue')){
				$("#imgduli").attr('src',"image/up.png");
			}else if($("#oldduli").textbox('getValue')>$("#duli").textbox('getValue')){
				$("#imgduli").attr('src',"image/down.jpg");
			}else{
				$("#imgduli").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#duli").numberbox({
		'onChange':function(){
			if($("#oldduli").textbox('getValue')<$("#duli").textbox('getValue')){
				$("#imgduli").attr('src',"image/up.png");
			}else if($("#oldduli").textbox('getValue')>$("#duli").textbox('getValue')){
				$("#imgduli").attr('src',"image/down.jpg");
			}else{
				$("#imgduli").attr('src',"image/equals.png");
			}
			
		}
	});
	
	$("#oldshuxin1").numberbox({
		'onChange':function(){
			if($("#oldshuxin1").textbox('getValue')<$("#shuxin1").textbox('getValue')){
				$("#imgshuxin1").attr('src',"image/up.png");
			}else if($("#oldshuxin1").textbox('getValue')>$("#shuxin1").textbox('getValue')){
				$("#imgshuxin1").attr('src',"image/down.jpg");
			}else{
				$("#imgshuxin1").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#shuxin1").numberbox({
		'onChange':function(){
			if($("#oldshuxin1").textbox('getValue')<$("#shuxin1").textbox('getValue')){
				$("#imgshuxin1").attr('src',"image/up.png");
			}else if($("#oldshuxin1").textbox('getValue')>$("#shuxin1").textbox('getValue')){
				$("#imgshuxin1").attr('src',"image/down.jpg");
			}else{
				$("#imgshuxin1").attr('src',"image/equals.png");
			}
			
		}
	});
	
	
	$("#oldfujia1").numberbox({
		'onChange':function(){
			if($("#oldfujia1").textbox('getValue')<$("#fujia1").textbox('getValue')){
				$("#imgfujia1").attr('src',"image/up.png");
			}else if($("#oldfujia1").textbox('getValue')>$("#fujia1").textbox('getValue')){
				$("#imgfujia1").attr('src',"image/down.jpg");
			}else{
				$("#imgfujia1").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#fujia1").numberbox({
		'onChange':function(){
			if($("#oldfujia1").textbox('getValue')<$("#fujia1").textbox('getValue')){
				$("#imgfujia1").attr('src',"image/up.png");
			}else if($("#oldfujia1").textbox('getValue')>$("#fujia1").textbox('getValue')){
				$("#imgfujia1").attr('src',"image/down.jpg");
			}else{
				$("#imgfujia1").attr('src',"image/equals.png");
			}
			
		}
	});
	
	$("#oldzengjia1").numberbox({
		'onChange':function(){
			if($("#oldzengjia1").textbox('getValue')<$("#zengjia1").textbox('getValue')){
				$("#imgzengjia1").attr('src',"image/up.png");
			}else if($("#oldzengjia1").textbox('getValue')>$("#zengjia1").textbox('getValue')){
				$("#imgzengjia1").attr('src',"image/down.jpg");
			}else{
				$("#imgzengjia1").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#zengjia1").numberbox({
		'onChange':function(){
			if($("#oldzengjia1").textbox('getValue')<$("#zengjia1").textbox('getValue')){
				$("#imgzengjia1").attr('src',"image/up.png");
			}else if($("#oldzengjia1").textbox('getValue')>$("#zengjia1").textbox('getValue')){
				$("#imgzengjia1").attr('src',"image/down.jpg");
			}else{
				$("#imgzengjia1").attr('src',"image/equals.png");
			}
			
		}
	});
	
	
	$("#oldbaoji1").numberbox({
		'onChange':function(){
			if($("#oldbaoji1").textbox('getValue')<$("#baoji1").textbox('getValue')){
				$("#imgbaoji1").attr('src',"image/up.png");
			}else if($("#oldbaoji1").textbox('getValue')>$("#baoji1").textbox('getValue')){
				$("#imgbaoji1").attr('src',"image/down.jpg");
			}else{
				$("#imgbaoji1").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#baoji1").numberbox({
		'onChange':function(){
			if($("#oldbaoji1").textbox('getValue')<$("#baoji1").textbox('getValue')){
				$("#imgbaoji1").attr('src',"image/up.png");
			}else if($("#oldbaoji1").textbox('getValue')>$("#baoji1").textbox('getValue')){
				$("#imgbaoji1").attr('src',"image/down.jpg");
			}else{
				$("#imgbaoji1").attr('src',"image/equals.png");
			}
			
		}
	});
	
	$("#oldbaojilv1").numberbox({
		'onChange':function(){
			if($("#oldbaojilv1").textbox('getValue')<$("#baojilv1").textbox('getValue')){
				$("#imgbaojilv1").attr('src',"image/up.png");
			}else if($("#oldbaojilv1").textbox('getValue')>$("#baojilv1").textbox('getValue')){
				$("#imgbaojilv1").attr('src',"image/down.jpg");
			}else{
				$("#imgbaojilv1").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#baojilv1").numberbox({
		'onChange':function(){
			if($("#oldbaojilv1").textbox('getValue')<$("#baojilv1").textbox('getValue')){
				$("#imgbaojilv1").attr('src',"image/up.png");
			}else if($("#oldbaojilv1").textbox('getValue')>$("#baojilv1").textbox('getValue')){
				$("#imgbaojilv1").attr('src',"image/down.jpg");
			}else{
				$("#imgbaojilv1").attr('src',"image/equals.png");
			}
			
		}
	});
	

	$("#oldkangxing1").numberbox({
		'onChange':function(){
			if($("#oldkangxing1").textbox('getValue')<$("#kangxing1").textbox('getValue')){
				$("#imgkangxing1").attr('src',"image/up.png");
			}else if($("#oldkangxing1").textbox('getValue')>$("#kangxing1").textbox('getValue')){
				$("#imgkangxing1").attr('src',"image/down.jpg");
			}else{
				$("#imgkangxing1").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#kangxing1").numberbox({
		'onChange':function(){
			if($("#oldkangxing1").textbox('getValue')<$("#kangxing1").textbox('getValue')){
				$("#imgkangxing1").attr('src',"image/up.png");
			}else if($("#oldkangxing1").textbox('getValue')>$("#kangxing1").textbox('getValue')){
				$("#imgkangxing1").attr('src',"image/down.jpg");
			}else{
				$("#imgkangxing1").attr('src',"image/equals.png");
			}
			
		}
	});
	
	$("#oldjianshang1").numberbox({
		'onChange':function(){
			if($("#oldjianshang1").textbox('getValue')<$("#jianshang1").textbox('getValue')){
				$("#imgjianshang1").attr('src',"image/up.png");
			}else if($("#oldjianshang1").textbox('getValue')>$("#jianshang1").textbox('getValue')){
				$("#imgjianshang1").attr('src',"image/down.jpg");
			}else{
				$("#imgjianshang1").attr('src',"image/equals.png");
			}
			
		}
	});
	$("#jianshang1").numberbox({
		'onChange':function(){
			if($("#oldjianshang1").textbox('getValue')<$("#jianshang1").textbox('getValue')){
				$("#imgjianshang1").attr('src',"image/up.png");
			}else if($("#oldjianshang1").textbox('getValue')>$("#jianshang1").textbox('getValue')){
				$("#imgjianshang1").attr('src',"image/down.jpg");
			}else{
				$("#imgjianshang1").attr('src',"image/equals.png");
			}
			
		}
	});
	
})