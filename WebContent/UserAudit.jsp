<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=Edge">
    <title>用户审核</title>
    <link href="bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/navbarmould.css" rel="stylesheet">
    <link href="css/public.css" rel="stylesheet">
</head>
<body>
<%@ include file="cache.jsp" %>
<!--内容区开始-->
<div class="page-wrapper">
    <div class="ask hidden">
        <i class="fa fa-fw fa-warning fa-3x"></i>
        <span class="askone">确定这样做？</span>
        <span class="asktwo">此操作不可逆！</span>
        <hr style="margin: 0;position:relative; top: 30px">
        <button class="table-btn-success ask-btn-one">确&nbsp&nbsp&nbsp定</button>
        <button class="table-btn-danger ask-btn-two">取&nbsp&nbsp&nbsp消</button>
    </div>
    <div class="show-position">
        <span><i class="fa fa-fw fa-home"></i> Home</span>
        <span><i class="fa fa-fw fa-angle-right"></i> 用户审核</span>
    </div>
    <div class="content">
        <hr style="width: 100%;border: 0;border-top: 2px solid #eee;border-bottom: 2px solid #fff;border-top-color: #dadada">
        <div class="user-info">
            <div class="table-bordered" style="text-align: center;height: 42px;">
                <button class="btn-change-table table-btn-danger" id="no_handle"><i class="fa fa-fw fa-remove"></i>未处理</button>
                <button class="btn-change-table table-btn-success" id="_handle"><i class="fa fa-fw fa-check-square"></i>已处理</button>
            </div>
            <i class="status"></i>
            <br>
            <div class="table-title">
                <span class="table-title-icon"><i class="fa fa-fw fa-flag"></i></span>
                <span>计算机学院</span>
                <span>监考人员注册信息</span>
            </div>
            <table class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                    <th>姓 名</th>
                    <th>年 龄</th>
                    <th>职 工 编 号</th>
                    <th>状 态</th>
                    <th class="col-md-3">操 作</th>
                </tr>
                </thead>
                <tbody class="no-handle">
                </tbody>
                <tbody class="handle hidden">
                </tbody>
            </table>
            <ul class="pagination">
                <li class="disabled pe"><a href="#">&laquo;</a></li>
                <li class="active"><a href="#">1</a></li>
                <li class="ne"><a href="#">&raquo;</a></li>
            </ul>
        </div>
    </div>
</div>
<!--内容区结束-->
</body>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/navbarmould.js"></script>
<script src="js/user-audit.js"></script>
</html>
