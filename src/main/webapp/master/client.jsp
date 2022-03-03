<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Name 
Number
City
Type
Activated
Aadhar Card/Pan

<h1>Add Client to Database</h1>
        <form name="fileform" method="post" action="/master/addClient" enctype="multipart/form-data">
            <label for="name">Client Name:</label>
            <input type="text" name="name" size="50" placeholder="Enter Your Item Name" required/><br><br>
            <label for="number">Client Number:</label>
            <input type="text" name="number" size="50" placeholder="Enter Your Item Number" required/><br><br>
            <label for="city">Client City:</label>
            <input type="text" name="city" size="50" placeholder="Enter Your Item City" required/><br><br>
            <label for="photo"> Client Identification:  </label>
            <input type="file" name="photo" placeholder="Upload Your Image" required/><br><br>
            <input type="submit" value="Save">
        </form>
</body>
</html>