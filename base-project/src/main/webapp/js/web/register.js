/**
 * Created by Admin on 2017/1/12.
 */
$(function () {
    $('.register-btn').on('click', function () {
        var username = $('#username').val();
        var psd = $('#password').val();
        var secPsd = $('#checkpassword').val();

        if (username == "" || psd == "" || secPsd == "") {
            alert("用户名或密码不能为空!");
            return;
        }
        if (psd != secPsd) {
           alert("两次输入的密码不一致！");
           return;
        }

        $.ajax({
            url:"/ydapi-project-col/api/userCol/register.do",
            method: "POST",
            async: true,
            data: {
                mobile: username,
                password: md5(psd)
            },
            success: function (res) {
                console.log(res);
                if (res.data.errorCode == 200) {
                    alert("注册成功，请登录！");
                    window.location.href = "login.do";
                }else {
                    alert("注册失败！");
                }

            },
            error: function (err) {
                console.log(err);
            }
        })

    });
});