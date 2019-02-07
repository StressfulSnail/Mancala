<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mancala</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='css/dialog.css'/>" />
    </head>
    <body>
        <c:choose>
            <c:when test="${empty game.winner}">
                <h1>It's a Tie!</h1>
            </c:when>
            <c:otherwise>
                <h1>${game.winner} Won!</h1>
            </c:otherwise>
        </c:choose>
                <p>
                    Final Scores:
                    Red: ${game.redScore}
                    Blue: ${game.blueScore}
                </p>
        <a href="<c:url value='/reset'/>">Play Again!</a>
    </body>
</html>
