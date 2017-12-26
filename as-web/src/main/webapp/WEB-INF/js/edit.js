$(function() { 
	//获取class为edittext的元素 
	$(".edittext").click(function() { 
		var td = $(this); 
		var txt = td.text(); 
		var input = $("<input type='text'value='" + txt + "'/>"); 
		td.html(input); 
		input.click(function() { return false; }); 
		//获取焦点 
		input.trigger("focus"); 
		//文本框失去焦点后提交内容，重新变为文本 
		input.blur(function() { 
			var newtxt = $(this).val(); 
			//判断文本有没有修改 
			if (newtxt != txt) { 
				td.html(newtxt); 
				//不需要使用数据库的这段可以不需要 
				 
				//ajax异步更改数据库,加参数date是解决缓存问题 ，这里我要接收对象的全部属性值，不会
				http://q.cnblogs.com/q/69488/
				var childs = td.parent().children();
				//去掉最后一个 “基本操作”
				var item_length = childs.length-1;
		        var item_value = new Array(item_length);
		             
	            for(i = 0; i < item_length; i++){
//	                item_value[i] = childs[i].innerHTML;
	            	item_value[i] = childs[i].innerText;
	            }
	           
//				var url = "updategoodtypes?id=" + item_value[0]+"&pid="+item_value[1]+"&typename="+item_value[2]+"&note="+item_value[3]; 
				//使用get()方法打开一个一般处理程序，data接受返回的参数（在一般处理程序中返回参数的方法 context.Response.Write("要返回的参数");） 
				//数据库的修改就在一般处理程序中完成  get 需要在服务器里编码防止乱码
//				$.get(url, function(data) { 
////					if(data=="1") 
////					{ 
////						alert("该类别已存在！"); 
////						td.html(txt); 
////						return; 
////					} 
//////					alert(data); 
////					td.html(newtxt); 
//				}); 
	            //http://www.w3school.com.cn/jquery/ajax_post.asp
				$.post("updategoodtypes",{id:item_value[0], pid:item_value[1], typename:item_value[2], note:item_value[3] });
				
			} 
			else 
			{ 
				td.html(newtxt);
				
			} 
		}); 
	}); 
}); 