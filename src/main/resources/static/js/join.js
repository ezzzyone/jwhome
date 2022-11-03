console.log("success");

$("#all").click(function(){
    let ch = $(this).prop("checked");
    console.log(ch);
    $(".check").prop("checked", ch);
})

$(".check").click(function(){// check를 클릭하면
    
    //let flag = true;
    $("#all").prop("checked", true); //all의 checked를 true로 바꿔라 (기존 값은 상관 없음)

    $(".check").each(function(idx, item){ //all의 checked를 true로 바꿔라 (기존 값은 상관 없음)
        let ch = $(item).prop("checked",); // 엘리먼트의 checked 값을 ch에 저장하고
        if(!ch){
            //flag = false;
            $("#all").prop("checked", false);  //all의 checked를 false로 바꿔라
            return false;  //그리고 반복문을 종료해라
        }

        //$("#all").prop("checked", flag);
    })
}
)


// let idCheck = false;
// let pwCheck = false;
// let pw2Check = false;
// let nameCheck = false;
// let emailCheck = false;

let results = [false, false, false, false, false];

$("#id").change(function(){
    // if(result){
    //     idCheck= true;
    //     $("#h1").text("")
    // }else{
    //     $("#h1").text("아이디는 1글자 이상이어야합니다.");
    // }
    let result = nullCheck($("#id").val(), $("#h1"), "id");
     if(result){
          //=========== id 중복체크 ajax ============ 
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "./idCheck?id="+$("#id").val());
    xhttp.send();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            result = xhttp.responseText.trim();
            result = JSON.parse(result);

    if(result == 1){

        $("#h1").text("사용중인 아이디입니다.");
        results[0] = false;
    }else{
        $("#h1").text("사용가능한 아이디입니다.");
        }
         }
        }   
    }

    results[0]=result;
})

$("#pw").change(function(){
    let result = nullCheck($("#pw").val(), $("#h2"), "pw");
    // if($("#pw").val().length>0){
    //     pwCheck= true;
    //     $("#h2").text("")
    // }else{
    //     $("#h2").text("비밀번호는 1글자 이상이어야합니다.");
    // }
    results[1]=result;
    results[2]=false;
    $("#h5").text("");
    $("#pw2").val("");
})

$("#pw2").change(function(){
    let result = equals($("#pw").val(), $("#pw2").val(), $("#h5"));
    // if(result){
    //     pw2Check= true;
    //     $("#h5").text("")
    // }else{
    //     $("#h5").text("비밀번호가 일치하지 않습니다.");
    // }
    results[2]=result;
})

$("#name").change(function(){
    let result = nullCheck($("#name").val(), $("#h3"), "name");
    // if($("#name").val().length>0){
    //     nameCheck= true;
    //     $("#h3").text("")
    // }else{
    //     $("#h3").text("이름은 1글자 이상이어야합니다.");
    // }
    results[3]=result;
})

$("#email").change(function(){
    let result = nullCheck($("#email").val(), $("#h4"), "email");
    // if($("#email").val().length>0){
    //     emailCheck= true;
    //     $("#h4").text("")
    // }else{
    //     $("#h4").text("이메일은 1글자 이상이어야합니다.");
    // }
    results[4]=result;
})


$("#joinbtn").click(function(){

    console.log(results);

    if(results.includes(false)){
        alert("필수항목을 입력하세요.")
    }else{
        //$("#joinfrm").submit();
    }

    //== 방법 1

    // let c = true;
    // $.each(results, function(idx, item){
    //     if(!item){ // reitem = true인지 false인지 배열의 값.
    //         console.log(results);
    //         alert("필수항목을 입력하세요.")
    //         c = false;
    //         return;
    //     }
    // })

    // == 방법 2

    // if(c){
    //     $("#joinfrm").submit();
    // }

    // if(idCheck == true && pwCheck == true && pw2Check == true && nameCheck == true && emailCheck == true){
    //     event 강제 실행
    //     $("#joinfrm").submit();
    // }else {
    //     alert("값을 입력해주세요");
    //     return;
    // }
});
