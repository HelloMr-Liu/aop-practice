package org.king2.aoppractice.constant;

/**
 * 描述：系统响应状态码信息
 *
 * @author 刘梓江
 * @date 2020/6/9  14:14
 */
public enum SystemResultCodeEnum {

    ERROR(500,"系统出错,请联系管理人员"),
    SUCCESS(200,"操作成功"),
    CHECK_ERROR(100,"数据校验异常"),

    SELECT_FAILURE(600,"查询失败"),
    INSERT_FAILURE(601,"添加失败"),
    UPDATE_FAILURE(602,"修改失败"),
    DELETE_FAILURE(603,"删除失败"),

    LOGIN_FAILURE( 700,"用户名或密码错误"),
    LOGIN_DISABLE( 701,"该账号被禁止使用"),
    NO_SHOW_AUTH(  702,"该账号无查看权限"),
    NO_UPDATE_AUTH(703,"该账号无编辑权限"),
    NO_DELETE_AUTH(704,"该账号无删除权限");

    public Integer CODE;            //反馈的状态码
    public String  MESS;            //反馈的描述信息
    SystemResultCodeEnum(Integer code, String mess){
        this.CODE=code;
        this.MESS=mess;
    }

    //获取对应的实例
    public static SystemResultCodeEnum getInstance(Integer content){
        for(SystemResultCodeEnum instance: SystemResultCodeEnum.values()){
            if(instance.CODE.intValue()==content.intValue()){
                return instance;
            }
        }
        return null;
    }
}