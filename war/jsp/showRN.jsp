<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${it.reqList}" var="req"> 
<p> reciever:   <c:out value="${req.SenderEmail}"> </c:out> </p><br>
<p> message:   <c:out value="${req.Status}"> </c:out> </p><br>

</c:forEach>
</body>
</html>