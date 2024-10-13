package com.newkey.ai.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一响应体
 */
@Data
public class Response extends HashMap<String, Object> {

    private String code;

    private String msg;

    private final static String ERRCODE_SUCCESS = "D5000000";

    private final static String SYSRESPCODE_SUCCESS = "00000";

    public Response() {
        put("code", CommonStatusEnum.SUCCESS.getCode());
        put("msg", CommonStatusEnum.SUCCESS.getMsg());
    }


    /**
     *
     * @return
     */
    public static Response success(){
        return new Response();
    }

    /**
     * 自定义失败 错误码 和
    }

    /**
     * 自定义失败 错误码 和 提升信息
     * @param code
     * @param msg
     * @return
     */
    public static Response fail(String code, String msg){
        Response r = new Response();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    /**
     * 根据errCode和sysRespCode 转 平台响应码code
     * @param result
     * @return
     */

    public static Response getResponseByErrAndSys(Object result) throws JsonProcessingException {
        Response r = new Response();
        Map<String, Object> resultMap = new ObjectMapper().readValue((String) result, Map.class);
        //开放平台和客户钱包响应码
        String errCode = (String)resultMap.remove("errCode");
        String errInfo = (String)resultMap.remove("errInfo");

        //中信银行响应码
        String sysRespCode = (String)resultMap.remove("sysRespCode");
        String sysRespDesc = (String)resultMap.remove("sysRespDesc");

        r.putAll(resultMap);

        if (ERRCODE_SUCCESS.equals(errCode) && SYSRESPCODE_SUCCESS.equals(sysRespCode))
            return  r;
        else {
            r.put("code", CommonStatusEnum.FAILE.getCode());
            String msg = ERRCODE_SUCCESS.equals(errCode)?sysRespDesc:errInfo;
            r.put("msg", CommonStatusEnum.FAILE.getMsg() + ":" + msg);
        }
        return r;
    }

    @Override
    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
