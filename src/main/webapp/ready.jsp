<%@ page import="com.example.imageuploader.dao.ImageDAOImp" %>
<%@ page import="com.example.imageuploader.dao.ImageDAO" %><%--
  Created by IntelliJ IDEA.
  User: solin
  Date: 9/8/2022
  Time: 1:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ready</title>
    <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap"
            rel="stylesheet"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="./globe.css">
    <link rel="stylesheet" href="./ready.css">

    <%
      ImageDAO imageDAO = new ImageDAOImp();
      String imageName= imageDAO.getImage(request.getParameter("id")).getImageName();
    %>

</head>
  <body>
          <div class="card">
            <div class="header">
              <p style="text-align: center;">Download Image</p>
            </div>

            <div class="image">
              <img id="output" src="./images/<%=imageName%>" alt="">
            </div>
            <div class="link">
              <a href="<%=request.getRequestURL().substring(0,request.getRequestURL().indexOf("ready"))+"download"+"?fileName="+imageName%>" target="_blank"><button class="downloadBtn">Download</button></a>
            </div>
          </div>

  </body>
</html>
