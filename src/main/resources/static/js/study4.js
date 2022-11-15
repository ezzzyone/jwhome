console.log("jqeury ajax말고 다른 ajax");


fetch("https://jsonplaceholder.typicode.com/posts/1",{
    method:"GET",
    headers:{"Content-Type":"application/json;charset=utf-8"}
}).then((result)=>result.json()) //result.json() = 응답 읽고 json 반환
    .then(r=>{console.log("Result =>", r)});
