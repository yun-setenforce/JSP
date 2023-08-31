<%@page import="kr.co.jboad1.dto.ArticleDTO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.jboad1.dao.ArticleDAO"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@  include file="./_header.jsp" %>
      
<%
      request.setCharacterEncoding("UTF-8");
      	String pg = request.getParameter("pg");

      	//현재 페이지 게시물 Limit 시작번호 
      	// 페이지 관련 변수
      	int start  			= 0; // 각 페이지별 글 10개를 읽어오기 위한 SQL문에서 LIMIT 제시값 
      	int currentPage 	= 1; //현재 페이지. 파라미터로 제시된 페이지가 없을 경우 기본은 1페이지 
      	int total 			= 0; // 전체 게시물 갯수 
      	int lastPageNum 	= 0; // 끝까지 넘겼을 떄 마지막 페이지 번호   
      	int pageGroupCurrent = 1; //페이지 번호를 10씩 나눴을 때 현재 그룹 몇번째인지. 그룹1(1~10p), 그룹2(11~20p)
      	int pageGroupStart  = 1; //현재 그룹의 시작 페이지 번호 
      	int pageGroupEnd  	= 0; //현재 그룹의 끝 페이지 번호  -lastPageNum보다 클 경우 lastPageNum 대입 
      	int pageStartNum 	= 0;//각 페이지의 시작 게시물 번호 
      	
      	//현재 페이지 계산 
      	if(pg != null){
      		currentPage = Integer.parseInt(pg);
      	}

      	//Limit 시작값 계산 
      	start = (currentPage - 1) * 10;
      	
      	//전체 게시물 갯수 조회
      	total = ArticleDAO.getInstance().selectCountTotal();
      	
      	//페이지 번호 계산 
      	if(total % 10 ==0){
      		lastPageNum = (total/10);
      	}else{
      		lastPageNum = (total/10)+1;
      	}
      	
      	// 페이지 그룹 계산 
      	pageGroupCurrent = (int) Math.ceil(currentPage/10.0);
      	pageGroupStart = (pageGroupCurrent-1)*10 +1;
      	pageGroupEnd = pageGroupCurrent*10;

      	
      	if(pageGroupEnd > lastPageNum){
      		pageGroupEnd = lastPageNum;
      	}
      	
      	//페이지 시작번호계산 
      	pageStartNum = total - start;
      	
      	//현재 페이지 게시물 조회 
      	List<ArticleDTO> articles = ArticleDAO.getInstance().selectArticles(start);
      %>      
        
<main>
    <section id="board" class="list">
        <h3>글목록</h3>
        <article>                
            <table border="0">
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>날짜</th>
                    <th>조회</th>
                </tr>
                <%
                for(ArticleDTO article : articles){
                %>
                <tr>
                    <td><%=pageStartNum-- %></td>
                    <td><a href="/Jboard1/view.jsp?no=<%=article.getNo()%>"><%=article.getTitle() %></a>&nbsp;[<%=article.getComment() %>]</td>
                    <td><%=article.getNick()%> <%--  <%=ArticleDAO.getInstance().selectUserNick(article.getWriter()) %> (<%=article.getWriter() %>) --%></td>
                    <td><%=article.getRdate() %></td>
                    <td><%=article.getHit() %></td>
                </tr>
                <%
                	}
                %>
            </table>
        </article>

        <!-- 페이지 네비게이션 -->
        <div class="paging">
        	<%if(pageGroupStart>1){ %>
            <a href="/Jboard1/list.jsp?pg=<%=pageGroupStart -1 %>" class="prev">이전</a>
            <%} %>
            <% for(int i=pageGroupStart; i<=pageGroupEnd; i++){ %>             
            <a href="/Jboard1/list.jsp?pg=<%=i %>" class="num <%=(i==currentPage)?"current":"" %>"><%=i %></a>                
            <% } %>            
            <%if(pageGroupEnd < lastPageNum){ %>
            <a href="/Jboard1/list.jsp?pg=<%=pageGroupEnd+1 %>" class="next">다음</a>
            <% } %>
        </div>

        <!-- 글쓰기 버튼 -->
        <a href="/Jboard1/write.jsp" class="btnWrite">글쓰기</a>

    </section>
</main>
<%@ include file="./_footer.jsp" %>