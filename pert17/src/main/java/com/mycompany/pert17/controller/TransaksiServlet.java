package com.mycompany.pert17.controller;

import com.mycompany.pert17.dao.TransaksiDAO;
import com.mycompany.pert17.dao.MobilDAO;
import com.mycompany.pert17.dao.CustomerDAO;
import com.mycompany.pert17.model.Transaksi;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/transaksi")
public class TransaksiServlet extends HttpServlet {
    private TransaksiDAO transaksiDAO = new TransaksiDAO();
    private MobilDAO mobilDAO = new MobilDAO();
    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "kembali":
                int id = Integer.parseInt(request.getParameter("id"));
                String nopol = request.getParameter("nopol");
                transaksiDAO.returnMobil(id, nopol);
                response.sendRedirect("transaksi");
                break;
            case "laporan":
                request.setAttribute("listTransaksi", transaksiDAO.getAllTransaksi());
                request.getRequestDispatcher("/laporan.jsp").forward(request, response);
                break;
            case "list":
            default:
                request.setAttribute("listTransaksi", transaksiDAO.getAllTransaksi());
                request.setAttribute("listMobil", mobilDAO.getAllMobil());
                request.setAttribute("listCustomer", customerDAO.getAllCustomer());
                request.getRequestDispatcher("/transaksi.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nikCustomer = request.getParameter("nikCustomer");
        String nopolMobil = request.getParameter("nopolMobil");
        Date tglSewa = Date.valueOf(request.getParameter("tglSewa"));
        Date tglKembali = null; // null because it's not returned yet, or we set to expected return
        String status = "Aktif";
        
        // Simple total harga logic
        double hargaMobil = mobilDAO.getMobil(nopolMobil).getHarga();
        double totalHarga = hargaMobil; // default 1 day for simplicity if no return date yet
        
        String tglKembaliStr = request.getParameter("tglKembali");
        if (tglKembaliStr != null && !tglKembaliStr.isEmpty()) {
             tglKembali = Date.valueOf(tglKembaliStr);
             long diff = tglKembali.getTime() - tglSewa.getTime();
             int days = (int) (diff / (1000 * 60 * 60 * 24));
             if (days <= 0) days = 1;
             totalHarga = hargaMobil * days;
        }

        Transaksi trx = new Transaksi(0, nikCustomer, nopolMobil, tglSewa, tglKembali, totalHarga, status);
        transaksiDAO.addTransaksi(trx);

        response.sendRedirect("transaksi");
    }
}
