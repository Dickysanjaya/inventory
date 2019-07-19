/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Pembelian;
import entity.PembelianDetail;
import entity.ReportPembelian;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ayu
 */
public interface PembelianDao extends Remote{
    boolean insert(Pembelian pembelian) throws RemoteException;
    List<Pembelian> getPembelian() throws RemoteException;
    List<Pembelian> getPembelian(Date tglMulai,Date tgAkhir) throws RemoteException;
    List<PembelianDetail> getPembelianDetil(String noBeli ) throws RemoteException;
    Pembelian getpPembelian(String id) throws RemoteException;
}
