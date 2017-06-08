$(document).ready(function(){
	//极光登录
	logged();
	/* 获取医生信息*/
	$.ajax({
		url: serUrl+'FindDoctorByPhone',  
        type: 'post',  
        dataType: 'json',
        success:function(data){
        	  $.each(data, function(commentIndex, comment){
                  
        		 // console.log("姓名:"+ comment['name'],comment['icon'],comment['section']);
        		  
        		  $(".information").html($(".information").html() +  "<img src=" + comment['icon'] +" />" + "<div class='section'>"+"<span>"+comment['name'] + "</span>"+"<li>" +comment['section'] + "</li>"+"</div>");
        	  });
        }
	});
	//获取挂号列表
	$.ajax({
		url: serUrl+'FindReminUser',  
        type: 'post',  
        dataType: 'json',
        success:function(data){
        	  $.each(data, function(commentIndex, comment){
        		  $("#registarationTbody").html($("#registarationTbody").html()+ "<tr id='theader'>"+
        				  "<td>"+comment['id']+"</td>"+
        				  "<td >"+comment['name']+"</td>"+
        				  "<td >"+comment['gender']+"</td>"+
        				  "<td >"+comment['age']+"</td>"+
        				  "<td >"+comment['section']+"</td>"+
        				  "<td >"+comment['phone']+"</td>"+
        				  "<td >"+comment['city']+"</td>"+
        				  "<td >"+comment['reservation_date']+"</td>"+
        				  "<td>" + '<div class="button-group">' +
        				  '<a class="button border-main edit" val=' + comment['id'] + ' href="#"><span class="icon-edit"></span>温馨提醒</a>' +
        				  '</div>' +
           				  "</td>"+
        		  "</tr>");
        	  });
        }
	});
	/* 按钮发送消息 */
	$("#send").click(function() {
		var msg = $("#sendMsg").val();
		sendSingleMsg(msg);
	});
	$(".leftnav h2").click(function(){
		  $(this).next().slideToggle(200);	
		  $(this).toggleClass("on"); 
	  })
	  $(".leftnav ul li a").click(function(){
		    $("#a_leader_txt").text($(this).text());
	  		$(".leftnav ul li a").removeClass("on");
			$(this).addClass("on");
	  });
    /*弹出会话框*/
	
	$("#user h3").unbind("click");
	$("#user h3").on("click",function(event){//用户列表单击
		$('.chat').css('display','block').removeClass('mins');
	   	$('.userName').html($(this).find('h3').html()); 		
	var from_id = $.trim($(this).html());
	var nowChat = $("#ChatContent").find("#chat"+from_id);
	if(nowChat.length == 0){//不存在
		$(".chatContent").append("<div id='chat"+from_id+"'></div>");
	}else{
		$(".chatContent").find("div").hide();
		nowChat.show();
	}
	$("#nowChat").val(from_id);
	});		
    
   /*患者管理列表*/
   	$(".subMenu li").click(function() {
	$("#content b").html($(this).html());
	});
   /*关闭对话框*/	
    $('.close').click(function(){
    $('.chat').css('display','none')
    });
   /*挂号医生提醒用户*/
    $("#registarationTbody,img").click(function(){
      $(".reply").show();
    });
   /*用药提醒设置*/
    $(".add input").click(function(){
    $(".renm").show();
    });
    /*医生嘱咐*/
    $(".tijiaoreply").click(function tijiaoreply(){
    var con = document.form2.con.value;
    	if(con == ""){
    		alert("请输入提醒内容");
    	}else{
    		document.form2.submit();
    		$(".reply").hide();
    		
    	}
    });
    /*添加用药*/
    $("#remindTbody,img").click(function(){
    	$("#remind").show();
		  
	  });
    /*保存提醒*/
    $(".sumbaocun").click(function(){
    	var startDay = document.reg_testdate.startDay.value; 	
    	var endDay = document.reg_testdate.endDay.value; 	
    	var time1 = document.reg_testdate.time1.value;
    	var content1 = document.reg_testdate.content1.value;
    	var content2 = document.reg_testdate.content2.value;
    	var time2 = document.reg_testdate.time2.value;
    	var content3 = document.reg_testdate.content3.value;
    	var time3 = document.reg_testdate.time3.value;
    	$.ajax({ 
        	url: serUrl+'AddRegUser', 
            type: 'post', 
            dataType:'json',
            data: $(".sum").serializeArray(), 
            success:function(data){
           	 if(data =="1"){
           		 alert("解析错误");
           	 }else if(data == "2"){
           		 alert("数据出错");
           	 }else if(data == "0"){
           		 alert("添加成功");
           	 }
          },
          error:function(){
        	  alert("系统错误");
          }
       });
    	
    });
    $("#remindTbody").find("#del").on("click", function() {
		alert($(this).attr("val"));
	});
    /*取消添加用药*/
    $("#remind .sumquxiao").click(function(){
	   $("#remind").hide();
   });
    /*录入患者*/
    $(".btn").click(function(){
    	$(".guahao").show();
    });
    /*取消录入框*/
    $(".guahaoquxiao").click(function(){
    	$(".guahao").hide();
    });
    /*搜索*/
    $("#seek").click(function() {
		var txt = $("#ptuser").val();
		if($.trim(txt) != "") {
			$("#remindTbody").find("tr").hide();
			$("#remindTbody").find("tr").filter(":contains('" + txt + "')").show().css("background", "#CCFFFF");
			$("#registarationTbody").find("tr").hide();
			$("#registarationTbody").find("tr").filter(":contains('" + txt + "')").show().css("background", "#CCFFFF");
		} else {
			$("#remindTbody tr:not('#theader')").css("background", "#fff").show();
			$("#registarationTbody tr:not('#theader')").css("background", "#fff").show();
		}
	});
    $('.time1').timepick();
	$('.time2').timepick();
	$('.time3').timepick();
	
	
});
/*患者管理列表打开*/
	function setTab(name, cursel, n) {
	  for(i = 1; i <= n; i++) {
		var menu = document.getElementById(name + i);
		var zzjs = document.getElementById("wwwzzjsnet_" + name + "_" + i);
		menu.className = i == cursel ? "hover" : "";
		zzjs.style.display = i == cursel ? "block" : "none";
	} 
}
/*添加用药时间*/
window.onload=function () {

	var oTab = document.getElementById("ghha");
	var oBt = document.getElementsByTagName("input");
	
}	
