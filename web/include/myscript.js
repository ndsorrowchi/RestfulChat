/*
* Assignment 3 JS
* Ming Chi
*/
var isIE=false;
var validemail=false;

function initfield() {
document.getElementById("register-password").onchange = validatePassword;
document.getElementById("confirm-password").onchange = validatePassword;
}

function validatePassword(){
var pass2=document.getElementById("confirm-password").value;
var pass1=document.getElementById("register-password").value;

if(pass1!=pass2)
    document.getElementById("confirm-password").setCustomValidity("Passwords Don't Match");
else
    document.getElementById("confirm-password").setCustomValidity('');	 
//empty string means no validation error
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

function togglebutton()
{
    if(validemail)
    {document.getElementById("submitbtn").disabled=false;}
    else
    {document.getElementById("submitbtn").disabled=true;}
}



function checkit()
{
    var elem=document.getElementById("register-username");
    //alert(elem.id);
    if(elem.value=="")
    {return;}// do not deal with white spaces
    
    var reqjson={};
    var xhr=getAjaxObj();
    xhr.onreadystatechange=processData;    

    reqjson["useremail"]=elem.value;

    xhr.open("POST","Check",true);
    xhr.send(JSON.stringify(reqjson));
    

function processData()
{
    if (xhr.readyState == 4) {
        if (xhr.status == 200) {
            //alert(xhr.responseText);
            var JSONresp = JSON.parse(xhr.responseText);
            var count=JSONresp["count"];
            var notice=document.createElement("SPAN");
            if(true)
            {
                if(count==1)
                {
                    notice.textContent="This email has been registered.";
                    notice.className="glyphicon glyphicon-remove text-danger";
                    validemail=false;
                    togglebutton();
                }
                else
                {
                    notice.textContent="Good. You can use this email.";
                    notice.className="glyphicon glyphicon-ok text-success";
                    validemail=true;
                    togglebutton();                   
                }
                var target=document.getElementById("chkemail");
                target.replaceChild(notice,target.children[0]);                
            }
            else
            {
                ;
            }
        }
        else
        {
            var JSONresp = JSON.parse(xhr.responseText);
            alert(JSONresp["error"]);
        }
    }
}

}