<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./../_header.jsp" %>
<%@include file="./_asideMarket.jsp" %>
<script>
	const success =  new URL(location.href).searchParams.get('success');
	
	if(success == 0){
	} else if(success == 100){
		alert('정상적으로 주문 완료 되었습니다.');
	}
</script>
                <article class="list">
                    <nav>
                        <img src="/Farmstory2/images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                        <p>
                            HOME > 장보기 > <em>장보기</em>
                        </p>
                    </nav>

                    <!-- 내용 시작 -->
                    <p class="sort">
                        <a href="/Farmstory2/market/list.do?type=0" class="${type eq '0' ? 'on' : '' }">전체 ${type eq '0' ? '('+=total+=')' : '' } |</a>
                        <a href="/Farmstory2/market/list.do?type=1" class="${type eq '1' ? 'on' : '' }">과일 ${type eq '1' ? '('+=total+=')' : '' } |</a>
                        <a href="/Farmstory2/market/list.do?type=2" class="${type eq '2' ? 'on' : '' }">야채 ${type eq '2' ? '('+=total+=')' : '' } |</a>
                        <a href="/Farmstory2/market/list.do?type=3" class="${type eq '3' ? 'on' : '' }">곡류 ${type eq '3' ? '('+=total+=')' : '' }</a>
                    </p>
                    <table border="0">
                    	<c:forEach var="product" items="${products}">
                    		<tr>
                    			<td>	
                    				<a href="/Farmstory2/market/view.do?pNo=${product.pNo}">
                    					<img src="/Farmstory2/thumb/${product.thumb1}" alt="${product.pName}">
                    				</a>
                    			</td>
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
                            	<td><a href="/Farmstory2/market/view.do?pNo=${product.pNo}">${product.pName}</a></td>
                            	<td><strong>${product.getPriceWithComma()}</strong>원</td>
                    		</tr>
                    	</c:forEach>
                    </table>

                    <div class="paging">
                    	<c:if test="${pageGroupStart > 1}">
                    		 <a href="/Farmstory2/market/list.do?type=${type}&pg=${pageGroupStart -1}" class="prev">이전</a>
                    	</c:if>
                    	<c:forEach var="i" begin="${pageGroupStart }" end="${pageGroupEnd }">
            				<a href="/Farmstory2/market/list.do?type=${type}&pg=${i}" class="num ${currentPage == i?'current':'off'}">${i}</a>
           			 	</c:forEach>
			            <c:if test="${pageGroupEnd < lastPageNum}">
			            	<a href="/Farmstory2/market/list.do?type=${type}&pg=${pageGroupEnd + 1}" class="next">다음</a>
			            </c:if>
                    </div>

                    <!-- 내용 끝 -->

                </article>
            </section>

        </div>
        
<%@include file="./../_footer.jsp" %>