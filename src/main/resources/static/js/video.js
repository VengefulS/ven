var table;
$(document).ready(function() {
	query2();
})

function query2(search) {
	
	table = $("#findAllVideo")
			.dataTable(datatables_options).api();

}
/*==============================datatables的配置=======================*/
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
		"lengthMenu" : [ [ 10, 20, 30, 100 ],
				[ "10", "20", "30", "100" ] ],
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
		
		 "search": { "smart": true, },//智能搜索
		 //"destroy": true,
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
			"url" : "/video/queryAllVideo",
			"type" : "POST",
			"async" : false,
			"dataType" : "json",
			/*"cache": false,*/
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
			"title" : "视频名称",
			"type" : "html",
			"data" : "videoName",
			"defaultContent" : "视频无名称",
			"visible" : true,
			"orderable": true,"width":"185px",
			"word-wrap": "break-word",
			"searchable": true,
		}, {
			"title" : "视频上传时间",
			"type" : "html",
			"data" : "displayVideoUploadTime",
			"defaultContent" : "",
			"visible" : true,
			"orderable": true,"width":"130px",
		}, {
			"title" : "视频时长",
			"type" : "html",
			"data" : "videoTime",
			"defaultContent" : "",
			"visible" : true,
			"orderable": true,"width":"140px",
		},  {
			"title" : "下载",
			"type" : "html",
			"data" : "",
			"defaultContent" : "",
			"visible" : true,
			"orderable": true,"width":"80px"
		} ],
		// 从右向左第三列列描述 加按钮
		"columnDefs" : [
				{
					"targets" : -1,
					"render" : function(data, type, full, meta) {
						var ret = "<button id="
								+ full.activityId
								+ " class = \"btn-download\" disabled = \"disabled\" onClick=\"downloadVideos(this)\" ><span class=\"glyphicon glyphicon-download-alt\"></span> </button>"
						
						return ret;
					}

				} ]
		
}