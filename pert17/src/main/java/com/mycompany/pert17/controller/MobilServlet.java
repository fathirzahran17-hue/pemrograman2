package com.mycompany.pert17.controller;

import com.mycompany.pert17.dao.MobilDAO;
import com.mycompany.pert17.model.Mobil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mobil")
public class MobilServlet extends HttpServlet {
    private MobilDAO mobilDAO = new MobilDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "delete":
                String nopol = request.getParameter("nopol");
                mobilDAO.deleteMobil(nopol);
                response.sendRedirect("mobil");
                break;
            case "list":
            default:
                request.setAttribute("listMobil", mobilDAO.getAllMobil());
                request.getRequestDispatcher("/mobil.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "add";

        String nopol = request.getParameter("nopol");
        String merk = request.getParameter("merk");
        String tipe = request.getParameter("tipe");
        int tahun = Integer.parseInt(request.getParameter("tahun"));
        double harga = Double.parseDouble(request.getParameter("harga"));
        String status = request.getParameter("status");

        Mobil mobil = new Mobil(nopol, merk, tipe, tahun, harga, status);

        if ("add".equals(action)) {
            mobilDAO.addMobil(mobil);
        } else if ("edit".equals(action)) {
            mobilDAO.updateMobil(mobil);
        }
        response.sendRedirect("mobil");
    }
}
