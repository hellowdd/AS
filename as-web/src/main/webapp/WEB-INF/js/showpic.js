		//下面用于图片上传预览功能
	function setImagePreview(avalue) {
		var docObj=document.getElementById("pic");
		var imgObjPreview=document.getElementById("preview");
		var AllImgExt=".jpg|.jpeg|.gif|.bmp|.png|"//全部图片格式类型
		var FileExt=docObj.value.substr(docObj.value.lastIndexOf(".")).toLowerCase(); 
		//console.log(docObj.value.substr(docObj.value.indexOf(".") + 1));
		//console.log(FileExt);
		if(AllImgExt.indexOf(FileExt+"|")!=-1)//如果图片文件，则进行图片信息处理 
		{
			if(docObj.files &&docObj.files[0])
			{
				//火狐下，直接设img属性
				imgObjPreview.style.display = 'block';
				imgObjPreview.style.width = '150px';
				imgObjPreview.style.height = '150px'; 
				//imgObjPreview.src = docObj.files[0].getAsDataURL();
				
				//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
				imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
			}
			else
			{
				//IE下，使用滤镜
				docObj.select();
				var imgSrc = document.selection.createRange().text;
				var localImagId = document.getElementById("localImag");
				//必须设置初始大小
				localImagId.style.width = "150px";
				localImagId.style.height = "150px";
				//图片异常的捕捉，防止用户修改后缀来伪造图片
				try{
					localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
					localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
				}
				catch(e)
				{
					alert("您上传的图片格式不正确，请重新选择!");
					return false;
				}
				imgObjPreview.style.display = 'none';
				document.selection.empty();
			}
		
		}else{
			alert("上传'.jpg、.jpeg、.gif、.bmp、.png'格式的图片，请重新选择!");
			docObj.value="";
			return false;
		}
		return true;
	}