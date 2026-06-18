package com.mycompany.pert17.controller;

import com.mycompany.pert17.dao.CustomerDAO;
import com.mycompany.pert17.model.Customer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "delete":
                String nik = request.getParameter("nik");
                customerDAO.deleteCustomer(nik);
                response.sendRedirect("customer");
                break;
            case "list":
            default:
                request.setAttribute("listCustomer", customerDAO.getAllCustomer());
                request.getRequestDispatcher("/customer.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "add";

        String nik = request.getParameter("nik");
        String nama = request.getParameter("nama");
        String alamat = request.getParameter("alamat");
        String telp = request.getParameter("telp");

        Customer customer = new Customer(nik, nama, alamat, telp);

        if ("add".equals(action)) {
            customerDAO.addCustomer(customer);
        } else if ("edit".equals(action)) {
            customerDAO.updateCustomer(customer);
        }
        response.sendRedirect("customer");
    }
}
