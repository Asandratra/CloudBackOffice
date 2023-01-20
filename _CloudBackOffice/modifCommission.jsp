<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ page import="objet.Commission" %>

<%
    Commission c = (Commission) request.getAttribute("commission");
%>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="refactor/head.jsp" />
<body>
    <jsp:include page="refactor/navbar.jsp" />
    <main class="page registration-page">
        <section class="clean-block clean-form dark">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">Modif pourcentage de commission</h2>
                </div>
                <form action="themes" method="post">
                    <div class="mb-3"><label class="form-label" for="name">Pourcentage</label><input class="form-control" type="number" name="pourcentage" max="100" min="0" value=<%= c.getPourcentage()*100 %> ></div><input type="submit" value="Confirmer">
                </form>
            </div>
        </section>
    </main>
    <jsp:include page="refactor/footer.jsp" />
</body>
<jsp:include page="refactor/script.jsp" />
</html>