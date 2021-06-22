<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
    <div class="right">
        <img class="wColck" src="${pageContext.request.contextPath }/statics/images/clock.jpg" alt=""/>
        <div class="wFont">
            <h2>${userSession.userName }</h2>
            <p>欢迎来到超市订单管理系统!</p>
            <table style="text-align: center">
            	<tr>
            		<td>班级：</td>
            		<td>软件技术4班</td>
            	</tr>
            	<tr>
            		<td>学号：</td>
            		<td>1904050443</td>
            	</tr>
            	<tr>
            		<td>姓名：</td>
            		<td>尤凯钊</td>
            	</tr>
            </table>
        </div>
    </div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
