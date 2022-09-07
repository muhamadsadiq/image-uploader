<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
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

    <title>Devchallenges</title>
</head>
<body>
    <form action="image-downloader" method="post" id="input-form" enctype="multipart/form-data">
        <div class="card">
            <div class="header">
                <p>Upload your image</p>
            </div>
            <div class="subheader">
                <p>File should be jpeg,png,...</p>
            </div>
            <div class="image">
                <img src="./image.svg" alt="">
                <p>Drag & Drop your image here</p>
            </div>
            <p>Or</p>
            <label for="input-file" class="file-label">Choose a file</label>
            <input type="file" name="file" id="input-file">
        </div>
    </form>
</body>
<script>
    const inputfile = document.getElementById('input-file');

    inputfile.addEventListener('change', (event) => {
        document.getElementById("input-form").submit();
    });
</script>
</html>