package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.manage.bean.CreateRoomRequestBean;
import boardmanagement.api.demo.common.bean.entity.JoinRoomEntityBean;
import boardmanagement.api.demo.common.bean.entity.RoomEntityBean;
import boardmanagement.api.demo.manage.dto.RegisterJoinRoomDto;
import boardmanagement.api.demo.manage.dto.RegisterRoomDto;
import boardmanagement.api.demo.manage.dto.RegisteredRoomDto;
import boardmanagement.api.demo.manage.service.JoinRoomService;
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

    /**
     * ルームサービスクラス.
     */
    private final
    RoomService roomService;

    /**
     * ルーム参加サービスクラス.
     */
    private final JoinRoomService joinRoomService;

    @Autowired
    public UserController(RoomService roomService, JoinRoomService joinRoomService) {
        this.roomService = roomService;
        this.joinRoomService = joinRoomService;
    }

    /**
     * ルームを作成する.
     * @param createRoomRequestBean ルーム情報
     * @return 作成結果
     */
    @PutMapping(path="{id:^[a-z0-9]+$}/create_room")
    SuccessBean<RoomEntityBean> createRoom(@RequestBody CreateRoomRequestBean createRoomRequestBean, @PathVariable String id){

        RegisterRoomDto registerRoomDto = new RegisterRoomDto();
        BeanUtils.copyProperties(createRoomRequestBean, registerRoomDto);
        RoomEntityBean createdRoom = roomService.register(registerRoomDto);

        return new SuccessBean<>(createdRoom);
    }

    /**
     * ルームに参加する.
     * @param id ユーザID
     * @param roomId ルームID
     * @return 参加後のルーム情報
     */
    @PutMapping(path="{id:^[a-z0-9]+$}/join/{roomId:^[a-z0-9]+$}")
    SuccessBean<JoinRoomEntityBean> joinRoom(@PathVariable String id, @PathVariable String roomId, Principal principal) {
        // idのユーザをroomIdのルームへ登録させる.
        int userIdNum = Integer.parseInt(id);
        int roomIdNum = Integer.parseInt(roomId);
        RegisterJoinRoomDto registerJoinRoomDto = new RegisterJoinRoomDto(userIdNum, roomIdNum, false);

        JoinRoomEntityBean result = joinRoomService.register(registerJoinRoomDto);

        return new SuccessBean<>(result);
    }
}

