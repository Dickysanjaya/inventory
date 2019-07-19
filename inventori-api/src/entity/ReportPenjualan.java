/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dao.PenjualanDao;
import entity.PenjualanDetail;
import java.io.Serializable;

/**
 *
 * @author ayu
 */
public class ReportPenjualan implements Serializable{
    private PenjualanDao penjualan;
    private PenjualanDetail penjualanDetail;

    public PenjualanDao getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(PenjualanDao penjualan) {
        this.penjualan = penjualan;
    }

    public PenjualanDetail getPenjualanDetail() {
        return penjualanDetail;
    }

    public void setPenjualanDetail(PenjualanDetail penjualanDetail) {
        this.penjualanDetail = penjualanDetail;
    }
    
}
