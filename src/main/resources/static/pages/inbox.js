$( document ).ready(function() {

    $('.check-mail-all').click(function () {
        $('.checkbox-mail').click();
    });

    // $('.checkbox-mail').each(function() {
    //     $(this).click(function() {
    //         if($(this).closest('tr').hasClass("checked")){
    //             $(this).closest('tr').removeClass('checked');
    //             hiddenMailOptions();
    //         } else {
    //             $(this).closest('tr').addClass('checked');
    //             hiddenMailOptions();
    //         }
    //     });
    // });
    //
    // $('.mailbox-content table tr td').not(":first-child").on('click', function(e) {
    //     e.stopPropagation();
    //     e.preventDefault();
    //
    //     window.location = "message-view.html";
    // });
});

let messageAllList = document.getElementById("message-all-list");
let oldMessageJson = null;

window.onload = function (){
    getAllMessage();
    let ts = document.getElementsByClassName("time-stamp");
    for (let i=0;i<ts.length;i++){
        let t = ts.item(i);
        t.innerHTML = parseTime(t.innerHTML);
    }
}

function createMessage(message){
    let tr = document.createElement("tr");
    let xs1 = createHiddenXs();
    let div = document.createElement("div");
    div.className = "check-box";
    let input = document.createElement("input");
    input.className = "checkbox-mail";
    input.type = "check-box";
    let label = document.createElement("label");
    div.appendChild(input).appendChild(label);
    xs1.appendChild(div);
    let xs2 = createHiddenXs();
    let i = document.createElement("i");
    i.className = "fa fa-star icon-state-warning";
    xs2.appendChild(i);
    let xs3 = createHiddenXs();
    xs3.innerText = message["fromEmail"];
    let msg = document.createElement("td");
    msg.innerText = message["message"];
    let date = document.createElement("td");
    date.innerText = message["messageTime"]
    tr.appendChild(xs1).appendChild(xs2).appendChild(xs3).appendChild(msg).appendChild(date);
    return tr;
}
function createHiddenXs(){
    let hiddenXs = document.createElement("td");
    hiddenXs.className = "hidden-xs";
    return hiddenXs;
}
function getAllMessage(){
    let xmlhttp;
    let json;
    let messages;
    if (window.XMLHttpRequest)
    {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp=new XMLHttpRequest();
    }
    else
    {
        // IE6, IE5 浏览器执行代码
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState===4 && xmlhttp.status===200)
        {
            let start = 0;
            json = xmlhttp.responseText;
            messages = JSON.parse(json);
            if (messages.length<=0) return ;
            if (oldMessageJson !== null) start = oldMessageJson.length;
            oldMessageJson = messages;
            //更新消息数量
            changeMessageNumber(messages.length);
            //添加消息
            for (let i=start;i<messages.length;i++){
                let message = createMessage(messages[i]);
                messageAllList.appendChild(message);
            }
        }
    }
    xmlhttp.open("GET","/message/get",true);
    xmlhttp.send();
}

function parseTime(timestamp){
    //获取当前时间
    let now=new Date();
    //根据指定时间戳转换为时间格式
    let time=new Date();
    time.setTime(Date.parse(timestamp));
    let newTime = "";
    //比较当前时间和指定时间的差来决定显示时间格式
    //1.年份与当前不同则显示完整日期 yyyy-MM-dd hh:mm
    if(time.getFullYear() !== now.getFullYear())
        newTime = time.getFullYear()+"-"
            +((time.getMonth()+1)<10?"0"+(time.getMonth()+1):(time.getMonth()+1))+"-"
            +(time.getDate()<10?"0"+time.getDate():time.getDate())+" "
            +(time.getHours()<10?"0"+time.getHours():time.getHours())+":"
            +(time.getMinutes()<10?"0"+time.getMinutes():time.getMinutes());
    //2.年份与当前相同但月份或日期不同时 显示 MM-dd hh:mm格式
    else if(time.getMonth() !== now.getMonth() || time.getDate() !== now.getDate())
        newTime = ((time.getMonth()+1)<10?"0"+(time.getMonth()+1):(time.getMonth()+1))+"-"
            +(time.getDate()<10?"0"+time.getDate():time.getDate())+" "
            +(time.getHours()<10?"0"+time.getHours():time.getHours())+":"
            +(time.getMinutes()<10?"0"+time.getMinutes():time.getMinutes());
    //3.年份与日期均与当前相同时，显示hh:mm格式
    else
        newTime = (time.getHours()<10?"0"+time.getHours():time.getHours())+":"
            +(time.getMinutes()<10?"0"+time.getMinutes():time.getMinutes());
    return newTime;
}