$(function () {
    var flag=true;
    getDoctorInfo();
    var userName;
	//按钮发送消息 ------------------------------------------------
	$("#send").click(function() {
		var msg = $("#sendMsg").val();
		if(msg=="" || msg==" " ||msg==null){//消息判断----------------
		    alert("消息不能为空");
		    return false;
        }
		//医生回复---------------------------------------------------------
		$("#sendMsg").val("");//清空输入框----------------
        JIM.sendSingleMsg({//发送单聊消息
            'target_username' : userName ,
            'target_nickname' : userName ,
            'content' : msg ,
            'appkey' : '7c2a0e6211914830a77efa41'
        }).onSuccess(function(data) {
            $("#ChatContent").empty();//清空聊天框-----------------------
            for(var i=0;i<Conversation.length;i++){//循环历史记录
                var msgs=Conversation[i].msgs.length;

                if(id==Conversation[i].key){//如果历史记录有当前点击用户的消息

                    if(msgs>99){//判断储存的历史消息条数,如果大于99条 就减去前面20条
                        Conversation[i].msgs.splice(0,20);
                    }

                    var content={'content':{'from_id':''+$.cookies.get("phone")+'','msg_body':{"text":msg}}};
                    Conversation[i].msgs.push(content);

                    for(var k=0;k<Conversation[i].msgs.length;k++){

                        if(Conversation[i].msgs[k].content.from_id==$.cookies.get("phone")){
                            $("#ChatContent").append('<div class="docterMsg"><img src="'+icon+'" class="doctoricon"/><span>'+Conversation[i].msgs[k].content.msg_body.text+'</span></div>');
                        }else {
                            $("#ChatContent").append('<div class="userMsg"><img src="imgs/server.jpg" class="usericon"/><span>'+Conversation[i].msgs[k].content.msg_body.text+'</span></div>');
                        }
                    }
                    break;
                }else {
                    var content={messages:[{'content':{'msg_body':{"text":msg},'from_type':'doctor'},'from_uid':''+id+''}]};
                    msgContent.push(content);
                    for(var b=0;b<msgContent.length;b++){
                        if(id == msgContent[b].messages[0].from_uid){
                            if( msgContent[b].messages[0].content.from_type == "user"){
                                $("#ChatContent").append('<div class="userMsg"><img src="imgs/server.jpg" class="usericon"/><span>'+msgContent[b].messages[0].content.msg_body.text+'</span></div>');
                            }
                            if(msgContent[b].messages[0].content.from_type == "doctor"){
                                $("#ChatContent").append('<div class="docterMsg"><img src="'+icon+'" class="doctoricon"/><span>'+msgContent[b].messages[0].content.msg_body.text+'</span></div>');
                            }
                        }
                    }
                    break;
                }
            }
        }).onFail(function(data) {
            console.log("发送失败"+JSON.stringify(data));
        });
	});

    //Ctrl+Enter发送消息---------------------------------------------
    $("#sendMsg").keydown(function() {
        if (event.ctrlKey && event.keyCode == 13) {//键盘事件
            var msg = $("#sendMsg").val();
            if(msg=="" || msg==" " ||msg==null){//消息判断----------------
                alert("消息不能为空");
                return false;
            }
            //医生回复---------------------------------------------------------

            $("#sendMsg").val("");//清空输入框----------------
            JIM.sendSingleMsg({//发送单聊消息
                'target_username' : userName ,
                'target_nickname' : userName ,
                'content' : msg ,
                'appkey' : '7c2a0e6211914830a77efa41'
            }).onSuccess(function(data) {

                $("#ChatContent").empty();//清空聊天框-----------------------
                for(var i=0;i<Conversation.length;i++){//循环历史记录
                    var msgs=Conversation[i].msgs.length;

                    if(id==Conversation[i].key){//如果历史记录有当前点击用户的消息

                        if(msgs>99){//判断储存的历史消息条数,如果大于99条 就减去前面20条
                            Conversation[i].msgs.splice(0,20);
                        }
                        var content={'content':{'from_id':''+$.cookies.get("phone")+'','msg_body':{"text":msg}}};
                        Conversation[i].msgs.push(content);

                        for(var k=0;k<Conversation[i].msgs.length;k++){

                            if(Conversation[i].msgs[k].content.from_id==$.cookies.get("phone")){
                                $("#ChatContent").append('<div class="docterMsg"><img src="'+icon+'" class="doctoricon"/><span>'+Conversation[i].msgs[k].content.msg_body.text+'</span></div>');
                            }else {
                                $("#ChatContent").append('<div class="userMsg"><img src="imgs/server.jpg" class="usericon"/><span>'+Conversation[i].msgs[k].content.msg_body.text+'</span></div>');
                            }
                        }
                        break;
                    }else {
                        var content={messages:[{'content':{'msg_body':{"text":msg},'from_type':'doctor'},'from_uid':''+id+''}]};
                        msgContent.push(content);
                        for(var b=0;b<msgContent.length;b++){
                            if(id == msgContent[b].messages[0].from_uid){
                                if( msgContent[b].messages[0].content.from_type == "user"){
                                    $("#ChatContent").append('<div class="userMsg"><img src="imgs/server.jpg" class="usericon"/><span>'+msgContent[b].messages[0].content.msg_body.text+'</span></div>');
                                }
                                if(msgContent[b].messages[0].content.from_type == "doctor"){
                                    $("#ChatContent").append('<div class="docterMsg"><img src="'+icon+'" class="doctoricon"/><span>'+msgContent[b].messages[0].content.msg_body.text+'</span></div>');
                                }
                            }
                        }
                        break;
                    }
                }
            }).onFail(function(data) {
                console.log("发送失败"+JSON.stringify(data));

            });
        }
    });

	//左边手风琴事件----------------------------------------------
	$(".leftnav h2").click(function(){
		  $(this).next().slideToggle(200);	
		  $(this).toggleClass("on"); 
	  });


    $(".leftnav ul li a").click(function(){
          $("#a_leader_txt").text($(this).text());
          $(".leftnav ul li a").removeClass("on");
          $(this).addClass("on");
    });


	  //点击查看消息-----------------------------------------------
	$(".user_tab").on("click","li",function (){
        $("#dialog").css("display",'block');
        $("#ChatContent").empty();
        id=$(this).attr("id");
        var text=$(this).children("span").text();
        userName=text;
        $(".username").text(text);
        for(var i=0;i<Conversation.length;i++){
            if(id==Conversation[i].key){
                for(var k=0;k<Conversation[i].msgs.length;k++){
                    if(Conversation[i].msgs[k].content.from_id==$.cookies.get("phone")){
                        $("#ChatContent").append('<div class="docterMsg"><img src="'+icon+'" class="doctoricon"/><span>'+Conversation[i].msgs[k].content.msg_body.text+'</span></div>');
                    }else {
                        $("#ChatContent").append('<div class="userMsg"><img src="imgs/server.jpg" class="usericon"/><span>'+Conversation[i].msgs[k].content.msg_body.text+'</span></div>');
                    }
                }
                return false;
            }
        }
        for(var b=0;b<msgContent.length;b++){
            if(id == msgContent[b].messages[0].from_uid){
                if( msgContent[b].messages[0].content.from_type !== "doctor"){
                    $("#ChatContent").append('<div class="userMsg"><img src="imgs/server.jpg" class="usericon"/><span>'+msgContent[b].messages[0].content.msg_body.text+'</span></div>');
                }else {
                    $("#ChatContent").append('<div class="docterMsg"><img src="'+icon+'" class="doctoricon"/><span>'+msgContent[b].messages[0].content.msg_body.text+'</span></div>');
                }
            }
        }
    });

   //关闭对话框------------------------------------------
    $('.close').click(function(){
    	$('#dialog').css('display','none');
        var user_li=$(".user_tab>li");
		for(var i=0;i<user_li.length;i++){
            if(user_li.eq(i).attr("id")==id){
                user_li.eq(i).remove();
			}
        }
    });

    function getDoctorInfo() {
        var iphone=$.cookies.get("phone");
        var docId;
		///获取医生信息------------------------------------
        if(iphone!==null&&iphone!==undefined){
            $.ajax({
                url: serUrl+"/doctor/getDocInfo",
                data:{"docid":iphone},
                type: "post",
                dataType: "json",
                success:function(data){
                    icon=data.icon;
                    $.cookies.set("icon", data.icon);
                    docId=data.id;
                    $(".information").html($(".information").html() +  "<img src=" + data.icon +" />" + "<div class='section'>"+"<p>"+data.name + "</p>"+"<p>" +data.section + "</p>"+"</div>");

                    if(data.id){
                        $.cookies.set("id", data.id);
                    }
                    var doctorId = $.cookies.get("id");
                }
            });
        }
    }



});








