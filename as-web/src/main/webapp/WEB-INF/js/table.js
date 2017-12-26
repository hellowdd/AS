window.onload = function() {
	var aTr = document.getElementsByTagName("tr");
	var oldColor;
	for (i = 0; i < aTr.length; i++) {
		/*if (i % 2 == 1) {
			aTr[i].style.backgroundColor='#CCC';
		}
		*/
		//JavaScript闭包
		aTr[i].onmouseover=function(){
			oldColor = this.style.backgroundColor;
			this.style.backgroundColor='#99F';
		};	
		aTr[i].onmouseout=function(){
			this.style.backgroundColor=oldColor;
		};	
		
	}
};
