package io.admin.common.enums;

public enum KcztEnum {

    ZK(1,"在库"),ZC(2,"在车"),
    ZR(3,"在人"),JC(4,"借出"),
    BF(6,"报废"),XN(7,"训练");


    private Integer code;
    private String name;

    private KcztEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
