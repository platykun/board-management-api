package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.manage.bean.CreateRoomBean;
import boardmanagement.api.demo.manage.bean.RoomBean;
import boardmanagement.api.demo.manage.dto.RegisterRoomDto;
import boardmanagement.api.demo.manage.dto.RegisteredRoomDto;
import boardmanagement.api.demo.manage.service.RoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * ユーザコントローラー
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    RoomService roomService;

    /**
     * ルームを作成する.
     * @param createRoomBean ルーム情報
     * @return 作成結果
     */
    @PutMapping(path="{id:^[a-z0-9]+$}/create_room")
    RoomBean createRoom(@RequestBody CreateRoomBean createRoomBean, @PathVariable String id){

        RegisterRoomDto registerRoomDto = new RegisterRoomDto();
        BeanUtils.copyProperties(createRoomBean, registerRoomDto);
        RegisteredRoomDto registeredRoomDto = roomService.register(registerRoomDto);

        RoomBean roomBean = new RoomBean();
        BeanUtils.copyProperties(registeredRoomDto, roomBean);

        return roomBean;
    }

    /**
     * ルームに参加する.
     * @param id ユーザID
     * @param roomId ルームID
     * @return 参加後のルーム情報
     */
    @PutMapping(path="{id:^[a-z0-9]+$}/join/{roomId:^[a-z0-9]+$}")
    RoomBean joinRoom(@PathVariable String id, @PathVariable String roomId, Principal principal) {
        // idのユーザをroomIdのルームへ登録させる.
        System.out.println(principal.toString());
        RoomBean roomBean = new RoomBean(1, "ルーム名", "タイトル", "どこか", 2, "備考");

        return roomBean;
    }


}

