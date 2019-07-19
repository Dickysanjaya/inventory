/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package entity;

import com.stripbandunk.jwidget.annotation.TableColumn;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ayu
 */
public class Pelanggan implements Serializable{
    
  @TableColumn(name="Kode",number=1,size=10)
  private String kd_plg;
  @TableColumn(name="Nama",number=2,size=25)
  private String nm_plg;
  @TableColumn(name="Telepon",number=3)
  private String telp;
  @TableColumn(name="Alamat",number=4,size=35)
  private String alamat;

    public String getKd_plg() {
        return kd_plg;
    }

    public void setKd_plg(String kd_plg) {
        this.kd_plg = kd_plg;
    }

    public String getNm_plg() {
        return nm_plg;
    }

    public void setNm_plg(String nm_plg) {
        this.nm_plg = nm_plg;
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
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.kd_plg);
        hash = 83 * hash + Objects.hashCode(this.nm_plg);
        hash = 83 * hash + Objects.hashCode(this.telp);
        hash = 83 * hash + Objects.hashCode(this.alamat);
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
        final Pelanggan other = (Pelanggan) obj;
        if (!Objects.equals(this.kd_plg, other.kd_plg)) {
            return false;
        }
        if (!Objects.equals(this.nm_plg, other.nm_plg)) {
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
        return kd_plg ;
    }
  
}
