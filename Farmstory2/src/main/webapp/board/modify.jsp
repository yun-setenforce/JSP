<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./../_header.jsp" %>
<jsp:include page="./_aside${group}.jsp"/>
<script>
$(document).ready(function() {
    $(".file-delete").on("click", function(e) {
        e.preventDefault();
        deleteFile($(this));
    });
})

function deleteFile(obj) {
    obj.parent().remove();
}

</script>
<section class="modify">
    <h3>글수정</h3>
    <article>
        <form action="/Farmstory2/board/modify.do" method="post" enctype="multipart/form-data" >
        	<input type="hidden" name="group" value="${group }">
        	<input type="hidden" name="cate" value="${cate }">
        	<input type="hidden" name="no" value="${article.no}">
        	<input type="hidden" name="writer" value="${article.writer}">
            <input type="hidden" name="existedFile1" value="${article.fileDto.fno}">
            <table>
                <tr>
                    <td>제목</td>
                    <td><input type="text" name="title" value="${article.title}" placeholder="제목을 입력하세요."/></td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td>
                        <textarea name="content">${article.content}</textarea>
                    </td>
                </tr>
                <tr>
                    <td>첨부</td>
                    <td>
                    	<input type="file" name="file"/>
				        <c:if test="${article.file > 0}">
				        	<div>
								<span> 기존 첨부 파일 :: </span>
				                <a href="/Farmstory2/board/fileDownload.do?fno=${article.fileDto.fno}" id="downloadFile" data-fno="${article.fileDto.fno}">${article.fileDto.oriName } &nbsp;</a>
				                <a href="#" class="file-delete"> [ 파일 삭제 ] </a>
				                <input type="hidden" name="existedFile2" value="${article.fileDto.fno}">
			                </div>
				        </c:if>
                   	</td>
                </tr>
            </table>
            <div>
                <a href="/Farmstory2/board/view.do?group=${group}&cate=${cate}&no=${no}" class="btnCancel">취소</a>
                <input type="submit"  class="btnWrite" value="수정완료">
            </div>
        </form>
    </article>
</section>
<!-- 내용 끝 -->
        </article>
    </section>
</div>			