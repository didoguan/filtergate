layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 温度管理
     */
    var Temperature = {
        tableId: "temperatureTable",    //表格id
        condition: {
            customerName: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Temperature.initColumn = function () {
        return [[
            {field: 'temperatureId', hide: true, sort: false, title: 'id'},
            {field: 'customerName', sort: false, title: '客户名称'},
            {field: 'gateCode', sort: false, title: '检测门编码'},
            {field: 'gateName', sort: false, title: '检测门名称'},
            {field: 'tempNumber', sort: false, title: '温度'},
            {field: 'createTime', sort: false, title: '上传日期'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Temperature.search = function () {
        var queryData = {};
        queryData['customerName'] = $("#customerName").val();
        table.reload(Temperature.tableId, {where: queryData});
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Temperature.tableId,
        url: Feng.ctxPath + '/temperature/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Temperature.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Temperature.search();
    });

});
