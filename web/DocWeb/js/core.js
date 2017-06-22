//实例化一个极光
var JIM = new JMessage({debug:false});

//全局变量--------------
var appkey,random_str,signature,timestamp;//签名所需的四个变量
var op,zp;//全局变量!获取
var auth_platform=null;//login 用到的的fromData;
var Conversation;//暂存的历史消息
var icon;//doctor头像
var message;//暂存的新消息
var id;//获取当前聊天用户的key;
var msgContent=[];



//路由页面儿-----------------------------------------------------
var mainBox={
    ajaxData:function (url,ele){
        $.ajax({
            type:"get",
            url:"templates/"+url,
            success:function(data){
                ele.html(data);
            }
        })
    },
    //点击选项   加载页面[SPA]------路由
    addEvent:function(){
        var _self=this;
        var admin=$(".admin");
        //默认渲染------
        this.ajaxData("adv.html",admin);
        $(".leftnav li").click(function () {
            var index=parseInt($(this).attr("data-num"));
                switch(index){
                case 1:_self.ajaxData("adv.html",admin);break;//挂号管理
                case 2:_self.ajaxData("HistoryMsg.html",admin);break;//历史会话
                default:console.log("data is empty..........");break;//出现错误则提醒用户数据为空值
            }
        })
    }
};


//登录------------------------------------------------------------
function login() {

    //获取参数-----------------------------------------------------
    var phone = $.trim($("#phone").val());
    var password = $.trim($("#password").val());
    $.cookies.set("phone",phone);//存贮cookies
    $.cookies.set("password",password);//存贮cookies
    //调用IM登录函数-----------------------------------------------
    JIM.login({
        'username' :phone,
        'password' : password
    }).onSuccess(function(data) {//请求成功回调----------------
        //alert("成功");
        window.location.href="index.html";

    }).onFail(function(data) {//请求失败回调----------------

        alert("登录失败,账号或密码错误!");

    }).onTimeout(function(data) {//请求超时回调----------------

        alert("登录失败,登录超时,请检查网络后重试");

    });
}



//保持登录------------------------------------------------------------
function logged() {
	var phone =$.cookies.get("phone");//调用cookies储存
    var password =$.cookies.get("password");//调用cookies储存

    //调用IM登录函数-----------------------------------------------
    JIM.login({
        'username' : phone,
        'password' : password
    }).onSuccess(function(data) {//请求成功回调------------------------------------------------------

        JIM.onSyncConversation(function(data) {//登录完成后自动获取历史消息历史消息-------------------
            Conversation=data
        });

        JIM.onMsgReceive(function(data) {//在线消息监听----------------------------------
            console.log(data);
                message=data.messages[0].from_uid;//获取到的历史消息数组;
                var content={'content':{'from_id':data.messages[0].content.from_id,'msg_body':{"text":data.messages[0].content.msg_body.text}}};
                var contents={'key':''+message+'',msgs:[{'content':{'from_id':data.messages[0].content.from_id,'msg_body':{"text":data.messages[0].content.msg_body.text}}}]};
                for(var i=0;i<Conversation.length;i++){//检查是否有此用户的历史消息;
                    if(String(message) == Conversation[i].key){//如果存在就添加到记录里
                        console.log(i);
                        Conversation[i].msgs.push(content);

                        var msgs=Conversation[i].msgs.length;
                        if(msgs>99){
                            Conversation[i].msgs.splice(0,20);
                        }
                    }
                }
                 msgContent.push(data);
                console.log(msgContent);
                if(message == id){
                    $("#ChatContent").empty();
                    for(var k=0;k<Conversation.length;k++){
                        if( Conversation[k].key == message ){
                            for(var b=0;b<Conversation[k].msgs.length;b++){
                                if(Conversation[k].msgs[b].content.from_id == $.cookies.get("phone")){
                                    $("#ChatContent").append('<div class="docterMsg"><img src="'+icon+'" class="doctoricon"/><span>'+Conversation[k].msgs[b].content.msg_body.text+'</span></div>');
                                }else {
                                    $("#ChatContent").append('<div class="userMsg"><img src="imgs/server.jpg" class="usericon"/><span>'+Conversation[k].msgs[b].content.msg_body.text+'</span></div>');
                                }
                            }
                        }
                    }
                }
                var leftli = $(".user_tab");
                var user_li=leftli.children("li");
                var friendName ='<li class="userTab" id="'+data.messages[0].from_uid+'">'+'<i class="New">New</i><span>'+data.messages[0].content.from_id+'</span></li>';

				if(user_li.length>0){//有新消息的时候检查聊天列表是否有用户,如果没有直接添加,如果有进IF;
                    for(var j=0;j<user_li.length;j++){
                        if(user_li.eq(j).attr("id")==message){
                            if($('#dialog').css('display')!=="block"){
                                user_li.eq(i).css("background","darkorange");
                                user_li.eq(i).append('<i class="New">New</i>');
                            }else{
                                user_li.eq(i).css("background","#fff");
                                user_li.eq(i).children("i").remove();
                                $("#ChatContent").empty();
                                for(var b=0;b<msgContent.length;b++){
                                    if(id == msgContent[b].messages[0].from_uid){
                                        $("#ChatContent").append('<div class="userMsg"><img src="imgs/server.jpg" class="usericon"/><span>'+msgContent[b].messages[0].content.msg_body.text+'</span></div>');
                                    }
                                }
                                break;
							}

                        }else {
                            leftli.append(friendName);
						}
                    }

				}else{
                    leftli.append(friendName);
				}

        });

    }).onFail(function(data) {//请求失败回调----------------

        alert("登录失败,账号或密码错误!");

    }).onTimeout(function(data) {//请求超时回调----------------

        alert("登录失败,登录超时,请检查网络后重试");

    });
}

function loginOut() {
	window.location.href="login.html";
    JIM.loginOut();
}

(function () {
    mainBox.addEvent();

    $("body").on("click","ul li a",function () {
        $("#a_leader_txt").text($(this).text());
        $(this).addClass("on").parent("li").siblings().children().removeClass("on");
    });
})();