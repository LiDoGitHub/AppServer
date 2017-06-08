(function($) {
	var $timePanel = null,
		$hour = null,
		$minute = null,
		currentTarget = null,
		date = new Date();
	var defOptions = {
		offsetX: 0,
		offsetY: 22
			,
		format: 'hh:mm',
		hourName: '时',
		minuteName: '分'
			,
		css: {
			'cursor': 'pointer'
		}
		,
		onOK: function(val, target) {
			target.val(val);
		}
	}
	$.fn.timepick = function(options, param) {
		options = options || {};
		return this.each(function() {
			var state = $.data(this, 'timepick');
			if(state) {
				$.extend(state.options, options);
			} else {
				state = $.data(this, 'timepick', {
					options: $.extend({}, defOptions, options)
				});
	
				init(this);
			}
			$(this).css(state.options.css);
		})
	}

// 初始化
	function init(target) {
		initPanel(target);
		initEvent(target);
	}

// 初始化事件
	function initEvent(target) {
		$(target).click(function(event) {
			currentTarget = target;
			loadValue(target);
			showTimePanel(target);
		});
	}

// 创建面板
	function initPanel(target) {
		if(!$timePanel) {
			// 创建一个一行三列的表格
			var $table = createTable(target);
	
			$timePanel = createPanel($table);
	
			createSelect($timePanel);
	
			$('body').append($timePanel);
		}
	}

	function createTable(target) {
		// 创建表格
		var $table = $('<table style="font-size:12px;border-collapse: collapse;"></table>');
		var $tr = $('<tr>');
		var timepick = $.data(target).timepick;
		var options = timepick.options;
	
		$tr.append(createHourTD())
			.append($('<td>' + options.hourName + '</td>'))
			.append(createMinuteTD())
			.append($('<td>' + options.minuteName + '</td>'))
			.append(createOkTD());
	
		$table.append($tr);
	
		return $table;
	}

	function createPanel($table) {
		var $panel = $('<div class="timepick"></div>').css({
			display: "none",
			position: "absolute",
			'background-color': '#fff',
			border: '1px solid #ccc'
		});
	
		$panel.append($table);
	
		return $panel;
	}

	function createSelect($timePanel) {
		var $selects = $timePanel.find('select');
	
		$hour = $selects.eq(0);
		$minute = $selects.eq(1);
	}

	function createHourTD() {
		return createTimeTD(24);
	}

	function createMinuteTD() {
		return createTimeTD(60);
	}

	function createOkTD() {
		var $td = $('<td>').css({
			'padding': '0px 5px 0px 5px'
		});
		var $a = $('<a href="javascript:void(0)">确定</a>');
	
		$a.css({
			color: '#329ECC',
			'text-decoration': 'none'
		});
	
		$a.click(function() {
			okHandler();
		});
	
		$td.append($a);
	
		return $td;
	}

// 显示面板
	function showTimePanel(target) {
		var targetOffset = $(target).offset();
		var timepick = $.data(target).timepick;
	
		$timePanel.offset({
			top: (targetOffset.top + timepick.options.offsetY),
			left: (targetOffset.left + timepick.options.offsetX)
		});
	
		$timePanel.show();
	}

// 关闭面板
	function hideTimePanel() {
		$timePanel.offset({
			top: 0,
			left: 0
		});
		$timePanel.hide();
	}

/**
 * 格式化日期<br>
 * 使用方法: <code>
 * var dateStr = format(new Date(),'yyyy-MM-dd hh:mm:ss.S');
 * </code>
 * 
 * @param dateInstance
 *            Date实例
 * @param 格式化字符串,如"yyyy-MM-dd","yyyy-MM-dd
 *            hh:mm:ss.S"
 * 
 * @return 返回格式化后的字符串
 */
	function format(dateInstance, pattern) {
		var o = {
			"M+": dateInstance.getMonth() + 1, // 月份
			"d+": dateInstance.getDate(), // 日
			"h+": dateInstance.getHours(), // 小时
			"m+": dateInstance.getMinutes(), // 分
			"s+": dateInstance.getSeconds(), // 秒
			"q+": Math.floor((dateInstance.getMonth() + 3) / 3), // 季度
			"S": dateInstance.getMilliseconds() // 毫秒
		};
		if(/(y+)/.test(pattern)) {
			pattern = pattern.replace(RegExp.$1, (dateInstance.getFullYear() + "").substring(4 - RegExp.$1.length));
		}
	
		for(var k in o) {
			if(new RegExp("(" + k + ")").test(pattern)) {
				pattern = pattern.replace(RegExp.$1, (RegExp.$1.length == 1) ?
					(o[k]) :
					(("00" + o[k]).substring(("" + o[k]).length)));
			}
		}
	
		return pattern;
	}

// 确定事件句柄
	function okHandler() {
		// 获取<select>标签,第一个是小时,第二个分
		var currentTimepick = $.data(currentTarget).timepick;
	
		var hourVal = $hour.val();
		var minuteVal = $minute.val();
	
		bindValue(currentTarget, hourVal, minuteVal);
	
		var onOK = currentTimepick.options.onOK;
	
		if($.isFunction(onOK)) {
			// 格式化输出
			var val = formatValue(currentTimepick.options.format, hourVal, minuteVal);
			onOK(val, $(currentTarget));
		}
	
		hideTimePanel();
	}

	function formatValue(formatStr, hourVal, minuteVal) {
		// 借用Date对象赋值,进行后续的格式化
		date.setHours(hourVal); // 时
		date.setMinutes(minuteVal); // 分
	
		return format(date, formatStr);
	}

	function bindValue(target, hour, minute) {
		target.hourVal = hour;
		target.minuteVal = minute;
	}

	function loadValue(target) {
		var hourVal = target.hourVal || '00';
		var minuteVal = target.minuteVal || '00';
	
		$hour.val(hourVal);
		$minute.val(minuteVal);
	}

	function createTimeTD(maxTime) {
		var $td = $('<td>');
	
		var selectHtml = ['<select>'];
	
		for(var i = 0, len = maxTime; i < len; i++) {
			var time = formatNum(i);
			selectHtml.push('<option value="' + time + '">' + time + '</option>');
		}
		selectHtml.push('</select>');
	
		$td.html(selectHtml.join(''));
	
		return $td;
	}

	function formatNum(num) {
		return num < 10 ? "0" + num : num;
	}

// 点击空白地方隐藏面板
	$(document).click(function(e) {
		if($timePanel) {
			var obj = e.target;
	
			var isOutSide = $(obj).parents('.timepick').length == 0;
			var notCurrentTarget = (obj != currentTarget);
			// 鼠标点空白地方并且没有点在输入框上
				if(isOutSide && notCurrentTarget) {
					hideTimePanel();
				}
		}
	});

})(jQuery);
