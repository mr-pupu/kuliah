/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tas;

import entity.Produk;
import java.util.*;

/**
 *
 * @author p14
 */
public class TasBelanja {

    List<IsiTasBelanja> items;
    int numberOfItems;
    double total;

    public TasBelanja() {
        items = new ArrayList<IsiTasBelanja>();
        numberOfItems = 0;
        total = 0;
    }

    /**
     * Adds a <code>IsiTasBelanja</code> to the <code>TasBelanja</code>'s
     * <code>items</code> list. If item of the specified <code>produk</code>
     * already exists in shopping cart list, the quantity of that item is
     * incremented.
     *
     * @param produk the <code>Produk</code> that defines the type of shopping cart item
     * @see IsiTasBelanja
     */
    public synchronized void addItem(Produk produk) {

        boolean newItem = true;

        for (IsiTasBelanja scItem : items) {

            if (scItem.getProduct().getId() == produk.getId()) {

                newItem = false;
                scItem.incrementQuantity();
            }
        }

        if (newItem) {
            IsiTasBelanja scItem = new IsiTasBelanja(produk);
            items.add(scItem);
        }
    }

    /**
     * Updates the <code>IsiTasBelanja</code> of the specified
     * <code>produk</code> to the specified quantity. If '<code>0</code>'
     * is the given quantity, the <code>IsiTasBelanja</code> is removed
     * from the <code>TasBelanja</code>'s <code>items</code> list.
     *
     * @param produk the <code>Produk</code> that defines the type of shopping cart item
     * @param quantity the number which the <code>IsiTasBelanja</code> is updated to
     * @see IsiTasBelanja
     */
    public synchronized void update(Produk produk, String quantity) {

        short qty = -1;

        // cast quantity as short
        qty = Short.parseShort(quantity);

        if (qty >= 0) {

            IsiTasBelanja item = null;

            for (IsiTasBelanja scItem : items) {

                if (scItem.getProduct().getId() == produk.getId()) {

                    if (qty != 0) {
                        // set item quantity to new value
                        scItem.setQuantity(qty);

                    } else {
                        // if quantity equals 0, save item and break
                        item = scItem;
                        break;
                    }
                }
            }

            if (item != null) {
                // remove from cart
                items.remove(item);
            }
        }
    }

    /**
     * Returns the list of <code>ShoppingCartItems</code>.
     *
     * @return the <code>items</code> list
     * @see IsiTasBelanja
     */
    public synchronized List<IsiTasBelanja> getItems() {

        return items;
    }

    /**
     * Returns the sum of quantities for all items maintained in shopping cart
     * <code>items</code> list.
     *
     * @return the number of items in shopping cart
     * @see IsiTasBelanja
     */
    public synchronized int getNumberOfItems() {

        numberOfItems = 0;

        for (IsiTasBelanja scItem : items) {

            numberOfItems += scItem.getQuantity();
        }

        return numberOfItems;
    }

    /**
     * Returns the sum of the produk price multiplied by the quantity for all
     * items in shopping cart list. This is the total cost excluding the surcharge.
     *
     * @return the cost of all items times their quantities
     * @see IsiTasBelanja
     */
    public synchronized double getSubtotal() {

        double amount = 0;

        for (IsiTasBelanja scItem : items) {

            Produk produk = (Produk) scItem.getProduct();
            amount += (scItem.getQuantity() * produk.getHarga().doubleValue());
        }

        return amount;
    }

    /**
     * Calculates the total cost of the order. This method adds the subtotal to
     * the designated surcharge and sets the <code>total</code> instance variable
     * with the result.
     *
     * @param surcharge the designated surcharge for all orders
     * @see IsiTasBelanja
     */
    public synchronized void calculateTotal(String surcharge) {

        double amount = 0;

        // cast surcharge as double
        double s = Double.parseDouble(surcharge);

        amount = this.getSubtotal();
        amount += s;

        total = amount;
    }

    /**
     * Returns the total cost of the order for the given
     * <code>TasBelanja</code> instance.
     *
     * @return the cost of all items times their quantities plus surcharge
     */
    public synchronized double getTotal() {

        return total;
    }

    /**
     * Empties the shopping cart. All items are removed from the shopping cart
     * <code>items</code> list, <code>numberOfItems</code> and
     * <code>total</code> are reset to '<code>0</code>'.
     *
     * @see IsiTasBelanja
     */
    public synchronized void clear() {
        items.clear();
        numberOfItems = 0;
        total = 0;
    }

}