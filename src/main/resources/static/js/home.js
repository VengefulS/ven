
$(document).ready(function(){
	var table;
	query("");
	$("#findAll_filter .input-sm").attr({'placeholder':'按活动名称查找'});

	
})

function query(search){
	//console.log(search);
	table = $("#findAll").dataTable({
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
		"lengthMenu" : [[10, 20, 30, 100], ["10", "20", "30", "100"]],
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
		//详细分页组，可以支持直接跳转到某页
		"sPaginationType" : "full_numbers", 
		//是否启动过滤、搜索功能 
		"bFilter" : false, 
		/*//智能搜索
		"search": {
		   "smart": true,
		}, */
		// 国际化配置
		"language" : {
			"sProcessing" : "处理中...",
			"sLengthMenu" : "显示 _MENU_ 项结果",
			"sZeroRecords" : "没有匹配结果",
			"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
			"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
			"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
			"sInfoPostFix" : "",
			"sSearch" : "搜索:",
			"sUrl" : "",
			"sEmptyTable" : "表中数据为空",
			"sLoadingRecords" : "载入中...",
			"sInfoThousands" : ",",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上页",
				"sNext" : "下页",
				"sLast" : "末页"
			},
			"oAria" : {
				"sSortAscending" : ": 以升序排列此列",
				"sSortDescending" : ": 以降序排列此列"
			}
		},
		
		
		// 异步请求
		"ajax" : {
			"url" : "/act/findAll",
			"type" : "POST",
			"async" : false,
			"dataType" : "json",
			"dataFilter" : function(data,type) {
				var json = JSON.parse(data);
				var returnData = {};
				returnData.draw = json.draw;
				returnData.recordsTotal = json.recordsTotal;
				returnData.recordsFiltered = json.recordsFiltered;
				returnData.data = json.data;
				return JSON.stringify(returnData);
			}	
		},
		// 列描述
		"columns" : [{
			"title" : "活动名称",
			"type" : "html",
			"data" : "activityName",
			"defaultContent" : "",
			"visible" : true
		}, {
			"title" : "时间",
			"type" : "html",
			"data" : "displayActivityBeginDate",
			"defaultContent" : "",
			"visible" : true
		},{
			"title" : "地点",
			"type" : "html",
			"data" : "activitySite",
			"defaultContent" : "",
			"visible" : true
		}, {
			"title" : "门类",
			"type" : "html",
			"data" : "activityType",
			"defaultContent" : "",
			"visible" : true
		},{
			"title" : "相关人物",
			"type" : "html",
			"data" : "activityPerson",
			"defaultContent" : "",
			"visible" : true
		}, {
			"title" : "采集人",
			"type" : "html",
			"data" : "activityVideoGatherer",
			"defaultContent" : "",
			"visible" : true
		},{
			"title" : "预览",
			"type" : "html",
			"data" : "",
			"defaultContent" : "",
			"visible" : true
		}
		],
		// 最后列描述  加按钮
		"columnDefs" : [{
			"targets" : -1,
			"render" : function(data, type, full, meta) {
				var ret = "<button id="+full.activityId+" name="+full.activityName+" class = \"btn btn-default btn-sm btn-tmodal\" type=\"button\"  data-toggle=\"modal\" data-target=\"#videoModal\" onClick=\"modalVideo(this)\"  ><span class=\"glyphicon glyphicon-play\"></span> </button>"
				/*var btn = $("<button class = \"btn btn-default btn-sm btn-tmodal\" type=\"button\"  data-toggle=\"modal\" data-target=\"#videoModal\" onClick=\"modalVideo(this)\"> </button>")
				btn.data("id",actid);
				btn.append("<span class=\"glyphicon glyphicon-play\"></span>");*/
				return ret;
				}
			
			}]

	}).api();
	
	
}


function modalVideo(act,data){
	$('.modal-video').empty();
	$('#myModalLabel').html('');
	
	$('#myModalLabel').html(act.name);
	// console.log(act.id);
	$.ajax({
		url	: "/video/findVideoByActid",
		type  : "POST",
		dataType : "json",
		async  : false,
		data  :{"activityId" : act.id},
		success : function(data){
			console.log("11111111111");
			var txt='';
			for(var i = 0;i<data.data.length;i++){
				txt = '\<div class="col-sm-8 col-md-4"\>'
					+'\<a href="#" class="thumbnail" \>'
					+'\<img src="'
					+data.data[i].videoAddress
					+'"alt="视频缩略图"\>\</a\>'
					+data.data[i].videoName
					+'\</div\>';
				$('.modal-video').append(txt);
			}
		}
		
		/*"dataFilter" : function(data,type) {
			var json = JSON.parse(data);
			var returnData = {};
			returnData.data = json.data;
			return JSON.stringify(returnData);
		}*/
	});
	
	var txtend=	'\<div class="col-sm-8 col-md-4"\>'
		+'\<a href="#" class="thumbnail" \>'
		+'\<img src='
		+'"img/add.gif"'
		+'alt="视频添加"\>\</a\>'
		+'\</div\>';
	$('.modal-video').append(txtend);
}

