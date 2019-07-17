$(document).ready(function(){
	$('#picker div:eq(1)').attr('style','position: absolute; top: 0px; left: 0px; width: 82px; height: 39px; overflow: hidden; bottom: auto; right: auto;');
/*	var $list = $("#thelist");
	var $btn = $("#ctlBtn");*/
})

var uploader = WebUploader.create({

    // swf文件路径
    swf: '/webupload/Uploader.swf',

    // 文件接收服务端。
    //server: 'http://localhost:8080/videof/upload',
    server: 'http://10.1.100.152:8998/videof/upload',
    //server: 'http://10.1.11.120:8080/videof/upload',
    // http://localhost:8080/videof/upload
   
    
    //设置超时时间为： 无超时时间
    timeout: 0,
    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#picker',
    // 视频格式
    accept: {
        title: 'Videos',
        extensions: 'MTS',
       mimeTypes: '/*'
    },
    resize: false
    //{Boolean} [可选] [默认值：false] 是否要分片处理大文件上传。
    //chunked: true ,
    
    //threads: 3 //上传并发数。允许同时最大上传进程数。
    //{Boolean} 是否开起同时选择多个文件能力。
   // multiple: true
});


	



//当有文件被添加进队列的时候
uploader.on( 'fileQueued', function( file ) {
	//console.log(uploader.md5File(file.source));
	console.log("fileName="+file.name);
	
	var actName = $(".modal-actName").text();
	console.log("actName="+actName);

    $("#thelist").append( '<div id="' + file.id + '" class="item">' +
        '<h4 class="info">' + file.name + '</h4>' +
        '<p class="state">等待上传...</p>' +
    '</div>' );
});



//文件上传过程中创建进度条实时显示。
uploader.on( 'uploadProgress', 
		function( file, percentage ) {
    var $li = $( '#'+file.id ),
        $percent = $li.find('.progress .progress-bar');

    // 避免重复创建
    if ( !$percent.length ) {
        $percent = $('<div class="progress progress-striped active">' +
          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
          '</div>' +
        '</div>').appendTo( $li ).find('.progress-bar');
    }

    $li.find('p.state').text('上传中');

    $percent.css( 'width', percentage * 100 + '%' );
});


var videoId;
uploader.on( 'uploadSuccess', function( file ) {
	//$( '#'+file.id ).find('.progress').fadeOut();
    $( '#'+file.id ).find('p.state').text('已上传');
    console.log("uploadSuccess");
    toMp4(file);
});

uploader.on( 'uploadError', function( file ) {
    $( '#'+file.id ).find('p.state').text('上传出错');
});

uploader.on( 'uploadComplete', function( file ) {
    $( '#'+file.id ).find('.progress').fadeOut();
    f=file;
    $( '#'+file.id ).find('p.state').text('上传完成，正在后台转码..');
});
uploader.on( 'uploadBeforeSend', function( block, data ) {
    //file.relActId= document.getElementsByName("modal-activityId")[0].id;
    
    // file为分块对应的file对象。
    var file = block.file;
 
    // 修改data可以控制发送哪些携带数据。
    data.relActId = document.getElementsByName("modal-activityId")[0].id;
});

	

$("#ctlBtn").on('click', function() {
		uploader.upload();
	
});



uploader.on("uploadAccept", function( file, data){
	videoId = data.videoId;
});

function toMp4(f){
	console.log("file.name="+f.name);
	var m = $(".modal-actName");
	console.log(m.text());
	var mactname = m.text();//modal-actName
	
	//
	var act = {};
	var a = $(".modal-activityId").attr("id");
	var b = $(".btn-click-to-list").attr("name");
	act["id"]=a;
	act["name"]=b;
	//
	$.ajax({
		url : "/videof/toMp4",
		type : "POST",
		dataType : "json",
		async : true,
		data : {
			"videoName" : f.name,
			"actName" : mactname,
			"videoId" :videoId
		},
		success : function(data)	{
			modalVideo(act)
		},
		error: function(data)	{
			modalVideo(act)
		}
	});
};

