<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="./_header.jsp" %>
<script src="./js/admin.js"></script>
        <main>
            <aside>
                <h3>주요기능</h3>
                <ul>
                    <li><a href="/Farmstory2/admin/productList.do">상품관리</a></li>
                    <li><a href="/Farmstory2/admin/orderList.do">주문관리</a></li>
                    <li class="on"><a href="/Farmstory2/admin/userList.do">회원관리</a></li>                     
                </ul>
            </aside>
            <section id="orderList">
                <nav>
                    <h3>회원목록</h3>
                </nav>

                <article>

                    <table border="0">
                        <tr>
                            <th><input type="checkbox" name="all"/></th>
                            <th>아이디</th>
                            <th>이름</th>
                            <th>별명</th>
                            <th>이메일</th>
                            <th>휴대폰</th>
                            <th>등급</th>
                            <th>가입일</th>
                            <th>확인</th>
                        </tr>
                      	<form id="formCheck" action="/Farmstory2/admin/userList.do" method="post">      
	                        <c:forEach var="user" items="${users}">
	                    		<tr>
	                    			<td class="chk"><input type="checkbox" name="chk" value="${user.uid}"/></td>
	                    			<td class="uid">${user.uid}</td>
	                    			<td class="name">${user.name}</td>
				                    <td class="nick">${user.nick}</td>                            
				                    <td class="email">${user.email}</td>
				                    <td class="hp">${user.hp}</td>
				                    <td class="grade">
		                                <select name="grade" id="selectRole">
		                                    <option ${user.role eq 'USER'? 'selected="selected"' :''} value="USER">1</option>
		                                    <option ${user.role eq 'VIP'? 'selected="selected"' :''} value="VIP">2</option>
		                                    <option ${user.role eq 'staff'? 'selected="selected"' :''} value="staff">3</option>
		                                    <option ${user.role eq 'manager'? 'selected="selected"' :''} value="manager">4</option>
		                                    <option ${user.role eq 'admin'? 'selected="selected"' :''} value="admin">5</option>
		                                </select>
				                    </td>
				                    <td class="regDate">${user.regDate}</td>
				                    
				                    <td class="hidden role">${user.role}</td>
				                    <td class="hidden zip">${user.zip}</td>
				                    <td class="hidden addr1">${user.addr1}</td>
				                    <td class="hidden addr2">${user.addr2}</td>
				                    <td class="hidden regip">${user.regip}</td>
				   
				                    <td><a href="#" class="showUserPopup">[상세확인]</a></td>
	                    		</tr>
	                    	</c:forEach>	
                        </form>
                    </table>
					<p>
						<span>회원 등급 : 1-USER / 2-VIP / 3-staff / 4-manager / 5-admin</span>
					</p>
                    <p>
                        <a href="#" class="updateSelected">[선택 일괄 수정]</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;   
                        <a href="#" class="deleteSelected">[선택 일괄 삭제]</a>                    
                    </p>
                    
                    <div class="paging">
                    	<c:if test="${pageGroupStart > 1}">
                    		 <a href="/Farmstory2/admin/userList.do?pg=${pageGroupStart -1}" class="prev">이전</a>
                    	</c:if>
                    	<c:forEach var="i" begin="${pageGroupStart }" end="${pageGroupEnd }">
            				<a href="/Farmstory2/admin/userList.do?pg=${i}" class="num ${currentPage == i?'current':'off'}">[${i}]</a>
           			 	</c:forEach>
			            <c:if test="${pageGroupEnd < lastPageNum}">
			            	<a href="/Farmstory2/admin/userList.do?pg=${pageGroupEnd + 1}" class="next">다음</a>
			            </c:if>
                    </div>
                </article>
            </section>
        </main>

        <div id="userPopup">
            <section>
                <nav>
                    <h1>사용자 상세정보</h1>
                    <button class="btnClose">닫기</button>
                </nav>

                <article>  
                    <h3>기본정보</h3>
                    <table border="0">
                        <tr>
                            <td>아이디</td>
                            <td class="uid">a101</td>
                        </tr>
                        <tr>
                            <td>이름</td>
                            <td class="name">김유신</td>
                        </tr>
                        <tr>
                            <td>별명</td>
                            <td class="nick">유신101</td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td class="email">yusin101@naver.com</td>
                        </tr>
                        <tr>
                            <td>휴대폰</td>
                            <td class="hp">010-1234-1001</td>
                        </tr>
                        <tr>
                            <td>등급</td>
                            <td class="grade">2등급(준회원)</td>
                        </tr>
                        <tr>
                            <td>주소</td>
                            <td class="address">
                            	<p class="zip">
                            		[48594] 
                            	</p>
                            	<p class="addr1">
                                    부산광역시 부산진구 대연동 120 
                            	</p>
                            	<p class="addr2">
                                    한빛빌딩 10층
                            	</p>
                            </td>
                        </tr>
                        <tr>
                            <td>아이피</td>
                            <td class="regip">192.168.10.112</td>
                        </tr>
                        <tr>
                            <td>회원가입일</td>
                            <td class="regDate">2023-01-01 13:06:14</td>
                        </tr>
                    </table>
                </article>
            </section>
        </div>
        
<%@include file="./_footer.jsp" %>