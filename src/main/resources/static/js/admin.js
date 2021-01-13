let frame = document.getElementById("content-frame");
let header = document.getElementById("content-header");
let footer = document.getElementById("content-footer");
let mainContent = document.getElementById("content-main");
let messageNumber = document.getElementById("message-number");
let messageList = document.getElementById("message-list");
let readAllButton = document.getElementById("read-all-button");
let oldMessageJson = null;
let messageHref = "#";

window.onload = function (){
    getNewMessage();
    screenResize();
    messageTack();
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

/**
 * 消息处理
 */

//获取未处理消息
function getNewMessage(){
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
            //更新消息数量
            changeMessageNumber(messages.length);
            //添加消息
            for (let i=0;i<messages.length-start;i++){
                let message = createMessageLink(messages[i]);
                message.id = "msg"+(i+start);
                if (oldMessageJson === null) messageList.appendChild(message);
                else {
                    messageList.insertBefore(message,document.getElementById("msg0"));
                    document.getElementById("msg0").id = "msg"+i+start;
                    message.id = "msg0";
                }
            }
            oldMessageJson = messages;
        }
    }
    xmlhttp.open("GET","/message/get-new",true);
    xmlhttp.send();
}

function messageTack(){
    setTimeout(function (){
        getNewMessage();
        console.log("获取了消息");
        messageTack();
        }, 3000);
}

//更新数量
function changeMessageNumber(num){
    messageNumber.innerText = num;
    if (parseInt(messageNumber.innerText)===0) {
        messageNumber.style.display = "none";
        readAllButton.style.display = "none";
    }
    else {
        messageNumber.style.display = "block";
        readAllButton.style.display = "block";
    }
}
//全部已读
function readAll(){
    let xmlhttp;
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
    xmlhttp.open("GET","/message/all-new-read",true);
    xmlhttp.send();
    changeMessageNumber(0);
    oldMessageJson = null;
    messageList.innerText = "";
}

//获取所有消息


/**
 * 工具
 */

//消息模板生成
function createMessageLink(message){
    let a = document.createElement("a");
    a.href = messageHref;
    let photo = document.createElement("span");
    photo.className = "photo";
    let img = document.createElement("img");
    img.alt = "img";
    img.src = "/images/users/avatar-8.jpg";
    img.className = "img-circle";
    photo.appendChild(img);
    let subject = document.createElement("span");
    subject.className = "subject";
    subject.innerText = message["fromEmail"]==="-1"? "系统通知":message["fromEmail"];
    let msg = document.createElement("span");
    msg.className = "message";
    msg.innerText = message["message"];
    let time = document.createElement("span");
    time.className = "time";
    time.innerText = parseTime(message["messageTime"]);
    a.appendChild(photo);
    a.appendChild(subject);
    a.appendChild(msg);
    a.appendChild(time);
    return a;
}
function createTaskLink(task){

}
//解析时间戳
function parseTime(timestamp){
    //获取当前时间
    let now=new Date();
    //根据指定时间戳转换为时间格式
    let time=new Date();
    time.setTime(timestamp);
    //比较当前时间和指定时间的差来决定显示时间格式
    //1.年份与当前不同则显示完整日期 yyyy-MM-dd hh:mm
    if(time.getFullYear() !== now.getFullYear())
        return time.getFullYear()+"-"
            +((time.getMonth()+1)<10?"0"+(time.getMonth()+1):(time.getMonth()+1))+"-"
            +(time.getDate()<10?"0"+time.getDate():time.getDate())+" "
            +(time.getHours()<10?"0"+time.getHours():time.getHours())+":"
            +(time.getMinutes()<10?"0"+time.getMinutes():time.getMinutes());
    //2.年份与当前相同但月份或日期不同时 显示 MM-dd hh:mm格式
    else if(time.getMonth() !== now.getMonth() || time.getDate() !== now.getDate())
        return ((time.getMonth()+1)<10?"0"+(time.getMonth()+1):(time.getMonth()+1))+"-"
            +(time.getDate()<10?"0"+time.getDate():time.getDate())+" "
            +(time.getHours()<10?"0"+time.getHours():time.getHours())+":"
            +(time.getMinutes()<10?"0"+time.getMinutes():time.getMinutes());
    //3.年份与日期均与当前相同时，显示hh:mm格式
    else
        return (time.getHours()<10?"0"+time.getHours():time.getHours())+":"
            +(time.getMinutes()<10?"0"+time.getMinutes():time.getMinutes());
}