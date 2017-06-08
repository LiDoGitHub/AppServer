	//  获取元素对象
	function g(id) {
		return document.getElementById(id);
	}
	
	var mouseOffsetX = 0; //  偏移
	var mouseOffsetY = 0;
	
	var isDraging = false; //  是否可拖拽的标记
	
	//  鼠标事件1 － 在标题栏上按下（要计算鼠标相对拖拽元素的左上角的坐标，并且标记元素为可拖动）
	g('dialogTitle').addEventListener('mousedown', function(e) {
			var e = e || window.event;
			mouseOffsetX = e.pageX - g('dialog').offsetLeft;
			mouseOffsetY = e.pageY - g('dialog').offsetTop;
			isDraging = true;
		})
		//  鼠标事件2 － 鼠标移动时（要检测，元素是否可标记为移动，如果是，则更新元素的位置，到当前鼠标的位置［ps：要减去第一步中获得的偏移］）
	document.onmousemove = function(e) {
		var e = e || window.event;
	
		var mouseX = e.pageX; // 鼠标当前的位置
		var mouseY = e.pageY;
	
		var moveX = 0; //  浮层元素的新位置
		var moveY = 0;
	
		if(isDraging == true) {
	
			moveX = mouseX - mouseOffsetX;
			moveY = mouseY - mouseOffsetY;
	
			//  范围限定   moveX > 0 并且  moveX < (页面最大宽度 - 浮层的宽度)
			//            moveY > 0 并且  movey < (页面最大高度 - 浮层的高度)
	
			var pageWidth = document.documentElement.clientWidth;
			var pageHeight = document.documentElement.clientHeight;
	
			var dialogWidth = g('dialog').offsetWidth;
			var dialogHeight = g('dialog').offsetHeight;
	
			var maxX = pageWidth - dialogWidth;
			var maxY = pageHeight - dialogHeight;
	
			moveX = Math.min(maxX, Math.max(0, moveX));
			moveY = Math.min(maxY, Math.max(0, moveY));
	
			g('dialog').style.left = moveX + 'px';
			g('dialog').style.top = moveY + 'px';
		}
	
	}
	
	//  鼠标事件3 － 鼠标松开的时候（标记元素为不可拖动即可）
	document.onmouseup = function() {
		isDraging = false;
	}
	
	
		
		
		
		