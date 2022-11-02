
let results = [false, false, false];

// $("#writer").change(function(){
//     // if(result){
//     //     idCheck= true;
//     //     $("#h1").text("")
//     // }else{
//     //     $("#h1").text("아이디는 1글자 이상이어야합니다.");
//     // }
//     let result = nullCheck($("#writer").val(), $("#h1"), "writer");
//      if(result){
//           //=========== id 중복체크 ajax ============ 
//     const xhttp = new XMLHttpRequest();
//     xhttp.open("GET", "./Check?id="+$("#id").val());
//     xhttp.send();
//     xhttp.onreadystatechange = function(){
//         if(this.readyState == 4 && this.status == 200){
//             result = xhttp.responseText.trim();
//             result = JSON.parse(result);

//     if(result == 1){

//         $("#h1").text("사용중인 아이디입니다.");
//         results[0] = false;
//     }else{
//         $("#h1").text("사용가능한 아이디입니다.");
//         }
//          }
//         }   
//     }

//     results[0]=result;
// })


$("#writer").on({
    change:(function(){
        let result = nullCheck($("#writer").val(), $("#h1"), "writer");
        results[0]=result;
    }),
    blur:(function(){
        let result = nullCheck($("#writer").val(), $("#h1"), "writer");
        results[0]=result;
    })
});




$("#title").on({
    change:(function(){
        let result = nullCheck($("#title").val(), $("#h2"), "title");
        results[1]=result;
    }),
    blur:(function(){
        let result = nullCheck($("#title").val(), $("#h2"), "title");
        results[1]=result;
    })
});


$("#writeBtn").click(function(){

    console.log(results);

    if(results.includes(false)){
        alert("필수항목을 입력하세요.")
    }else{
        $("#writeBtn").submit();
    }

});