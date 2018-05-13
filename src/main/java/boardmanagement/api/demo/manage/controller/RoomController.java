package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.manage.service.SampleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ルームコントローラー
 */
@RestController
@CrossOrigin
@RequestMapping("/room")
public class RoomController {

    @Autowired
    SampleService sampleService;

    @RequestMapping("*")
    public SampleResult top2(Model model) {
        sampleService.callRepositorySample();
        return new SampleResult();
    }

    @Data
    class SampleResult {
        private String name = "sample";
        private String password = "p@ssw0rd";
    }
}

