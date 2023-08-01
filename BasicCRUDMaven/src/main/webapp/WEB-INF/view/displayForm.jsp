<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>
    <head>
    </head>
    <body>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <h1>Dados do usuario: </h1>
        <jsp:useBean id="friend" scope="request" class="ar.com.egomusic.basiccrudmaven.model.Friend" />
        Nome: <jsp:getProperty name="friend" property="name" /><BR>
        Endereço: <jsp:getProperty name="friend" property="address" /><BR>
        Telefone: <jsp:getProperty name="friend" property="phone" /><BR>
        Idade: <jsp:getProperty name="friend" property="age" /><BR>
        Nível de amizade: <jsp:getProperty name="friend" property="friendshipLevel" /><BR>
        Comentários: <jsp:getProperty name="friend" property="comments" /><BR>
        
        <a href="/BasicCRUDMaven<%=application.getInitParameter("mainForm")%>">Voltar ao menu principal</a>

    </body>
</html>
