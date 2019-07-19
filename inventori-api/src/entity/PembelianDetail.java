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
public class PembelianDetail implements Serializable{
  @TableColumn(name="Kode Beli", number=1)
  private String no_beli;
  @TableColumn(name="Barang", number=2)
  private Barang barang;
  @TableColumn(name="Jumlah", number=3)
  private Double jumlah;
  @TableColumn(name="Harga Satuan", number=4)
  private Double harga_beli;
  @TableColumn(name="Total Harga", number=5)
  private Double harga_beli_total;

    public String getNo_beli() {
        return no_beli;
    }

    public void setNo_beli(String no_beli) {
        this.no_beli = no_beli;
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

    public Double getHarga_beli() {
        return harga_beli;
    }

    public void setHarga_beli(Double harga_beli) {
        this.harga_beli = harga_beli;
    }

    public Double getHarga_beli_total() {
        return harga_beli_total;
    }

    public void setHarga_beli_total(Double harga_beli_total) {
        this.harga_beli_total = harga_beli_total;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.no_beli);
        hash = 71 * hash + Objects.hashCode(this.barang);
        hash = 71 * hash + Objects.hashCode(this.jumlah);
        hash = 71 * hash + Objects.hashCode(this.harga_beli);
        hash = 71 * hash + Objects.hashCode(this.harga_beli_total);
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
        final PembelianDetail other = (PembelianDetail) obj;
        if (!Objects.equals(this.no_beli, other.no_beli)) {
            return false;
        }
        if (!Objects.equals(this.barang, other.barang)) {
            return false;
        }
        if (!Objects.equals(this.jumlah, other.jumlah)) {
            return false;
        }
        if (!Objects.equals(this.harga_beli, other.harga_beli)) {
            return false;
        }
        if (!Objects.equals(this.harga_beli_total, other.harga_beli_total)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PembelianDetail{" + "no_beli=" + no_beli + ", barang=" + barang + ", jumlah=" + jumlah + ", harga_beli=" + harga_beli + ", harga_beli_total=" + harga_beli_total + '}';
    }
  

  
}
