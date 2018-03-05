<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>基本信息</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/miniui/boot.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/frame.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/plupload/plupload.full.min.js"></script>
</head>
<body>
<div class="wraper">
		<div class="btn-wraper">
			<input type="button" value="选择文件..." id="browse" />
			<input type="button" value="开始上传" id="upload-btn" />
		</div>
		<ul id="file-list">

		</ul>
	</div>
</body>
<script type="text/javascript">
 /*******以下来图片上传******/
 var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'browse',
		url : '<%=request.getContextPath()%>/work/f10010101/uploadPic.action',
		flash_swf_url : '<%=request.getContextPath()%>/resources/plupload/Moxie.swf',
		silverlight_xap_url : '<%=request.getContextPath()%>/resources/plupload/Moxie.xap',
		filters: {
		  mime_types : [ 
		    { title : "图片文件", extensions : "jpg,gif,png,bmp" }
		  ],
		  max_file_size : '50kb', 
		  prevent_duplicates : true 
		}
	});
	uploader.init(); //初始化

	//绑定文件添加进队列事件
	uploader.bind('FilesAdded',function(uploader,files){
		for(var i = 0, len = files.length; i<len; i++){
			var file_name = files[i].name; //文件名
			//构造html来更新UI
			var html = '<li id="file-' + files[i].id +'"><p class="file-name">' + file_name + '</p><p class="progress"></p></li>';
			$(html).appendTo('#file-list');
		}
	});

	//上传按钮
	$('#upload-btn').click(function(){
		uploader.start(); //开始上传
	});
 
 /*******以上来图片上传******/
</script>
</html>
