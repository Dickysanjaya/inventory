/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.stripbandunk.jwidget.annotation.TableColumn;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ayu
 */
public class Penjualan implements Serializable{
    @TableColumn(name="KOde Jual", number=1)
    private String no_jual;
    @TableColumn(name="Tanggal", number=2)
    private Date tgl;
    @TableColumn(name="Pelanggan", number=3)
    private Pelanggan pelanggan;
    @TableColumn(name="Gudang", number=4)
    private Gudang gudang; 
    private double total;
    private List<PenjualanDetail>detailPenjualan;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNo_jual() {
        return no_jual;
    }

    public void setNo_jual(String no_jual) {
        this.no_jual = no_jual;
    }

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public Gudang getGudang() {
        return gudang;
    }

    public void setGudang(Gudang gudang) {
        this.gudang = gudang;
    }

    public List<PenjualanDetail> getDetailPenjualan() {
        return detailPenjualan;
    }

    public void setDetailPenjualan(List<PenjualanDetail> detailPenjualan) {
        this.detailPenjualan = detailPenjualan;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.no_jual);
        hash = 73 * hash + Objects.hashCode(this.tgl);
        hash = 73 * hash + Objects.hashCode(this.pelanggan);
        hash = 73 * hash + Objects.hashCode(this.gudang);
        hash = 73 * hash + Objects.hashCode(this.detailPenjualan);
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
        final Penjualan other = (Penjualan) obj;
        if (!Objects.equals(this.no_jual, other.no_jual)) {
            return false;
        }
        if (!Objects.equals(this.tgl, other.tgl)) {
            return false;
        }
        if (!Objects.equals(this.pelanggan, other.pelanggan)) {
            return false;
        }
        if (!Objects.equals(this.gudang, other.gudang)) {
            return false;
        }
        if (!Objects.equals(this.detailPenjualan, other.detailPenjualan)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Penjualan{" + "no_jual=" + no_jual + ", tgl=" + tgl + ", pelanggan=" + pelanggan + ", gudang=" + gudang + ", detailPenjualan=" + detailPenjualan + '}';
    }
    
    
    
    
}
