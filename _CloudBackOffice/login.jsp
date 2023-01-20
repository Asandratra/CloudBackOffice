<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="refactor/head.jsp" />
<body>
    <main class="page login-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h4>Log in</h4>
                <%
                if (request.getAttribute("message")!=null){
                    out.println("<p style='color:red;'>"+request.getAttribute("message").toString()+"</p>");
                }
                %>
            </div>
                <form action="start" method="post">
                    <div class="mb-3"><label class="form-label">Admin Name</label><input class="form-control" type="text" name="username" value="admin"></div>
                    <div class="mb-3"><label class="form-label" for="password">Password</label><input class="form-control" type="password" name="mdp" value="admin"></div>
                    <div class="mb-3"></div><button class="btn btn-primary" type="submit">Log In</button>
                </form>
            </div>
        </section>
        <jsp:include page="refactor/footer.jsp" />
    </main>
</body>
<jsp:include page="refactor/script.jsp" />
</html>