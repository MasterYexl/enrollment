function passTutorInformation(pass, message) {
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
            else swal("操作失败","请稍后再试", "error");
        }
    }
    if (pass) xmlhttp.open("GET", "/admin/tutor-pass/" + document.getElementById("tid").value, true);
    else xmlhttp.open("GET", "/admin/tutor-not-pass/" + document.getElementById("tid").value + "?message=" + message, true);
    xmlhttp.send();
}

function showMessage() {

    swal({
        title: "请输入理由",
        showCancelButton: "true",
        closeOnCancel: "true",
        content: {
            element: "input",
            attributes: {
                placeholder: "在此输入",
                type: "text",
            },
        },
        buttons: ["取消", "提交"],
    }).then((value) => {
        if (value!==null) passTutorInformation(false, value);
    });
}
