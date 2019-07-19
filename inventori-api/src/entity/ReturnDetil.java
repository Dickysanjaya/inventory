/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.stripbandunk.jwidget.annotation.TableColumn;
import java.io.Serializable;

/**
 *
 * @author ayu
 */
public class ReturnDetil implements Serializable{
    @TableColumn(name="Retrurn Id", number=1)
    private ReturPenjualan returPenjualan;
    @TableColumn(name="Barang", number=2)
    private Barang barang;
    @TableColumn(name="Jumlah", number=3)
    private int jumlah;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    

    public ReturPenjualan getReturPenjualan() {
        return returPenjualan;
    }

    public void setReturPenjualan(ReturPenjualan returPenjualan) {
        this.returPenjualan = returPenjualan;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
}
