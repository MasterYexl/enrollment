
let frame = document.getElementById("content-frame");
let header = document.getElementById("content-header");
let footer = document.getElementById("content-footer");
let mainContent = document.getElementById("content-main");

window.onload = function (){
    screenResize();
}

window.onresize = function (){
    screenResize();
}

function screenResize(){
    let hs = document.body.offsetHeight;
    mainContent.style.height = hs + "px";
    frame.style.height = (hs - header.offsetHeight-footer.offsetHeight)+"px";
}

function frameJump(src){
    frame.src = src;
}