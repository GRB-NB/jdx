package cn.yiidii.jdx.controller;

import cn.hutool.core.util.StrUtil;
import cn.yiidii.jdx.model.R;
import cn.yiidii.jdx.service.OauthService;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * auth
 *
 * @author ed w
 * @since 1.0
 */
@Slf4j
@Validated
@RestController
@RequestMapping("oauth")
@RequiredArgsConstructor
public class OauthController {

    private final OauthService oauthService;
    private final HttpServletResponse response;
    /**
     * 授权
     *
     * @param source   source
     * @throws IOException IOException
     */
    @GetMapping("/render/{source}")
    public void render(@PathVariable String source) throws IOException {
        AuthRequest authRequest = oauthService.getAuthRequest(source);
        String authorize = authRequest.authorize(AuthStateUtils.createState());
        response.sendRedirect(authorize);
    }

    /**
     * 社交登录
     *
     * @param paramJo paramJo
     * @return R
     */
    @PostMapping("/login")
    public R<?> login(@RequestBody JSONObject paramJo) throws Exception {
        JSONObject result = oauthService.login(paramJo);
        log.debug(StrUtil.format("社交登录结果: {}", result.toJSONString()));
        return R.ok(result);
    }

}
