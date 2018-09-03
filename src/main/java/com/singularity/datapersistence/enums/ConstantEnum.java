package com.singularity.datapersistence.enums;

public enum ConstantEnum {

    execSuccessCode("1","正常"),
    notBaseEntityErrorCode("2","未继承基类"),
    execErrorCode("500","异常");

    private String key;
    private String text;

    private ConstantEnum(String key, String text){
        this.key=key;
        this.text=text;
    }


    public static String getValue(String value) {
        ConstantEnum[] constantEnums = values();
        for (ConstantEnum constantEnum: constantEnums) {
            if (constantEnum.getKey().equals(value)) {
                return constantEnum.getKey();
            }
        }
        return null;
    }

    public static String getDesc(String value) {
        ConstantEnum[] constantEnums = values();
        for (ConstantEnum constantEnum : constantEnums) {
            if (constantEnum.getKey().equals(value)) {
                return constantEnum.getText();
            }
        }
        return null;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
