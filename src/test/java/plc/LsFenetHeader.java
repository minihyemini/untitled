package plc;

import java.util.ArrayList;
import java.util.List;

public class LsFenetHeader {

    public char[] companyID;
    public short length;
    public byte reservedBCC;

    public List<Byte> copy(byte length, List<Byte> ... cDataFrame) {
        List<Byte> header = new ArrayList<>();
        byte checkSum = 0;

        companyID = "LSIS-XGT".toCharArray();
        for (char c : companyID) {
            header.add((byte)c);
            checkSum += (byte) c;
        }

        //예약영역
        header.add((byte)0x00);
        header.add((byte)0x00);

        //클라이언트(MMI) -> 서버(PLC) : Don't care(0x00)
        //PLC info
        header.add((byte)0x00);
        header.add((byte)0x00);
        //XGK: 0xA0
        //cpuinfo
        header.add((byte)0xA0);
        //클라이언트(MMI) → 서버(PLC) : 0x33
        //프레임방향
        header.add((byte)0x33);
        //프레임 순서를 구별하기 위한 ID
        header.add((byte)0x00);
        header.add((byte)0x00);
        //Application Instruction의 바이트 크기
        header.add((byte)length);
        header.add((byte)0x00);
        // CPU 이므로 0
        // (Bit0~3 : FEnet I/F 모듈의 슬롯(Slot) 번호
        // Bit4~7 : FEnet I/F 모듈의 베이스(Base) 번호
        header.add((byte)0x00);
        //0x00 : 예약영역 (Application Header의 Byte Sum)
        reservedBCC = 0x00;

        for (byte c : header) {
            checkSum += c;
        }

        System.out.println("checksum :: " + Integer.toHexString(checkSum));
        header.add(checkSum);

        return header;
    }
}
