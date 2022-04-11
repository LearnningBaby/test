$(function () {



    //登录按钮点击跳转事件
    $(".loginbtn").click(function () {
        location.href = "../login/login.html"
    })

    $(".boxbtn_first").click(function () {
        location.href = "../login/student_login.html"
    })

    $(".box_download").click(function () {
        location.href = "../login/teach_login.html"
    })

    //监听滚轮上下滚动事件
    $(document).on("mousewheel DOMMouseScroll", function (event) {
        var delta = (event.originalEvent.wheelDelta && (event.originalEvent.wheelDelta > 0 ? 1 : -1)) ||  // chrome & ie
            (event.originalEvent.detail && (event.originalEvent.detail > 0 ? -1 : 1));              // firefox

        if (delta > 0) {
            // 向上滚
            $(".tabbar").css({
                "top": "0px"
            })
        } else if (delta < 0) {
            // 向下滚
            $(".tabbar").css({
                "top": "-72px"
            })
        }
    })

    //监听滚动条高度
    $(window).scroll(function () {
        if ($(document).scrollTop() >= 357) {
            $(".tabbar").addClass("tabbar_jquery")
            $(".logo").addClass("logo_jquery")
            $(".down").addClass("down_jquery")
            $(".tabsA").css({
                "color": "#000"
            })
            $(".hellobox").css({
                "color": "#000"
            })
            $(".loginbtn").css({
                "color": "#000",
                "border": "1px solid rgba(0, 0, 0, 0.8)"
            })
        } else {
            $(".tabbar").removeClass("tabbar_jquery")
            $(".logo").removeClass("logo_jquery")
            $(".down").removeClass("down_jquery")
            $(".tabsA").css({
                "color": "#ffffff"
            })
            $(".loginbtn").css({
                "color": "#ffffff",
                "border": "1px solid #fefefe"
            })
            $(".hellobox").css({
                "color": "#fff"
            })
        }
    })


    //左右滑动
    // console.log($(".bigBox")[0].childNodes[3]);
    $("#second_box").mouseover(function () {
        $(this).attr("id", "hover2")
        $(this).siblings().removeAttr("id")
    })
    $("#second_box").mouseout(function () {
        $(this).removeAttr("id")
        $(this).siblings().attr("id", "hover1")
    })
})
