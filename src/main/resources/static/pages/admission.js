
window.onload = function (){
    let grade = document.getElementById("grade").value;
    let grades = grade.split(" ");
    let tot = 0;
    for (let i=0;i<grades.length;i++){
        tot+=parseInt(grades[i]);
    }
    document.getElementById("tot-grade").value = tot;

}

function pass(admission) {
    let xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    } else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            if (xmlhttp.responseText === "1") swal("操作成功", "以通知用户查看", "success");
            else if (xmlhttp.responseText === "-1") swal("录取失败","该学生已被录取", "error");
            else swal("操作失败","请稍后再试", "error");
        }
    }
    xmlhttp.open("GET", "/user/admission/" + document.getElementById("sid").value + "?admission=" + admission, true);
    xmlhttp.send();
}