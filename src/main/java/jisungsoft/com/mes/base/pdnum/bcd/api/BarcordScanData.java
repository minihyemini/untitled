package jisungsoft.com.mes.base.pdnum.bcd.api;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class BarcordScanData {

    String ipAddress = "192.142.6.51";
    int port = 2020;
    int timeOut = 2000; //time out of the scanner
    int qrRetryCount = 10;  //Number of times retry for scanning.

    public BarcordScanData() {
        QRSCANDATA(ipAddress, port, timeOut, qrRetryCount);
    }

    public static void QRSCANDATA(String ipAddress, int port, int timeOut, int qrRetryCount) {
        Socket socket=null ;
        InputStream is = null;
        BufferedInputStream bis=null;
        boolean flag = false;
        try {
            socket = new Socket(ipAddress,port);
            socket.setSoTimeout(timeOut);

            int i=0;
            while(i<qrRetryCount && !flag) {
                if(socket.isBound()) {
                    try {
                        is = socket.getInputStream();
                        bis=new BufferedInputStream(is);

                        String inputString = readInputStream(bis);
                        System.out.println("Scanned input------>"+inputString);

                    } catch(UnknownHostException u) {
                        System.out.println(u + ipAddress);
                    } finally {
                        bis.close();
                        is.close();
                        bis = null;
                        is = null;
                    }
                }

                i++;
                Thread.sleep(500);
            }
        }

        catch (Exception exception)
        {
            exception.printStackTrace();
        } finally {
            if(socket!=null){
                try{
                    socket.close();
                }catch(Exception e){}
                socket=null;
            }
        }


    }

    private static String readInputStream(BufferedInputStream bis) throws IOException {
        String data = "";
        int s = bis.read();
        if(s==-1)
            return null;
        data += ""+(char)s;
        int len = bis.available();

        if(len > 0) {
            byte[] byteData = new byte[len];
            bis.read(byteData);
            data += new String(byteData);
        }
        return data;
    }
}
