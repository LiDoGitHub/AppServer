var LoginBox={
	login:function(){
		//获取参数
		var phone = $.trim($("#phone").val());
		var password = md5Encrypt($.trim($("#password").val()));
		//console.log("password:"+password+"\n长度:"+password.length);
		if(phone && password){
			var _this=this;
			$.ajax({
				url : serUrl+'user/Login',
				data : {'phone': phone,'password': password},
				type:'post',
				crossDomain:true,
				dataType:"json",
				success:function(data) {
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
	/*var user = JSON.parse($.cookie("user"));
	$("#userName").text(user.name);
	LoginBox.logged();*/
	var login=$("#login");
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



