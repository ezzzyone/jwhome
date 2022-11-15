console.log("s3.js 연결확인");

let f1 = function(num){
    console.log("this is f1 function", num);
}

f1(3);

//매개변수를 무엇을 받을지, function내용만 남기고 다 지움
//=> 로 그 방향을 정해줌
let f2 = (num)=>{
console.log("t2 function", num);
};

//매개변수없는 것
//코드 한 줄이면 중괄호 생략해도 됨
let f3 = ()=>console.log("f3 function");

    f2(2);
    f3();

    // v3.click(function(){

    // })
    // v4.click(()=>{} );
    
    // $.ajax({
    
    //     success:(result)=>{}
    // })