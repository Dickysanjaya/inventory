/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asep.ws.service;

import asep.ws.entity.NumberField;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author user
 */
public interface AutoNumber extends Remote{
    String getAutoNumberInt(NumberField field)throws RemoteException;
}
