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
                <button type="button" class="btn btn-default btn-primary" onclick="geliA()">隔离</button>
            </div>

            <div class="row clearfix">
                <div class="col-md-12 column">
                    <label id="count">共0条数据</label>
                </div>
            </div>

            <div class="row clearfix">
                <div class="col-md-12 column" style="overflow:scroll">
                    <table class="table" style="font-size:5px;">
                        <thead>
                        <tr>
                            <th>name</th>
                            <th>ipv4addr</th>
                            <th style="color: red">disabled</th>
                            <th>shared_record_group</th>
                        </tr>
                        </thead>
                        <tbody id="record">
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
    var count = 0;
    var geliBody;

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
                    $("#sgw").append("<option value='" + data[i].comment + "'>" + data[i].comment + "</option>");
                }
                //这一步不要忘记 不然下拉框没有数据
                $("#sgw").selectpicker("refresh");

            }
        })
    }

    function querySgwStatus() {
        var sgw = $("#sgw").val();
        $.ajax({
            type: 'get',
            url: "/dns/a/status?sgw=" + sgw,
            success: function (data) {
                $("#count").empty();
                $("#count").append("共" + data.length + "条数据");
                $("#record").empty();
                count = data.length;
                geliBody = data.slice(0);
                for (var i = 0; i < data.length; i++) {
                    $("#record").append(
                        "<tr><td>" + data[i].name +"</td>" +
                        "<td>" + data[i].ipv4addr +"</td>" +
                        "<td style=\"color: #ff0000\">" + data[i]["disable"] +"</td>" +
                        "<td>" + data[i].shared_record_group +"</td></tr>");
                }
                alert("共查到" + data.length + "条A记录！");
            }
        })
    }

    function geliA() {
        var sgw = $("#sgw").val();
        $.ajax({
            type: "post",
            url: "/dns/a/gelihuige?disable=true&sgw=" + sgw,
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(geliBody),
            success: function (data) {
                alert("共隔离了" + data.length + "条A记录！请重新查询记录状态确认结果！");
            }
        })
    }
</script>
</body>
</html>


