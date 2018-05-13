package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.manage.bean.CreateRoomBean;
import boardmanagement.api.demo.manage.bean.Room;
import boardmanagement.api.demo.manage.service.SampleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * ユーザコントローラー
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    SampleService sampleService;

    /**
     * ルームを作成する.
     * @param createRoomBean ルーム情報
     * @return 作成結果
     */
    @PutMapping(path="{id}/create_room")
    Room createRoom(@RequestBody CreateRoomBean createRoomBean){
        return new Room();
    }


}

