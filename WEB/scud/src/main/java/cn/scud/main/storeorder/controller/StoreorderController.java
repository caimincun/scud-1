package cn.scud.main.storeorder.controller;

import cn.scud.main.storeorder.service.StoreorderService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/10/3.
 */
@Controller
public class StoreorderController {

    @Resource
    private StoreorderService storeorderService;
}
