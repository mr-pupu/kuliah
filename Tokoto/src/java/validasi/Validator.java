/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validasi;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author p14
 */
public class Validator {


    public boolean validateQuantity(String idProduk, String quantity) {

        boolean errorFlag = false;

        if (!idProduk.isEmpty() && !quantity.isEmpty()) {

            int i = -1;

            try {

                i = Integer.parseInt(quantity);
            } catch (NumberFormatException nfe) {

                System.out.println("User did not enter a number in the quantity field");
            }

            if (i < 0 || i > 99) {

                errorFlag = true;
            }
        }

        return errorFlag;
    }


    public boolean validateForm(String nama,
            String email,
            String phone,
            String alamat,

            HttpServletRequest request) {

        boolean errorFlag = false;
        boolean namaError;
        boolean emailError;
        boolean phoneError;
        boolean alamatError;


        if (nama == null
                || nama.equals("")
                || nama.length() > 45) {
            errorFlag = true;
            namaError = true;
            request.setAttribute("namaError", namaError);
        }
        if (email == null
                || email.equals("")
                || !email.contains("@")) {
            errorFlag = true;
            emailError = true;
            request.setAttribute("emailError", emailError);
        }
        if (phone == null
                || phone.equals("")
                || phone.length() < 7) {
            errorFlag = true;
            phoneError = true;
            request.setAttribute("phoneError", phoneError);
        }
        if (alamat == null
                || alamat.equals("")
                || alamat.length() > 45) {
            errorFlag = true;
            alamatError = true;
            request.setAttribute("alamatError", alamatError);
        }


        return errorFlag;
    }
}
