/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Barang;
import entity.Gudang;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author ayu
 */
public interface BarangDao extends Remote{
  boolean insert (Barang barang) throws RemoteException;
  boolean update (Barang barang) throws RemoteException;
  boolean delete (Barang barang) throws RemoteException;
  boolean kurangJumlahStok(double jumlah, Barang barang)throws RemoteException;
  boolean tambahJumlahStok(double jumlah, Barang barang)throws RemoteException;
  Barang getById(String Id)throws RemoteException;
  List<Barang> getBarang()throws RemoteException;
  List<Barang> getBarang(Gudang g)throws RemoteException;
  List<Barang> getBarangNotIn()throws RemoteException;
}
