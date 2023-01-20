<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ page import="objet.Admin" %>
<%@ page import="objet.Commission" %>
<%@ page import="objet.Stat" %>
<%@ page import="java.text.NumberFormat" %>

<%
    double total = Double.parseDouble(request.getAttribute("total").toString());
    Commission c = (Commission) request.getAttribute("commission");
    Object[] a = (Object[]) request.getAttribute("stats");
    NumberFormat nf = NumberFormat.getInstance(new Locale("da","DK"));
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
                    <h3 class="text-dark mb-4">Statistiques</h3>
                    <div style="text-align: center;">Montant total en caisse: <%= nf.format(total) %></div>
                    <div class="text-end">Commission recuperer: <%= c.getPourcentage()*100 %> % <a href="change.commission"><button>Modifier</button></a></div>
                </div>
                <div class="card shadow">
                    <div class="card-body">
                        <div class="table-responsive table mt-2" id="dataTable-1" role="grid" aria-describedby="dataTable_info">
                            <table class="table my-0" id="dataTable" style="text-align: center;">
                                <thead>
                                    <tr>
                                        <th>Username</th>
                                        <th>Nom</th>
                                        <th>Prenom</th>
                                        <th>Montant rapporter</th>
                                        <th>Contribution (en %)</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    int i;
                                    for(i=0;i<a.length;i++){
                                    %>
                                    <tr>
                                        <td><%= ((Stat) a[i]).getUsername() %></td>
                                        <td><%= ((Stat) a[i]).getNom() %></td>
                                        <td><%= ((Stat) a[i]).getPrenom() %></td>
                                        <td><%= nf.format(((Stat) a[i]).getMontant()) %></td>
                                        <td><%= (((Stat) a[i]).getMontant()*100)/total %> %</td>
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