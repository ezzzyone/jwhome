<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<c:import url="../temp/summer.jsp"></c:import>
<script defer src="/js/file.js"></script>
<script defer src="/js/util.js"></script>
<script defer src="/js/write.js"></script>
</head>
<body>
<section class="container-fluid col-lg-10 mt-5">
   <h1>글쓰기 페이지</h1>

   <div>
      <form:form action="write" method="post" enctype="multipart/form-data" modelAttribute="qnaVO" >
    
         <div class="mb-3">
            <label for="formGroupExampleInput" class="form-label">작성자</label>
            <form:input path="writer" cssClass="form-control" id="writer"/>
            <!-- <input type="text" class="form-control" name="writer" placeholder="작성자를 입력하세요."> -->
            
              <h5 id="h1" class="warning">
       		  <form:errors path="writer"></form:errors>
    		 </h5> <!--js메세지-->
         </div>
   
         <div class="mb-3">
           <label for="formGroupExampleInput" class="form-label">제목</label>
           <form:input path="title" cssClass="form-control" id="title"/>
           <h5 id="h2" class="warning">
           <form:errors path="title"></form:errors>
           </h5>
         </div>
         
         <div class="mb-3">
           <label for="formGroupExampleInput2" class="form-label">글내용</label>
           <form:textarea path="contents" cssClass="form-control" id="contents"/>
           <h5 id="h3" class="warning">
           <form:errors path="contents"></form:errors>
           </h5>
         </div>
         
         <div class="mb-3" id="fileDiv">

		</div>
         
    
       		 <form:button type="button" id="fileAdd">FileAdd</form:button>
    	
         
       
         
          <!-- <div class="mb-3">
           <label for="formGroupExampleInput" class="form-label">file</label>
           <input type="file" name="files">
         </div>   
         
         <div class="mb-3">
           <label for="formGroupExampleInput" class="form-label">file</label>
           <input type="file" name="files">
         </div> -->
            
         <div style="display: inline-block; margin: 0 5px;  float: right;">
            <form:button type="button" class="btn btn-primary" id="writeBtn">작성완료</form:button>
         </div>   
      
   
      </form:form>
   </div>
   
   <div style="margin-top: 10px;">
      <button class="btn btn-secondary" type="button" onclick="location='/'">홈</button>
      <button class="btn btn-primary" type="button" onclick="location='./list'">list</button>
   </div>

</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<script type="text/javascript">
    $('#contents').summernote({
        tabsize: 4,
        height: 250
      });
   </script>
</body>
</html>