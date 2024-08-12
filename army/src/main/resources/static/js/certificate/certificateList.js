document.querySelector(".type1").addEventListener("click",function(evt){
    location.href="/public/certificate?type=1";
});
document.querySelector(".type2").addEventListener("click",function(evt){
    location.href="/public/certificate?type=2";
});
document.querySelector(".type3").addEventListener("click",function(evt){
    location.href="/public/certificate";
});
document.querySelector(".type4").addEventListener("click",function(evt){
    location.href="/public/certificate?type=3";
});
document.querySelector(".type5").addEventListener("click",function(evt){
    location.href="/public/certificate?type=4";
});

document.querySelector(".search-box").addEventListener("click",function(evt){
    const inputValue = document.querySelector(".input").value;
    location.href=`/public/certificate?type=5&keyword=${inputValue}`;
});



