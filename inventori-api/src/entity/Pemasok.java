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
public class Pemasok implements Serializable{

 @TableColumn(name="Kode", number=1, size=10)
 private String kd_pemasok;
 @TableColumn(name="Nama", number=2, size=25)
 private String nama;
 @TableColumn(name="Telepon",number=3, size=15)
 private String telp;
 @TableColumn(name="Alamat", number=4, size=35)
 private String alamat;

    public String getKd_pemasok() {
        return kd_pemasok;
    }

    public void setKd_pemasok(String kd_pemasok) {
        this.kd_pemasok = kd_pemasok;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.kd_pemasok);
        hash = 59 * hash + Objects.hashCode(this.nama);
        hash = 59 * hash + Objects.hashCode(this.telp);
        hash = 59 * hash + Objects.hashCode(this.alamat);
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
        final Pemasok other = (Pemasok) obj;
        if (!Objects.equals(this.kd_pemasok, other.kd_pemasok)) {
            return false;
        }
        if (!Objects.equals(this.nama, other.nama)) {
            return false;
        }
        if (!Objects.equals(this.telp, other.telp)) {
            return false;
        }
        if (!Objects.equals(this.alamat, other.alamat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nama;
    }

    
 
}
