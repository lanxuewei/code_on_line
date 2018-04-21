package com.lanxuewei.code_on_line.authorization.manager;

import com.lanxuewei.code_on_line.authorization.model.TokenModel;

/**
 * create by lanxuewei in 2018/4/14 15:07
 * description: 对 token 进行操作的接口
 */
public interface TokenManager {

    /**
     * 创建一个 token 关联上指定用户
     * @param userId 指定用户的 userId
     * @return 生成的 token
     */
    TokenModel createToken(Long userId);

    /**
     * 检查 token 是否有效
     * @param model token
     * @return 是否有效
     */
    boolean checkToken(TokenModel model);

    /**
     * 从字符串解析 token
     * @param authentication 加密后的字符串
     * @return token
     */
    TokenModel getToken(String authentication);

    /**
     * 清除token
     * @param userId 登陆用户的userId
     */
     void deleteToken(Long userId);
}
