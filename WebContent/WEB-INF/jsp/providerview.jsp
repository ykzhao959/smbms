<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
 <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户信息查看页面</span>
        </div>
        <div class="providerView">
            <p><strong>供应商编号：</strong><span>${provider.proCode }</span></p>
            <p><strong>公司名称：</strong><span>${provider.proName }</span></p>
            <p><strong>负责人：</strong>
            	<span>
            		${provider.proContact} 
				</span>
			</p>
            <p><strong>电话：</strong><span>${provider.proPhone }</span></p>
            <p><strong>创建日期</strong><span>${provider.creationDate }</span></p>
            <p><strong>公司地址：</strong><span>${provider.proAddress }</span></p>
			<div class="providerAddBtn">
            	<input type="button" id="back" name="back" value="返回" >
            </div>
        </div>
    </div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/providerview.js"></script>
