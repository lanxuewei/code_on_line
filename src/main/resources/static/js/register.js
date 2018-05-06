var register = {

    URL : {

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
                console.log("username = " + username +
                    ", password = " + password +
                    ", realname = " + realname +
                    ", sex = " + sex + ", des = " + des);
                alert(sex)
                alert(des);
            });

        });
    }
}