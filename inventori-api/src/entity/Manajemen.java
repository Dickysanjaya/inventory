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
public class Manajemen implements Serializable{
    @TableColumn(name="Kode", number=1)
    private String kdManag;
    @TableColumn(name="Barang", number=2, size = 30)
    private Barang barang;
    @TableColumn(name="Qty pertahun", number=6)
    private double qty_tahun;
    @TableColumn(name="Biaya Pesan", number=7)
    private double biy_pesan;
    @TableColumn(name="Biaya Simpan", number=8)
    private double biy_simpan;
    @TableColumn(name="Qty perhari", number=9)
    private double qty_hari;
    @TableColumn(name="Qty / hari max", number=10)
    private double qty_hari_max;
    @TableColumn(name="Qty Eoq", number=11)
    private double qty_eoq;
    @TableColumn(name="Lead Time", number=12)
    private double lead_time;
    @TableColumn(name="Rop Safety", number=13)
    private double rop_safety;
    
    public double getQty_tahun() {
        return qty_tahun;
    }

    public void setQty_tahun(double qty_tahun) {
        this.qty_tahun = qty_tahun;
    }

    public double getBiy_pesan() {
        return biy_pesan;
    }

    public void setBiy_pesan(double biy_pesan) {
        this.biy_pesan = biy_pesan;
    }

    public double getBiy_simpan() {
        return biy_simpan;
    }

    public void setBiy_simpan(double biy_simpan) {
        this.biy_simpan = biy_simpan;
    }

    public double getQty_hari() {
        return qty_hari;
    }

    public void setQty_hari(double qty_hari) {
        this.qty_hari = qty_hari;
    }

    public double getQty_hari_max() {
        return qty_hari_max;
    }

    public void setQty_hari_max(double qty_hari_max) {
        this.qty_hari_max = qty_hari_max;
    }

    public double getQty_eoq() {
        return qty_eoq;
    }

    public void setQty_eoq(double qty_eoq) {
        this.qty_eoq = qty_eoq;
    }

    public double getLead_time() {
        return lead_time;
    }

    public void setLead_time(double lead_time) {
        this.lead_time = lead_time;
    }

    public double getRop_safety() {
        return rop_safety;
    }

    public void setRop_safety(double rop_safety) {
        this.rop_safety = rop_safety;
    }

    public String getKdManag() {
        return kdManag;
    }

    public void setKdManag(String kdManag) {
        this.kdManag = kdManag;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.qty_tahun) ^ (Double.doubleToLongBits(this.qty_tahun) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.biy_pesan) ^ (Double.doubleToLongBits(this.biy_pesan) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.biy_simpan) ^ (Double.doubleToLongBits(this.biy_simpan) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.qty_hari) ^ (Double.doubleToLongBits(this.qty_hari) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.qty_hari_max) ^ (Double.doubleToLongBits(this.qty_hari_max) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.qty_eoq) ^ (Double.doubleToLongBits(this.qty_eoq) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.lead_time) ^ (Double.doubleToLongBits(this.lead_time) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.rop_safety) ^ (Double.doubleToLongBits(this.rop_safety) >>> 32));
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
        final Manajemen other = (Manajemen) obj;
        if (Double.doubleToLongBits(this.qty_tahun) != Double.doubleToLongBits(other.qty_tahun)) {
            return false;
        }
        if (Double.doubleToLongBits(this.biy_pesan) != Double.doubleToLongBits(other.biy_pesan)) {
            return false;
        }
        if (Double.doubleToLongBits(this.biy_simpan) != Double.doubleToLongBits(other.biy_simpan)) {
            return false;
        }
        if (Double.doubleToLongBits(this.qty_hari) != Double.doubleToLongBits(other.qty_hari)) {
            return false;
        }
        if (Double.doubleToLongBits(this.qty_hari_max) != Double.doubleToLongBits(other.qty_hari_max)) {
            return false;
        }
        if (Double.doubleToLongBits(this.qty_eoq) != Double.doubleToLongBits(other.qty_eoq)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lead_time) != Double.doubleToLongBits(other.lead_time)) {
            return false;
        }
        if (Double.doubleToLongBits(this.rop_safety) != Double.doubleToLongBits(other.rop_safety)) {
            return false;
        }
        return true;
    }
    
}
