layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 设备管理
     */
    var Equipment = {
        tableId: "equipmentTable",    //表格id
        condition: {
            equipmentName: ""
        }
    };

    /**
     * 初始化表格的列
     */
    Equipment.initColumn = function () {
        return [[
            {field: 'equipmentId', hide: true, sort: false, title: 'id'},
            {field: 'equipmentName', sort: false, title: '设备名称'},
            {field: 'uniqueNo', sort: false, title: '唯一码'},
            {field: 'serialNo', sort: false, title: '序列号'},
            {field: 'equipmentType', sort: false, title: '设备类型'},
            {field: 'status', sort: false, title: '状态'},
            {field: 'customerName', sort: false, title: '客户名称'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Equipment.search = function () {
        var queryData = {};
        queryData['equipmentName'] = $("#equipmentName").val();
        table.reload(Equipment.tableId, {where: queryData});
    };

    /**
     * 弹出添加设备
     */
    Equipment.openAddEquipment = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加设备',
            content: Feng.ctxPath + '/equipment/addPage',
            end: function () {
                admin.getTempData('formOk') && table.reload(Equipment.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    Equipment.exportExcel = function () {
        var checkRows = table.checkStatus(Equipment.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑设备
     *
     * @param data 点击按钮时候的行数据
     */
    Equipment.onEditEquipment = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改设备',
            content: Feng.ctxPath + '/equipment/editPage?equipmentId=' + data.equipmentId,
            end: function () {
                admin.getTempData('formOk') && table.reload(Equipment.tableId);
            }
        });
    };

    /**
     * 点击删除设备
     *
     * @param data 点击按钮时候的行数据
     */
    Equipment.onDeleteEquipment = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/equipment/remove", function () {
                Feng.success("删除成功!");
                table.reload(Equipment.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("equipmentId", data.equipmentId);
            ajax.start();
        };
        Feng.confirm("是否删除设备 " + data.equipmentName + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Equipment.tableId,
        url: Feng.ctxPath + '/equipment/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Equipment.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Equipment.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Equipment.openAddEquipment();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Equipment.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Equipment.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Equipment.onEditEquipment(data);
        } else if (layEvent === 'delete') {
            Equipment.onDeleteEquipment(data);
        }
    });
});
