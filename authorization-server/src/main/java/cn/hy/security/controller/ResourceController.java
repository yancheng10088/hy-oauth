package cn.hy.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

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
