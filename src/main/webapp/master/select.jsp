<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/css/master.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div id="div1">
    <h1>Add Goods:</h1>
        <form class="form" name="fileform" method="post" action="/master/addGoods" enctype="multipart/form-data">
            <label for="itemname">Item Name:</label>
            <input type="text" name="itemname" size="50" placeholder="Enter Your Item Name" required/><br><br>
            <label for="photo"> Item Photo:  </label>
            <input type="file" name="photo" placeholder="Upload Your Image" required/><br><br>
            <input type="submit" value="Save">
        </form>
</div>
<div id="div2">
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
            <input type="submit" value="Save" >
        </form>
</div>
<div id="div3">
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
</div>
<div id="div4"><h1>Add Vehicle Owner Details</h1><br><br>
<form name="fileform" method="post" action="/master/addVehicleOwner" enctype="multipart/form-data">
            <label for="name">Owner Name:</label>
            <input type="text" name="name" size="50" placeholder="Enter Your Item Name" required/><br><br>
            <label for="number">Owner Number:</label>
            <input type="text" name="number" size="50" placeholder="Enter Your Item Number" required/><br><br>
            <label for="city">Owner City:</label>
            <input type="text" name="city" size="50" placeholder="Enter Your Item City" required/><br><br>
            <label for="photo"> Owner Identification:  </label>
            <input type="file" name="photo" placeholder="Upload Your Image" required/><br><br>
             <label for="vnumber">Owner Vehicle Number:</label>
            <input type="text" name="vnumber" size="50" placeholder="Enter Your Item City" required/><br><br>
            <input type="submit" value="Save">
        </form>
</div>


</body>
</html>
