package com.imooc.utils;

import com.imooc.vo.ResultVo;

/**
 * ResultVo utils
 * Created by msi on 2019/7/14.
 */
public class ResultVoUtils {

    /**
     * success
     * @param msg
     * @return
     */
    public  static ResultVo success(String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg(msg);
        return resultVo;
    }

    public  static ResultVo success(){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setData(null);
        return resultVo;
    }

    /**
     * error
     * @param code
     * @param msg
     * @return
     */
    public  static ResultVo error(Integer code,String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }


}
