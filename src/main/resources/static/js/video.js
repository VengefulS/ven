var table;
var aid;
//var oUrl = "/video/queryAllVideo";
//var oUrl = "/video/findVideoByActid2?activityId=" ;
//var oUrl;
var oUrl;
var sUrl = "/video/queryAllVideo";
$(document).ready(function() {
	
	$.ajax({
		url : "/act/aidAname",
		type : "POST",
		dataType : "json",
		async : false,
		success : function(data) {
			aid = data.activityId;		
		}
	});
	if(aid == null){
		oUrl = "/video/queryAllVideo";
		sUrl = oUrl;
	}else{
		oUrl = "/video/findVideoByActid2?activityId=";
		sUrl = oUrl + aid;
	}
	
	//alert(sUrl);
	//alert(aid);
	//query2();


	
	query2();
	$("#findAllVideo_filter .input-sm").attr({
		'placeholder' : '按视频标签查找'
	});
})

function query2(search) {

	/* ==============================datatables的配置======================= */
	var datatables_options = {
			// 是否使用服务器端处理模式（依赖paging）
			"serverSide" : true,
			// 是否分页
			"paging" : true,
			// 是否显示每页多少条记录下拉框（依赖paging）
			"lengthChange" : true,
			// 完整页数按钮
			// "pagingType" : "full_numbers",
			// 是否显示左下角信息
			"info" : true,
			// 自定义分页长度
			"lengthMenu" : [ [ 10, 20, 30, 100 ], [ "10", "20", "30", "100" ] ],
			// 是否使用排序
			"ordering" : false,
			// 是否使用搜索
			"searching" : true,
			// 是否自动计算列宽
			"autoWidth" : true,
			// 是否显示处理状态
			"processing" : false,
			// 是否水平滚动
			"scrollX" : false,
			// 是否垂直滚动（依赖paging）
			"scrollY" : false,
			// 是否延迟渲染
			"deferRender" : true,
			// 是否显示过滤信息
			"filter" : true,
			// 详细分页组，可以支持直接跳转到某页
			"sPaginationType" : "full_numbers",
			// 是否启动过滤、搜索功能
			"bFilter" : false,
			"destroy": true,
			"search" : {
				"smart" : true,
			},// 智能搜索
			// "destroy": true,
			// 国际化配置
			"language" : {
				"sProcessing" : "处理中...",
				"sLengthMenu" : "显示 _MENU_ 项结果",
				"sZeroRecords" : "没有匹配结果",
				"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
				"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
				"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
				"sInfoPostFix" : "",
				"sSearch" : "活动搜索:",
				"sUrl" : "",
				"sEmptyTable" : "表中数据为空",
				"sLoadingRecords" : "载入中...",
				"sInfoThousands" : ",",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上页",
					"sNext" : "下页",
					"sLast" : "末页"
				}
			},

			// 异步请求
			"ajax" : {
				"url" : sUrl,
				"type" : "POST",
				"async" : false,
				"dataType" : "json",
				/* "cache": false, */
				"dataFilter" : function(data, type) {
					console.log("执行ajax");
					var json = JSON.parse(data);
					var returnData = {};
					returnData.draw = json.draw;
					returnData.recordsTotal = json.recordsTotal;
					returnData.recordsFiltered = json.recordsFiltered;
					returnData.data = json.data;
					returnData.length = json.length;
					return JSON.stringify(returnData);
				}
			},
			// 列描述
			"columns" : [ {
				"title" : "预览图",
				"type" : "html",
				"data" : "",
				"defaultContent" : "预览图",
				"visible" : true,
				"orderable" : true,
				"width" : "50px"
			}, {
				"title" : "视频名称",
				"type" : "html",
				"data" : "videoName",
				"defaultContent" : "视频无名称",
				"visible" : true,
				"orderable" : true,
				"width" : "100px",
				"word-wrap" : "break-word",
				"searchable" : true,
			}, {
				"title" : "视频上传时间",
				"type" : "html",
				"data" : "displayVideoUploadTime",
				"defaultContent" : "",
				"visible" : true,
				"orderable" : true,
				"width" : "100px",
			}, {
				"title" : "视频时长",
				"type" : "html",
				"data" : "videoName",
				"defaultContent" : "",
				"visible" : true,
				"orderable" : true,
				"width" : "120px",
			}, {
				"title" : "视频标签",
				"type" : "html",
				"data" : "tagNames",
				"defaultContent" : "视频无标签",
				"visible" : true,
				"orderable" : true,
				"width" : "100px",
				
			}, {
				"title" : "视频信息",
				"type" : "html",
				"data" : "",
				"defaultContent" : "",
				"visible" : true,
				"orderable" : true,
				"width" : "80px"
			}, {
				"title" : "下载",
				"type" : "html",
				"data" : "",
				"defaultContent" : "",
				"visible" : true,
				"orderable" : true,
				"width" : "80px"
			} ],
			//data-toggle=\"modal\" data-target=\"#activityInfoModal\"
			// 从右向左第三列列描述 加按钮
			"columnDefs" : [ {
				"targets" : -1,
				"render" : function(data, type, full, meta) {
					var ret = "<button id="
							+ full.videoId
							+ " class = \"btn-def\"  onClick=\"downloadVideo(this)\" ><span class=\"glyphicon glyphicon-download-alt\"></span> </button>"

					return ret;
				}

			},{
				"targets" : -2,
				"render" : function(data, type, full, meta) {
					var ret = "<button id="
						+ full.videoId
						+ " name="
						+ full.videoName
						+ " class = \"btn btn-primary btn-sm\" type=\"button\"  data-toggle=\"modal\" data-target=\"#videoInfoModal\" onClick=\"modalVideoInfo(this)\" ><span class=\"glyphicon glyphicon-edit\"></span>修改</button>"
				return ret;
				}

			},{
				"targets" : 0,
				"render" : function(data, type, full, meta) {
					var ret = "<img src="
						+ full.videoPicAddress
						+ " class = \"videoP\" alt=\"视频缩略图\" >"
				return ret;
				}

			} ]

		}
	
	
		table = $("#findAllVideo").dataTable(datatables_options).api();

}



//$('#input-videoSearch').bind('input propertychange',function() {
//	var activityId = this.value;
//	url = "/video/findVideoByActid2?activityId=" + activityId;
//	table.ajax.url(url).load();
//});

$('#toHome').click(function(){
	
		$.ajax({
			url : "/login/toH",
			type : "POST",
			async : false,
			data : {
				
			},
			success : function() {
				
				window.location.href="/home";
			}
		
		});
	
})



//function query2(search) {
//
//	table = $("#findAllVideo").dataTable(datatables_options).api();
//
//}

function downloadVideo(video){
	console.log("downloadVideo");
	window.location.href = "/videod/downloadVideo?videoId=" + video.id;
}


function modalVideoInfo(v) {

	
	var strs = new Array();
	var str= null;
	var i = null;
	var s = "";
	var videoId = null;
	
	$("#videoTagUp").attr("data",v.id);
	
	$.ajax({
		url : "/video/findOneVideo",
		type : "POST",
		dataType : "json",
		async : false,
		data : {
			"videoId" : v.id
		},
		success : function(data) {
			str = data.videoTag;
			if(str != null){
				strs = str.split(",");
			}
			i = strs.length;
			videoId = data.videoId;
			for(var a=0;a<i;a++){
				s += "<div class = 'tags_style_class tags_del' data='"+videoId+"'>"+strs[a]+"</div>";
			}
			$("#videoNameUp").val(data.videoName),//
			$("#videoTagUp").append(s),//val(data.videoTag),
			$("#videoIdUp").val(data.videoId)
		}
	});
	$("#videoTagUp").append("<div class='tags_style_class' id='addTag' onclick='addTagNamesDiv1()'  style='cursor:pointer;float: right;'> +展开标签库  </div>")
}
//x 和 关闭 两个按钮
$(".videoModalClose").click(function(){
	$("#videoTagUp").empty();
	/*if($("#selectTagNames")){
		$("#selectTagNames").remove();
	}*/
	var k = $("#selectTagNames").css("display") 
	if(k == "block"){
		$("#selectTagNames").empty();
		$("#selectTagNames").css("display","none");
	}
	query2();
})


//点击保存按钮
$("#updateVideo").click(function(){
	
	var vn = $("#videoNameUp").val();
	console.log(vn);
	var vid = $("#videoTagUp").attr("data");
	$.ajax({
		url : "/video/updateVideoInfo",
		type : "POST",
		dataType : "json",
		async : false,
		data : {
			"videoId" : vid,
			"videoName" : vn
		},
		success : function(data) {
		}
	});
	
	$("#videoTagUp").empty();
	$("#selectTagNames").empty();
	$("#selectTagNames").css("display","none");
	$(".deleteTag").remove();
	query2();
})

function addTagNamesDiv1(){
	var i=null;
	var str = "";
	var videoId = null;
	$("#addTag").html(" -隐藏标签库 ")
	$("#selectTagNames").css("display","block");
	$("#addTag").attr("onclick","addTagNamesDiv2()");
	//<div class="tags_style_class"><span>演出</span></div>
	$(".deleteTag").remove();
	$(".tags_del").append("<div class='deleteTag' onclick='deleteTag(this)'></div>");
	
	var vid = $("#videoTagUp").attr("data");//$(".tags_del").attr("data");//==================vid
	$.ajax({
		url : "/videoTag/getAllVideoTag",
		type : "POST",
		dataType : "json",
		async : false,
		success : function(data) {
			
			i = data.length;
			
			for(var a=0;a<i;a++){
				str += "<div class = 'tags_style_class tags_add_class' data='"+vid+"'  onclick='addTags(this)' >"+data[a].tag_name+"</div>";
			}
			$("#selectTagNames").append(str);
		}
	});
}
function addTagNamesDiv2(){
	
	$("#addTag").html("+展开标签库")
	$("#selectTagNames").empty();
	$("#selectTagNames").css("display","none");
	$("#addTag").attr("onclick","addTagNamesDiv1()");
	$(".deleteTag").remove();
}


//点击红色x号 删除单个标签

function deleteTag(d){
	
	var r = confirm("是否确认删除该标签？");
	
	if(r){
		var videoId = d.parentElement.getAttribute("data");
		var tagName = d.parentElement.textContent;
		
		$.ajax({
			url : "/video/deleteMatchRelByName",
			type : "POST",
			dataType : "json",
			async : false,
			data : {
				"videoId" : videoId,
				"tagName" : tagName
			},
			success : function(data) {
				}
			});
		$("#videoTagUp").empty();
		$("#selectTagNames").empty();
		addTagNamesDiv2()
		var v = {};
		v["id"] = videoId;
		console.log(v.id);
		modalVideoInfo(v)
	}
	

}

$(".tags_add_class").click(function(){
	console.log("1");
})

//点击标签库中的标签 添加
function addTags(t){
	var tt = t.innerText;
	var videoId = t.getAttribute("data");
	console.log(tt);
	console.log(videoId);
	var strs;
	var id = $("#videoTagUp").attr("data");//$(".tags_del").attr("data");
	var str = "<div class = 'tags_style_class tags_del' data='"+id+"'>"+tt+"</div>";
	$("#videoTagUp>div").each(function(){
		strs += $(this).text();
		
    });
	
	if(strs.indexOf(tt) != -1){
		alert("已有此标签");
	}else{
		//$("#videoTagUp").html();
		$("#selectTagNames").empty();
		$("#videoTagUp").append(str);
		addTagNamesDiv1();
		$.ajax({
			url : "/video/addMatchRel",
			type : "POST",
			dataType : "json",
			async : false,
			data : {
				"videoId" : videoId,
				"tagName" : tt
			},
			success : function(data) {
				
				}
			});
	}
		
}


$("#addNewTag").click(function(){
	var tagName = $("#addTagNames").val();
	var p = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]") 
	
	if(p.test(tagName)){
		alert("标签不可含有符号");
		return false;
	}else{
		
		//进行保存
		$.ajax({
			url : "/videoTag/addVideoTag",
			type : "POST",
			dataType : "json",
			async : false,
			data : {
				"tagName" : tagName
			},
			success : function(data) {
				
				}
			});
		$("#addTagNames").val("");
	}
	//alert(reg);
})

$(".close-tag").click(function(){
	$("#addTagNames").val("");
})


