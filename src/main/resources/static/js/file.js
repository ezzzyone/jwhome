//파일 개수 지정 변수
let count=1
let flag =true;
$("#fileAdd").click(function(){

    if(count>=5){
        alert("최대 5개까지 등록할 수 있습니다.");
        return;
    }else{
        let what = '<div><input type="file" name="files" id="file'+count+'"/><button class="del">X</button></div>';
        $("#fileDiv").append(what);
        count++;
    }

});  

$("#fileDiv").on("click", ".del", function(){ //이벤트 위임
    console.log("del del del");
    $(this).parent().remove();
    count--;
})

$(".del").click(function(){ //이건 안찍힘
    console.log("del del del");
})

//수정 시 첨부파일 삭제

$(".delFile").click(function(){
    //DB, HDD 파일 삭제
    let check = confirm("삭제됩니다 복구 불가합니다.");
    let fileNum = $(".delFile").attr("value");
  
    const img = $(this)
    if(check){

        if(flag){
            let size = $("#fileDiv").attr("data-file-size");
            count=size;
            flag=false;
        }

        $.ajax({
            type:"POST",
            url :"./fileDelete",
            traditional:true, //배열을 전송할 때 사용, true
            data:{
                fileNum:fileNum
            },
            success : function(){
                $(img).parent().remove();
                alert("삭제되었습니다");
                count--;
            },
            error   : function(){
                alert("삭제실패입니다");
            }
        })
    
    }
})

