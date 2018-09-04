package com.singularity.datapersistence.bean;


import com.singularity.datapersistence.enums.ConstantEnum;
import org.apache.commons.lang.StringUtils;

//*执行反馈类
public class ExecInfo extends Exception{

    private String message;//错误信息

    private String code;//错误代码

    private Object object;//数据类

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public ExecInfo() {

    }

    public ExecInfo(String message, ConstantEnum code, Object object, Throwable t) {
        super(t);
        if(code!=null){
            this.code =code.getKey();
            if(StringUtils.isEmpty(message)) {
                this.message = ConstantEnum.getValue(this.code);
            }else{
                this.message =message;
            }
        }else{
            this.message =message;
        }
        this.object = object;
    }

    private  void getMessageByCode(String code){
        this.code=code;
        this.message = ConstantEnum.getValue(this.code);
    }

    /**
     * 成功
     * @return
     */
    public static ExecInfo successExecInfo(Object o){
        ExecInfo execInfo=new ExecInfo();
        execInfo.getMessageByCode(ConstantEnum.execSuccessCode.getKey());
        return execInfo;
    }

    /**
     * 设置异常
     * @param message
     * @param code
     * @param object
     * @param e
     * @return
     */
    public static ExecInfo setExecInfo(String message,ConstantEnum code,  Object object,Exception e){
        return new ExecInfo(message,code,object,e);
    }

    /**
     * 设置异常
     * @param code
     * @param object
     * @param e
     * @return
     */
    public static ExecInfo setExecInfo(ConstantEnum code,  Object object,Exception e){
        return new ExecInfo("",code,object,e);
    }

    /**
     * 设置异常
     * @param code
     * @param object
     * @return
     */
    public static ExecInfo setExecInfo(String message,ConstantEnum code,  Object object){
        return new ExecInfo(message,code,object,null);
    }


    /**
     * 设置异常
     * @param message
     * @param code
     * @return
     */
    public static ExecInfo setExecInfo(String message,ConstantEnum code,Exception e){
        return new ExecInfo(message,code,"",e);
    }

}
