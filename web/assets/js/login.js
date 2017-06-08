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
						var user=JSON.parse($.cookie("user"));
						console.log($.cookie("user"));
						console.log("Id："+user.userid+"昵称："+data.name+"手机号："+data.phone+"性别："+data.gender);
						_this.loadAjax("main/main.html",$("body"));
					}else if(data.role == "1"){
						$.cookie("user",JSON.stringify(data));
						console.log("Id："+data.id+"昵称："+data.name+"手机号："+data.phone+"性别："+data.gender);
						location.href = "index-guest.html";
						$("body").html("34455");
					}else if(data.role == "3"){
						$.cookie("user",JSON.stringify(data));
						location.href = "index-doctor.html";
						$("body").html();
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
	},
	loadAjax:function(url,ele){
		var _this=this;
		$.ajax({
			type: "get",
			url: "views/"+url,
			success: function (data){
				//获取数据将login页面的数据加载到页面当中------------------------------
				ele.html(data);
				//--------------------------------------点击登录事件-----------------------
				var body=$("body");
				body.on("click","#login",function(){
					_this.login();
				});
				/* --------------------------回车键登录-------------------------------- */
				body.keydown("keydown","#login",function(ev){
					if(ev.keyCode==13){
						this.trigger();
					}
				})
			}
		})
	}
};



$(function () {
	var user = JSON.parse($.cookie("user"));
	$(".leftnav h2").click(function () {
		$(this).next().slideToggle(200);
		$(this).toggleClass("on");
	});
	$(".leftnav ul li a").click(function () {
		$("#a_leader_txt").text($(this).text());
		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
	});
	LoginBox.logged();
	$("#userName").text(user.name);
	LoginBox.loadAjax("login/login.html",$("body"));
});



