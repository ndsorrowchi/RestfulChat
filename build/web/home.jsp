<%-- 
    Document   : home
    Created on : 2015-12-15, 15:48:14
    Author     : chiming
--%>

<%@page import="beans.userbean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Home Page</title>
    <meta name="viewport" content="width=device-width,height=device-height, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="include/bootstrap/css/bootstrap.min.css"/>
    <style type="text/css">
        .mylistbox{
            overflow-y: scroll;
            min-height: 300px;
            max-height: 500px;
            width: 100%;
        }

        .mychatbox{
            overflow-y: scroll;
            min-height: 200px;
            max-height: 300px;
            width: 100%;
        }
        .special .list-group-item:first-child {
            border-top-right-radius: 0px !important;
            border-top-left-radius: 0px !important;
         }

         .special .list-group-item:last-child {
           border-bottom-right-radius: 0px !important;
           border-bottom-left-radius: 0px !important;
         }
         
         .online {
             color: #d9ffb3;
         }
         .offline{
             color:#ffffff;
         }
         .income-msg
         {
             background-color: #ddffaa;
         }
         .all-msg
         {
             min-width:30%; padding: 5px; margin:5px; box-shadow: 0px 1px 2px #C1C1C1
         }
    </style>
    <jsp:useBean id="usrbn" class="beans.userbean" scope="session"/>
</head>
<body onload="init()">
    <nav class="navbar navbar-inverse navbar-static-top" role="navigation" id="mynav" style="margin-bottom: 0px;">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" 
                    data-target="#navbar-collapse">
                <span class="sr-only">switch</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#mynav">RestSocial</a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse">
            <ul class="nav navbar-nav navbar-right" style="margin-right: 10px;">
                <li><a href="#user-profile-wrapper"><span class="glyphicon glyphicon-user" style="margin-right:5px;"></span><jsp:getProperty name="usrbn" property="uname"/></a></li>
                <li><a href="home"><span class="glyphicon glyphicon-home" style="margin-right:5px;"></span>Home</a></li>
                <li><a href="Logout"><span class="glyphicon glyphicon-off" style="margin-right:5px;"></span>Logout</a></li>
            </ul>
        </div>
    </nav>
    <div id="user-email" style="display:none;"><jsp:getProperty name="usrbn" property="uid"/></div>
    <div class="container-fluid" style="margin-top:20px;">
        <div class="row">
            <div class="col-sm-3">
                <div class="list-group special" style="margin-bottom: 0px;">
                    <div class="list-group-item active">Friends <span class="offline pull-right">Offline</span><span class="online pull-right" style="margin-right:10px;">Online</span></div>
                    <div class="list-group special mylistbox" id="friends">
                        
                    </div>                    
                </div>

            </div>
            <div class="col-sm-6">
                <div class="panel panel-default">
                    <div class="panel-heading">Search for User</div>
                    <div class="panel-body">
                        <div>
                            <form  onsubmit="return false" action="" role="form" style="margin-bottom:10px;">
                                <div class="row">
                                    <div class="col-sm-10">
                                        <input class="form-control" id="searchbox" type="text" name="key" placeholder="enter user nickname or email">
                                    </div>
                                    <div class="col-sm-2">
                                        <button class="btn btn-default" onclick="searchfriend()"><span class="glyphicon glyphicon-search"</span></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="list-group" style="margin-bottom:5px;" id="search-result">
                        </div>
                    </div>
                </div>
                <div id="chat-box-all" style="display:none;">
                    <div class="panel panel-info">
                        <div class="panel-heading" id="chat-heading"></div>
                        <div class="panel-body" style="padding:0px;">
                            <div id="chat-wrapper" class="mychatbox">
                                
                            </div>
                            <hr style="border-color: #bce8f1; width: 100%; margin-top: 5px; margin-bottom: 5px;"/>
                            <div style="padding-bottom: 10px; margin-bottom: 5px;">
                                <form onsubmit="return false" action="" role="form" style="padding-top: 0px;padding-left:10px; padding-right:10px; padding-bottom: 0px;">
                                    <div class="form-group">
                                        <label for="send-post">Write Message <small>(Max 200 Characters.)</small></label>
                                        <textarea class="form-control" rows="2" id="send-post"></textarea>
                                    </div>
                                    <button onclick="sendMessage()" class="btn btn-primary pull-right" style="margin-bottom:10px;">Send</button>
                                </form>                                
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="col-sm-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">Your Profile</div>
                    <div class="panel-body" id="user-profile-wrapper"></div>
                </div>
                <div class="panel panel-success">
                    <div id="friend-profile-header" class="panel-heading">No Friend User Selected</div>
                    <div id="friend-profile-wrapper" class="panel-body"></div>
                </div> 
            </div>
    </div>

        
<script src="include/bootstrap/js/jquery-2.1.3.min.js"></script>
<script src="include/bootstrap/js/bootstrap.min.js"></script>
<script src="include/json2.js"></script>
<script src="include/userui.js"></script>
</body>
</html>
