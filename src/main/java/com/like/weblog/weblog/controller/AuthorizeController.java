package com.like.weblog.weblog.controller;

import com.like.weblog.weblog.dto.AccessTokenDTO;
import com.like.weblog.weblog.dto.GithubUser;
import com.like.weblog.weblog.map.UserMapper;
import com.like.weblog.weblog.model.User;
import com.like.weblog.weblog.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;
    @Autowired
    UserMapper userMapper;

    @Value("${github.client.id}")
    String clinetId;
    @Value("${github.client.secret}")
    String clientSecret;
    @Value("${github.redirect.uri}")
    String RedirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clinetId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(RedirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null){
            //登陆状态保持方法1：写入session
//         request.getSession().setAttribute("githubUser",githubUser);
            //登录状态保持方法2：一般用内存数据库保存用户信息
            User user = new User();
            //2.生成token，并通过user存入数据库
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAcountId(githubUser.getId());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setName(githubUser.getName());
            userMapper.insertUser(user);
            //2.把token存入cookie
            response.addCookie(new Cookie("token",token));
            return "redirect:index";
        }else {
            request.setAttribute("login_erro","登陆失败，请重新登陆");
            //登陆失败，重新登陆
            return "redirect:index";
        }
    }
}
