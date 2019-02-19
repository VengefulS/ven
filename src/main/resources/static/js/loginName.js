$(document).ready(function() {
	$.ajax({
		url : "/act/loginName",
		type : "POST",
		dataType : "json",
		async : false,
		success : function(data) {
			
			var loginName = data.loginName;
			document.getElementsByTagName('b')[0].innerHTML=loginName;
			if(loginName == "管理员"){				
				$(".btn-download").removeAttr("title");
				$(".btn-download").removeAttr("disabled");
			}
			
			
		}
	});
	
	

})