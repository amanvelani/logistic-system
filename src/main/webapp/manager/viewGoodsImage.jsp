<!DOCTYPE html>
<html>
<body>
<%
String id = (String)request.getAttribute("id");
%>
<a href="/manager/showGoods"><button style="width:150px; height:30px"> Back </button></a>
<br>
<br>
<br>


<img src="/images/goods/<%=id%>.jpg"  style="width:400px; height:750px; padding-left:450px ">

</body>
</html>


