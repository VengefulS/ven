$(document).ready(function() {
	$.ajax({
		url : "/act/loginName",
		type : "POST",
		dataType : "json",
		async : false,
		success : function(data) {
			document.getElementsByTagName('b')[0].innerHTML=data.loginName;
		}
	});

})