layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 检测门管理
     */
    var Gate = {
        tableId: "gateTable",    //表格id
        condition: {
            customerName: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Gate.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'gateId', hide: true, sort: false, title: 'id'},
            {field: 'gateName', sort: false, title: '检测门名称'},
            {field: 'gateCode', sort: false, title: '检测门编码'},
            {field: 'customerName', sort: false, title: '客户名称'},
            {field: 'setAddress', sort: false, title: '安装地址'},
            {field: 'setDate', sort: false, title: '安装日期', templet:'<div>{{layui.util.toDateString(d.setDate, "yyyy-MM-dd")}}</div>'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Gate.search = function () {
        var queryData = {};
        queryData['customerName'] = $("#customerName").val();
        table.reload(Gate.tableId, {where: queryData});
    };

    /**
     * 弹出添加检测门
     */
    Gate.openAddGate = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加检测门',
            content: Feng.ctxPath + '/gate/addPage',
            end: function () {
                admin.getTempData('formOk') && table.reload(Gate.tableId);
            }
        });
    };

    /**
     * 点击编辑客户
     *
     * @param data 点击按钮时候的行数据
     */
    Gate.onEditGate = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改检测门',
            content: Feng.ctxPath + '/gate/editPage?gateId=' + data.gateId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Gate.tableId);
            }
        });
    };

    /**
     * 点击删除客户
     *
     * @param data 点击按钮时候的行数据
     */
    Gate.onDeleteGate = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/gate/remove", function () {
                Feng.success("删除成功!");
                table.reload(Gate.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("gateId", data.gateId);
            ajax.start();
        };
        Feng.confirm("是否删除检测门 " + data.gateName + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Gate.tableId,
        url: Feng.ctxPath + '/gate/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Gate.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Gate.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Gate.openAddGate();
    });

    // 工具条点击事件
    table.on('tool(' + Gate.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Gate.onEditGate(data);
        } else if (layEvent === 'delete') {
            Gate.onDeleteGate(data);
        }
    });
});
