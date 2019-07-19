/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventori.client.laporan.path;

import java.io.InputStream;

/**
 *
 * @author asep
 */
public class Direktori {
     public InputStream getPath(String file){
            return getClass().getClassLoader().
                        getResourceAsStream("inventori/client/laporan/"+file+".jasper");
    }
}
