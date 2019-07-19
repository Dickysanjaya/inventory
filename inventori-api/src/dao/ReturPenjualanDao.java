/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entity.ReturPenjualan;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ayu
 */
public interface ReturPenjualanDao extends Remote{
    boolean insertReturn(ReturPenjualan kembali, String idJual) throws RemoteException; 
    List<ReturPenjualan>getReturPenjualan() throws RemoteException;
    List<ReturPenjualan>getReturPenjualan(Date awal, Date akhir) throws RemoteException;
}
