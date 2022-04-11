$(function () {

    //登录按钮点击跳转事件
    $(".loginbtn").click(function () {
        location.href = "../login/login.html"
    })

    $(".logo").click(function () {
        location.href = "../index.html";
    })

    //监听滚轮上下滚动事件
    $(document).on("mousewheel DOMMouseScroll", function (event) {
        // console.log(event.pageX);
        // console.log(event);
        var delta = (event.originalEvent.wheelDelta && (event.originalEvent.wheelDelta > 0 ? 1 : -1)) ||  // chrome & ie
            (event.originalEvent.detail && (event.originalEvent.detail > 0 ? -1 : 1));              // firefox

        if (delta > 0) {
            // 向上滚
            // console.log("up+++++");
            $(".tabbar").css({
                "top": "0px"
            })
        } else if (delta < 0) {
            // 向下滚
            // console.log("down+++++");
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

    //登录后鼠标经过显示下拉事件
    $(".hello_name, .loginInfo, .down").hover(function () {
        $(".loginInfo").stop().slideToggle(150);
    })
})