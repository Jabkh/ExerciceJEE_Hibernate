package org.example.exerciceProduct.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exerciceProduct.model.Product;
import org.example.exerciceProduct.service.ProductService;
import org.example.exerciceProduct.util.HibernateSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "productServlet", value = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService(HibernateSession.getSessionFactory());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo().substring(1);
        switch (action) {
            case "list":
                showAll(req, resp);
                break;
            case "addForm":
                showForm(req, resp, "add");
                break;
            case "editForm":
                showForm(req, resp, "edit");
                break;
            case "add":
                add(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "detail":
                showDetails(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", productService.getProducts());
        request.getRequestDispatcher("/WEB-INF/products/list.jsp").forward(request, response);
    }

    protected void showForm(HttpServletRequest request, HttpServletResponse response, String mode) throws ServletException, IOException {
        if (mode.equals("add")) {
            Product productToAdd = new Product("", "", LocalDate.now(), 0.0, 0);
            request.setAttribute("product", productToAdd);
        } else if (mode.equals("edit")) {
            int productId = Integer.parseInt(request.getParameter("id"));
            Product productToUpdate = productService.getProduct(productId);
            request.setAttribute("product", productToUpdate);
        }
        request.setAttribute("mode", mode);
        request.getRequestDispatcher("/WEB-INF/products/productForm.jsp").forward(request, response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String brand = request.getParameter("brand");
        String reference = request.getParameter("reference");
        LocalDate purchaseDate = LocalDate.parse(request.getParameter("purchaseDate"));
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        productService.createProduct(brand, reference, purchaseDate, price, stock);
        response.sendRedirect("list");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product productToUpdate = productService.getProduct(id);

        if (productToUpdate != null) {
            productToUpdate.setBrand(request.getParameter("brand"));
            productToUpdate.setReference(request.getParameter("reference"));
            productToUpdate.setPurchaseDate(LocalDate.parse(request.getParameter("purchaseDate")));
            productToUpdate.setPrice(Double.parseDouble(request.getParameter("price")));
            productToUpdate.setStock(Integer.parseInt(request.getParameter("stock")));

            productService.updateProduct(productToUpdate);
        }

        response.sendRedirect("list");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteProduct(id);
        response.sendRedirect("list");
    }

    protected void showDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProduct(productId);
        request.setAttribute("product", product);
        request.setAttribute("mode", "view");
        request.getRequestDispatcher("/WEB-INF/products/productForm.jsp").forward(request, response);
    }
}
