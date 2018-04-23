var login = {

    login : function () {
        layui.use('form', function(){
            var form = layui.form;
            /*表单提交事件*/
            $('#login-form').submit(function () {
                var username = $("#username").val();
                var password = $("#password").val();
                console.log(username);
                console.log(password);
                $.ajax({
                    type : utilRequest.POST(),
                    url : "/tokens",
                    data : "userName=" + username + "&password=" + password + "&status=0",
                    success : function (msg) {
                        alert(JSON.stringify(msg));
                        console.log(msg);
                        location.href='management.html';
                    },
                    fail : function (msg) {
                        alert(msg);
                        console.log(msg);
                    }
                });
            });
        });
    }
}