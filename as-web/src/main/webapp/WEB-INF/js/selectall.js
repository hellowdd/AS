//全选
    	function selectAllDels() 
		{ 
			var allCheckBoxs = document.getElementsByName("checkall"); 
			var desc = document.getElementById("allChecked"); 
			var selectOrUnselect = false; 
			for(var i = 0; i < allCheckBoxs.length; i ++ ) 
			{ 
				if(allCheckBoxs[i].checked){ 
					selectOrUnselect=true; 
					break; 
				} 
			} 
			if (selectOrUnselect) 
			{ //只要有一个被选中，本次点击按钮就是取消全选
				_allUnchecked(allCheckBoxs); 
			}else 
			{ // 当前一个都没有被选中，则全部选中
				_allchecked(allCheckBoxs); 
			} 
		} 
		function _allchecked(allCheckBoxs){ 
			for(var i = 0; i < allCheckBoxs.length; i ++ ) 
			{ 
				allCheckBoxs[i].checked = true; 
			} 
		} 
		function _allUnchecked(allCheckBoxs){ 
			for(var i = 0; i < allCheckBoxs.length; i ++ ) 
			{ 
				allCheckBoxs[i].checked = false; 
			} 
		} 