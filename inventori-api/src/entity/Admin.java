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
public class Admin implements Serializable{
    @TableColumn(name="Kode", number=1, size=10)
    private String kd_user;
    @TableColumn(name="Nama", number=2, size=25)
    private String nm_user;
    @TableColumn(name="Username", number=3, size=25)
    private String user_name;
    private String user_pass;
    @TableColumn(name="Bagian", number=4)
    private String bagian;
    @TableColumn(name="Telepon", number=5)
    private String telepon;
    @TableColumn(name="Alamat", number=6, size=30)
    private String alamat;

    public String getKd_user() {
        return kd_user;
    }

    public void setKd_user(String kd_user) {
        this.kd_user = kd_user;
    }

    public String getNm_user() {
        return nm_user;
    }

    public void setNm_user(String nm_user) {
        this.nm_user = nm_user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
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
        hash = 71 * hash + Objects.hashCode(this.kd_user);
        hash = 71 * hash + Objects.hashCode(this.nm_user);
        hash = 71 * hash + Objects.hashCode(this.user_name);
        hash = 71 * hash + Objects.hashCode(this.user_pass);
        hash = 71 * hash + Objects.hashCode(this.bagian);
        hash = 71 * hash + Objects.hashCode(this.telepon);
        hash = 71 * hash + Objects.hashCode(this.alamat);
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
        final Admin other = (Admin) obj;
        if (!Objects.equals(this.kd_user, other.kd_user)) {
            return false;
        }
        if (!Objects.equals(this.nm_user, other.nm_user)) {
            return false;
        }
        if (!Objects.equals(this.user_name, other.user_name)) {
            return false;
        }
        if (!Objects.equals(this.user_pass, other.user_pass)) {
            return false;
        }
        if (!Objects.equals(this.bagian, other.bagian)) {
            return false;
        }
        if (!Objects.equals(this.telepon, other.telepon)) {
            return false;
        }
        if (!Objects.equals(this.alamat, other.alamat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Admin{" + "kd_user=" + kd_user + '}';
    }

    
  
}
