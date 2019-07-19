$(document).ready(function() {
	var table;
	query();
	$("#findAll_filter .input-sm").attr({
		'placeholder' : '按名称、采集人和相关人物查找'
	});
})

function query(search) {
	// console.log(search);
	table = $("#findAll")
			.DataTable(

					{
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

						"search" : {
							"smart" : true,
						},// 智能搜索

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
							},

							"oAria" : {
								"sSortAscending" : ": 以升序排列此列",
								"sSortDescending" : ": 以降序排列此列"
							}
						},
						/*
						 * createdRow: function ( row, data, index ) { if (
						 * index %2 == 0 ) { $('td',
						 * row).css('font-weight',"bold").css("background-color","#cac1c1"); } },
						 */

						// 异步请求
						"ajax" : {
							"url" : "/act/findAll",
							"type" : "POST",
							"async" : false,
							"dataType" : "json",
							"dataFilter" : function(data, type) {
								console.log("执行ajax");
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
						"columns" : [ {
							"title" : "活动名称",
							"type" : "html",
							"data" : "activityName",
							"defaultContent" : "",
							"visible" : true,
							"orderable" : true,
							"width" : "185px",
							"word-wrap" : "break-word",
							"searchable" : true,
						}, {
							"title" : "开始时间",
							"type" : "html",
							"data" : "displayActivityBeginDate",
							"defaultContent" : "",
							"visible" : true,
							"orderable" : true,
							"width" : "130px",
						}, {
							"title" : "活动地点",
							"type" : "html",
							"data" : "activitySite",
							"defaultContent" : "",
							"visible" : true,
							"orderable" : true,
							"width" : "140px",
						}, {
							"title" : "活动门类",
							"type" : "html",
							"data" : "activityType",
							"defaultContent" : "",
							"visible" : true,
							"orderable" : true,
							"width" : "100px",
						}, {
							"title" : "相关人物",
							"type" : "html",
							"data" : "activityPerson",
							"defaultContent" : "",
							"visible" : true,
							"orderable" : true,
							"width" : "135px",
							"searchable" : true,
						}, {
							"title" : "视频采集人",
							"type" : "html",
							"data" : "activityVideoGatherer",
							"defaultContent" : "",
							"visible" : true,
							"orderable" : true,
							"width" : "100px",
							"searchable" : true,
						}, {
							"title" : "修改活动信息",
							"type" : "html",
							"data" : "",
							"defaultContent" : "",
							"visible" : true,
							"orderable" : true,
							"width" : "100px"
						}, {
							"title" : "预览",
							"type" : "html",
							"data" : "",
							"defaultContent" : "",
							"visible" : true,
							"orderable" : true,
							"width" : "60px"
						}, {
							"title" : "批量下载",
							"type" : "html",
							"data" : "",
							"defaultContent" : "",
							"visible" : true,
							"orderable" : true,
							"width" : "80px"
						} ],
						// 从右向左第三列列描述 加按钮
						"columnDefs" : [
								{
									"targets" : -3,
									"render" : function(data, type, full, meta) {
										var ret = "<button id="
												+ full.activityId
												+ " name="
												+ full.activityName
												+ " class = \"btn btn-primary btn-sm\" type=\"button\"  data-toggle=\"modal\" data-target=\"#activityInfoModal\" onClick=\"modalActivity(this)\" ><span class=\"glyphicon glyphicon-edit\"></span>修改</button>"
										return ret;
									}
								},
								{
									"targets" : -2,
									"render" : function(data, type, full, meta) {
										var ret = "<button id="
												+ full.activityId
												+ " name="
												+ full.activityName
												+ " class = \"btn btn-default btn-sm btn-tmodal btn-actRelId btn-click-to-list\" type=\"button\"  data-toggle=\"modal\" data-target=\"#videoListModal\" onClick=\"modalVideo(this)\"  ><span class=\"glyphicon glyphicon-play\"></span> </button>"
										/*
										 * var btn = $("<button class = \"btn
										 * btn-default btn-sm btn-tmodal\"
										 * type=\"button\" data-toggle=\"modal\"
										 * data-target=\"#videoModal\"
										 * onClick=\"modalVideo(this)\">
										 * </button>") btn.data("id",actid);
										 * btn.append("<span class=\"glyphicon
										 * glyphicon-play\"></span>");
										 */
										return ret;
									}

								},
								{
									"targets" : -1,
									"render" : function(data, type, full, meta) {
										var ret = "<button id="
												+ full.activityId
												+ " class = \"btn-download\" disabled = \"disabled\" title=\"没有权限! \" onClick=\"downloadVideos(this)\" ><span class=\"glyphicon glyphicon-download-alt\"></span> </button>"
										/*
										 * var btn = $("<button class = \"btn
										 * btn-default btn-sm btn-tmodal\"
										 * type=\"button\" data-toggle=\"modal\"
										 * data-target=\"#videoModal\"
										 * onClick=\"modalVideo(this)\">
										 * </button>") btn.data("id",actid);
										 * btn.append("<span class=\"glyphicon
										 * glyphicon-play\"></span>");
										 */
										return ret;
									}

								} ]

					});
}

/* 修改 活动列表 */

function modalActivity(act) {

	var typeObj = document.getElementById('activityTypeUp');
	// $("#datetimeStart").datetimepicker("setDate", new Date());
	// $("#activityBeginDateUp").val(data.activityBeginDate),
	$.ajax({
		url : "/act/findOneAc",
		type : "POST",
		dataType : "json",
		async : false,
		data : {
			"activityId" : act.id
		},
		success : function(data) {
			var tt1 = data.activityBeginDate;
			var date = new Date(tt1);
			$("#activityNameUp").val(data.activityName), $('#activityTypeUp')
					.selectpicker('val', (data.activityType)), $(
					"#activityPersonUp").val(data.activityPerson), $(
					"#activitySiteUp").val(data.activitySite), $(
					"#datetimepicker1").datetimepicker("setDate", date),
					$("#activityVideoGathererUp").val(
							data.activityVideoGatherer), $("#activityIdUp")
							.val(data.activityId)
		}
	});
	// typeObj.setAttribute('title',data.activityType)
	// console.log($("#activityTypeUp").val());

}
$("#updateAtivity").click(function() {
	var tt = $("#datetimepicker1").find("input").val();
	// $("#datetimepicker1").data("datetimepicker").getDate();这种也行
	console.log("activityBeginDateUp = " + activityBeginDateUp);
	var date = new Date(tt);
	console.log("date:" + date);

	$.ajax({
		type : 'post',
		dataType : 'text',
		url : '/act/updateActivity',
		data : ({
			"activityName" : $("#activityNameUp").val(),
			"activityType" : $("#activityTypeUp").val(),
			"activityPerson" : $("#activityPersonUp").val(),
			"activitySite" : $("#activitySiteUp").val(),
			"activityBeginDate" : date,
			"activityVideoGatherer" : $("#activityVideoGathererUp").val(),
			"activityId" : $("#activityIdUp").val()
		}),
		success : function(data) {
			window.location.reload()
		}
	})

});

/* 视频列表 */
function modalVideo(act) {
	$('.modal-video').empty();
	$('#myModalLabel').empty();
	$('#myModalLabel').html('');

	$('#myModalLabel').html(act.name);

	$(".modal-activityId").attr("id", act.id);
	$
			.ajax({
				url : "/video/findVideoByActid",
				type : "POST",
				dataType : "json",
				async : false,
				data : {
					"activityId" : act.id
				},
				success : function(data) {
					var txt = '';
					var va = '';
					var vpa = '';
					var vn = '';
					var vid = '';
					for (var i = 0; i < data.data.length; i++) {
						vid = data.data[i].videoId;

						va = data.data[i].videoAddress;
						vb = "";
						vpa = data.data[i].videoPicAddress;
						vpb = "img/timg.jpg";
						if (data.data[i].videoTransform == 'Y') {
							/*
							 * txt = '\<div class="col-sm-8 col-md-4 modal-pic "
							 * id="modalDiv'+i+'" \>' + '\<a href="#"
							 * class="thumbnail" onClick="videoPlay(\'' + va +
							 * '\',\'' + vpa + '\')" onmouseover="over('+i+')"
							 * onmouseout="out('+i+')" \>' + '\<img src="' +
							 * vpa + '" alt="视频缩略图" \>\</a\>' + '\</div\>';
							 * $('.modal-video').append(txt);
							 * onmouseover="over('+i+')" onmouseout="out('+i+')"
							 */
							// \<a href="#" style="word-wrap:break-word;"
							// class="btn btn-danger"
							// onClick="alertB()"\>删除\</a\>
							txt = '\<div class="col-sm-8 col-md-4 modal-pic " id="modalDiv'
									+ i
									+ '" \>'
									+ '\<div class="mask-video"\>'
									+ '\<img class ="thumbnail videoslt" src="'
									+ vpa
									+ '" alt="视频缩略图"  onClick="videoPlay(\''
									+ va
									+ '\',\''
									+ vpa
									+ '\')"  \>'
									+ '\<div class="mask-video-content videoslt" onClick="deleteVideoById(\''
									+ vid
									+ '\')"\>'
									+ '\<span class="title glyphicon glyphicon-remove delspan" \>\</span\>'
									+ '\</div\>' + '\</div\>' + '\</div\>';
							$('.modal-video').append(txt);
						} else {
							txt = '\<div class="col-sm-8 col-md-4 modal-pic " \>'
									+ '\<a href="#" class="thumbnail" onClick="videoPlay2(\''
									+ va
									+ '\',\''
									+ vpb
									+ '\')" \>'
									+ '\<img src="'
									+ vpb
									+ '" alt="视频缩略图"\>\</a\>' + '\</div\>';
							$('.modal-video').append(txt);
						}

					}
				},
				error : function(e) {
					alert("视频获取异常");
					window.clearInterval(timer);
				}

			});

	var txtend = '\<button type="button" class="btn btn-info" onClick=addVideo()\>'
			+ '\<span class="glyphicon glyphicon-plus"\>'
			+ '\</span\>添加视频'
			+ '\</button\>';

	$('.modal-list-footer').empty();
	$('.modal-list-footer').append(txtend);

	/*
	 * var actId = act.id; passActivityId(actId);
	 */
}

/* 视频播放 */
function videoPlay(url, picUrl, name) {
	$(".modal-videoName").empty();
	$("#videoModal").modal('show');

	$(".modal-videoName").html(name);
	var txt1 = '';
	var txt11 = '\<video id="videoplayid1"  controls="controls" autoplay="autoplay" name="media"  height="98%" width="100%">'
			+ '\<source src="' + url + '"' + ' type="video/mp4">' + '\</video>';
	$(".video-source1").append(txt11);

	// $(".video-source1").find("video").find("source").attr("src", url);
	// $("#videoPlay1").attr("poster", picUrl);

	$(".videoPlay-close").click(function() {
		$(".video-source1").append(txt1);
	})
}

function videoPlay2() {
	alert("转码中。。。");
}

/* 视频上传窗口弹出 */

function addVideo() {

	$(".modal-add-actName").empty();
	$("#thelist").empty();
	$(".videoName").val("");
	$(".videoIntroduction").val("");
	$("#addVideoModal").modal('show');

	var aname = document.getElementsByName("modal-aName")[0].innerText;
	$(".modal-add-actName").html("添加" + aname + "活动视频");

}

/* 批量下载视频 */
function downloadVideos(v) {

	console.log("downloadVideos");
	console.log(v.id);

	window.location.href = "/videod/download?id=" + v.id;
}

$("#addAtivity").click(function() {
	var t = $("#datetimepicker11").find("input").val();
	var date1 = new Date(t);

	/*
	 * var datetime = ""; //date.setTime(tt); console.log(date); datetime +=
	 * date.getFullYear(); //年 console.log(datetime) datetime += "-" +
	 * getMonth(date); //月 datetime += "-" + getDay(date);//日
	 * console.log(datetime); console.log(typeof(datetime));
	 */

	$.ajax({
		type : 'post',
		// $('#activityTypeUp').selectpicker('val',(data.activityType)),
		dataType : 'text',
		url : '/act/addActivity',
		data : ({
			"activityName" : $("#activityName").val(),
			"activityType" : $('#activityType').selectpicker('val'),
			"activityPerson" : $("#activityPerson").val(),
			"activitySite" : $("#activitySite").val(),
			"activityBeginDate" : date1,
			"activityVideoGatherer" : $("#activityVideoGatherer").val()
		}),
		success : function(data) {
			window.location.reload()
			// alert("添加成功");
			// console.log($("#activityName").val());
		}
	})

});

// 点击关闭视频按钮
$(".videoPlay-close1").click(function() {
	var videoObj = document.getElementById('videoplayid1');
	if (videoObj) {
		videoObj.pause();
	}
	$("#videoplayid1").remove();

});
// 点击关闭添加视频窗口按钮刷新视频列表
$(".addVideoModal-close").click(function() {
	var act = {};
	var a = $(".modal-activityId").attr("id");
	var b = $(".btn-click-to-list").attr("name");
	act["id"] = a;
	act["name"] = b;
	modalVideo(act)
})
// #datetimepicker1 #activityBeginDateUp 日期选择
$(function() {
	$('#datetimepicker1').datetimepicker({
		format : 'yyyy-mm-dd',
		locale : moment.locale('zh-cn'),
		minView : "month",
		language : 'zh-CN',
		// initialDate: new Date(),
		autoclose : true
	});

	$('#datetimepicker11').datetimepicker({
		format : 'yyyy-mm-dd',
		locale : moment.locale('zh-cn'),
		minView : "month",
		language : 'zh-CN',
		autoclose : true,
		// initialDate:new Date(),//初始时间
		todayBtn : true

	});
})

// 用户注销
$("#logout").click(function() {
	$.ajax({
		url : "/logout",
		type : "POST",
		dataType : "json",
		async : false
	});
})

// 鼠标悬停
function over(x) {
	$(
			"<div class='shade' style='opacity:0.35;background:red;' ></div><img class='shade1' src='/img/add.gif'  onClick='maskClick()' />")
			.css({
				"position" : "absolute",
				"top" : "0",
				"right" : "0",
				"height" : "100%",
				"width" : "30%",
				"zIndex" : "100	"
			// "pointer-events": "none"
			}).appendTo('#modalDiv' + x);

}

// 遮罩层点击事件
function maskClick() {
	alert("11111111111111");
}

function out(x) {

	$('.shade').remove();
	$('.shade1').remove();
	$('.shade2').remove();
	console.log("out");
}

function alertA() {
	alert("AAAAAAAAAAAAAA");
}

// oninput onpropertychange ==========================根据标签查视频===========

$('#input-videoSearch').bind('input propertychange', function() {
	var tag = this.value;
	var dataJson;
	$.ajax({
		url : '/act/findActivityListByVideoTag',  //'/videoTag/findActivityByTag',  
		type : 'post',
		async : false,
		dataType : "json",
		dataFilter : function(data, type) {
			console.log("执行ajax--viseoSearch");
			var json = JSON.parse(data);
			var returnData = {};
			returnData.draw = json.draw;
			returnData.recordsTotal = json.recordsTotal;
			returnData.recordsFiltered = json.recordsFiltered;
			returnData.data = json.data;
			dataJson = json.data;
			return JSON.stringify(returnData);
		},
		
		data : ({
			"tag" : tag
		})
		
	})
	table.clear().ajax.url(dataJson).load();
	
});

/*
 * "ajax" : { "url" : "/act/findAll", "type" : "POST", "async" : false,
 * "dataType" : "json", "dataFilter" : function(data, type) {
 * console.log("执行ajax"); var json = JSON.parse(data); var returnData = {};
 * returnData.draw = json.draw; returnData.recordsTotal = json.recordsTotal;
 * returnData.recordsFiltered = json.recordsFiltered; returnData.data =
 * json.data; return JSON.stringify(returnData); }
 * 
 */

function deleteVideoById(vid) {
	// alert("此视频vid="+vid);
	var r = confirm("是否确认删除该视频？")
	console.log("rrrrrrrrrr=" + r)
	if (r) {

		$.ajax({
			type : 'post',
			// $('#activityTypeUp').selectpicker('val',(data.activityType)),
			dataType : 'text',
			url : '/video/deleteVideoById',
			data : ({
				"videoId" : vid,
				"actId" : $(".modal-activityId").attr("id")
			}),
			success : function(data) {
				var act = {};
				var a = $(".modal-activityId").attr("id");
				var b = $(".btn-click-to-list").attr("name");
				act["id"] = a;
				act["name"] = b;
				modalVideo(act)

			}
		})
	}
}
