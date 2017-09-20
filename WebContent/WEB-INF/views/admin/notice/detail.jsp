<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../../css/reset.css" type="text/css" rel="stylesheet" />
<link href="../../css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
   <jsp:include page="../../inc/header.jsp"></jsp:include>
   <div id="visual">
      <div class="content-container">
      </div>
   </div>
   

   
   <div id="body" class="clearfix">
      <div class="content-container">      
      
      <aside id="aside">
         
         <!-- <h1>고객센터</h1>
         
         <nav>
            <h1>고객센터 메뉴</h1>
            <ul>
               <li><a href="">공지사항</a></li>
               <li><a href="">1:1고객문의</li>
               <li><a href="">학습안내</li>
            </ul>
         </nav>
      
         <nav>
            <h1>추천사이트</h1>
            <ul>
               <li>앤서이즈</li>
               <li>W3C</li>
               <li>마이크로소프트</li>
            </ul>
         </nav>
         
         <nav>
            <h1>구글광고</h1>
         </nav> -->
        </aside>
        
        <main id="main">
        
        
        
        
        
        <!--  <h2>공지사항</h2>
         
         <div>
            <h3>경로</h3>
            <ol>
               <li>home</li>
               <li>고객센터</li>
               <li>공지사항</li>
            </ol>
         </div>
   
         <div>
            <h3>공지사항 검색 폼</h3>
            <form action="notice" method="get">
               <label>검색어</label>
               <input type="text" name="title" />
               <input type="submit" />
            </form>
         </div> -->
        <%--  <table border="1">
            <tr>
               <th>번호</th>
               <th>제목</th>
               <th>작성자</th>
               <th>작성일</th>
               <th>조회수</th>
            </tr>
            <tr>
               <td>${notice.id}</td>
               <td>${notice.title}</td>
               <td> </td>
               <td>  </td>
               <td>  </td>         
            </tr>
         </table> --%>
       	<%--  <table border="1">
            <tr>
               <th>제목</th>
               <td colspan="5">${notice.title}</td>
            </tr>
            <tr>
            <th>
            작성일</th>
            <td colspan="3">${notice.writerId}
            </td>
            <th>조회수</th>
            <td>${notice.hit}</td>
            
            </tr>
        <tr>
         <th>첨부파일</th>
            <td colspan="3"></td>
            </tr>
            <tr>
            <td colspan="7">${notice.content}</td> 
            </tr>
         </table>
       	 --%>
       	
       	 <table class="table table-list">
            <tr>
               <th>제목</th>
               <td colspan="5">${notice.title}</td>
            </tr>
            <tr>
           <th>
           	 작성일</th>
            <td colspan="3">${notice.regDate}
            </td>
            <th>조회수</th>
            <td>${notice.hit}</td>
            </tr>
            
            <tr><th>첨부파일</th><td colspan="3"></td><th>작성자</th><td>${notice.writerId}</td></tr>
            <tr>
            <td colspan="7">${notice.content}</td> 
            </tr>
         </table>
       	
       	
       	
       	<div><a href="list" class="btn btn-default">목록</a><a href="edit?id=${notice.id}" class="btn btn-default">수정</a><a href="notice-del" class="btn btn-default">삭제</a></div>
       	
      </main>   
      </div>
   </div>
   
   <footer id="footer">
      <div class="content-container">
      </div>
   </footer>
   
</body>
</html>