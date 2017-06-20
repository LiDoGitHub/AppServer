$(document).ready(function(){
	/*var leftli = $(".user");
	var li = leftli.find("li");
	li.unbind("click");
	var temptr = $();
	li.on("click",function(event){//用户列表单击
		
		//选中当前元素						
		temptr.removeClass("host");
        temptr = $(this);
        temptr.addClass("host")
        var phone = $.trim($(this).text());
        alert(phone);
		var from_id = $.trim($(this).text());
        $(".chat").show();
		var nowChat = $("#ChatContent").find("#chat"+from_id);
		if(nowChat.length == 0){//不存在
			$("#ChatContent").append("<div id='chat"+from_id+"'></div>");
		}else{
			//showMsg('ChatContent');//显示聊天记录
			$("#ChatContent").find("div").hide();
			nowChat.show();
		}
		$("#nowChat").val(from_id);
	});*/
   
});

function savei18ninfo() {
	var i18ninfo = geti18ninfo();
	alert(i18ninfo);

}
//获取i18n值 
function geti18ninfo() {
	var key = "";
	var value = "";
	var i18ndata = "";
	var table = $("#i18ntable");
	var tbody = table.children();
	var trs = tbody.children();
	for(var i = 1; i < trs.length; i++) {
		var tds = trs.eq(i).children();
		for(var j = 0; j < tds.length; j++) {
			if(j == 0) {
				if(tds.eq(j).text() == null || tds.eq(j).text() == "") {
					return null;
				}
				key = "key\":\"" + tds.eq(j).text();
			}
			if(j == 1) {
				if(tds.eq(j).text() == null || tds.eq(j).text() == "") {
					return null;
				}
				value = "value\":\"" + tds.eq(j).text();
			}
		}
		if(i == trs.length - 1) {
			i18ndata += "{\"" + key + "\",\"" + value + "\"}";
		} else {
			i18ndata += "{\"" + key + "\",\"" + value + "\"},";
		}
	}
	i18ndata = "[" + i18ndata + "]";
	return i18ndata;
}
var clientWidth = document.documentElement.clientWidth;
var clientHeight = document.documentElement.clientHeight;
var div_left_width = 200;
var tempWidth = 0;

function resizeLayout() {
	try {
		clientWidth = document.documentElement.clientWidth;
		var div_left_width = $("#left").width() + 11;
		$("#cc").layout("resize");
		$('#userquery').panel('resize', {
			width: clientWidth - div_left_width
		});
		$('#10100801').datagrid('resize', {
			width: clientWidth - div_left_width
		});

		$('#userrange').combobox({
			width: $('#right').width() * 0.35
		});
	} catch(e) {}
}

function initResize() {
	//自动适应页面大小 
	$(".layout-button-left").bind("click", function() {
		$('#userquery').panel('resize', {
			width: clientWidth - 28
		});
		$('#10100801').datagrid('resize', {
			width: clientWidth - 28
		});
		$(".layout-button-right").bind("click", function() {
			$('#userquery').panel('resize', {
				width: tempWidth
			});
			$('#10100801').datagrid('resize', {
				width: tempWidth
			});
		});
	});
}

function tdclick(tdobject) {
	var td = $(tdobject);
	td.attr("onclick", "");
	//1,取出当前td中的文本内容保存起来 
	var text = td.text();
	//2,清空td里面的内容 
	td.html(""); //也可以用td.empty(); 
	//3，建立一个文本框，也就是input的元素节点 
	var input = $("<input>");
	//4，设置文本框的值是保存起来的文本内容 
	input.attr("value", text);
	input.bind("blur", function() {
		var inputnode = $(this);
		var inputtext = inputnode.val();
		var tdNode = inputnode.parent();
		tdNode.html(inputtext);
		tdNode.click(tdclick);
		td.attr("onclick", "tdclick(this)");
	});
	input.keyup(function(event) {
		var myEvent = event || window.event;
		var kcode = myEvent.keyCode;
		if(kcode == 13) {
			var inputnode = $(this);
			var inputtext = inputnode.val();
			var tdNode = inputnode.parent();
			tdNode.html(inputtext);
			tdNode.click(tdclick);
		}
	});

	//5，将文本框加入到td中 
	td.append(input);
	var t = input.val();
	input.val("").focus().val(t);
	//6,清除点击事件 
	td.unbind("click");
}
function deletetr(tdobject) {
}