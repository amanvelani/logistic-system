<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
company
model
number
regstate
photo
<h1>Add Vehicle to Database</h1>
        <form name="fileform" method="post" action="/master/addVehicle" enctype="multipart/form-data">
            <label for="company">Car Company:</label>
            <input type="text" name="company" size="50" placeholder="Enter Your Car Company Name" required/><br><br>
            <label for="model">Car Model:</label>
            <input type="text" name="model" size="50" placeholder="Enter Your Car Model Name" required/><br><br>
            <label for="number">Car Number:</label>
            <input type="text" name="number" size="50" placeholder="Enter Your Car Regisetered Number" required/><br><br>
            <label for="regstate">Car Registered State:</label>
            <input type="text" name="regstate" size="50" placeholder="Enter Your Car Registered State" required/><br><br>
            <label for="photo"> RC Book Photo:  </label>
            <input type="file" name="photo" placeholder="Upload Your Image" required/><br><br>
            <input type="submit" value="Save">
        </form>
</body>
</html>