var login = {

    URL : {
        /**
         * login url
         * @returns {string}
         */
        login : function () {
            return "/token"
        }
    },

    /**
     * login 登陆
     */
    login : function () {
        layui.use(['layer', 'form'], function(){
            var form = layui.form,
                layer = layui.layer;
            /**表单提交事件*/
            $('#login-form').submit(function () {
                //layer.msg("test");
                var username = $("#username").val();  //获得账号
                var password = $("#password").val();  //密码
                var status = $('input[name="identity"]:checked').val(); //身份
                $.ajax({  //发起登陆请求
                    type : utilRequest.POST(),
                    url : login.URL.login(),
                    data : "username=" + username + "&password=" + password + "&status=" + status,
                    success : function (msg) {
                        //alert(JSON.stringify(msg));
                        var code = msg.code;
                        //alert(status);
                        if (code === 0) {               //登陆成功,跳转到后台管理界面
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
            });
        });
    }
}