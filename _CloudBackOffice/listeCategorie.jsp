<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ page import="objet.Categorie" %>

<%
    Object[] c = (Object[]) request.getAttribute("categories");
%>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="refactor/head.jsp" />
<body>
    <jsp:include page="refactor/navbar.jsp" />
    <main class="page landing-page">
        <section class="clean-block clean-gallery dark">
            <div class="container">
                <div class="block-heading">
                    <h3 class="text-dark mb-4">Categorie</h3>
                    <div class="text-end"><a href="add.categorie"><button>+ Nouvelle categorie</button></a></div>
                </div>
                <div class="card shadow">
                    <div class="card-body">
                        <div class="table-responsive table mt-2" id="dataTable-1" role="grid" aria-describedby="dataTable_info">
                            <table class="table my-0" id="dataTable" style="text-align: center;">
                                <thead>
                                    <tr>
                                        <th>N</th>
                                        <th>Nom</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    int i;
                                    for(i=0;i<c.length;i++){
                                    %>
                                    <tr>
                                        <td><%= ((Categorie) c[i]).getId() %></td>
                                        <td><%= ((Categorie) c[i]).getNom() %></td>
                                    </tr>
                                    <%
                                    }
                                    %>
                                </tbody>
                                <tfoot>
                                    <tr></tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <jsp:include page="refactor/footer.jsp" />
</body>
<jsp:include page="refactor/script.jsp" />
</html>