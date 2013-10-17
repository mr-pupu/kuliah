/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Kategori;
import entity.Produk;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.KategoriFacade;
import session.OrderManager;
import session.ProdukFacade;
import tas.TasBelanja;
import validasi.Validator;


/**
 *
 * @author p14
 */
@WebServlet(name = "ControllerServlet",
            loadOnStartup = 1,
            urlPatterns = {"/kategori",
                           "/tambahKeTas",
                           "/lihatTas",
                           "/updateTas",
                           "/order",
                           "/pembelian",
                           "/chooseLanguage"})
public class ControllerServlet extends HttpServlet {

    private String surcharge;

    @EJB
    private KategoriFacade kategoriFacade;
    @EJB
    private ProdukFacade produkFacade;
    @EJB
    private OrderManager orderManager;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        super.init(servletConfig);
        surcharge = servletConfig.getServletContext().getInitParameter("deliverySurcharge");
        //mendaftar kategori list ke servlet context
        //untukberkomunikasi dengan pemanggailnya
        getServletContext().setAttribute("kategori2", kategoriFacade.findAll());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        Kategori pilihKategori;
        Collection<Produk> kategoriProduk;

        // if kategori page is requested
        if (userPath.equals("/kategori")) {
            // get id_kategori
            String idKategori = request.getQueryString();

            if (idKategori != null){
                // get kategori
                pilihKategori = kategoriFacade.find(Short.parseShort(idKategori ));
                session.setAttribute("pilihKategori", pilihKategori);

                // get produk
                kategoriProduk = pilihKategori.getProdukCollection();
                session.setAttribute("kategoriProduk", kategoriProduk);
            }

        // if cart page is requested
        } else if (userPath.equals("/lihatTas")) {
            String clear = request.getParameter("clear");

            if ((clear != null) && clear.equals("true")) {

                TasBelanja tas = (TasBelanja) session.getAttribute("tas");
                tas.clear();
            }

            userPath = "/tas";

        // if order page is requested
        } else if (userPath.equals("/order")) {
            TasBelanja tas = (TasBelanja) session.getAttribute("tas");

            // calculate total
            tas.calculateTotal(surcharge);


        // if user switches language
        } else if (userPath.equals("/chooseLanguage")) {
            // TODO: Implement language request

        }

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

        request.setCharacterEncoding("UTF-8");

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        TasBelanja tas = (TasBelanja) session.getAttribute("tas");
        Validator validator = new Validator();

        // if tambahKeTas action is called
        if (userPath.equals("/tambahKeTas")) {
            // if user is adding item to cart for first time
            // create cart object and attach it to user session
            if (tas == null) {

                tas = new TasBelanja();
                session.setAttribute("tas", tas);
            }

            // get user input from request
            String idProduk = request.getParameter("idProduk");

            if (!idProduk.isEmpty()) {

                Produk produk = produkFacade.find(Long.parseLong(idProduk));
                if (produk.getStok()!=0)
                    tas.addItem(produk);
                else
                    session.setAttribute("stokEmpty", "maaf stok kosong !");
            }

            userPath = "/kategori";

            // if updateTas action is called
        } else if (userPath.equals("/updateTas")) {
            // get input from request
            String idProduk = request.getParameter("idProduk");
            String quantity = request.getParameter("quantity");

            boolean invalidEntry = validator.validateQuantity(idProduk, quantity);

            if (!invalidEntry) {

                Produk produk = produkFacade.find(Long.parseLong(idProduk));
                if(produk.getStok() >= Integer.parseInt(quantity))
                    tas.update(produk, quantity);
                else
                    session.setAttribute("maxUpdate", "Stok tersedia : "+produk.getStok());
            }


            userPath = "/tas";

            // if pembelian action is called
        } else if (userPath.equals("/pembelian")) {

            if (tas != null) {

                // extract user data from request
                String nama = request.getParameter("nama");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String alamat = request.getParameter("alamat");

                boolean validationErrorFlag = false;
                validationErrorFlag = validator.validateForm(nama, email, phone, alamat, request);


                if (validationErrorFlag == true){
                    request.setAttribute("validationErrorFlag", validationErrorFlag);
                    userPath = "/order";
                } else {

                    long idOrder = orderManager.placeOrder(nama, email, phone,alamat, tas);

                    // update stok = piye

                    if(idOrder != 0){
                        tas = null;
                        session.invalidate();

                        //OrderDetail
                        Map orderMap = orderManager.getOrderDetails(idOrder);

                        request.setAttribute("pelanggan", orderMap.get("pelanggan"));
                        request.setAttribute("produk", orderMap.get("produk"));
                        request.setAttribute("recordOrder", orderMap.get("recordOrder"));
                        request.setAttribute("orderProduk", orderMap.get("orderProduk"));

                        userPath = "/konfirmasi";

                    } else {
                        userPath = "/order";
                        request.setAttribute("orderFailureFlag", true);
                    }
                }

            }

        }

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}