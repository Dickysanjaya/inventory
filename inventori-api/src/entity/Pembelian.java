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
public class Pembelian implements Serializable{
   @TableColumn(name = "NO Beli", number = 1)
   private String no_beli;
   @TableColumn(name = "Tanggal", number = 2)
   private Date tgl;
   @TableColumn(name = "Pemasok", number = 3)
   private Pemasok pemasok;
   @TableColumn(name = "Gudang", number = 4)
   private Gudang gudang;
   private double total;
   private List<PembelianDetail>detailPembelian;

    public String getNo_beli() {
        return no_beli;
    }

    public void setNo_beli(String no_beli) {
        this.no_beli = no_beli;
    }

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }

    public Pemasok getPemasok() {
        return pemasok;
    }

    public void setPemasok(Pemasok pemasok) {
        this.pemasok = pemasok;
    }

    public Gudang getGudang() {
        return gudang;
    }

    public void setGudang(Gudang gudang) {
        this.gudang = gudang;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<PembelianDetail> getDetailPembelian() {
        return detailPembelian;
    }

    public void setDetailPembelian(List<PembelianDetail> detailPembelian) {
        this.detailPembelian = detailPembelian;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.no_beli);
        hash = 53 * hash + Objects.hashCode(this.tgl);
        hash = 53 * hash + Objects.hashCode(this.pemasok);
        hash = 53 * hash + Objects.hashCode(this.gudang);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.total) ^ (Double.doubleToLongBits(this.total) >>> 32));
        hash = 53 * hash + Objects.hashCode(this.detailPembelian);
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
        final Pembelian other = (Pembelian) obj;
        if (!Objects.equals(this.no_beli, other.no_beli)) {
            return false;
        }
        if (!Objects.equals(this.tgl, other.tgl)) {
            return false;
        }
        if (!Objects.equals(this.pemasok, other.pemasok)) {
            return false;
        }
        if (!Objects.equals(this.gudang, other.gudang)) {
            return false;
        }
        if (Double.doubleToLongBits(this.total) != Double.doubleToLongBits(other.total)) {
            return false;
        }
        if (!Objects.equals(this.detailPembelian, other.detailPembelian)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  no_beli ;
    }

    
}
