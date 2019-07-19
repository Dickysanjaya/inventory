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
public class Gudang implements Serializable{
    
    @TableColumn(name="Kode", number=1, size=10)
    private String kd_gd;
    @TableColumn(name="Nama Gudang", number=2, size=20)
    private String nm_gd;
    @TableColumn(name="Jenis",number=3)
    private String jenis;

    public String getKd_gd() {
        return kd_gd;
    }

    public void setKd_gd(String kd_gd) {
        this.kd_gd = kd_gd;
    }

    public String getNm_gd() {
        return nm_gd;
    }

    public void setNm_gd(String nm_gd) {
        this.nm_gd = nm_gd;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.kd_gd);
        hash = 67 * hash + Objects.hashCode(this.nm_gd);
        hash = 67 * hash + Objects.hashCode(this.jenis);
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
        final Gudang other = (Gudang) obj;
        if (!Objects.equals(this.kd_gd, other.kd_gd)) {
            return false;
        }
        if (!Objects.equals(this.nm_gd, other.nm_gd)) {
            return false;
        }
        if (!Objects.equals(this.jenis, other.jenis)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nm_gd;
    }
    
}
