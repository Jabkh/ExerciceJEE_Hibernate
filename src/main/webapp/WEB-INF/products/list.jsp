<%@ page import="org.example.exerciceProduct.model.Product" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/06/2024
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="products" type="java.util.ArrayList<org.example.exerciceProduct.model.Product>" scope="request" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/bootstrap-includes.html" %>
    <title>Products List</title>
</head>
<body>
<main class="container">
    <div class="row my-3">
        <div class="col-8 offset-2 rounded text-bg-grey p-3">
            <h1 class="fw-light">- Products List -</h1>
            <hr>
            <% if (!products.isEmpty()) { %>
            <table class="table table-dark align-middle table-striped text-center">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Brand</th>
                    <th>Reference</th>
                    <th>PurchaseDate</th>
                    <th>Price</th>
                    <th>Stock</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <% for (Product p : products) { %>
                <tr>
                    <td><%= p.getId() %></td>
                    <td><%= p.getBrand() %></td>
                    <td><%= p.getReference() %></td>
                    <td><%= p.getPurchaseDate().toString() %></td>
                    <td><%= p.getPrice() %></td>
                    <td><%= p.getStock() %></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/product/detail?id=<%= p.getId() %>&mode=view"
                           class="btn btn-outline-info"><i class="bi bi-eye"></i> View</a>
                        <a href="${pageContext.request.contextPath}/product/detail?id=<%= p.getId() %>&mode=edit"
                           class="btn btn-outline-warning"><i class="bi bi-pencil"></i> Update</a>
                        <form action="${pageContext.request.contextPath}/product/delete" method="post" name="deleteForm_<%= p.getId() %>">
                            <input type="hidden" name="id" value="<%= p.getId() %>">
                            <button type="submit" class="btn btn-outline-danger" onclick="return confirm('Are you sure you want to delete this product?');"><i class="bi bi-trash"></i> Delete</button>
                        </form>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <%  } else { %>
            <p>There are no products in the database yet!</p>
            <%  }  %>
            <hr>
            <div class="text-end">
                <a href="${pageContext.request.contextPath}/product/addForm" class="btn btn-outline-success"><i
                        class="bi bi-plus-circle"></i> Add a Product</a>
            </div>
        </div>
    </div>
</main>
</body>
</html>
