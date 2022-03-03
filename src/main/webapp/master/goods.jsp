<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Item Name :
Item Added :
Photo of Item: 
Accepted:
 <h1>Add Goods:</h1>
        <form name="fileform" method="post" action="/master/addGoods" enctype="multipart/form-data">
            <label for="itemname">Item Name:</label>
            <input type="text" name="itemname" size="50" placeholder="Enter Your Item Name" required/><br><br>
            <label for="photo"> Item Photo:  </label>
            <input type="file" name="photo" placeholder="Upload Your Image" required/><br><br>
            <input type="submit" value="Save">
        </form>
</body>
</html>
