var register = {

    URL : {

        /**
         * 用户注册接口
         * @returns {string}
         */
        registerUrl : function () {
            return "/user";
        }
    },

    /**
     * 注册 TODO 后期可以添加各种校验方式
     */
    register : function () {
        layui.use(['form', 'layer'], function(){
            var form = layui.form
                ,layer = layui.layer

            /**
             * 提交注册
             */
            $('#register-form').submit(function () {
                var username = $("#username").val();             //用户名
                var password = $("#password").val();             //密码
                var realname = $("#realname").val();             //姓名
                var sex = $('input[name="sex"]:checked').val();  //性别
                var des = $("#des").val();                       //个人简介
                /*console.log("username = " + username +
                    ", password = " + password +
                    ", realname = " + realname +
                    ", sex = " + sex + ", des = " + des);*/
                $.ajax({  //发起注册请求
                    type : utilRequest.POST(),
                    url : register.URL.registerUrl(),
                    data : "username=" + username + "&password=" + password + "&realname=" + realname + "&sex=" + sex + "&des=" + des,
                    success : function (msg) {
                        alert("恭喜你注册成功,请登录");
                        //alert(JSON.stringify(msg));
                        var code = msg.code;
                        //alert(status);
                        if (code === 0) {               //注册成功,跳转到登陆界面
                            location.href='login.html';
                        } else {                        //注册失败,显示错误信息并继续跳回注册界面
                            alert(msg.info);            //显示注册失败原因
                            top.location.href='register.html';
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