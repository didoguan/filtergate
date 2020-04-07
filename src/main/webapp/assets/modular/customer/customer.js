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
    var Customer = {
        tableId: "customerTable",    //表格id
        condition: {
            customerName: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Customer.initColumn = function () {
        return [[
            {field: 'customerId', hide: true, sort: false, title: 'id'},
            {field: 'customerName', sort: false, title: '客户名称'},
            {field: 'contactPerson', sort: false, title: '联系人'},
            {field: 'contactNumber', sort: false, title: '联系电话'},
            {field: 'fullAddress', sort: false, title: '详细地址'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Customer.search = function () {
        var queryData = {};
        queryData['customerName'] = $("#customerName").val();
        table.reload(Customer.tableId, {where: queryData});
    };

    /**
     * 弹出添加客户
     */
    Customer.openAddCustomer = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加客户',
            content: Feng.ctxPath + '/customer/addPage',
            end: function () {
                admin.getTempData('formOk') && table.reload(Customer.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Customer.exportExcel = function () {
        var checkRows = table.checkStatus(Customer.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑客户
     *
     * @param data 点击按钮时候的行数据
     */
    Customer.onEditCustomer = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改客户',
            content: Feng.ctxPath + '/customer/editPage?customerId=' + data.customerId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Customer.tableId);
            }
        });
    };

    /**
     * 点击删除客户
     *
     * @param data 点击按钮时候的行数据
     */
    Customer.onDeleteCustomer = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/customer/remove", function () {
                Feng.success("删除成功!");
                table.reload(Customer.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("customerId", data.customerId);
            ajax.start();
        };
        Feng.confirm("是否删除客户 " + data.customerName + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Customer.tableId,
        url: Feng.ctxPath + '/customer/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Customer.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Customer.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Customer.openAddCustomer();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Customer.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Customer.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Customer.onEditCustomer(data);
        } else if (layEvent === 'delete') {
            Customer.onDeleteCustomer(data);
        }
    });
});
