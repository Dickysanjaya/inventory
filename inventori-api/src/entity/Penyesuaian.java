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
public class Penyesuaian implements Serializable{
    @TableColumn(name = "No Penyesuaian", number = 1)
    private String noPenyesiaian;
    @TableColumn(name = "Tanggal", number = 2)
    private Date tanggal;
    @TableColumn(name = "Barang", number = 3)
    private Barang barang;
    @TableColumn(name = "Keterangan", number = 4)
    private String keterangan;
    @TableColumn(name = "Jumlah", number = 5)
    private int jumlah;

    public String getNoPenyesiaian() {
        return noPenyesiaian;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void setNoPenyesiaian(String noPenyesiaian) {
        this.noPenyesiaian = noPenyesiaian;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
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
        hash = 67 * hash + Objects.hashCode(this.noPenyesiaian);
        hash = 67 * hash + Objects.hashCode(this.tanggal);
        hash = 67 * hash + Objects.hashCode(this.barang);
        hash = 67 * hash + Objects.hashCode(this.keterangan);
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
        final Penyesuaian other = (Penyesuaian) obj;
        if (!Objects.equals(this.noPenyesiaian, other.noPenyesiaian)) {
            return false;
        }
        if (!Objects.equals(this.tanggal, other.tanggal)) {
            return false;
        }
        if (!Objects.equals(this.barang, other.barang)) {
            return false;
        }
        if (!Objects.equals(this.keterangan, other.keterangan)) {
            return false;
        }
        return true;
    }
    
}
