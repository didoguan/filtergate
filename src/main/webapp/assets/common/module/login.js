var Login = {
    login_ : function () {
        var encrypt = new JSEncrypt();
        encrypt.setPublicKey($("#publicKey").val());

        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            type: "post",
            dataType: "json",
            url: Feng.ctxPath + "",
            data: {"username":encrypt.encrypt($("#username").val()), "password":encrypt.encrypt($("#password").val())},
            success: function (result) {
                if (result.code == 200) {
                    window.location.href = Feng.ctxPath + "/";
                } else if (result.code == 201){
                    Feng.error("服务器内部错误！");
                } else {
                    Feng.error("用户名或密码有误！");
                }
            },
            error: function (e) {
                console.log(e.responseText);
            }
        });
    }
};
$(function () {
    $("#submit").bind("click", function () {
        Login.login_();
    });
});