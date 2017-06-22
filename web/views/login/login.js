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
                        alert(1);
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
                    }
                });
            }
	}
};

$(function () {
	var login=$("#login");

	$(".loginTab1").click(function () {//后台登录
        $(this).removeClass("border-sub").addClass("bg-green")
		$(this).siblings().removeClass("bg-green").addClass("border-sub")
    });

    $(".loginTab2").click(function () {//医生登录
        window.open("DocWeb/login.html");
    });

	//--------------------------------------点击登录事件-----------------------
	login.on("click",function(){
            LoginBox.login();
	});
	/* --------------------------回车键登录-------------------------------- */
	$(document).on("keydown",function(ev){
		ev=ev||window.event;
		if(ev.keyCode==13){
            LoginBox.login();
		}
	});
});



