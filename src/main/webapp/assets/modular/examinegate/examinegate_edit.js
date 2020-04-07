layui.use(['layer', 'form', 'admin', 'laydate', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var layer = layui.layer;

    // 渲染时间选择框
    laydate.render({
        elem: '#setDate'
    });

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    //初始化检测门的详情数据
    var ajax = new $ax(Feng.ctxPath + "/gate/view/" + Feng.getUrlParam("gateId"));
    var result = ajax.start();
    var gateSetDate = result.data.setDate;
    if (gateSetDate) {
        result.data.setDate = gateSetDate.split(" ")[0];
    }
    form.val('gateForm',result.data);

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/gate/edit", function (data) {
            Feng.success("修改成功!");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("修改失败!" + data.responseJSON.message + "!");
        });
        ajax.set(data.field);
        ajax.start();
    });
});