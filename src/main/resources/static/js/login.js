var login = {

    URL : {
        /**
         * login url
         * @returns {string}
         */
        loginUrl : function () {
            return "/token"
        }
    },

    /*init : function () {
        login.login();
        login.toRegister();
    },*/

    /**
     * login 登陆 TODO 为空时做拦截，不发送请求
     */
    login : function () {
        layui.use(['layer', 'form'], function(){
            var form = layui.form,
                layer = layui.layer;
            /** 表单提交事件 */
            var toLogin = function () {
                //alert("login")
                //layer.msg("test");
                var username = $("#username").val();  //获得账号
                var password = $("#password").val();  //密码
                var status = $('input[name="identity"]:checked').val(); //身份
                /*if (username == null || password == null || status == null) {
                    return false;
                }*/
                $.ajax({  //发起登陆请求
                    type : utilRequest.POST(),
                    url : login.URL.loginUrl(),
                    data : "username=" + username + "&password=" + password + "&status=" + status,
                    success : function (msg) {
                        //alert(JSON.stringify(msg));
                        var code = msg.code;
                        //alert(status);
                        if (code === 0) {               //登陆成功,跳转到后台管理界面
                            $.cookie('token', msg.data.token, { expires: 7 });  //将token存储到cookie 默认存储7天
                            if (msg.data.status ==0) {  //管理员身份
                                location.href='management.html';
                            } else {                    //游客或者学生身份
                                location.href='problem.html';
                            }
                        } else {                        //登陆失败,显示错误信息并继续跳回登陆界面
                            alert(msg.info);            //显示登陆失败原因
                            location.href='login.html';
                        }
                    },
                    fail : function (msg) {
                        alert("request failure!");
                    }
                });
            };
            $("#to-login-btn").click(toLogin);
        });
    },

    /**
     * 跳转到注册界面
     */
    toRegister : function () {

        /** 注册按钮点击事件 */
        var clickToRegister = function () {
            //alert("register");
            location.href='/register.html';
        }
        /** 绑定注册按钮点击事件 */
        $("#to-register-btn").click(clickToRegister);
    }
}