$("#searchBtn").click(function(){

        $.ajax({
            type:"get",
            url :"./list",
            traditional:true, //배열을 전송할 때 사용, true
            data:{
                search:search,
                page:page

            },
            success : function(){
                $("#searchFrm").submit();
            },
            error   : function(){
          
            }
        })

})
    