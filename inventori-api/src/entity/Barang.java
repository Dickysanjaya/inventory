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
public class Barang implements Serializable{
    @TableColumn(name="Kode", number=1)
    private String kd_brg;
    @TableColumn(name="Nama", number=2)
    private String nm_brg;
    @TableColumn(name="Kategori", number=3)
    private Kategori kategori;
    @TableColumn(name="HargBeli", number=4)
    private double harga_beli;
    @TableColumn(name="Harga Jual", number=5)
    private double harga_jual;
    @TableColumn(name="Jumalah", number=6)
    private int jumlah;
    private Gudang gudang;

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public Gudang getGudang() {
        return gudang;
    }

    public void setGudang(Gudang gudang) {
        this.gudang = gudang;
    }

    public String getKd_brg() {
        return kd_brg;
    }

    public void setKd_brg(String kd_brg) {
        this.kd_brg = kd_brg;
    }

    public String getNm_brg() {
        return nm_brg;
    }

    public void setNm_brg(String nm_brg) {
        this.nm_brg = nm_brg;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public double getHarga_beli() {
        return harga_beli;
    }

    public void setHarga_beli(double harga_beli) {
        this.harga_beli = harga_beli;
    }

    public double getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(double harga_jual) {
        this.harga_jual = harga_jual;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.kd_brg);
        hash = 31 * hash + Objects.hashCode(this.nm_brg);
        hash = 31 * hash + Objects.hashCode(this.kategori);
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.harga_beli) ^ (Double.doubleToLongBits(this.harga_beli) >>> 32));
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.harga_jual) ^ (Double.doubleToLongBits(this.harga_jual) >>> 32));
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
        final Barang other = (Barang) obj;
        if (!Objects.equals(this.kd_brg, other.kd_brg)) {
            return false;
        }
        if (!Objects.equals(this.nm_brg, other.nm_brg)) {
            return false;
        }
        if (!Objects.equals(this.kategori, other.kategori)) {
            return false;
        }
        if (Double.doubleToLongBits(this.harga_beli) != Double.doubleToLongBits(other.harga_beli)) {
            return false;
        }
        if (Double.doubleToLongBits(this.harga_jual) != Double.doubleToLongBits(other.harga_jual)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return kd_brg;
    }
    
    
    

    
    
    
}
