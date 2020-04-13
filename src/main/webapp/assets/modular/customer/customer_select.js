layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 客户管理
     */
    var CustomerSel = {
        tableId: "customerSelTable",    //表格id
        condition: {
            customerName: ""
        }
    };

    /**
     * 初始化表格的列
     */
    CustomerSel.initColumn = function () {
        return [[
            {field: 'customerId', hide: true, sort: false, title: 'id'},
            {field: 'customerName', sort: false, title: '客户名称'},
            {field: 'fullAddress', sort: false, title: '详细地址'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    CustomerSel.search = function () {
        var queryData = {};
        queryData['customerName'] = $("#customerName").val();
        table.reload(CustomerSel.tableId, {where: queryData});
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + CustomerSel.tableId,
        url: Feng.ctxPath + '/customer/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: CustomerSel.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        CustomerSel.search();
    });

});
