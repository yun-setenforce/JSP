<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./../_header.jsp" %>
<%@include file="./_asideMarket.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/Farmstory2/js/zipcode.js"></script>
<script>
	$(function(){

		$('#btnBuy').click(function(e){
			e.preventDefault();
			$('#formOrder').submit();
		});
		$('#btnShopping').click(function(e){
			e.preventDefault();
			
			if(confirm("주문을 취소하시겠습니까?")){
				location.href ='/Farmstory2/market/view.do?pNo=${pNo}';
				return;
			}
		});
		
	});

</script>
                <article class="order">
                    <nav>
                        <img src="/Farmstory2/images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                        <p>
                            HOME > 장보기 > <em>장보기</em>
                        </p>
                    </nav>

                    <!-- 내용 시작 -->
                    <h3>주문상품 확인</h3>
                    <div class="info">
                        <img src="/Farmstory2/thumb/${thumb2}" alt="${pName}">

                        <table border="0">                            
                            <tr>
                                <td>상품명</td>
                                <td>${pName}</td>
                            </tr>
                            <tr>
                                <td>상품코드</td>
                                <td>${pNo}</td>
                            </tr>
                            <tr>
                                <td>배송비</td>
		                        <td id="delivery">
		                        	<c:if test="${delivery > 0 }">
		                            <span>${deliveryWithComma}원</span>
		                            </c:if>
		                        	<c:if test="${delivery == 0 }">
		                            <span>배송비 무료</span>
		                            </c:if>
		                            <em>3만원 이상 무료배송</em>
		                        </td>
                            </tr>
                            <tr>
                                <td>판매가격</td>
                                <td>${priceWithComma}원</td>
                            </tr>
                            <tr>
                                <td>구매수량</td>
                                <td class="count">${count}개</td>
                            </tr>
                            <tr>
                                <td>합계</td>
                                <td class="total">${finalPriceWithComma}원</td>
                            </tr>
                        </table>
                    </div>
                    <h3>주문정보 입력</h3>
                    <div class="shipping">
	            	<form id="formOrder" action="/Farmstory2/market/orderInsert.do" method="post">
						<input type="hidden" name="pNo" value="${pNo}">
						<input type="hidden" name="uid" value="${sessUser.uid}">
						<input type="hidden" name="delivery" value="${delivery}">
						<input type="hidden" name="price" value="${price}">
	                	<input type="hidden" name="count"    value="${count}">
	                	<input type="hidden" name="finalPrice"    value="${finalPrice}"> 
                    
                        <table>
                            <tr>
                                <td>받는분</td>
                                <td><input type="text" name="receiver" value="${sessUser.name}"></td>
                            </tr>
                            <tr>
                                <td>휴대폰</td>
                                <td><input type="text" name="hp" value="${sessUser.hp}"></td>
                            </tr>
                            <tr>
                                <td>배송주소</td>
                                <td>
                                    <input type="text" name="zip" value="${sessUser.zip}" readonly>
                                    <button type="button" id="btnZip" onclick="zipcode()">우편번호 검색</button>
                                    <input type="text" name="addr1" placeholder="기본주소 검색" value="${sessUser.addr1}">
                                    <input type="text" name="addr2" placeholder="상세주소 입력" value="${sessUser.addr2}">
                                </td>
                            </tr>
                            <tr>
                                <td>기타</td>
                                <td>
                                    <textarea name="etc"></textarea>
                                </td>
                            </tr>
                        </table>
                    </form>
                    </div>

                    <p>
                        <a href="#" id="btnBuy"><img src="/Farmstory2/images/market_btn_buy.gif" alt="구매하기"></a>
                        <a href="#" id="btnShopping"><img src="/Farmstory2/images/market_btn_shopping.gif"></a>
                    </p>
                    <!-- 내용 끝 -->
                    
                </article>
            </section>

<%@include file="./../_footer.jsp" %>
