
$(document).ready(function(){

	$("#file").change(function(){
//		$.messager.alert("提示",window.navigator.userAgent.indexOf("MSIE"));
//		if(window.navigator.userAgent.indexOf("MSIE")>=1){
//			document.getElementById("picDiv").style.display="block";
//			document.getElementById("picDiv").style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='"+$(this).val()+"')";
//		}else{
//			
//		}
		$("#file_upload_form").submit();
		return false;
	})
})
