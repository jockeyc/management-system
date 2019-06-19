package demo.demo.respone;

public class RespCode {
    private int code;
    private String msg;

    public RespCode(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}