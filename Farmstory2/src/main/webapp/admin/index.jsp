<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./_header.jsp" %>
        <main>
            <aside>
                <h3>주요기능</h3>
                <ul>
                    <li><a href="/Farmstory2/admin/productList.do">상품관리</a></li>
                    <li><a href="/Farmstory2/admin/orderList.do">주문관리</a></li>
                    <li><a href="/Farmstory2/admin/userList.do">회원관리</a></li>                    
                </ul>
            </aside>
            <section>
                <nav>
                    <h3>관리자 메인</h3>
                </nav>

                <article>
                    <h3>
                        <a href="/Farmstory2/admin/productList.do">상품현황</a>
                        <a href="/Farmstory2/admin/productList.do" class="more">+ 더보기</a>
                    </h3>
                    <table border="0">
                        <tr>
                            <th>상품번호</th>
                            <th>상품명</th>
                            <th>구분</th>
                            <th>가격</th>
                            <th>재고</th>
                            <th>등록일</th>
                        </tr>
                        
                        <c:forEach var="product" items="${latests1}">
                    		<tr>
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
                    </table>
                </article>

                <article>
                    <h3>
                        <a href="/Farmstory2/admin/orderList.do">주문현황</a>
                        <a href="/Farmstory2/admin/orderList.do" class="more">+ 더보기</a>
                    </h3>
                    <table border="0">
                        <tr>
                            <th>주문번호</th>
                            <th>상품명</th>
                            <th>판매가격</th>
                            <th>수량</th>
                            <th>배송비</th>
                            <th>합계</th>
                            <th>주문자</th>
                            <th>주문일</th>
                        </tr>
                        <c:forEach var="order" items="${latests2}">
                    		<tr>
                    			<td class="orderNo">${order.getOrderNo()}</td>
			                    <td class="">${order.getpNameWithEllipsis()}</td>                            
			                    <td class="price">${order.getOrderPriceWithComma()}원</td>
			                    <td class="count">${order.getOrderCount()}개</td>
			                    <td class="delivery">${order.getOrderDeliveryWithComma()}원</td>
			                    <td class="total">${order.getOrderTotalWithComma()}원</td>
			                    <td class="orderer">${order.getOrderUserWithName()}</td>
			                    <td class="date">${order.getOrderDate()}</td>
                    		</tr>
                    	</c:forEach>
                    </table>
                </article>
                <article>
                    <h3>
                        <a href="/Farmstory2/admin/userList.do">회원현황</a>
                        <a href="/Farmstory2/admin/userList.do" class="more">+ 더보기</a>
                    </h3>
                    <table border="0">
                        <tr>
                            <th>회원아이디</th>
                            <th>이름</th>
                            <th>닉네임</th>
                            <th>휴대폰</th>
                            <th>이메일</th>
                            <th>등급</th>
                            <th>회원가입일</th>
                        </tr>    
                        <c:forEach var="user" items="${latests3}">
                    		<tr>
                    			<td class="uid">${user.uid}</td>
                    			<td class="name">${user.name}</td>
			                    <td class="nick">${user.nick}</td>    
			                    <td class="hp">${user.hp}</td>                        
			                    <td class="email">${user.email}</td>
			                    <td class="grade">
                    				<c:choose>
										 <c:when test="${user.role eq 'admin' }">
										  5
										 </c:when>
										 <c:when test="${user.role eq 'manager' }">
										  4
										 </c:when>
										 <c:when test="${user.role eq 'staff' }">
										 3
										 </c:when>
										 <c:when test="${user.role eq 'VIP' }">
										 2
										 </c:when>
										 <c:when test="${user.role eq 'USER' }">
										 1
										 </c:when>
										 <c:otherwise>
										  -
										 </c:otherwise>
									</c:choose>
			                    </td>
			                    <td class="regDate">${user.regDate}</td>
                    		</tr>
                    	</c:forEach>	                   
                    </table>
                </article>
            </section>
        </main>
<%@include file="./_footer.jsp" %>