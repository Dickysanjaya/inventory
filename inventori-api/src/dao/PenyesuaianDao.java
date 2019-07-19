/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Penyesuaian;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ayu
 */
public interface PenyesuaianDao extends Remote{
    boolean insert (Penyesuaian penyesuaian)throws RemoteException;
    boolean update (Penyesuaian penyesuaian)throws RemoteException;
    boolean delete (Penyesuaian penyesuaian)throws RemoteException;
    Penyesuaian getPenyesuaian (String id)throws RemoteException;
    List<Penyesuaian> getPenyesuaian()throws RemoteException;
    List<Penyesuaian> getPenyesuaian(Date awal, Date akhir)throws RemoteException;
}
