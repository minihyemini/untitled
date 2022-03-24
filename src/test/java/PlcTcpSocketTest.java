import plc.LsFenetHeader;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlcTcpSocketTest {
    private static final String HOST = "192.168.1.2";//access point
    private static final int PORT = 2004;//Use port
    private static final String err = "0000";

    public static void main(String[] args) throws InterruptedException {
        //[ h00 비트 ][ h01 바이트 ][ h02 워드 ][ h03 더블워드 ][ h04 롱워드][ h14 연속]
        String szDevice = "%DW110";
        String data = "20";

        PlcTcpSocketTest aa = new PlcTcpSocketTest();
        char type = 'W';    //write
        byte[] bytes = aa.registerWriteOne(szDevice, data, type);
        aa.Cascii(bytes);
        type = 'R'; //read
        bytes = aa.registerWriteOne(szDevice, data, type);
        aa.Cascii(bytes);
    }

    public byte[] registerWriteOne(String szDevice, String pData, char type) {
        List<Byte> bufBody = new ArrayList<>();

        //명령어(2)
        // [ h5400 읽기][ h5800 쓰기 ]
        switch (type) {
            case 'R' :
                bufBody.add((byte) 0x54);
                bufBody.add((byte) 0x00);
                break;
            case 'W':
                bufBody.add((byte) 0x58);
                bufBody.add((byte) 0x00);
                break;
        }

        //데이터 타입(2)
        int nLength = 0;
        switch (szDevice.substring(2, 3)){
            case "X": {
                nLength = 1;
                bufBody.add((byte) 0x00);
                bufBody.add((byte) 0x00);
                break;
            }
            case "B":{
                nLength = 1;
                bufBody.add((byte) 0x01);
                bufBody.add((byte) 0x00);
                break;
            }
            case "W":{
                nLength = 2;
                bufBody.add((byte) 0x02);
                bufBody.add((byte) 0x00);
                break;
            }
            case "D":{
                nLength = 4;
                bufBody.add((byte) 0x03);
                bufBody.add((byte) 0x00);
                break;

            }
            case "L":{
                // 검증 안됨
                // nLength = 8;
                // bufBody[nCnt++] = (char)0x04;
                // bufBody[nCnt++] = (char)0x00;
                // break;
            }
        }

        //예약 영역(2)
        //0x0000 : Don't care
        bufBody.add((byte) 0x00);
        bufBody.add((byte) 0x00);

        //블록수(2)
        //읽고자 하는 블록의 개수. 0x0001
        bufBody.add((byte) 0x01);
        bufBody.add((byte) 0x00);

        //변수명 길이(2)
        //변수 명의 길이. 최대 16자.
        bufBody.add((byte) szDevice.length());
        bufBody.add((byte) 0x00);

        //데이터 주소
        for (char c : szDevice.toCharArray()) {
            bufBody.add((byte) c);
        }

        //데이터 갯수(2)
        bufBody.add((byte) pData.getBytes().length);
        bufBody.add((byte) 0x00);
//        bufBody.add((byte) (nLength & 0xFF));
//        bufBody.add((byte) (nLength & 8));

        switch (type) {
            case 'R' :

                break;
            case 'W':
                switch (szDevice.substring(2, 3)) {
                    case "X":

                        break;
                    case "B":

//                        bufBody.add((byte) pData);
                        break;
                    case "W":
                        /*
                        for (char c : pData.toCharArray()) {
                            bufBody.add((byte) c);
                        }
*/
                        bufBody.add((byte) (Integer.parseInt(pData) & 0xFF)); // Data length(L)
                        bufBody.add((byte) (Integer.parseInt(pData) >> 8));   // Data length(H)

//                        System.out.println(">>" + (pData & 0xFF));
//                        System.out.println(">>" + (pData >> 8));
                        break;
                    case "D":

                        break;
                    case "L":
                        // 검증 안됨 //bufBody[nCnt++] = (char)(pData & 0xFF);
                        // bufBody[nCnt++] = (char)(pData >> 8);
                        // bufBody[nCnt++] = (char)(pData & 0xFFFF);
                        // bufBody[nCnt++] = (char)(pData >> 16);
                        // bufBody[nCnt++] = (char)(pData & 0xFFFF);
                        // bufBody[nCnt++] = (char)(pData >> 32);
                        // bufBody[nCnt++] = (char)(pData & 0xFFFFFFFF);
                        // bufBody[nCnt++] = (char)(pData >> 64);
                        break;
                }

                break;
        }

        LsFenetHeader lsFenetHeader = new LsFenetHeader();
        List<Byte> copy = lsFenetHeader.copy((byte) bufBody.size(), bufBody);
        for (byte c : bufBody) {
            copy.add(c);
        }

        byte[] data = new byte[copy.size()];
        for (int i=0; i<copy.size(); i++) {
            int dec = copy.get(i);
            data[i] = (byte) dec;
            System.out.print(String.format("%2x", (byte) dec));
            System.out.print(" ");
        }

        System.out.println("");

        return data;
    }

    public void Cascii(byte[] data) throws InterruptedException {
        Socket socket = null;
        try {
            socket = new Socket();
            InetSocketAddress ipep = new InetSocketAddress(HOST, PORT);
            socket.connect(ipep);
        } catch (UnknownHostException e1) {
            //TODO auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            //TODO auto-generated catch block
            e1.printStackTrace();
        }
        try{
            OutputStream os = socket.getOutputStream();
            FilterInputStream is = new BufferedInputStream(socket.getInputStream());
            os.write(data);

            System.out.println("Client connected IP Address : " + socket.getRemoteSocketAddress());

            //Receive string from server
            System.out.print("receive->");
            byte[] buf = new byte[100];
            if(is.read(buf) != -1){
                List<String> writeDataList = getWriteDataList(buf);
                System.out.println(writeDataList);
            } else {
                System.out.println("return is null");
            }

        }catch(IOException err){
            err.printStackTrace();
        } finally{
            try {
                if (socket != null) {
                    socket.close();
                }
                System.out.println("Client side end");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getWriteDataList(byte[] buf) {
        List<String> headerData = new ArrayList<>();
        List<String> bodyData = new ArrayList<>();

        int cnt = 0;
        int fromLength = 16;
        int toLength = 17;
        int headerSize = 20;
        int totSize = 0;
        for (byte c : buf) {
            String hxCode = Integer.toHexString(c);
            if (cnt < headerSize) {
                //헤더 정보 담기
                headerData.add(hxCode);
            } else {
                //body 길이
                String format = headerData.get(fromLength) + (headerData.get(toLength).equals("0") ? "" : headerData.get(toLength));
                int lengthData = Integer.parseInt(format, 16);

                if (lengthData > 0) {
                    totSize = lengthData + headerSize;
                    if (cnt < totSize) {
                        //바디 정보 담기
                        bodyData.add(hxCode);
                        System.out.print(hxCode + " ");
                    } else {
                        break;
                    }
                }
            }

//            System.out.print(hxCode + " ");
            cnt++;
        }

        if (bodyData.size() > 0) {
            int defaultSize = 10;
            String format = bodyData.get(6) + (bodyData.get(7).equals("0") ? "" : bodyData.get(7));
            //에러상태(상태가 0이면 정상)
            int errorStatus = Integer.parseInt(format, 16);

            if (errorStatus == 0) {

                return new ArrayList<>();
            } else {
                //error status가 에러인 경우 하위 byte가 에러 번호
                String errorNum = bodyData.get(9);
            }
        }

        return null;
    }

    public List<String> getDataList(byte[] buf) {
        List<String> headerData = new ArrayList<>();
        List<String> bodyData = new ArrayList<>();

        int cnt = 0;
        int fromLength = 16;
        int toLength = 17;
        int headerSize = 20;
        int totSize = 0;
        for (byte c : buf) {
            String hxCode = Integer.toHexString(c);
            if (cnt < headerSize) {
                //헤더 정보 담기
                headerData.add(hxCode);
            } else {
                //body 길이
                String format = headerData.get(fromLength) + (headerData.get(toLength).equals("0") ? "" : headerData.get(toLength));
                int lengthData = Integer.parseInt(format, 16);

                if (lengthData > 0) {
                    totSize = lengthData + headerSize;
                    if (cnt < totSize) {
                        //바디 정보 담기
                        bodyData.add(hxCode);
                    } else {
                        break;
                    }
                }
            }

            System.out.print(hxCode + " ");
            cnt++;
        }

        if (bodyData.size() > 0) {
            int defaultSize = 10;
            String format = bodyData.get(6) + (bodyData.get(7).equals("0") ? "" : bodyData.get(7));
            //에러상태(상태가 0이면 정상)
            int errorStatus = Integer.parseInt(format, 16);

            if (errorStatus == 0) {
                //error status가 정상인 경우 변수 개수
                String variableLength = bodyData.get(8) + (bodyData.get(9).equals("0") ? "" : bodyData.get(9));

                //사용할 데이터 얻기
                List<String> getData = new ArrayList<>();
                String originalData = "";
                for (int i = 0; i < Integer.parseInt(variableLength); i++) {

                    String dataSize = bodyData.get(defaultSize);
                    defaultSize++;
                    dataSize += (bodyData.get(defaultSize).equals("0") ? "" : bodyData.get(defaultSize));
                    defaultSize++;

                    for (int j = 0; j < Integer.parseInt(dataSize); j++) {
                        originalData += bodyData.get(defaultSize);
                        defaultSize++;
                    }
                    getData.add(originalData);
                    originalData = "";
                }
                Collections.swap(getData, 0, getData.size() - 1);
                System.out.println(getData);

                return getData;
            } else {
                //error status가 에러인 경우 하위 byte가 에러 번호
                String errorNum = bodyData.get(9);
            }
        }
        return null;
    }
}
