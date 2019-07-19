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
public class ReturPenjualan implements Serializable{
    @TableColumn(name="Kode Retur", number=1)
    private String kd_retur_jual;
    @TableColumn(name="Tanggal", number=5)
    private Date tgl;
    @TableColumn(name="Kasir", number=6)
    private Admin admin;
    @TableColumn(name="Keterangan", number=7)
    private String keterangan;
    private List<ReturnDetil> returnDetils;

    public String getKd_retur_jual() {
        return kd_retur_jual;
    }

    public List<ReturnDetil> getReturnDetils() {
        return returnDetils;
    }

    public void setReturnDetils(List<ReturnDetil> returnDetils) {
        this.returnDetils = returnDetils;
    }

    public void setKd_retur_jual(String kd_retur_jual) {
        this.kd_retur_jual = kd_retur_jual;
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
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.kd_retur_jual);
        hash = 79 * hash + Objects.hashCode(this.tgl);
        hash = 79 * hash + Objects.hashCode(this.admin);
        hash = 79 * hash + Objects.hashCode(this.keterangan);
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
        final ReturPenjualan other = (ReturPenjualan) obj;
        if (!Objects.equals(this.kd_retur_jual, other.kd_retur_jual)) {
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
        return kd_retur_jual;
    }
    
    
    
    
}
