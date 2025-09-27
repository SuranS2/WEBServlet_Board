<%@page import="kr.or.bit.service.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	/* 
	servelet 에서 사용하는 정상적인 방법
	String writer = request.getParameter("writer");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	String email = request.getParameter("email");
	String homepage = request.getParameter("homepage");
	String filename = request.getParameter("filename");
	String pwd = request.getParameter("pwd"); 
	
	Board board = new Board();
	board.setSubject(subject)......
	*/
%>

<jsp:forward page="redirect.jsp"></jsp:forward>











