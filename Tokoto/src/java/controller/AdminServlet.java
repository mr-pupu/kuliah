/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Kategori;
import entity.Pelanggan;
import entity.PelangganOrder;
import entity.Produk;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.OrderManager;
import session.PelangganFacade;
import session.PelangganOrderFacade;
import session.ProdukFacade;

/**
 *
 * @author p14
 */
@WebServlet(name = "AdminServlet",
urlPatterns = {"/admin/",
    "/admin/lihatOrder",
    "/admin/lihatPelanggan",
    "/admin/produk",
    "/admin/lihatProduk",
    "/admin/tambahProduk",
    "/admin/recordPelanggan",
    "/admin/recordOrder",
    "/admin/recordProduk",
    "/admin/logout"})
@ServletSecurity(
@HttpConstraint(rolesAllowed = {"tokotoAdmin"}))
public class AdminServlet extends HttpServlet {

    @EJB
    private OrderManager orderManager;
    @EJB
    private PelangganFacade pelangganFacade;
    @EJB
    private PelangganOrderFacade pelangganOrderFacade;
    @EJB
    private ProdukFacade produkFacade;
    private String userPath;
    private Pelanggan pelanggan;
    private PelangganOrder order;
    private Produk produk;
    private List daftarOrder = new ArrayList();
    private List daftarPelanggan = new ArrayList();
    private List allProduk = new ArrayList();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        userPath = request.getServletPath();

        // if viewCustomers is requested
        if (userPath.equals("/admin/lihatPelanggan")) {
            daftarPelanggan = pelangganFacade.findAll();
            request.setAttribute("daftarPelanggan", daftarPelanggan);
        }

        // if viewOrders is requested
        if (userPath.equals("/admin/lihatOrder")) {
            daftarOrder = pelangganOrderFacade.findAll();
            request.setAttribute("daftarOrder", daftarOrder);
        }

        if (userPath.equals("/admin/lihatProduk")) {
            allProduk = produkFacade.findAll();
            request.setAttribute("allProduk", allProduk);
        }

        if (userPath.equals("/admin/produk")) {
            
        }

        if (userPath.equals("/admin/recordProduk")) {


            String idProduk = request.getQueryString();
            produk = produkFacade.find(Long.parseLong(idProduk));
            request.setAttribute("recordProduk", produk);

        }

        // if customerRecord is requested
        if (userPath.equals("/admin/recordPelanggan")) {

            // get customer id from request
            String idPelanggan = request.getQueryString();

            // get customer details
            pelanggan = pelangganFacade.find(Long.parseLong(idPelanggan));
            request.setAttribute("recordPelanggan", pelanggan);

            // get customer order details
            order = pelangganOrderFacade.findByPelanggan(pelanggan);
            request.setAttribute("order", order);
        }

        // if orderRecord is requested
        if (userPath.equals("/admin/recordOrder")) {

            // get customer id from request
            String idOrder = request.getQueryString();

            // get order details
            Map orderMap = orderManager.getOrderDetails(Long.parseLong(idOrder));

            // place order details in request scope
            request.setAttribute("pelanggan", orderMap.get("pelanggan"));
            request.setAttribute("produk", orderMap.get("produk"));
            request.setAttribute("recordOrder", orderMap.get("recordOrder"));
            request.setAttribute("orderProduk", orderMap.get("orderProduk"));
        }

        // if logout is requested
        if (userPath.equals("/admin/logout")) {
            session = request.getSession();
            session.invalidate();   // terminate session
            response.sendRedirect("/Tokoto/admin/");
            return;
        }

        // use RequestDispatcher to forward request internally
        userPath = "/admin/index.jsp";
        try {
            request.getRequestDispatcher(userPath).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        HttpSession session = request.getSession();

        if (userPath.equals("/admin/addProduk")) {

            try {
                String nama = request.getParameter("nama");
                String harga = request.getParameter("harga");
                String keterangan = request.getParameter("keterangan");
                String stok = request.getParameter("stok");
                String idKategori = request.getParameter("idKategori");
                Kategori kategori = new Kategori();
                kategori.setId(Short.parseShort(idKategori));

                Produk p = new Produk();
                p.setNama(nama);
                p.setHarga(BigDecimal.valueOf(Double.parseDouble(harga)));
                p.setStok(Long.parseLong(stok));
                p.setIdKategori(kategori);

                produkFacade.create(p);

                session.setAttribute("addProduk", p);

            } catch (Exception e) {
                e.printStackTrace();
            }

            userPath="admin/produk";


        }
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
