package com.budiluhur.catalog.servlet;

import com.budiluhur.Product;
import com.budiluhur.ProductNotFoundException;
import com.budiluhur.ProductRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = new ProductRepository();
        productRepository.addProduct(new Product("PRD-01", "Keyboard Mechanical", 450000.0));
        productRepository.addProduct(new Product("PRD-02", "Mouse Wireless Silent", 175000.0));
        productRepository.addProduct(new Product("PRD-03", "Monitor Gaming 24 Inch", 2100000.0));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        String idParam = request.getParameter("id");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>Katalog Produk</title>");
        out.println("</head>");
        out.println("<body>");

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                Product product = productRepository.findById(idParam.trim());
                out.println("    <h2>=== DETAIL PRODUK ===</h2>");
                out.println("    <table border=\"1\" cellpadding=\"8\">");
                out.println("        <tr><th>ID Produk</th><td>" + product.getId() + "</td></tr>");
                out.println("        <tr><th>Nama Produk</th><td>" + product.getName() + "</td></tr>");
                out.println("        <tr><th>Harga Satuan</th><td>Rp " + String.format("%,.0f", product.getPrice()) + "</td></tr>");
                out.println("    </table>");
                out.println("    <p><a href=\"/products\">&laquo; Kembali ke Daftar Produk</a></p>");
            } catch (ProductNotFoundException e) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("    <h2 style=\"color: red;\">Error 404 - Produk Tidak Ditemukan</h2>");
                out.println("    <p style=\"color: red;\">" + e.getMessage() + "</p>");
                out.println("    <p><a href=\"/products\">&laquo; Kembali ke Daftar Produk</a></p>");
            }
        } else {
            out.println("    <h2>=== DAFTAR KATALOG PRODUK (WEB) ===</h2>");
            out.println("    <table border=\"1\" cellpadding=\"8\">");
            out.println("        <thead>");
            out.println("            <tr>");
            out.println("                <th>ID Produk</th>");
            out.println("                <th>Nama Produk</th>");
            out.println("                <th>Harga Satuan</th>");
            out.println("            </tr>");
            out.println("        </thead>");
            out.println("        <tbody>");

            List<Product> products = productRepository.findAll();
            for (Product p : products) {
                out.println("            <tr>");
                out.println("                <td>" + p.getId() + "</td>");
                out.println("                <td>" + p.getName() + "</td>");
                out.println("                <td>Rp " + String.format("%,.0f", p.getPrice()) + "</td>");
                out.println("            </tr>");
            }

            out.println("        </tbody>");
            out.println("    </table>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
