<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>

<html>
    <head>
    </head>
    <body>
        <jsp:useBean id="friend" scope="request" class="ar.com.egomusic.basiccrudmaven.model.Friend" />
        <jsp:useBean id="message" scope="request" class="java.lang.String" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <% if(message == null){
            message = ":";
            }
        %>
        <% if(friend.getName() != null){%>
        <h1>Usuario <%= message %></h1><BR>
        Nome: <jsp:getProperty name="friend" property="name" /><BR>
        Endereço: <jsp:getProperty name="friend" property="address" /><BR>
        Telefone: <jsp:getProperty name="friend" property="phone" /><BR>
        Idade: <jsp:getProperty name="friend" property="age" /><BR>
        Nível de amizade: <jsp:getProperty name="friend" property="friendshipLevel" /><BR>
        Comentários: <jsp:getProperty name="friend" property="comments" /><BR><BR>
       <%}
        else{ %>
        Operação realizada com sucesso.
        <%} %>
        <a href="/BasicCRUDMaven<%=application.getInitParameter("mainForm")%>">Voltar ao menu principal</a>
    </body>
</html>