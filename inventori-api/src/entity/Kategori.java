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
public class Kategori implements Serializable{
    @TableColumn(name="Kode",number=1,size=25)
   private String kd_kat;
    @TableColumn(name="Nama Kategori",number=2, size=15)
   private String nm_kat;

    public String getKd_kat() {
        return kd_kat;
    }

    public void setKd_kat(String kd_kat) {
        this.kd_kat = kd_kat;
    }

    public String getNm_kat() {
        return nm_kat;
    }

    public void setNm_kat(String nm_kat) {
        this.nm_kat = nm_kat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.kd_kat);
        hash = 47 * hash + Objects.hashCode(this.nm_kat);
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
        final Kategori other = (Kategori) obj;
        if (!Objects.equals(this.kd_kat, other.kd_kat)) {
            return false;
        }
        if (!Objects.equals(this.nm_kat, other.nm_kat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nm_kat;
    }
   
}
