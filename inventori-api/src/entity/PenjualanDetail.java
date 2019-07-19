/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.stripbandunk.jwidget.annotation.TableColumn;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ayu
 */
public class PenjualanDetail implements Serializable{
    private Penjualan penjualan;
    @TableColumn(name="Barang", number=2)
    private Barang barang;
    @TableColumn(name="Jumlah", number=3)
    private Double jumlah;
    @TableColumn(name="Harga Satuan", number=4)
    private Double harga_jual;
    @TableColumn(name="Total Harga", number=5)
    private Double harga_jual_total;

    public Penjualan getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(Penjualan penjualan) {
        this.penjualan = penjualan;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public Double getJumlah() {
        return jumlah;
    }

    public void setJumlah(Double jumlah) {
        this.jumlah = jumlah;
    }

    public Double getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(Double harga_jual) {
        this.harga_jual = harga_jual;
    }

    public Double getHarga_jual_total() {
        return harga_jual_total;
    }

    public void setHarga_jual_total(Double harga_jual_total) {
        this.harga_jual_total = harga_jual_total;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.barang);
        hash = 37 * hash + Objects.hashCode(this.jumlah);
        hash = 37 * hash + Objects.hashCode(this.harga_jual);
        hash = 37 * hash + Objects.hashCode(this.harga_jual_total);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PenjualanDetail other = (PenjualanDetail) obj;
        
        if (!Objects.equals(this.barang, other.barang)) {
            return false;
        }
        if (!Objects.equals(this.jumlah, other.jumlah)) {
            return false;
        }
        if (!Objects.equals(this.harga_jual, other.harga_jual)) {
            return false;
        }
        if (!Objects.equals(this.harga_jual_total, other.harga_jual_total)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PenjualanDetail{" + ", barang=" + barang + ", jumlah=" + jumlah + ", harga_jual=" + harga_jual + ", harga_jual_total=" + harga_jual_total + '}';
    }
    

    

   

   
    
    
}
