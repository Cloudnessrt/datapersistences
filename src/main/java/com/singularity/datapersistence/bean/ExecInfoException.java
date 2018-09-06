package com.singularity.datapersistence.bean;


import com.singularity.datapersistence.enums.ConstantEnum;
import org.apache.commons.lang.StringUtils;

//*执行反馈类
public class ExecInfoException extends Exception{
    //错误信息
    private String info;
    //错误代码
    private String code;
    //数据类
    private Object object;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public ExecInfoException() {

    }

    public ExecInfoException(String info, ConstantEnum code, Object object, Throwable t) {
        super(t);
        if(code!=null){
            this.code =code.getKey();
            if(StringUtils.isEmpty(info)) {
                this.info = ConstantEnum.getValue(this.code);
            }else{
                this.info = info;
            }
        }else{
            this.info = info;
        }
        this.object = object;
    }

    private  void getMessageByCode(String code){
        this.code=code;
        this.info = ConstantEnum.getValue(this.code);
    }

    /**
     * 成功
     * @return
     */
    public static ExecInfoException successExecInfo(Object o){
        ExecInfoException execInfoException =new ExecInfoException();
        execInfoException.getMessageByCode(ConstantEnum.execSuccessCode.getKey());
        return execInfoException;
    }

    /**
     * 设置异常
     * @param message
     * @param code
     * @param object
     * @param e
     * @return
     */
    public static ExecInfoException setExecInfo(String message, ConstantEnum code, Object object, Exception e){
        return new ExecInfoException(message,code,object,e);
    }

    /**
     * 设置异常
     * @param code
     * @param object
     * @param e
     * @return
     */
    public static ExecInfoException setExecInfo(ConstantEnum code, Object object, Exception e){
        return new ExecInfoException("",code,object,e);
    }

    /**
     * 设置异常
     * @param code
     * @param object
     * @return
     */
    public static ExecInfoException setExecInfo(String message, ConstantEnum code, Object object){
        return new ExecInfoException(message,code,object,null);
    }


    /**
     * 设置异常
     * @param message
     * @param code
     * @return
     */
    public static ExecInfoException setExecInfo(String message, ConstantEnum code, Exception e){
        return new ExecInfoException(message,code,"",e);
    }

    public boolean getResult(){
        if(ConstantEnum.execSuccessCode.getKey().equals(this.getCode())){
            return true;
        }else {
            return false;
        }
    }
}
