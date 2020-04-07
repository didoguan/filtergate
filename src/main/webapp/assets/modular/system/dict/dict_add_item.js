var DictInfo = {
    data: {
        refId: "",
        refName: ""
    }
}

layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    // 点击关联字典时
    $('#refName').click(function () {
        var formName = encodeURIComponent("parent.DictInfo.data.refName");
        var formId = encodeURIComponent("parent.DictInfo.data.refId");
        var treeUrl = encodeURIComponent(Feng.ctxPath + "/dict/reftree");

        layer.open({
            type: 2,
            title: '关联字典',
            area: ['300px', '200px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                $("#refId").val(DictInfo.data.refId);
                $("#refName").val(DictInfo.data.refName);
            }
        });
    });

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/dict/add", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});