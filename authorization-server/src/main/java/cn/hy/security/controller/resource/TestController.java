package cn.hy.security.controller.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cnh
 * @date 2022/8/15
 * @description TODO
 */
@RestController
@RequestMapping("/resource/test")
public class TestController {
    /**
     * 测试
     * @return
     */
    @RequestMapping(value = "/api")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String api() {
        return "SUCCESS";
    }
}
