package plc;

public class RecHeaderVO {
    /** char sid[5] */
    public String sid;

    /** char status[10] */
    public String status;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
