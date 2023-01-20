<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="refactor/head.jsp" />
<body>
    <jsp:include page="refactor/navbar.jsp" />
    
    <main class="page registration-page">
        <section class="clean-block clean-form dark">
            <div class="container">
                <div class="block-heading">
                    <h2 class="text-info">Nouvelle Categorie</h2>
                </div>
                <form action="themes" method="post">
                    <div class="mb-3"><label class="form-label" for="name">Nom Categorie</label><input class="form-control" type="text" name="nom"></div><input type="submit" value="Ajouter">
                </form>
            </div>
        </section>
    </main>
    <jsp:include page="refactor/footer.jsp" />
</body>
<jsp:include page="refactor/script.jsp" />
</html>