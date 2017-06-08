(function(){
    var isopen = false;
    var newImg;
    var w = 200; //将图片宽度+200
    var h = 200; // 将图片高度 +200
    $.ajax({ /* 139.224.196.16 */
        url: 'FindLectureAll',
        type: 'post',
        dataType: 'json',
        success: function(data) {
            $.each(data, function(commentIndex, comment) {
                $("#goods3").html($("#goods3").html() + "<tr>" +
                    "<td>" + comment['id'] + "</td>" +
                    "<td>" + '<div class="operation"><p>' + comment['title'] + '</p></div>' + "</td>" +
                    "<td>" + '<img src=' + comment["cover"] + ' width="100" height="50"  />' + "</td>" +
                    "<td>" + '<div class="look"><video controls="controls" src='+ comment["video"] +' width="100" height="50"></video></div>' + "</td>" +
                    "<td>" + '<div class="button-group">' +
                    '<a class="button border-red del" 	val=' + comment['id'] + ' href="javascript:void(0)"><span class="icon-trash-o"></span> 删除</a>' +
                    '</div>' +
                    "</td>" +
                    "</tr>");
            });
            var theTable = document.getElementById("goods3");
            var totalPage = document.getElementById("spanTotalPage");
            var pageNum = document.getElementById("spanPageNum");

            var numberRowsInTable = theTable.rows.length;
            var pageSize = 10;
            var page = 1;

            //隐藏表格
            function hide() {
                for(var i = pageSize; i < numberRowsInTable; i++) {
                    theTable.rows[i].style.display = 'none';
                }
                totalPage.innerHTML = pageCount();
                pageNum.innerHTML = '1';
            }
            hide();

            //下一页
            $("#next").click(function(){
                hideTable();
                currentRow = pageSize * page;
                maxRow = currentRow + pageSize;
                if(maxRow > numberRowsInTable) maxRow = numberRowsInTable;
                for(var i = currentRow; i < maxRow; i++) {
                    theTable.rows[i].style.display = '';
                }
                page++;
                showPage();

            })

            //上一页
            $("#pre").click(function(){
                hideTable();
                page--;
                currentRow = pageSize * page;
                maxRow = currentRow - pageSize;
                if(currentRow > numberRowsInTable) currentRow = numberRowsInTable;
                for(var i = maxRow; i < currentRow; i++) {
                    theTable.rows[i].style.display = '';
                }
                showPage();
            })
            //第一页
            $("#first").click(function(){
                page = 1;
                for(var i = 0; i < pageSize; i++) {
                    theTable.rows[i].style.display = '';
                }
                showPage();
            })
            //最后一页
            $("#last").click(function(){
                hideTable();
                page = pageCount();
                currentRow = pageSize * (page - 1);
                for(var i = currentRow; i < numberRowsInTable; i++) {
                    theTable.rows[i].style.display = '';
                }
                showPage();
            })

            function hideTable() {
                for(var i = 0; i < numberRowsInTable; i++) {
                    theTable.rows[i].style.display = 'none';
                }
            }
            function showPage() {
                pageNum.innerHTML = page;
            }
            //总共页数
            function pageCount() {
                var count = 0;
                if(numberRowsInTable % pageSize != 0) count = 1;
                return parseInt(numberRowsInTable / pageSize) + count;
            }
            //删除
            $("#goods3").find(".del").on("click", function() {
                var id = $(this).attr("val");
                del(id);

            });
            $("video").bind("click", function() {
                newImg = this;
                if(!isopen) {
                    isopen = true;
                    $(this).width($(this).width() + w);
                    $(this).height($(this).height() + h);
                    moveImg(10, 10);
                } else {
                    isopen = false;
                    $(this).width($(this).width() - w);
                    $(this).height($(this).height() - h);
                    moveImg(-20, -20);
                }

            });
        },
    });

//删除视频
    function del(id) {
        $.ajax({
            url: 'DeleteLectureById',
            type: 'post',
            dataType: 'json',
            data: {'id': id},
            success: function() {
                confirm('你真的打算删除吗?',function(action){
                    if(action == 'ok'){
                        window.location.reload();
                    }
                });
                return false;
            },
            error: function() {
                alert("服务器错误，请稍后重试！");
            }
        });
    }
//移位
    i = 0;
    function moveImg(left, top) {
        var offset = $(newImg).offset();
        $(newImg).offset({
            top: offset.top + top,
            left: offset.left + left
        });
        if(i == 10) {
            i = 0;
            return;
        }
        setTimeout("moveImg(" + left + "," + top + ")", 20);
        i++;
    }

})();
