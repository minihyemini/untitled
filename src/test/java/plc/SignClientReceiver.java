package plc;

import plc.RecHeaderVO;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;

public class SignClientReceiver extends Thread{
    private Socket socket;

    public SignClientReceiver(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedInputStream bis = null;

        try {
            RecHeaderVO recHeaderVO = new RecHeaderVO();

            bis = new BufferedInputStream(socket.getInputStream());
            byte[] buff = new byte[1024];

            int read = 0;
            while (true) {
                if (this.socket == null) {
                    break;
                }

                read = bis.read(buff, 0, 1024);

                if (read < 0) {
                    break;
                }

                // sid
//                byte[] tempArr = new byte[100];
//                System.arraycopy(buff, 0, tempArr, 0, 100);
                String hexText = new BigInteger(buff).toString(16);
                recHeaderVO.setSid(hexText);

                // status
//                tempArr = new byte[100];
//                System.arraycopy(buff, 5, tempArr, 0, 100);
//                recHeaderVO.setStatus(new String(buff));
            }

            System.out.println(recHeaderVO.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                bis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
