<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="../Include/topmenu.jsp" %>

<html>
   <head><title> 자료 올리기 </title>
   </head> 
<script>
	function pds_send(){
		if(!pds.name.value){
			alert("이름을 입력하세요");
			pds.name.focus();
			return;
		}
/*		
		//파일용량 체크
		if(pds.filename.value){
			var size = pds.filename[0].files[0].size;
			if(size>(2*1024*1024)){
				alert("파일용량은 2M를 초과할수 없습니다");
				pds.filename.focus();
			}
		}
*/		
		alert("저장합니다");
		pds.submit();
	}
/*
 *  jQuery 사용시
 
	$(document).ready(function() {	
		$("#btn_send").click(function(){
			
			if($("#name").val()==''){
				alert("이름을 입력하세요");
				$("#name").focus();
				return;
			}
			if($("#subject").val()==''){
				alert("제목을 입력하세요");
				$("#subject").focus();
				return;
			}
			if($("#contents").val()==''){
				alert("내용을 입력하세요");
				$("#contents").focus();
				return;
			}
			//파일 선택시
			if($("#filename").val()){
				var size = $("#filename")[0].files[0].size;
				if(size>(2*1024*1024)){
					alert("파일용량은 2M을 초과할수 없습니다");
					$("#filename").focus();
				}
			}
			
			if($("#pass").val()==''){
				alert("비밀번호을 입력하세요");
				$("#pass").focus();
				return;
			}
			
			$("#pds").submit();
		});
		
		$("#btn_cancle").click(function(){
			history.back();
		});

	});
*/
</script>   
<body>
<link rel="stylesheet" type="text/css" href="stylesheet.css">

<table border="0" width="800">
 <tr>
   <td width="20%" height="500" bgcolor="#ecf1ef" valign="top">

	<!--     다음에 추가할 로그인  -->
	<jsp:include page="../Include/login_form.jsp" /> 

   </td>
   <td width="80%" valign="top">&nbsp;<br>
     <img src="/Images/img/bullet-01.gif"><font size="3" face="돋움" color="blue"> <b>참 좋은 자료실</b></font>
     <font size="2"> - 자료올리기</font><p>
     <img src="/Images/img/bullet-03.gif"><font size="2" face="돋움" color="orange"> 잠깐</font> &nbsp;
     <img src="/Images/img/bullet-02.gif"><font size="2" face="돋움">는 필수 입력 사항입니다.</font><p>
	<form name="pds" method="post" enctype="multipart/form-data" action="/Pds/pds_modify">
	<input type="hidden" name="idx" value="${pdsDTO.idx}">
	<input type="hidden" name="oldfilename" value="${pdsDTO.filename}">
	<input type="hidden" name="page" value="${page}">
	  <table border="0" >
		<tr>
         <td width="5%" align="right"><img src="/Images/img/bullet-02.gif"></td>
         <td width="15%"><font size="2" face="돋움">글쓴이</font></td>
         <td width="80%">
			<input type="text" size="20" name="name" value="${pdsDTO.name}" readonly></td>
		</tr>
		<tr> 
		  <td align="right">&nbsp;</td>
          <td><font size="2" face="돋움">메일주소</font></td>
		  <td><input type="text" size="20" name="email" value="${pdsDTO.email}"></td>
		</tr>	
       <tr>
         <td align="right"><img src="/Images/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">제목</font></td>
         <td><input type="text" size="60" name="subject" value="${pdsDTO.subject}"></td>
       </tr>
       <tr>
         <td align="right"><img src="/Images/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">내용</font></td>
         <td><textarea wrap="physical" rows="10" name="contents" cols="60">${pdsDTO.contents}</textarea></td>
       </tr>
		<tr>
		  <td align="right"><img src="/Images/img/bullet-02.gif"></td>
		  <td><font size="2" face="돋움">파일첨부</font></td>
		  <td><input type="file" name="filename" size="30"></td></tr>
		<tr>
       <tr>
         <td align="right"><img src="/Images/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">비밀번호</font></td>
          <td><input type="password" size="10" name="pass" > 
          <font size="2" face="돋움">*.게시글의 수정 및 삭제시 필요.</font>
         </td>
        </tr>

		<tr></tr>			<tr></tr>
          <td align="right">&nbsp;</td>
          <td><font size="2">&nbsp;</font></td>
          <td align=center>
			<img src="/Images/img/purple_save.gif" width="56" height="18" border="0" onClick="pds_send()">
			<img src="/Images/img/purple_cancle.gif" width="56" height="18" border="0" onClick="history.back()">

		  </td>
        </tr>
      </table>
      </form>
    </td>
  </tr>
 </table>
</body>		
</html>
