<%-- 
    Document   : error
    Created on : 2015-12-13, 12:39:25
    Author     : chiming
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="include/bootstrap/css/bootstrap.min.css"/>
    <title>Oops...</title>
    <%
        String errmsg=(String)request.getAttribute("errmsg");
        if(errmsg==null)
            errmsg="Unexpected Error";
        String redirect=(String)request.getAttribute("redirect");
        if(redirect==null)
            redirect="home";
        String target=(String)request.getAttribute("target");
        if(target!=null)
            redirect=target;
                
        response.setHeader("Refresh", "5; URL="+redirect);
        
    %>
</head>
<body>

    <div class="container" style="height:100%;">
        <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-4">
                <img class="img-responsive center-block" src="include/shy-icon.png" alt="Sorry" style="margin-top:50%;"></img>
                <%--img source:http://icons.iconarchive.com/icons/bad-blood/yolks-2/256/shy-icon.png--%>
            </div>
            <div class="col-sm-6">
                <h3 class="text-center text-danger" style="margin-top:50%;"><%=errmsg%></h3>
                <h4 class="text-center text-warning">redirecting...</h4>
            </div>
            <div class="col-sm-1"></div>
        </div>
    </div>
    <script src="include/bootstrap/js/jquery-2.1.3.min.js"></script>
    <script src="include/bootstrap/js/bootstrap.min.js"></script>    
</body>
</html>
