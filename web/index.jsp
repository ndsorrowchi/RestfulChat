<%-- 
    Document   : index
    Created on : 2016-2-29, 15:15:20
    Author     : chiming
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.sql.*,beans.userbean"%>
<!DOCTYPE html>
<html>
<head>
    <title>Social Restful Demo</title>
    <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width,height=device-height, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="include/bootstrap/css/bootstrap.min.css"/>
    <%
        if(session.getAttribute("usrbn")!=null)
        {
            userbean ub=(userbean)session.getAttribute("usrbn");
            if(ub.getUname()!=null&&ub.getUid()!=null)
            {
                response.setHeader("Refresh", "1; URL=home");
            }
        }      
    %>    
</head>
<body onload="initfield()">
    <div style="width:100%;">
        <div class="container">
            <div class="text-center">
                <h1 style="color: #23527c"><big>RestSocial</big></h1>
                <h4 style="color: #23527c"><big>A Social site combining servlet and restful APIs.</big></h4>
            </div>    
            <div class="row">
                <div class="col-sm-4"></div>
                <div class="col-sm-4">
                    <ul class="nav nav-tabs" style="margin-top:20px;">
                        <li class="active"><a data-toggle="tab" href="#tab-login">Login</a></li>
                        <li><a data-toggle="tab" href="#tab-register">Register</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-login" class="tab-pane fade in active">
                            <br/>
                            <form role="form" action="Login" method="post">
                                <div class="form-group">
                                    <label for="login-username">Email Address</label>
                                    <input id="login-username" type="email" name="username" maxlength="40" class="form-control" required />
                                </div>
                                <div class="form-group">
                                    <label for="login-password">Password</label>
                                    <input id="login-password" type="password" name="password" maxlength="20" class="form-control" required />
                                </div>
                                <input type="submit" value="sign in" class="btn btn-primary pull-right"/>
                            </form>
                        </div>
                        <div id="tab-register" class="tab-pane fade">
                            <br/>
                            <form role="form" action="Register" method="post">
                                <div class="form-group">
                                    <label for="register-username">Email Address</label>
                                    <input id="register-username" onblur="checkit()" maxlength="40" type="email" name="username" class="form-control" required />
                                    <div id="chkemail" style="display: inline"><span></span></div>
                                </div>
                                <div class="form-group">
                                    <label for="register-nickname">Display Name</label>
                                    <input id="register-nickname" type="text" name="nickname" maxlength="30" class="form-control" required />
                                </div>
                                <div class="form-group">
                                    <label for="register-password">Password</label>
                                    <input maxlength="20" id="register-password" type="password" name="password" class="form-control" required />
                                </div>
                                <div class="form-group">
                                    <label for="confirm-password">Confirm Password</label>
                                    <input maxlength="20" id="confirm-password" type="password" name="confirm-password" class="form-control" required />
                                </div>
                                <div class="form-group">
                                    <label style="margin-right: 10px;">Gender</label><br>
                                    <label class="radio-inline"><input type="radio" name="gender" value="male" checked>male</label>
                                    <label class="radio-inline"><input type="radio" name="gender" value="female">female</label>
                                </div>
                                <div class="form-group">
                                    <label for="yob">Year of Birth (yyyy)</label>
                                    <input id="yob" type="number" name="yob" min="1950" max="2016" class="form-control" required />
                                </div>
                                <input id="submitbtn" type="submit" value="sign up" class="btn btn-success pull-right"/>
                            </form>                        
                        </div>
                    </div>
                </div>
                <div class="col-sm-4"></div>
            </div>
        </div>
            
    </div>

<script src="include/myscript.js"></script>       
<script src="include/bootstrap/js/jquery-2.1.3.min.js"></script>
<script src="include/bootstrap/js/bootstrap.min.js"></script>
<script src="include/json2.js"></script>

</body>
</html>
