package com.budiluhur.catalog;

import com.budiluhur.catalog.servlet.CategoryServlet;
import com.budiluhur.catalog.servlet.ProductServlet;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());

        Tomcat.addServlet(ctx, "ProductServlet", new ProductServlet());
        ctx.addServletMappingDecoded("/products", "ProductServlet");

        Tomcat.addServlet(ctx, "CategoryServlet", new CategoryServlet());
        ctx.addServletMappingDecoded("/categories", "CategoryServlet");

        System.out.println("Server berhasil berjalan di: http://localhost:8080/products");
        System.out.println("Akses daftar kategori di: http://localhost:8080/categories");

        tomcat.start();
        tomcat.getServer().await();
    }
}
