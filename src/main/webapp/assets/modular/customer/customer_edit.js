layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    //初始化省份
    var provinceSel = new $ax(Feng.ctxPath + "/dict/getChildDicts?parentCode=&code=Province");
    var result = provinceSel.start();
    var provinceData = result.data;
    if (provinceData) {
        $("#provinceValue").empty();
        $("#provinceValue").append("<option value=''>请选择</option>");
        $.each(provinceData, function (i, o) {
            $("#provinceValue").append("<option value='" + o.dictId + "'>" + o.name + "</option>");
        });
        form.render('select');
    }

    form.on('select(province)', function(data){
        //初始化城市
        var citySel = new $ax(Feng.ctxPath + "/dict/getRefDicts?id=" + data.value);
        var cityResult = citySel.start();
        var cityData = cityResult.data;
        if (cityData) {
            $("#cityValue").empty();
            $("#cityValue").append("<option value=''>请选择</option>");
            $.each(cityData, function (i, o) {
                $("#cityValue").append("<option value='" + o.dictId + "'>" + o.name + "</option>");
            });
        }
        form.render('select');
    });

    form.on('select(city)', function(data){
        //初始化城市
        var areaSel = new $ax(Feng.ctxPath + "/dict/getRefDicts?id=" + data.value);
        var areaResult = areaSel.start();
        var areaData = areaResult.data;
        if (areaData) {
            $("#districtValue").empty();
            $("#districtValue").append("<option value=''>请选择</option>");
            $.each(areaData, function (i, o) {
                $("#districtValue").append("<option value='" + o.dictId + "'>" + o.name + "</option>");
            });
        }
        form.render('select');
    });

    //初始化客户的详情数据
    var ajax = new $ax(Feng.ctxPath + "/customer/view/" + Feng.getUrlParam("customerId"));
    var result = ajax.start();
    form.val('customerForm',result.data);
    //设置城市值
    if (result.data.cityValue) {
        $("#cityValue").append("<option value='" + result.data.cityValue + "'>" + result.data.cityText + "</option>");
    }
    //设置区域值
    if (result.data.districtValue) {
        $("#districtValue").append("<option value='" + result.data.districtValue + "'>" + result.data.distText + "</option>");
    }
    form.render('select');

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/customer/edit", function (data) {
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