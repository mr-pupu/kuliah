/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tas;

import entity.Produk;

/**
 *
 * @author p14
 */
public class IsiTasBelanja {

    Produk produk;
    short quantity;

    public IsiTasBelanja(Produk produk) {
        this.produk = produk;
        quantity = 1;
    }

    public Produk getProduct() {
        return produk;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

    public double getTotal() {
        double amount = 0;
        amount = (this.getQuantity() * produk.getHarga().doubleValue());
        return amount;
    }



}