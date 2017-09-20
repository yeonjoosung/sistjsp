<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/reset.css" type="text/css" rel="stylesheet" />
<link href="../css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
   <jsp:include page="../inc/header.jsp"></jsp:include>
   <jsp:include page="inc/visual.jsp"></jsp:include>
   
   <div id="body" class="clearfix">
      <div class="content-container">      
      
      <jsp:include page="inc/aside.jsp"></jsp:include>
      
        
        <main id="main">
        
        
       <form method="post">
         <fieldset>
            <legend>로그인</legend>
            <table>
               <tbody>
                  <tr>
                     <th><label>아이디</label></th>
                     <td>
                        <input name="id" value=""/>
                     </td>
                  </tr>
                  <tr>
                     <th><label>비밀번호</label></th>
                     <td><input name="pwd" /></td>
                  </tr>
                  <tr>                     
                     <td><input type="submit" name="btn" value="확인" /></td>
                  </tr>
               </tbody>
            </table>
         </fieldset>
      </form>
      
      
      </main>   
      </div>
   </div>
   <jsp:include page="../inc/footer.jsp"></jsp:include>
   
</body>
</html>