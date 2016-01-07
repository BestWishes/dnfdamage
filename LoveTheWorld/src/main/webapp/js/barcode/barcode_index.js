$(document).ready(function(){
	
	$("#barcode_button").click(function(){
		
		$("#barcode_form").form({
			success:function(barcode_img){
				alert(barcode_img);
			}
		})
		$("#barcode_form").form('submit');
	})
	
	
})