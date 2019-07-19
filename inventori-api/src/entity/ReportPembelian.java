/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dao.PembelianDao;
import java.io.Serializable;

/**
 *
 * @author ayu
 */
public class ReportPembelian implements Serializable{
   private PembelianDao pembelianDao;
   private PembelianDetail pembelianDetail;

    public PembelianDao getPembelianDao() {
        return pembelianDao;
    }

    public void setPembelianDao(PembelianDao pembelianDao) {
        this.pembelianDao = pembelianDao;
    }

    public PembelianDetail getPembelianDetail() {
        return pembelianDetail;
    }

    public void setPembelianDetail(PembelianDetail pembelianDetail) {
        this.pembelianDetail = pembelianDetail;
    }
   
   
}
