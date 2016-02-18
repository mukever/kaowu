<%@page import="javax.websocket.Session"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.awt.Point"%>
<%@page import="com.dao.kaowu.sau.www.SignDAO"%>
<%@page import="com.bean.kaowu.sau.www.SignBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>沈阳航空航天大学考务系统</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script src="js/jquery-2.1.4.min.js"></script>
<script src="css/jquery-ui.min.css"></script>
<script src="js/highcharts.js"></script>
<script src="js/jquery.json-2.2.js"></script>
<script src="js/dark-unica.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
#reporting {
	position: absolute; display：none;
	padding: 5px;
	border: blue 3px solid;
	background-color: #ffffff;
	color: "#ff0000";
	fontSize: "5px";
	fontWeight: "blod";
	fontFamily: "Courir new"
}
</style>
<style type="text/css">
a:link {
	text-decoration: none;
}

a:hover {
	text-decoration: none;
}

a:visited {
	text-decoration: none;
}
</style>
</head>
<body>

	<div class="header"
		style="width: 1152px; height: 100px; margin: 10px auto">
		<img src="imgs/logo.gif" class="logo">
		<h1>考 务 系 统</h1>
	</div>
	<%=request.getSession().getAttribute("id") %>Hello
	<br>
	<!-- 表单数据显示 -->
	<!--
	<table  width="300" border="1" cellspacing="0"  id = "datatable">
	
		<tr>
			<td>用户名</td>  
			<td>最后访问时间</td>  
			<td>访问次数</td>  
		 </tr> 
	 -->
	<%	
		Map<String,String> map = new HashMap();
		StringBuffer USER = new StringBuffer();
		StringBuffer DATA= new StringBuffer();
		StringBuffer FRA= new StringBuffer();
		USER.append("[");
		DATA.append("[");
		FRA.append("[");
		List<SignBean>  list = SignDAO.getList();
		//System.out.print(request.getSession().getAttribute("id")+"查看用户访问信息");
		if(list!= null){
		for(SignBean g:list){%>
	<!-- 
			<tr>
				<th><%=g.getUsername() %></th>  
				<td><%=g.getDate()%></td>
				<td><%=g.getFrequency()%></td>
			 </tr> 
			 -->
	<%
			map.put(g.getUsername(), g.getDate());
			USER.append("'"+g.getUsername()+"'"+",");
			DATA.append(g.getFrequency()+",");
			FRA.append(g.getDate().substring(0, 10)+",");
			}
		USER.append("]");
		DATA.append("]");
		FRA.append("]");
		//request.getSession().setAttribute("map", map);
		//System.out.print(FRA.toString());
		}
		%>

	</table>
	<br>
	<!-- 图标数据显示测试 -->

	<div id="container3" style="width: 800px; height: 400px"></div>

	<a href="index.jsp">返回首页</a>

</body>



<script type="text/javascript">
	$(function () {                                                               
    $('#container3').highcharts({                                          
        chart: {       
        	margin: 75,
            options3d: {
                enabled: true,
                alpha: 8,
                beta: 25,
                depth: 70
            },
        	backgroundColor: 'rgba(0,0,0,0)',
        },                                                                
        title: {
            text: '用户访问记录',      					//指定图表标题
        	style: {                      // 文字内容相关样式
                color: "#ff0000",
                fontSize: "12px",
                fontWeight: "blod",
                fontFamily: "Courir new",
            },
        },                                                               
        xAxis: {  
       
        	categories: <%=USER.toString()%>,
        	 
        },  
        yAxis: {
        	title: {
                text: '用户访问次数',
                style: {                      // 文字内容相关样式
                    color: "#ff0000",
                    fontSize: "12px",
                    fontWeight: "blod",
                    fontFamily: "Courir new",
                },
                fontSize: "14px",//指定y轴的标题
                fontFamily: "Courir new"
            },
            style: {                      // 文字内容相关样式
                color: "#ff0000",
                fontSize: "12px",
                fontWeight: "blod",
                fontFamily: "Courir new",
            },
        	//name : '用户访问次数' ;
            //opposite: true,
        },
        credits:{
            //enabled:false, // 禁用版权信息
            text:'沈阳航空航天大学',               // 显示的文字
        	href:'http://www.sau.edu.cn/',   // 链接地址
       },
        tooltip: {
            backgroundColor: '#6E8B3D',   // 背景颜色
            useHTML: true,
            //borderColor: 'black',         // 边框颜色
            borderRadius: 10,             // 边框圆角
            borderWidth: 1,               // 边框宽度
            //shadow: ture,                 // 是否显示阴影
            animation: true,               // 是否启用动画效果
            style: {                      // 文字内容相关样式
                //color: "#ff0000",
                fontSize: "12px",
                fontWeight: "blod",
                fontFamily: "Courir new"
            },                                                       
            formatter: function() {
            	
            	var o;
            	$.ajax({
            		url : 'Sign_getdata.action',
            		type : 'post',
            		async: false,//使用同步的方式,true为异步方式
            		data : {username:this.x},//这里使用json对象
            		success : function(data){
            		    o = JSON.parse(data);
            			
            		},
            		fail:function(){
            			//code here...
            		}
            		});
            	return this.x+  '<br>访问次数:' + this.y + '<br>最后访问时间：'+o.date;
            	
            }                                                             
        },       
           
        series: [{                                                        
            type: 'column',                                               
            name: '用户访问时间',                                                 
            data:   <%=DATA.toString()%>                               
        }, {                                                              
            type: 'spline',                                               
            name: '用户访问次数',                                              
            data: <%=DATA.toString()%> ,                              
            marker: {                                                     
            	lineWidth: 2,                                               
            	lineColor: Highcharts.getOptions().colors[3],               
            	fillColor: 'black'                                          
            }                                                             
        }                                                           
        ]                                                                
    });                                                                   
});                 
	
	
	
		
	</script>

</html>