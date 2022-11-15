<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
   <h1>Write Page</h1>

   <div>
      <form:form action="write" method="post" enctype="multipart/form-data" modelAttribute="qnaVO" >
    <sec:csrfInput/>
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
            <button class="btn btn-light" type="button" onclick="location='/'">Home</button>
      		<button class="btn btn-light" type="button" onclick="location='./list'">List</button>
      		 <form:button type="button" class="btn btn-light" id="writeBtn">Submit</form:button>
         </div>   
    
      </form:form>
   </div>

</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<script type="text/javascript">
    $('#contents').summernote({
        tabsize: 4,
        height: 250,
        callbacks: {
         onImageUpload: function(file) {
         // upload image to server and create imgNode...
         console.log("imgLog");
         console.log("file ", file);
         uploadFile(file);
      
        },
      onMediaDelete:function(file){
         console.log("onMediaDelete");
         console.log("deleteFile =>", file);

         deleteFile(file);
      }
      }
      });

      function deleteFile(file){
         console.log("src: ",file.attr("src"));
         $.post("./summerFileDelete", {fileName:file.attr("src")}, function(result){

         })
      }


      function uploadFile(file){
         console.log("file ",file);
         console.log("fileName ",file[0].name);
         const formData = new FormData();
         //input type = "file"
         formData.append('file', file[0])

         $.ajax({
            type:"POST",
            url:"summerFile",
            data:formData,
            cache: false,
            processData:false,
            contentType:false,
            enctype:'multipart/form-data',
            success:function(img){
               console.log("IMG => ", img);
               //img = "<img src="+img+">"
               $('#contents').summernote('insertImage', img, file[0].name);
            //onImageUpload 설정 
            //$summernote.summernote('insertNode', imgNode);
            ///$summernote. 에러 발생 -jquery 형식으로 바꿔줌 
            //imgNode가 없음 = 이미지 태그 같은 것인데 임의로 만들어줘야
            },
            error:function(){
               console.log("error");
            }
         })
      }
     
   </script>
</body>
</html>