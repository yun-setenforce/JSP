<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./_header.jsp" %>
<script src="./js/admin.js"></script>
        <main>
            <aside>
                <h3>주요기능</h3>
                <ul>
                    <li class="on"><a href="/Farmstory2/admin/productList.do">상품관리</a></li>
                    <li><a href="/Farmstory2/admin/orderList.do">주문관리</a></li>
                    <li><a href="/Farmstory2/admin/userList.do">회원관리</a></li>                    
                </ul>
            </aside>
            <section id="productList">
                <nav>
                    <h3>상품목록</h3>
                </nav>

                <article>
                  <table border="0">
                      	<tr>
	                        <th><input type="checkbox" name="all"/></th>
	                        <th>사진</th>
	                        <th>상품번호</th>
	                        <th>상품명</th>
	                        <th>구분</th>
	                        <th>가격</th>
	                        <th>재고</th>
	                        <th>등록일</th>
                      	</tr>
                      	<form id="formCheck" action="/Farmstory2/admin/productList.do" method="post">
                    	<c:forEach var="product" items="${products}">
                    		<tr>
                    			<td><input type="checkbox" name="chk" value="${product.pNo}"/></td>
                    			<td>	
                   					<img src="/Farmstory2/thumb/${product.thumb1}" class="thumb" alt="${product.pName}">
                    			</td>
                    			<td>${product.pNo}</td>
                    			<td>${product.pName}</td>
                    			<td>
                    				<c:choose>
										 <c:when test="${product.type eq 1 }">
										  과일
										 </c:when>
										 <c:when test="${product.type eq 2 }">
										  야채
										 </c:when>
										 <c:when test="${product.type eq 3 }">
										 곡물
										 </c:when>
										 <c:otherwise>
										  -
										 </c:otherwise>
									</c:choose>
                    			</td>
                    			<td><strong>${product.getPriceWithComma()}</strong>원</td>
                    			<td>${product.stock}</td>
                    			<td>${product.rdate}</td>
                    		</tr>
                    	</c:forEach>
						</form>
                    </table>

                    <p>
                        <a href="#" class="deleteSelected">선택삭제</a>
                        <a href="/Farmstory2/admin/productRegister.do" class="productRegister">상품등록</a>
                    </p>
                    
                    <div class="paging">
                    	<c:if test="${pageGroupStart > 1}">
                    		 <a href="/Farmstory2/admin/productList.do?type=${type}&pg=${pageGroupStart -1}" class="prev">이전</a>
                    	</c:if>
                    	<c:forEach var="i" begin="${pageGroupStart }" end="${pageGroupEnd }">
            				<a href="/Farmstory2/admin/productList.do?type=${type}&pg=${i}" class="num ${currentPage == i?'current':'off'}">[${i}]</a>
           			 	</c:forEach>
			            <c:if test="${pageGroupEnd < lastPageNum}">
			            	<a href="/Farmstory2/admin/productList.do?type=${type}&pg=${pageGroupEnd + 1}" class="next">다음</a>
			            </c:if>
                    </div>
                </article>

                
            </section>
        </main>
<%@include file="./_footer.jsp" %>