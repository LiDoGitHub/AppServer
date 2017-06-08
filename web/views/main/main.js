var mainBox={
    //ajax-----------------获取页面的数据
    ajaxData:function (url,ele){
        /*
         * 1.URL-----路径
         * 2.ele----元素
         * */
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
        this.ajaxData("Manag/userManag.html",admin);
        $(".leftnav").on("click","li",function(){
            var index=parseInt($(this).attr("data-num"));
            //console.log(index);
            switch(index){
                case 1:_self.ajaxData("webset/webset.html",admin);break;//网站设置
                case 2:_self.ajaxData("password/pass.html",admin);break;//修改密码
                case 3:_self.ajaxData("singlePage/page.html",admin);break;//单页管理
                case 4:_self.ajaxData("carousel/carousel.html",admin);break;//首页轮播
                case 5:_self.ajaxData("leaveWord/book.html",admin);break;//留言管理
                case 6:_self.ajaxData("column/column.html",admin);break;//栏目管理
                case 7:_self.ajaxData("Feedback/list.html",admin);break;//意见反馈
                case 8:_self.ajaxData("disease/disease.html",admin);break;//疾病管理
                case 9:_self.ajaxData("zixun/cyclopedia.html",admin);break;//资讯管理
                case 10:_self.ajaxData("consultation/consultClass.html",admin);break;//资讯分类
                case 11:_self.ajaxData("video/lecture.html",admin);break;//视频管理
                case 12:_self.ajaxData("keDepart/kedepart.html",admin);break;//科室管理
                case 13:_self.ajaxData("Manag/userManag.html",admin);break;//用户管理
                case 14:_self.ajaxData("Doctor/Doctor.html",admin);break;//医生管理
                case 15:_self.ajaxData("health/health-records.html",admin);break;//健康管理管理
                case 16:_self.ajaxData("register/registration.html",admin);break;//用户挂号
                case 17:_self.ajaxData("remind/remind.html",admin);break;//用户用药提醒
                default:console.log("data is empty..........");break;//出现错误则提醒用户数据为空值
            }
        })
    }
};

(function(){
    //点击选项卡内容--------调用函数
    mainBox.addEvent();
    var $leftnav=$(".leftnav h2");
    $leftnav.on("click",function () {
        $(this).next().slideToggle();
        $(this).removeClass("on");
    });
    $("body").on("click","ul li a",function () {
        $("#a_leader_txt").text($(this).text());
        $(this).addClass("on").parent("li").siblings().children().removeClass("on");
    });

    //显示登录人的名称
    var username=$.cookie("username");
    $("#userName").html(username);




})();










