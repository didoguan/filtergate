layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    var EquipmentEdit = {};

    //初始化设备的详情数据
    var ajax = new $ax(Feng.ctxPath + "/equipment/view/" + Feng.getUrlParam("equipmentId"));
    var result = ajax.start();
    form.val('equipmentForm',result.data);

    EquipmentEdit.selCustomer = function () {
        parent.layui.admin.open({
            type: 2,
            title: '选择客户',
            area : ['850px' , '450px'],
            content: Feng.ctxPath + '/customer/selCustomerPage',
            btn: ['确定','关闭'],
            yes: function(index){
                var frameId = "layui-layer-iframe" + index;
                parent.mainCallIframe(frameId, "CustomerSel.customerCallback");
                var row = parent.MainCallBackData_;
                if (row) {
                    $("#customerName").val(row.customerName);
                    $("#customerId").val(row.customerId);
                } else {
                    layer.msg("请选择一个值！");
                }
            },
            cancel: function(){

            }
        });
    };

    $('#customerName').click(function () {
        EquipmentEdit.selCustomer();
    });

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/equipment/save", function (data) {
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