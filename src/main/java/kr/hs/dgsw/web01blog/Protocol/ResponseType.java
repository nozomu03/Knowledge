package kr.hs.dgsw.web01blog.Protocol;

public enum ResponseType {
    FAIL(0, "Fail to run"),

    USER_DELETEE(101, "User [%d] Deleted"),
    USER_ADD(102, "User [%d] Added"),
    USER_UPDATE(103, "User [%d] Updated"),
    USER_LIST(104, "User Count : [%d]."),
    USER_GET(105, "User Get"),

    POST_GET(201, "Post Geted"),
    POST_ADD(202, "Post [%d] Added"),
    POST_UPDATE(203, "Post [%d] Updated"),
    POST_DELETE(204, "Post [%d] Deleted"),
    POST_LIST(205, "Post List print"),

    ATTACHMENT_STORED(301, "Attacment Stored"),
    ;

    final private  int code;
    final private  String desc;

    ResponseType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
