/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asep.ws.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author user
 */
public class NumberField implements Serializable{
    private String awalan="";
    private int panjangField;
    private String namaField;
    private String namaTabel;

    public String getAwalan() {
        return awalan;
    }

    public void setAwalan(String awalan) {
        this.awalan = awalan;
    }

    public String getNamaField() {
        return namaField;
    }

    public void setNamaField(String namaField) {
        this.namaField = namaField;
    }

    public String getNamaTabel() {
        return namaTabel;
    }

    public void setNamaTabel(String namaTabel) {
        this.namaTabel = namaTabel;
    }

    public int getPanjangField() {
        return panjangField;
    }

    public void setPanjangField(int panjangField) {
        this.panjangField = panjangField;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NumberField other = (NumberField) obj;
        if (!Objects.equals(this.awalan, other.awalan)) {
            return false;
        }
        if (this.panjangField != other.panjangField) {
            return false;
        }
        if (!Objects.equals(this.namaField, other.namaField)) {
            return false;
        }
        if (!Objects.equals(this.namaTabel, other.namaTabel)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.awalan);
        hash = 29 * hash + this.panjangField;
        hash = 29 * hash + Objects.hashCode(this.namaField);
        hash = 29 * hash + Objects.hashCode(this.namaTabel);
        return hash;
    }

    @Override
    public String toString() {
        return "NumberField{" + "awalan=" + awalan + ", panjangField=" + panjangField + ", namaField=" + namaField + ", namaTabel=" + namaTabel + '}';
    }
    
    
    
}
