<%--
  Created by IntelliJ IDEA.
  User: solin
  Date: 9/7/2022
  Time: 3:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1,
  maximum-scale=5"
    />

    <link rel="icon" href="devchallenges.png" />

    <link
            rel="shortcut icon"
            type="image/x-icon"
            href="https://devchallenges.io/"
    />

    <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap"
            rel="stylesheet"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@300;400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="./style.css">
    <script src="https://kit.fontawesome.com/dad822c6bd.js" crossorigin="anonymous"></script>

</head>
<body>
    <form id="download-form"></form>
        <div class="card">
            <div class="header">
                <p><span><i class="fa-solid fa-circle-check"></i></span></p>
            </div>
            <div class="subheader">
                <p>Uploaded Successfully!</p>
            </div>
            <div class="image">
                <img id="output" src="./images/${imageName}">
            </div>
            <div class=" link">
                <input type="text" id="url-holder" value="${link}" readonly disabled>
                <button onclick="copylink()" class="copy-link">copy link</button></div>
        </div>
    </form>

</body>
<script>
    function copylink() {
        // Get the text field
        var copyText = document.getElementById("url-holder");

        // Select the text field
        copyText.select();
        copyText.setSelectionRange(0, 99999); // For mobile devices

        // Copy the text inside the text field
        navigator.clipboard.writeText(copyText.value);
    }
</script>
</html>
