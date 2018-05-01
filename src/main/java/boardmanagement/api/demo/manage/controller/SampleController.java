package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.manage.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * サンプル実装
 */
@RestController
public class SampleController {

    @Autowired
    SampleService sampleService;

    /**
     * DB接続検証用の実装
     *
     * @param model モデル
     * @return トップ画面
     */
    @RequestMapping("")
    public String top(Model model) {

        sampleService.callRepositorySample();
        return "hogehoge";
    }
}

