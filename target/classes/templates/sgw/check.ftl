<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

    <#include "../common/nav.ftl">

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <select id="city" class="selectpicker" onchange="selectcityChange()">
                    <option value="">选择城市</option>
                </select>
                <select id="sgw" class="selectpicker">
                    <option value="">选择sgw</option>
                </select>
                <button type="button" class="btn btn-default btn-primary" onclick="querySgwStatus()">查询</button>
            </div>

            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>
                                编号
                            </th>
                            <th>
                                产品
                            </th>
                            <th>
                                交付时间
                            </th>
                            <th>
                                状态
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                1
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                01/04/2012
                            </td>
                            <td>
                                Default
                            </td>
                        </tr>
                        <tr class="success">
                            <td>
                                1
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                01/04/2012
                            </td>
                            <td>
                                Approved
                            </td>
                        </tr>
                        <tr class="error">
                            <td>
                                2
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                02/04/2012
                            </td>
                            <td>
                                Declined
                            </td>
                        </tr>
                        <tr class="warning">
                            <td>
                                3
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                03/04/2012
                            </td>
                            <td>
                                Pending
                            </td>
                        </tr>
                        <tr class="info">
                            <td>
                                4
                            </td>
                            <td>
                                TB - Monthly
                            </td>
                            <td>
                                04/04/2012
                            </td>
                            <td>
                                Call in to confirm
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    $(function () {
        //初始化下拉框
        //动态加载
        $.ajax({
            type: 'get',
            url: '/dns/public/citys',
            dataType: "json",
            success: function (data) {
                //拼接下拉框
                for (var i = 0; i < data.length; i++) {
                    $("#city").append("<option value='" + data[i] + "'>" + data[i] + "</option>");
                }
                //这一步不要忘记 不然下拉框没有数据
                $("#city").selectpicker("refresh");
            }
        });
    });

    function selectcityChange() {
        // 选中性别
        var city = $("#city").val();
        //console.log(sex);
        $.ajax({
            type: 'get',
            url: "/dns/public/sgw?city=" + city,
            success: function (data) {
                $("#sgw").empty();
                for (var i = 0; i < data.length; i++) {
                    $("#sgw").append("<option value='" + data[i] + "'>" + data[i] + "</option>");
                }
                //这一步不要忘记 不然下拉框没有数据
                $("#sgw").selectpicker("refresh");
            }
        })
    }

    function querySgwStatus() {
        var sgw = $("#sgw").val();
        console.log(sgw);
        $.ajax({
            type: 'get',
            url: "/dns/sgw/status?sgw=" + sgw,
            success: function (data) {
                console.log(data);
            }
        })
    }
</script>
</body>
</html>


