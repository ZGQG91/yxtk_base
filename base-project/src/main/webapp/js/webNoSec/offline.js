/*jslint this:true*/
/**
 * Created by Zhang Mingming on 2016/12/19.
 */
$(function () {
    var ajaxFlag = true;
    var direction = "right";
    /*
     *   初始化申请数据
     * */
    $(".main-left ul li").eq(2).on("click", function () {
        myAjax(1, 1);
    });

    /*
     *   分页申请数据
     * */
    $(document).on("click", "#offline .my-nav li", function () {
        ajaxFlag = false;
        var activeLiIndex = $("#offline .my-nav li.active").index();

        if ($(this).index() > 0 && $(this).index() < $("#offline .my-nav li").length - 1) {
            activeLiIndex = $(this).index();
        }
        if ($(this).index() === 0) {
            if (activeLiIndex == 1) {
                return;
            }
            activeLiIndex--;
        }
        if ($(this).index() === $("#offline .my-nav li").length - 1) {
            if (activeLiIndex == $("#offline .my-nav li").length - 2) {
                return;
            }
            activeLiIndex++;
        }

        if (activeLiIndex === 1) {
            $("#offline .my-nav li").eq(0).addClass("disabled");
        }else {
            $("#offline .my-nav li").eq(0).removeClass("disabled");
        }
        if (activeLiIndex === $("#offline .my-nav li").length - 2) {
            $("#offline .my-nav li").eq($("#offline .my-nav li").length - 1).addClass("disabled");
        }else {
            $("#offline .my-nav li").eq($("#offline .my-nav li").length - 1).removeClass("disabled");
        }

        $("#offline .my-nav li").removeClass("active");
        $("#offline .my-nav li").eq(activeLiIndex).addClass("active");

        if ($("#offline .my-nav li.active a").html() === "...") {
            ajaxFlag = true;

            if ($("#offline .my-nav li.active").index() === 2) {
                direction = "left";
                var  leftHtml = $("#offline .my-nav li").eq(activeLiIndex + 1).find("a").html();
                myAjax(leftHtml - 1, leftHtml - 10);
            }else{
                direction = "right";
                var rightHtml = $("#offline .my-nav li").eq(activeLiIndex - 1).find("a").html();
                myAjax(rightHtml / 1 + 1, rightHtml / 1 + 1);
            }

            return;
        }
        myAjax($("#offline .my-nav li.active a").html() - 0);
    });

    /*
     *   创建table中tr元素数据
     * */
    function setData (arr) {
        var i;
        var j;
        var oTr;
        var offlineDiv = $("#offline");
        var len=offlineDiv.find("table tr").length - 1;
        for (j=len;j>0;j-=1) {
            offlineDiv.find("table tr").eq(j).remove();
        }
        for (i = 0; i < arr.length; i+=1) {
            oTr = $("<tr></tr>");
            oTr.append($("<td>" + (i + 1) +
                "</td><td>" + arr[i].moduleId +
                "</td><td>" + arr[i].modulePkg +
                "</td><td>" + arr[i].modulePath +
                "</td><td>" + arr[i].pkgType +
                "</td><td>" + arr[i].versionForks + "</td>"));
            offlineDiv.find("table").append(oTr);
        }

    }

    /*
     *   创建分页导航
     * */
    function setNav (allCount,startIndex) {
        ajaxFlag = false;
        var myNav = $("#offline .my-nav");
        var activeClass = "active";

        if (startIndex === 1 && direction === "right") {
            activeClass = "active";
        }else {
            activeClass = "";
        }

        myNav.find("ul").empty();
        myNav.find("ul").append($("<li><a href='javascript:void(0)'>&laquo;</a></li>"));
        myNav.find("ul").append($("<li class='"+ activeClass +"'><a href='javascript:void(0)'>1</a></li>"));

        if (startIndex !== 1) {
            myNav.find("ul").append($("<li><a href='javascript:void(0)'>...</a></li>"));
        }

        for (var i = startIndex; i <= startIndex + 9; i++) {

            if (i === 1 || i >=  Math.ceil(allCount / 10)) {
                continue;
            }

            var oLi = $("<li><a href='javascript:void(0)'>" + i + "</a></li>");

            if ((direction === "left" && i % 10 === 0) || (direction === "right" && i % 10 === 1)) {
                oLi.addClass("active");
            }

            myNav.find("ul").append(oLi);
        }

        if (startIndex + 10 < Math.ceil(allCount / 10)) {
            myNav.find("ul").append($("<li><a href='javascript:void(0)'>...</a></li>"));
        }

        if (Math.ceil(allCount / 10) != 1) {
            myNav.find("ul").append($("<li><a href='javascript:void(0)'>" + Math.ceil(allCount / 10) + "</a></li>"));
        }
        myNav.find("ul").append($("<li><a href='javascript:void(0)'>&raquo;</a></li>"));
    }

    /*
     *   ajax函数
     * */
    function myAjax(pageNo, startIndex) {
        $.ajax({
            url: "api/module/getModuleListPage.do",
            type: "POST",
            data: {
                pageNo: pageNo,
                pageSize: 10
            },
            async: true,
            success: function (data) {
                console.log(data);
                var arr = data.data;
                setData(arr);

                if (ajaxFlag) {
                    setNav(data.totalCount, startIndex);
                }

            },
            error: function (data) {

            }
        });
    }
});