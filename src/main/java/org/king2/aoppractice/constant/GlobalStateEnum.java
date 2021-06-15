package org.king2.aoppractice.constant;

/**
 * 描述：全局表字段状态信息
 * @author 刘梓江
 * @date 2020/6/9  14:13
 */
public enum GlobalStateEnum {
    NO_DEL(false,  "未删除"),
    YES_DEL(true,"已删除");

    public boolean CODE;        //状态值
    public String MESS;         //状态值的描述信息
    GlobalStateEnum(boolean code, String mess){
        this.CODE=code;
        this.MESS=mess;
    }
}