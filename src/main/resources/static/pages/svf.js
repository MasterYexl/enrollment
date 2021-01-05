
let gd1 = document.getElementById("gd1");
let gd2 = document.getElementById("gd2");
let gd3 = document.getElementById("gd3");
let gd = document.getElementById("grade");

window.onload = function (){
    let gs = gd.value.split(" ");
    gd1.value = gs[0];
    gd2.value = gs[1];
    gd3.value = gs[2];
}

function alertStudentGrade() {
    let xmlhttp;
    let grade = "";
    grade = gd1.value + " " + gd2.value + " " + gd3.value + " ";
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
            else swal("操作失败","请稍后再试", "error");
        }
    }
    xmlhttp.open("GET", "/admin/grade-alert/" + document.getElementById("sid").value + "?grade=" + grade, true);
    xmlhttp.send();
}