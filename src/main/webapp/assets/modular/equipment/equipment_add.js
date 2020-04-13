layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    var EquipmentAdd = {};

    EquipmentAdd.selCustomer = function () {
        parent.layui.admin.open({
            type: 2,
            title: '选择客户',
            area : ['850px' , '450px'],
            content: Feng.ctxPath + '/customer/selCustomerPage',
            end: function () {

            }
        });
    };

    $('#customerName').click(function () {
        EquipmentAdd.selCustomer();
    });

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/equipment/save", function (data) {
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