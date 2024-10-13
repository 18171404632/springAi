package com.newkey.ai.common;

import lombok.Getter;


/**
 * 响应枚举
 */
public enum CommonStatusEnum {


    /**
     * 成功
     */
    SUCCESS("0", "成功"),

    /**
     *  渠道响应失败
     */

    FAILE("1", "渠道响应失败！"),


    /**
     *  验签失败
     */
    SIGN_FAILE("2", "验签失败！"),


    /**
     * 请求超时
     */
    TIMEOUT("3", "请求渠道超时！"),

    /**
     * 重复注册
     */
    ACCT_OPEN_REPEAT("4", "重复注册!"),

    /**
     * 失败
     */
    EXCEPTION("5", "平台内部错误"),

    /**
     *  清分数据异常
     */
    DEVIDE_BILL_FAILE("6", "分账数据异常！"),

    /**
     *  清分数据异常
     */
    QF_TRANS_FAILE("7", "清分数据异常！");




    @Getter
    private String code;
    @Getter
    private String msg;

    CommonStatusEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
