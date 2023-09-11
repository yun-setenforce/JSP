<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="./_header.jsp" %>
 <script src="./js/admin.js"></script>
        <main>
            <aside>
                <h3>주요기능</h3>
                <ul>
                    <li><a href="/Farmstory2/admin/productList.do">상품관리</a></li>
                    <li class="on"><a href="/Farmstory2/admin/orderList.do">주문관리</a></li>
                    <li><a href="/Farmstory2/admin/userList.do">회원관리</a></li>                   
                </ul>
            </aside>
            <section id="orderList">
                <nav>
                    <h3>주문목록</h3>
                </nav>

                <article>

                    <table border="0">
                        <tr>
                            <th><input type="checkbox" name="all"/></th>
                            <th>주문번호</th>
                            <th>상품명</th>
                            <th>판매가격</th>
                            <th>수량</th>
                            <th>배송비</th>
                            <th>합계</th>
                            <th>주문자</th>
                            <th>주문일</th>
                            <th>확인</th>
                        </tr>
                        
                      	<form id="formCheck" action="/Farmstory2/admin/orderList.do" method="post">
	                        <c:forEach var="order" items="${orders}">
	                    		<tr>
	                    			<td class="chk"><input type="checkbox" name="chk" value="${order.orderNo}"/></td>
	                    			<td class="orderNo">${order.getOrderNo()}</td>
				                    <td class="">${order.getpNameWithEllipsis()}</td>                            
				                    <td class="price">${order.getOrderPriceWithComma()}원</td>
				                    <td class="count">${order.getOrderCount()}</td>
				                    <td class="delivery">${order.getOrderDeliveryWithComma()}원</td>
				                    <td class="total">${order.getOrderTotalWithComma()}원</td>
				                    <td class="orderer">${order.getOrderUserWithName()}</td>
				                    <td class="date">${order.getOrderDate()}</td>
				                    
				                    <td class="hidden orderProduct">${order.getOrderProduct()}</td>
				                    <td class="hidden thumb1">${order.getThumb1()}</td>
				                    <td class="hidden receiver">${order.getReceiver()}</td>
				                    <td class="hidden address" >${order.getAddr1()+= " " += order.getAddr2()}</td>	
				                    <td class="hidden etc" >${order.getOrderEtc()}</td>	
				                    <td class="hidden pName" >${order.getpName()}</td>
				                     
				                    <td><a href="#" class="showOrderPopup">[상세확인]</a></td>
	                    		</tr>
	                    	</c:forEach>
	                    </form>
                    </table>

                    <p>
                        <a href="#" class="deleteSelected">선택삭제</a>                      
                    </p>
                    
                    
                    <div class="paging">
                    	<c:if test="${pageGroupStart > 1}">
                    		 <a href="/Farmstory2/admin/orderList.do?pg=${pageGroupStart -1}" class="prev">이전</a>
                    	</c:if>
                    	<c:forEach var="i" begin="${pageGroupStart }" end="${pageGroupEnd }">
            				<a href="/Farmstory2/admin/orderList.do?pg=${i}" class="num ${currentPage == i?'current':'off'}">[${i}]</a>
           			 	</c:forEach>
			            <c:if test="${pageGroupEnd < lastPageNum}">
			            	<a href="/Farmstory2/admin/orderList.do?pg=${pageGroupEnd + 1}" class="next">다음</a>
			            </c:if>
                    </div>
                </article>
            </section>
        </main>

        <div id="orderPopup">
            <section>
                <nav>
                    <h1>상세주문내역</h1>
                    <button class="btnClose">닫기</button>
                </nav>

                <article>  
                    <h3>기본정보</h3>
                    <table border="0">
                        <tr>
                            <td rowspan="7" class="thumb"><img src="/Farmstory2/admin/images/sample_item1.jpg" alt="사과 500g"></td>
                            <td>상품번호</td>
                            <td class="orderProduct">1011</td>
                        </tr>
                        <tr>
                            <td>상품명</td>
                            <td class="pName">사과 500g</td>
                        </tr>
                        <tr>
                            <td>판매가격</td>
                            <td class="price">4,000원</td>
                        </tr>
                        <tr>
                            <td>수량</td>
                            <td class="count">2개</td>
                        </tr>
                        <tr>
                            <td>배송비</td>
                            <td class="delivery">3,000원</td>
                        </tr>
                        <tr>
                            <td>합계</td>
                            <td class="total">11,000원</td>
                        </tr>
                        <tr>
                            <td>주문자</td>
                            <td class="orderer">홍길동</td>
                        </tr>                        
                    </table>

                    <h3>배송지 정보</h3>
                    <table border="0">
                        <tr>
                            <td>받는분</td>
                            <td class="receiver">홍길동</td>
                        </tr>
                        <tr>
                            <td>배송지</td>
                            <td class="address">부산광역시 부산진구 대연동 120 루미너스 10층</td>
                        </tr>
                        <tr>
                            <td>기타</td>
                            <td class="etc">빠른 배송 부탁드려요.</td>
                        </tr>
                    </table>
                </article>
            </section>
        </div>
        
     
<%@include file="./_footer.jsp" %>