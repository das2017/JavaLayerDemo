package org.zzj.controller;

import org.zzj.common.auth.AuthLogin;
import org.zzj.common.auth.AuthManager;
import org.zzj.dto.common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController extends BaseController {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/login")
    public ApiResponse login(HttpServletResponse response, String username, String password) throws Exception {
        logger.info("用户登录username:{}，password:{}", username, password);
        String tokenId= AuthManager.generateToken(username);
        response.addHeader("Authorization", tokenId);
        return success(tokenId);
    }

    @GetMapping("/check")
    @AuthLogin
    public ApiResponse check(HttpServletRequest request) {
        String tokentId = request.getHeader("Authorization");
        String uid = AuthManager.getUid(tokentId);
        logger.info("获取用户信息userId:{}", uid);
        return success(uid);
    }
}
