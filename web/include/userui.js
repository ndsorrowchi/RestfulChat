/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var isIE=false;

var friendstimer;
var chattimer=null;
var laststamp=0;
var lastfriendid="";

function init()
{
    updatefriends();
    getselfinfo();
    friendstimer=setInterval('updatefriends()',10000);
}

function getAjaxObj()
{
    var req;
    if (window.XMLHttpRequest)
    { req = new XMLHttpRequest(); }
    else if (window.ActiveXObject)
    { isIE = true;
    req = new ActiveXObject("Microsoft.XMLHTTP");}
    return req;
}

function updatefriends()
{
    var uid=document.getElementById("user-email").textContent;
    var xhr1=getAjaxObj();
    xhr1.onreadystatechange=processFriendsList; 
    xhr1.open("GET","restapi/users/"+uid+"/friends",true);
    xhr1.send(); 
    
function processFriendsList()
{
    if (xhr1.readyState == 4) {
        if (xhr1.status == 200) {
            //alert(xhr.responseText);
            var JSONresp = JSON.parse(xhr1.responseText);
            var listdom = document.getElementById("friends");
            while (listdom.hasChildNodes()) {   
                listdom.removeChild(listdom.firstChild);
            }
            var arr=JSONresp.userslist;
            for (var i = 0; i < arr.length; i++) {
                var user = arr[i];
                var temp=document.createElement("a");
                temp.setAttribute("href","#");
                temp.setAttribute("data-useremail",user.id);
                temp.setAttribute("onclick","handleclick(this);return true;");
                console.log(user);
                temp.textContent=user.nickname;
                if(user.status == "online")
                {
                    temp.setAttribute("class","list-group-item list-group-item-success");
                }
                else
                {
                    temp.setAttribute("class","list-group-item");
                }
                listdom.appendChild(temp);
            }
        }
        else
        {
            var JSONresp = JSON.parse(xhr1.responseText);
            alert(JSONresp["error"]);
        }
    }
}    
}

function getselfinfo()
{
    var uid=document.getElementById("user-email").textContent;
    var xhr1=getAjaxObj();
    xhr1.onreadystatechange=setUserPanel; 
    xhr1.open("GET","restapi/users/"+uid,true);
    xhr1.send(); 
    
function setUserPanel()
{
    if (xhr1.readyState == 4) {
        if (xhr1.status == 200) {
            //alert(xhr.responseText);
            var JSONresp = JSON.parse(xhr1.responseText);
            var divdom = document.getElementById("user-profile-wrapper");
            while (divdom.hasChildNodes()) {   
                divdom.removeChild(divdom.firstChild);
            }
            var ul=document.createElement("UL");
            ul.setAttribute("style","list-style-type:none");
            var uid=document.createElement("li");
            uid.textContent="ID: "+JSONresp.id;
            var nickname=document.createElement("li");
            nickname.textContent="Nickname: "+JSONresp.nickname;
            var age=document.createElement("li");
            age.textContent="Age: "+JSONresp.age;
            var gender=document.createElement("li");
            gender.textContent="Gender: "+JSONresp.gender;
            var status=document.createElement("li");
            status.textContent="Status: "+JSONresp.status;
            ul.appendChild(uid);
            ul.appendChild(nickname);
            ul.appendChild(age);
            ul.appendChild(gender);
            ul.appendChild(status);
            divdom.appendChild(ul);
        }
        else
        {
            var JSONresp = JSON.parse(xhr1.responseText);
            alert(JSONresp["error"]);
        }
    }
}    
}

function handleclick(elem)
{
   getfriendinfo(elem); 
}

function getfriendinfo(elem)
{
    var uid=elem.getAttribute("data-useremail");
    var xhr1=getAjaxObj();
    xhr1.onreadystatechange=setFriendPanel; 
    xhr1.open("GET","restapi/users/"+uid,true);
    xhr1.send(); 
    
function setFriendPanel()
{
    if (xhr1.readyState == 4) {
        if (xhr1.status == 200) {
            //alert(xhr.responseText);
            var JSONresp = JSON.parse(xhr1.responseText);
            var headerdom = document.getElementById("friend-profile-header");
            headerdom.textContent=elem.textContent+"'s Profile";
            var divdom = document.getElementById("friend-profile-wrapper");
            while (divdom.hasChildNodes()) {   
                divdom.removeChild(divdom.firstChild);
            }
            var ul=document.createElement("UL");
            ul.setAttribute("style","list-style-type:none");
            var uid=document.createElement("li");
            uid.textContent="ID: "+JSONresp.id;
            var nickname=document.createElement("li");
            nickname.textContent="Nickname: "+JSONresp.nickname;
            var age=document.createElement("li");
            age.textContent="Age: "+JSONresp.age;
            var gender=document.createElement("li");
            gender.textContent="Gender: "+JSONresp.gender;
            var status=document.createElement("li");
            status.textContent="Status: "+JSONresp.status;
            ul.appendChild(uid);
            ul.appendChild(nickname);
            ul.appendChild(age);
            ul.appendChild(gender);
            ul.appendChild(status);
            divdom.appendChild(ul);
            var chatbtn=document.createElement("button");
            chatbtn.setAttribute("class","btn btn-info center-block");
            chatbtn.textContent="start chat";
            chatbtn.setAttribute("data-friendid",JSONresp.id);
            chatbtn.setAttribute("data-friendname",JSONresp.nickname);
            chatbtn.setAttribute("onclick","startChat(this)");
            divdom.appendChild(chatbtn);
        }
        else
        {
            var JSONresp = JSON.parse(xhr1.responseText);
            alert(JSONresp["error"]);
        }
    }
}    
}


function searchfriend()
{
    var uid=document.getElementById("user-email").textContent;
    var content=document.getElementById("searchbox").value;
    var xhr1=getAjaxObj();
    var listdom = document.getElementById("search-result");
    while(listdom.hasChildNodes())
    {
        listdom.removeChild(listdom.firstChild);
    }
    //alert(content);
    if(content.replace(/\s+/g,"")=="")
        return;
    xhr1.onreadystatechange=processSearchList; 
    xhr1.open("GET","restapi/users/search/"+content,true);
    xhr1.send();     
function processSearchList()
{
    if (xhr1.readyState == 4) {
        if (xhr1.status == 200) {
            //alert(xhr1.responseText);
            var JSONresp = JSON.parse(xhr1.responseText);
            var arr=JSONresp.userslist;
            for (var i = 0; i < arr.length; i++) {
                var user = arr[i];
                var temp=document.createElement("li");
                var text=document.createElement("span");
                text.innerHTML="  "+user.nickname+"<small>("+user.id+")</small>";
                text.setAttribute("class","lead");
                text.setAttribute("style","display:inline-block; margin-bottom:0px;");
                var btn=document.createElement("button");
                btn.setAttribute("class","btn btn-primary btn-sm pull-right");
                btn.setAttribute("data-useremail",user.id);
                if(uid==user.id)
                {
                    btn.disabled=true;
                }
                btn.textContent="add";
                btn.setAttribute("onclick","handleclickadd(this)");
                temp.appendChild(text);
                temp.appendChild(btn);
                temp.setAttribute("class","list-group-item");
                listdom.appendChild(temp);
            }
            if(listdom.hasChildNodes()==false)
            {
                var temp2=document.createElement("p");
                temp2.textContent="Cannot find that user.";
                temp2.setAttribute("class","text-muted");
                listdom.appendChild(temp2);
            }
        }
        else
        {
            var JSONresp = JSON.parse(xhr1.responseText);
            alert(JSONresp["error"]);
        }
    }
}    
}

function handleclickadd(elem)
{
    var uid=document.getElementById("user-email").textContent;
    var fid=elem.getAttribute("data-useremail");
    var reqobj={};
    var xhr1=getAjaxObj();
    xhr1.onreadystatechange=showadded; 
    xhr1.open("POST","restapi/users/follow",true);
    xhr1.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    reqobj["from"]=uid;
    reqobj["to"]=fid;
    xhr1.send(JSON.stringify(reqobj));    
    
function showadded()
{
    if (xhr1.readyState == 4) {
        if (xhr1.status == 200) {
            //alert(xhr.responseText);
            elem.setAttribute("class","btn btn-default btn-sm pull-right");
            elem.textContent="added";
            elem.disabled=true;
            updatefriends();
        }
        else
        {
            var JSONresp = JSON.parse(xhr1.responseText);
            alert(JSONresp["error"]);
        }
    }
}   

}

function listenChat()
{
    var uid=document.getElementById("user-email").textContent;
    var elem=document.getElementById("chat-wrapper");
    var fid=elem.getAttribute("data-fid");
    var fname=elem.getAttribute("data-fname");
    var reqobj={};
    var xhr1=getAjaxObj();
    xhr1.onreadystatechange=updateChat; 
    xhr1.open("POST","Chat",true);
    xhr1.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    reqobj["from"]=uid;
    reqobj["to"]=fid;
    reqobj["message"]=null;
    reqobj["time"]=laststamp;
    xhr1.send(JSON.stringify(reqobj));    
    
function updateChat()
{
    if (xhr1.readyState == 4) {
        if (xhr1.status == 200) {
            //alert(xhr.responseText);
            var JSONresp = JSON.parse(xhr1.responseText);
            var arr=JSONresp.msglist;
            if(arr.length>0)
            {
                laststamp=arr[arr.length-1].time;
            }
            for (var i = 0; i < arr.length; i++) {
                var msg = arr[i];
                console.log(JSONresp);
                var tempbox=document.createElement("div");
                var temp=document.createElement("div");
                var dateline = document.createElement("p");
                var msgline = document.createElement("p");
                dateline.className="small text-muted";
                dateline.setAttribute("style","margin-bottom:3px;");
                msgline.setAttribute("style","margin-bottom:3px;");
                var x=new Date(msg.time);
                var classtxt="";
                var datestr="You (";
                classtxt="pull-right"
                if(msg.from==fid)
                {
                    classtxt="pull-left income-msg";
                    datestr=fname+" (";
                }
                datestr=datestr+x.toLocaleString('en-US')+"):";
                dateline.textContent=datestr;
                msgline.textContent=arr[i].message;
                classtxt="well all-msg "+classtxt;
                temp.className=classtxt;
                temp.setAttribute("style","margin: 2px 7px;")
                temp.appendChild(dateline);
                temp.appendChild(msgline);
                tempbox.appendChild(temp);
                tempbox.setAttribute("style","width:100%; clear:both;");
                elem.appendChild(tempbox);
            }            
        }
        else
        {
            var JSONresp = JSON.parse(xhr1.responseText);
            alert(JSONresp["error"]);
        }
    }
} 
}

function startChat(elem)
{
    var friendid=elem.getAttribute("data-friendid");
    var friendname=elem.getAttribute("data-friendname");
    //only do sth when starting a new chat
    if(friendid!=lastfriendid&&friendid!=null)
    {
        if(chattimer!=null)
        {
           clearInterval(chattimer);
           chattimer=null;     
        }       
        laststamp=0;
        document.getElementById("chat-box-all").setAttribute("style","display:block;");
        
        document.getElementById("chat-heading").textContent="Chat with "+friendname;
        var temp=document.getElementById("chat-wrapper");
        temp.setAttribute("data-fid",friendid);
        temp.setAttribute("data-fname",friendname);
        // new chat should remove all
        while(temp.hasChildNodes())
        {
            temp.removeChild(temp.firstChild);
        }       
        
        lastfriendid=friendid;
        listenChat();
        chattimer=setInterval('listenChat()',3000);
        
    }
    
}

function sendMessage()
{
    var textarea=document.getElementById("send-post");
    var msg=textarea.value;
    
    if(msg.replace(/\s+/g,"")=="")//no white msg allowed
    {return;}
    if(msg.length>200)
    {alert("Message should not contain more than 200 characters (including white space).");return;}
    console.log("sent");
    var uid=document.getElementById("user-email").textContent;
    var elem=document.getElementById("chat-wrapper");
    var fid=elem.getAttribute("data-fid");
    var fname=elem.getAttribute("data-fname");
    var reqobj={};
    var xhr1=getAjaxObj();
    xhr1.onreadystatechange=postMessage; 
    xhr1.open("POST","Chat",true);
    xhr1.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    reqobj["from"]=uid;
    reqobj["to"]=fid;
    reqobj["message"]=textarea.value.toString();
    reqobj["time"]=Date.now();
    xhr1.send(JSON.stringify(reqobj));  
    
function postMessage()
{
    if (xhr1.readyState == 4) {
        if (xhr1.status == 200) {
            //alert(xhr.responseText);
            listenChat();
            textarea.value="";
        }
        else
        {
            var JSONresp = JSON.parse(xhr1.responseText);
            alert(JSONresp["error"]);
        }
    }
}   

}