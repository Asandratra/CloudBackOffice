<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ page import="objet.DemandeRecharge" %>
<%@ page import="java.text.NumberFormat" %>

<%
    Object[] d = (Object[]) request.getAttribute("demandes");
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
                    <h3 class="text-dark mb-4">Demandes de recharge</h3>
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
                                        <th>Montant</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    int i;
                                    for(i=0;i<d.length;i++){
                                    %>
                                    <tr>
                                        <td><%= ((DemandeRecharge) d[i]).getUsername() %></td>
                                        <td><%= ((DemandeRecharge) d[i]).getNom() %></td>
                                        <td><%= ((DemandeRecharge) d[i]).getPrenom() %></td>
                                        <td><%= nf.format(((DemandeRecharge) d[i]).getMontant()) %></td>
                                        <td><a href="confirm.demande?id=<%= ((DemandeRecharge) d[i]).getIdDemandeRecharge() %>"><button>Confirmer</button></a></td>
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