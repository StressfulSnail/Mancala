<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mancala</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='css/game.css'/>" />
    </head>
    <body class="${game.playerTurn}">
        <p>Current Player: ${game.playerTurn}</p>
        <div id="game-board">
            <c:forEach var="pit" varStatus="loop" items="${game.board.pits}">
                <a href="<c:url value='/makeMove?pit=${loop.index}'/>"
                   id="pit-${loop.index}"
                   class="pit ${pit.owner} ${pit.isStore() ? 'store' : 'house'}">
                    <c:if test="${(pit.seeds > 0 and not pit.isStore()) or pit.isStore()}">
                        <span class="count">${pit.seeds}</span>
                    </c:if>
                </a>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/reset'/>"><button>End Game</button></a></p>
        <c:if test="${not empty badMove}">
            <p>Sorry! That's not a valid move!</p>
        </c:if>
    </body> 
</html>
