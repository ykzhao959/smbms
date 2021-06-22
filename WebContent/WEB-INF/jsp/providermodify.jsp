<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 供应商修改页面</span>
        </div>
        <div class="providerAdd">
        <form id="providerForm" name="providerForm" method="post" action="${pageContext.request.contextPath }/user/providermodifysave.html">
			<input type="hidden" name="id" value="${provider.id }"/>
			 <div>
                    <label for="proCode">供应商编码：</label>
                    <input type="text" name="proCode" id="proCode" value="${provider.proCode }"> 
					<font color="red"></font>
             </div>
			 <div>
                    <label for="proName">公司名称：</label>
                    <input type="text" name="proName" id="proName" value="${provider.proName }"> 
					<font color="red"></font>
             </div>
             <div>
                    <label for="proContact">负责人：</label>
                    <input type="text" name="proContact" id="proContact" value="${provider.proContact }"> 
					<font color="red"></font>
                </div>
             <div>
                    <label for="proPhone">电话：</label>
					 <input type="text" name="proPhone" id="proPhone" value="${provider.proPhone }"> 
					<font color="red"></font>
                </div>
			 <div>
                    <label for="creationDate">创建日期：</label>
                    <input type="text" Class="Wdate" id="creationDate" name="creationDate" value="${provider.creationDate }"
					readonly="readonly" onclick="WdatePicker();">
					<font color="red"></font>
                </div>
                <div>
                    <label for="proAddress">公司地址：</label>
                    <input type="text" name="proAddress" id="proAddress" value="${provider.proAddress }">
                </div>
				
			 <div class="providerAddBtn">
                    <input type="submit" name="save" id="save" value="保存" />
                    <input type="button" id="back" name="back" value="返回"/>
                </div>
            </form>
        </div>
    </div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/providermodify.js"></script>
