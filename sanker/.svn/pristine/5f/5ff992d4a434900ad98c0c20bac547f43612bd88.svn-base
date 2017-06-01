
var initUploader = false;
var contextPath = E.getContextPath();
function ajaxAddNews(){ //添加图片新闻
	var options = {
		dataType: "json",
		beforeSubmit: function(){
			//alert('开始提交'); 
			var newsTitle = $.trim($("#newsTitle").val());
			if(newsTitle == ""){
				alert("请输入标题");
				return false;
			}
			/*
			if(getStringLength(newsTitle) > 9){
				alert("标题最多输入9个汉字");
				return false;
			}
			var newsContent = $("#newsContent").val();
			if(newsContent.length > 2500){
				alert("内容最多输入2500个中文");
				return false;
			}*/
			var cate = $("#cate").val();
			if(cate != "0" && cate != "" && cate != "undefined"){
				$("#cateId").val(cate);
			}else{
				alert("请选择栏目");
				return false;
			}
			showOrHideCover();
			return true;
		},  
		success: function(data){
			//alert("提交成功");
			//alert(data.versionName);
			$("#newsId").val(data.newsId);
			$("#newsIdForImage").val(data.newsId);
			$("#newsTitleForImage").val(data.newsTitle);
			$("#createTimeString").val(data.createTimeString);
			$("#createTimeStringForImage").val(data.createTimeString);
			
			$("#p_step2").removeAttr("disabled").click();
			
			if(!initUploader){
				createMutiUploader(data.newsId);
				initUploader = true;
			}
			
			showOrHideCover(true);
		}  
	};  
	$('#newsForm').submit(function(){
		$(this).ajaxSubmit(options);
		return false;  
	});
}

/*
function ajaxUploadImage(){ //上传图片
	var options = {
		dataType: "json",
		beforeSubmit:   function(){
			if($("#newsIdForImage").val()){
			
			}else{
				alert("请先创建新闻主题！");
				return false;
			}
			if($("#files_list p").length <= 0){
				alert("待上传图片列表为空，不能上传，请选择至少一个图片");
				return false;
			}
			showOrHideCover();
			return true;
		}, 
		success: function(data){
			$("#p_step3").removeAttr("disabled").click();
			if(data.newsfileList){
				var newsfileList = data.newsfileList;
				if(newsfileList){
					var imageListDiv = "";
					for(var i = 0; i < newsfileList.length; i++){
						//imageListDiv += '<div id="imgDiv_' + newsfileList[i].id + '" class="pic_yi">' + 
						//					'<img id="img_' + newsfileList[i].id + '" src="jsp/news/imageNewsAction!getImg.action?imageFileName=' + newsfileList[i].filePath + '&newsTitle=' + $("#newsTitleForImage").val() + '&createTimeString=' + $("#createTimeStringForImage").val() + '" onclick="addImageDesc(\'' + newsfileList[i].id + '\')" class="img"/>' + 
						//					'<input type="hidden" id="imageFileId_' + newsfileList[i].id + '" name="imageFileId" value="' + newsfileList[i].id + '" />' + 
						//					'<input type="hidden" id="descriptionString_' + newsfileList[i].id + '" name="descriptionString" value="' + newsfileList[i].descriptionString + '" />' + 
						//				'</div>';
						imageListDiv += '<li id="imgDiv_' + newsfileList[i].id + '" class="pic_yi" style="width: 80px; height: 75px;">' + 
											'<div>' + 
												'<span style="float: left; padding-left: 2px; ">' + (i + 1) + '</span>' + 
												'<label id="labelDel_' + newsfileList[i].id + '" title="删除" onclick="deleteImage(\'' + newsfileList[i].id + '\')" style="float: right; color: #aaa; cursor: pointer; padding-right: 2px; ">删除</label>' + 
												//'<input type="button" id="btn_' + newsfileList[i].id + '" value="删除" /><br/>' + 
											'</div>' + 
											'<img id="img_' + newsfileList[i].id + '" src="jsp/news/imageNewsAction!getImg.action?imageFileName=' + newsfileList[i].filePath + '&newsIdForImage=' + $("#newsIdForImage").val() + '&createTimeString=' + $("#createTimeStringForImage").val() + '" onclick="addImageDesc(\'' + newsfileList[i].id + '\', \'' + (i+1) + '\')" class="img"/>' + 
											'<input type="hidden" id="imageFileId_' + newsfileList[i].id + '" name="imageFileId" value="' + newsfileList[i].id + '" />' + 
											'<input type="hidden" id="descriptionString_' + newsfileList[i].id + '" name="descriptionString" value="' + newsfileList[i].descriptionString + '" />' + 
										'</li>';
					}
					//$("#divAcrollPic1").html(imageListDiv);
					$("#wrap").html('<ul id="mycarousel" class="jcarousel-skin-tango"></ul>');
					//$("#wrap").html('<ul id="mycarousel" ></ul>');
					$("#mycarousel").html(imageListDiv);
					$('#mycarousel').jcarousel({scroll: 1});
					$('#mycarousel').css("margin-top", "-10px");
					//$("#wrap").easySlider();
				}
			}
			
			$("#imageFileSelect").html('<input id="my_file_element" class="addfile" type="file" name="imageFile" size="1" title="添加新的图片">');
			$("#files_list").html("");
			var multi_selector = new MultiSelector(document.getElementById('files_list'), 100);      
			multi_selector.addElement(document.getElementById('my_file_element'));
			
			$("#descString").attr("disabled", "disabled");
			$("#descString").val("");
			$("#addImageDescSubmit").attr("disabled", "disabled");
			
			showOrHideCover(true);
		}
	};  
	$('#uploadImageForm').submit(function(){
		$(this).ajaxSubmit(options);  
		return false;  
	});
}
*/

function $j(id) {
	return document.getElementById(id);	
}
		
var fileslist = null;
var uploader = null;
//创建上传组件
function createMutiUploader(newsIdForImage, newsTitleForImage, createTimeStringForImage){
	if(!initUploader){
		var date = new Date();
		var max_file_size = "1mb";
		uploader = new plupload.Uploader({
			runtimes : 'gears,html5,flash,silverlight,browserplus',
			browse_button : 'imageFileSelect',
			container: 'container',
			file_data_name: "imgFile",
			max_file_size : max_file_size,
			url : contextPath + '/jsp/news/imageNewsAction!uploadOneImage.action?newsIdForImage=' + newsIdForImage + "&t=" + date.valueOf(),
	//		resize : {width : 320, height : 240, quality : 90},
			resize: {width : "100%", height : "100%", quality : 90},
			flash_swf_url : contextPath + '/javascript/plupload/js/plupload.flash.swf',
			silverlight_xap_url : contextPath + '/javascript/plupload/js/plupload.silverlight.xap',
			filters : [
				{title : "图片文件", extensions : "jpg,gif,png,bpm,bmp,jpeg"}
	//			{title : "Zip files", extensions : "zip"}
			]
		});
		
		uploader.bind('FilesAdded', function(up, files) {
			fileslist = files;
			for (var i in files) {	
				var id =files[i].id+'';
				$('#files_list').append('<div id="'+ files[i].id +'">'+files[i].name+'('+ plupload.formatSize(files[i].size)+')<b></b><a href="javascript:removef('+id+')" style="color:red">X</a></div>');
			}
		});
		uploader.bind('Error', function(up, error) {
			showOrHideCover(true);
			if(error.code == "-601"){
				alert("文件后缀为" + up.settings.filters[0].extensions);
				$('#files_list div[id="' + error.file.id + '"]').remove();
			}
			if(error.code == "-600"){
				alert("文件大小不能超过" + max_file_size);
				$('#files_list div[id="' + error.file.id + '"]').remove();
			}
		});
		uploader.bind('UploadProgress', function(up, file) {
			$j(file.id).getElementsByTagName('b')[0].innerHTML = '&nbsp;&nbsp;<span>' + file.percent + "%</span>&nbsp;&nbsp;";
			$j(file.id).getElementsByTagName('a')[0].innerHTML="";
		});
		
		uploader.bind('UploadComplete', function(up, files) {
			//for(var i = 0; i < files.length; i++){
			//	alert("id: " + files[i].id + ", name: " + files[i].name);
			//}
			showOrHideCover(true);
			getImageNewsImage();
		});
		$("#uploadImageButton").bind("click", function(){
			if($("#newsIdForImage").val()){
			
			}else{
				alert("请先创建新闻主题！");
				return false;
			}
			
			if($("#files_list div").length <= 0){
				alert("待上传图片列表为空，不能上传，请选择至少一个图片");
				return false;
			}
			showOrHideCover();
			uploader.start();
			return false;
		});
		/*
		uploader.bind('FileUploaded', function(up, file, response) {
			alert(up.settings.file_data_name);
				alert("id: " + file.id + ", name: " + file.name);
				var json = $.parseJSON(response.response);
				alert(json.aaa);
		});
		*/
		uploader.init();
		
		initUploader = true;
	}
}

function removef(str){
	str.parentNode.removeChild(str);
	//str.style.display="none";
	for(var i in fileslist){
		if(fileslist[i].id==str.id){
			uploader.removeFile(fileslist[i]);
		}
	}
	//file.remove(removeFile);
}

function getImageNewsImage(){ //获取图片新闻的图片
	var newsIdForImage = $("#newsIdForImage").val();
	var url1 = "jsp/news/imageNewsAction!getImageNewsImage.action?newsIdForImage=" + newsIdForImage + "&t=" + (new Date()).valueOf();
	url1=encodeURI(url1);
	$.ajax({
	    url: url1,
	    type: 'post',
	    dataType: 'json',
	    timeout: 30000,
	    async: false,
	    error: function(XMLHttpRequest, textStatus, errorThrown){
	    	//EAjax.gotoErrorPage();
	    },
	    success: function(data){
	    	if(data.result == "success"){
	    		$("#p_step3").removeAttr("disabled").click();
				if(data.newsfileList){
					var newsfileList = data.newsfileList;
					if(newsfileList){
						var imageListDiv = "";
						for(var i = 0; i < newsfileList.length; i++){
							imageListDiv += '<li id="imgDiv_' + newsfileList[i].id + '" class="pic_yi" style="width: 80px; height: 75px;">' + 
												'<div>' + 
													//'<span style="float: left; padding-left: 2px; ">' + (i + 1) + '</span>' + 
													'<label id="labelDel_' + newsfileList[i].id + '" title="删除" onclick="deleteImage(\'' + newsfileList[i].id + '\')" style="float: right; color: #aaa; cursor: pointer; padding-right: 2px; ">删除</label>' + 
												'</div>' + 
												'<img id="img_' + newsfileList[i].id + '" src="jsp/news/imageNewsAction!getImg.action?imageFileName=sm_' + newsfileList[i].filePath + '&newsIdForImage=' + $("#newsIdForImage").val() + '&createTimeString=' + $("#createTimeStringForImage").val() + '" onclick="addImageDesc(\'' + newsfileList[i].id + '\', \'' + (i+1) + '\')" class="img"/>' + 
												'<input type="hidden" id="imageFileId_' + newsfileList[i].id + '" name="imageFileId" value="' + newsfileList[i].id + '" />' + 
												'<input type="hidden" id="descriptionString_' + newsfileList[i].id + '" name="descriptionString" value="' + newsfileList[i].descriptionString + '" />' +
												'<input type="hidden" id="sortOrder_' + newsfileList[i].id + '" name="sortOrder" value="' + newsfileList[i].sortOrder + '" />' +  
											'</li>';
						}
						$("#wrap").html('<ul id="mycarousel" class="jcarousel-skin-tango"></ul>');
						$("#mycarousel").html(imageListDiv);
						$('#mycarousel').jcarousel({scroll: 1});
						$('#mycarousel').css("margin-top", "-10px");
					}
				}
				
				$("#files_list").html("");
				
				$("#descString").attr("disabled", "disabled");
				$("#descString").val("");
				$("#addImageDescSubmit").attr("disabled", "disabled");
				
				showOrHideCover(true);
	    	}
	    }
	});
}

var currImageId = null; //被选中图片的id
function addImageDesc(id, picNO){
	if($("#descString").attr("disabled")){ //如果textarea被禁用
		$("#descString").removeAttr("disabled");
		$("#sortOrder").removeAttr("disabled");
		$("#addImageDescSubmit").removeAttr("disabled");
	}
	if(currImageId != null){ //被选中图片id存在
		//$("#descriptionString_" + currImageId).val($.trim($("#descString").val()));
		$("#imgDiv_" + currImageId).css("border", "1px solid #aaa");
	}
	currImageId = id;
	if($("#descriptionString_" + currImageId).val() != "undefined"){
		$("#descString").val($("#descriptionString_" + currImageId).val());
	}else{
		$("#descString").val("");
	}
	if($("#sortOrder_" + currImageId).val() != "undefined"){
		$("#sortOrder").val($("#sortOrder_" + currImageId).val());
	}else{
		$("#sortOrder").val("");
	}
	$("#currImage").attr("src", $("#img_" + id).attr("src"));
	//$("#picNO").html(picNO);
	$("#imgDiv_" + currImageId).css("border", "2px solid darkgreen");
	$("#descString").focus();
}

function textAreaChange(){
	if(currImageId != null){
		$("#descriptionString_" + currImageId).val($.trim($("#descString").val()));
	}
}


function sortOrderChange(){
	var regExp = "^[1-9]{1}[0-9]{0,4}$";
	if(!$("#sortOrder").val().match(regExp)){
		$("#sortOrder").val("");
		return;
	}
	if(currImageId != null){
		$("#sortOrder_" + currImageId).val($.trim($("#sortOrder").val()));
	}
}

function ajaxSaveImageDesc(){ //保存图片简介
	var options = {
		dataType: "json",
		beforeSubmit:   function(){
			/*
			if(currImageId != null){
				$("#descriptionString_" + currImageId).val($.trim($("#descString").val()));
			}
			*/
			showOrHideCover();
			return true;
		}, 
		success: function(data){
			if(currImageId != null){
				$("#imgDiv_" + currImageId).css("border", "1px solid #aaa");
			}
			$("#descString").val("").attr("disabled", "disabled");
			$("#sortOrder").val("").attr("disabled", "disabled");
			$("#currImage").attr("src", "images/p.gif");
			showOrHideCover(true);
		}
	};  
	$('#addImageDescForm').submit(function(){
		$(this).ajaxSubmit(options);  
		return false;  
	});
}

function deleteImage(id){ //删除图片
	var iconFileNameOld = $("#iconFileNameOld").val();
	var url1 = "jsp/news/imageNewsAction!deleteImage.action?imageId=" + id + "&newsTitleForImage=" + $("#newsTitleForImage").val() + "&createTimeStringForImage=" + $("#createTimeStringForImage").val() + "&t=" + (new Date()).valueOf();
	url1=encodeURI(url1);
	$.ajax({
	    url: url1,
	    type: 'post',
	    dataType: 'json',
	    timeout: 30000,
	    async: false,
	    error: function(XMLHttpRequest, textStatus, errorThrown){
	    	//EAjax.gotoErrorPage();
	    },
	    success: function(data){
	    	if(data.result == "success"){
	    		$("#labelDel_" + id).parent().parent().remove();
	    		alert("删除成功");
	    		$("#descString").val("").attr("disabled", "disabled");
				$("#sortOrder").val("").attr("disabled", "disabled");
				$("#currImage").attr("src", "images/p.gif");
				showOrHideCover(true);
	    	}
	    }
	});
}

function showOrHideCover(hide){
	if(!hide){
		$("#coverDiv").css("display", "block");
		$("#showResult").css("display", "block");
	}else{
		$("#coverDiv").css("display", "none");
		$("#showResult").css("display", "none");
	}
}

function collapsedMenu(){//压缩菜单
	var previ_a = $('#menu_ul dt p:first');
	var last_a = $('#menu_ul dt[id^="step"]:last');
	var this_parent = null;
	//alert(last_a.attr("id"));
	//$('#menu_ul ul').hide();
	//$('#menu_ul ul:first').show();
	$('#menu_ul .ulClass').hide();
	$('#menu_ul .ulClass').first().show();
	$('#menu_ul dt p').click(
		function() {
			var checkElement = $(this).next(".ulClass");
			if((checkElement.is('dl')) && (checkElement.is(':visible'))) {
				return false;
			}
			if((checkElement.is('dl')) && (!checkElement.is(':visible'))) {
				//$('#menu_ul ul:visible').hide().slideUp('normal');
				//$('#menu_ul .ulClass :visible').hide().slideUp('normal');
				$('#menu_ul .ulClass').each(
					function(){
						if($(this).is(":visible")){
							$(this).hide().slideUp('normal');
						}
					}
				)
				$(this).removeClass();
				$(this).addClass("title_a bg");
				checkElement.slideDown('normal');
				previ_a.removeClass();
				previ_a.addClass("title_b bg");
				previ_a = $(this);
				
				this_parent = $(this).parent('dt[id^="step"]');
				if(last_a.attr("id") == this_parent.attr("id")){
					last_a.css("border-bottom", "1px solid #a1c9d3");
				}else{
					last_a.css("border-bottom", "0");
				}
				return false;
			}
		}
	);
}

//返回
function goBack(){
	window.location.href = contextPath + "/jsp/news/searchImageNews.jsp";
}