/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Penjualan;
import entity.PenjualanDetail;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ayu
 */
public interface PenjualanDao extends Remote{
    boolean insert (Penjualan penjualan) throws RemoteException;
    List<Penjualan> getPenjualan() throws RemoteException;
    List<Penjualan> getPenjualan(Date tglMulai,Date tgAkhir) throws RemoteException;;
    PenjualanDetail getPenjualanDetil(Penjualan penjualan, String barang) throws RemoteException;
    List<PenjualanDetail> getpenjualanDetil(Penjualan penjualan) throws RemoteException;
    Penjualan getPenjualan(String id) throws RemoteException;
    Integer penjualanTahunan(String barangId, Date Tanggal) throws RemoteException;
}
