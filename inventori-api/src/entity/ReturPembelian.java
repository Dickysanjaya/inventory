/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.stripbandunk.jwidget.annotation.TableColumn;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author ayu
 */
public class ReturPembelian implements Serializable{
    @TableColumn(name="Kode Retur", number=1)
    private String kd_retur_beli;
    @TableColumn(name="Nama Barang", number=2)
    private Barang barang;
    @TableColumn(name="Jumlah", number=4)
    private double jumlah;
    @TableColumn(name="Tanggal", number=5)
    private Date tgl;
    @TableColumn(name="Kasir", number=6)
    private Admin admin;
    @TableColumn(name="Keterangan", number=7)
    private String keterangan;

    public String getKd_retur_beli() {
        return kd_retur_beli;
    }

    public void setKd_retur_beli(String kd_retur_beli) {
        this.kd_retur_beli = kd_retur_beli;
    }

    

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.kd_retur_beli);
       
        hash = 59 * hash + Objects.hashCode(this.barang);
        hash = 59 * hash + Objects.hashCode(this.jumlah);
        hash = 59 * hash + Objects.hashCode(this.tgl);
        hash = 59 * hash + Objects.hashCode(this.admin);
        hash = 59 * hash + Objects.hashCode(this.keterangan);
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
        final ReturPembelian other = (ReturPembelian) obj;
        if (!Objects.equals(this.kd_retur_beli, other.kd_retur_beli)) {
            return false;
        }
        
        if (!Objects.equals(this.barang, other.barang)) {
            return false;
        }
        
        if (!Objects.equals(this.jumlah, other.jumlah)) {
            return false;
        }
        if (!Objects.equals(this.tgl, other.tgl)) {
            return false;
        }
        if (!Objects.equals(this.admin, other.admin)) {
            return false;
        }
        if (!Objects.equals(this.keterangan, other.keterangan)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return keterangan;
    }

    
}
