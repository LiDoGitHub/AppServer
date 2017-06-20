var LoginBox={
	login:function(){
            //获取参数
            var phone = $.trim($("#phone").val());
            var password = md5Encrypt($.trim($("#password").val()));
            if(phone && password){
                var _this=this;
                $.ajax({
                    url : serUrl+'user/Login',
                    data : {'phone': phone,'password': password},
                    type:'post',
                    crossDomain:true,
                    dataType:"json",
                    success:function(data) {
                        console.log(data);
                        if(data == "pwdError"||data=="phoneError"){
                            alert("用户名或密码错误");
                        }else if(data.role == "2"){
                            $.cookie("user",JSON.stringify(data));
                            var username=$.cookie("username",data.name);
                            var user=JSON.parse($.cookie("user"));
                            console.log($.cookie("user"));
                            console.log("Id："+user.userid+"昵称："+data.name+"手机号："+data.phone+"性别："+data.gender);
                            window.open("main.html","_self");
                        }else if(data.role == "1"){
                            $.cookie("user",JSON.stringify(data));
                            console.log("Id："+data.id+"昵称："+data.name+"手机号："+data.phone+"性别："+data.gender);
                            location.href = "index-guest.html";
                        }else if(data.role == "3"){
                            $.cookie("user",JSON.stringify(data));
                            location.href = "index-doctor.html";
                        }
                    },
                    error : function(data) {
                        alert("error:"+data);
//					alert("系统错误");
                    }
                });
            }
	},
    login2:function () {
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
            alert("成功");
            window.location.href="index.html";

        }).onFail(function(data) {//请求失败回调----------------

            alert("登录失败,账号或密码错误!");

        }).onTimeout(function(data) {//请求超时回调----------------

            alert("登录失败,登录超时,请检查网络后重试");

        });
    },
	logged:function(){
		var user=JSON.parse($.cookie("user"));
		var phone = user.phone;
		var name = user.name;
		var gender = user.gender;
		var Id = user.userid;
		var role =user.role;
		console.log(user);
		console.log("保持登录{"+" id:"+Id+" 手机号:" +phone+" 昵称:"+name+" 角色:"+role)+" }";
	}
};

$(function () {
	var flag=0;//0 是后台,1是医生    默认是后台界面;
	var login=$("#login");

	$(".loginTab1").click(function () {//后台登录
        flag=0;
        $(this).removeClass("border-sub").addClass("bg-green")
		$(this).siblings().removeClass("bg-green").addClass("border-sub")
    });

    $(".loginTab2").click(function () {//医生登录
        flag=1;
        //极光签名-------------------------------------
        $.ajax({
            url:serUrl+'/Singnature',
            type:'post',
            dataType:'json',
            success:function(data){
                appkey=data.appkey;
                random_str=data.random_str;
                signature=data.signature;
                timestamp=data.timestamp;
                //初始化
                JIM.init({
                    'appkey' : appkey ,
                    'random_str' : random_str ,
                    'signature' : signature ,
                    'timestamp' : timestamp ,
                    'flag' : 1
                }).onSuccess(function(data) {
                    console.log(data);
                    console.log("初始化成功");
                    logged();
                }).onFail(function(data) {
                    alert('初始化失败,请重试');
                });
            }
        });
        $(this).removeClass("border-sub").addClass("bg-green")
        $(this).siblings().removeClass("bg-green").addClass("border-sub")
    });

	//--------------------------------------点击登录事件-----------------------
	login.on("click",function(){
		if(flag=="0"){
            LoginBox.login();
		}else {
           LoginBox.login2();
		}

	});
	/* --------------------------回车键登录-------------------------------- */
	$(document).on("keydown",function(ev){
		ev=ev||window.event;
		if(ev.keyCode==13){
            if(flag=="0"){
                LoginBox.login();
            }else {
                LoginBox.login2();
            }
		}
	});
});




