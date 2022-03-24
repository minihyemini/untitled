package plc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class JavaSocketClient {
    public SignClientReceiver signClientReceiver;
    public OutputStream os;
    public BufferedReader in;
    public BufferedWriter out;
    public Socket socket = null;
    public static JavaSocketClient client;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        client = new JavaSocketClient();
    }

    public JavaSocketClient() {
        init();
        start();
        setSignClientReceiver();

        try {
            // 스레드 대기
            signClientReceiver.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 스레드 종료 후 소켓 종료
        client.stop();
    }

    // 리시버 클라이언트 스레드 생성
    public void setSignClientReceiver() {
        signClientReceiver = new SignClientReceiver(socket);
        signClientReceiver.setDaemon(true);
        signClientReceiver.start();
    }

    public void init() {
        if (socket == null) {
            try {
                InetSocketAddress isa = new InetSocketAddress("192.168.1.2", 2004);
                socket = new Socket();
                // 이전에 연결된 소켓이 해당 포트를 점유하고 있어도 바인드하기 위한 설정, 때문에 빈 소켓을 먼저 만들고 connect 함
                socket.setReuseAddress(true);
                socket.connect(isa);
                socket.setSoTimeout(10000); // 10초
                // 링거타임 설정 0, 소켓을 닫을 때 아직 전송되지 않은 패킷이 있다면 이를 버리고 소켓을 즉시 닫음
                socket.setSoLinger(true, 0);

                os = socket.getOutputStream();

            } catch (IOException e) {
                e.printStackTrace();
                stop();
            }
        }
    }

    public void start() {
        try {
            String sid = "D700";

            //ByteBuffer
            ByteBuffer sendByteBuffer = null;

            sendByteBuffer = ByteBuffer.allocate(14);
            sendByteBuffer.order(ByteOrder.LITTLE_ENDIAN);

            sendByteBuffer.put(sid.getBytes(StandardCharsets.US_ASCII));
//            sendByteBuffer.put(new byte[5 - sid.getBytes().length]);

//            sendByteBuffer.put(sabun.getBytes());
//            sendByteBuffer.put(new byte[9 - sabun.getBytes().length]);

            os.write(sendByteBuffer.array());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
            stop();
        }
    }

    public void stop() {
        try {
            socket.close();
            os.close();
        } catch (IOException e) {
        }
    }
}
