<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<div class="hide-screen hidden"></div>
<!--顶部导航栏开始-->
<div class="header">
    <ul class="header-right">
        <li>
            <a href="login.html"><i class="fa fa-fw fa-power-off"></i> 退出</a>
        </li>
        <li>
            <span><i class="fa fa-user"></i> User</span>
        </li>
        <li>
            <span><i class="fa fa-flag"></i> 计算机学院</span>
        </li>
    </ul>
</div>
<!--顶部导航栏结束-->

<!--左导航栏开始-->
<ul class="navbar-left">
    <li class="class">
        <span><i class="fa fa-fw fa-user"></i> 班 级<i id="btn-class" class="fa fa-fw fa-caret-down"></i></span>
    </li>
    <ul class="class-flip" style="display: none;">
        <li>
            <a><i class="fa fa-fw fa-ellipsis-h"></i> 列表</a>
        </li>
        <li>
            <a><i class="fa fa-fw fa-user-plus"></i> 添加</a>
        </li>
        <li>
            <a><i class="fa fa-fw fa-user-times"></i> 删除</a>
        </li>
    </ul>
    <li class="test">
        <span><i class="fa fa-fw fa-pencil-square-o"></i> 考 试<i id="btn-test" class="fa fa-fw fa-caret-down"></i></span>
    </li>
    <ul class="test-flip" style="display: none;">
        <li>
            <a><i class="fa fa-fw fa-list-alt"></i> 列表</a>
        </li>
        <li>
            <a><i class="fa fa-fw fa-calendar-plus-o"></i> 创建</a>
        </li>
        <li>
            <a><i class="fa fa-fw fa-calendar-times-o"></i> 删除</a>
        </li>
    </ul>
    <li class="room">
        <span><i class="fa fa-fw fa-tasks"></i> 教 室<i id="btn-room" class="fa fa-fw fa-caret-down"></i></span>
    </li>
    <ul class="room-flip" style="display: none;">
        <li>
            <a><i class="fa fa-fw fa-list"></i> 列表</a>
        </li>
        <li>
            <a><i class="fa fa-fw fa-plus-square"></i> 添加</a>
        </li>
        <li>
            <a><i class="fa fa-fw fa-exclamation-triangle"></i> 删除</a>
        </li>
    </ul>
    <li class="audit">
        <i class="fa fa-fw fa-check-square"></i> 用户审核
        <span style="display: inline-block">10</span>
    </li>
    <li><i class="fa fa-fw fa-repeat"></i> 最近访问</li>
</ul>
<!--左导航栏结束-->