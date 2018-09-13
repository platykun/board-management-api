package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.manage.bean.RoomBean;
import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.manage.dto.RegisteredRoomDto;
import boardmanagement.api.demo.manage.service.RoomService;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ルームコントローラー
 */
@RestController
@CrossOrigin
@RequestMapping("/room")
public class RoomController {

    /**
     * ルームサービスクラス.
     */
    @NonNull
    private final
    RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * ルームを検索する.
     * @param page ページ番号
     * @return ルーム情報
     */
    @GetMapping("find_all/{page:^[a-z0-9]+$}")
    public SuccessBean<List<RoomBean>> findAll(@PathVariable int page) {
        List<RegisteredRoomDto> registerdRoomDtoList = roomService.findAll(page);

        List<RoomBean> result = new ArrayList<>();
        registerdRoomDtoList.forEach(r -> {
            RoomBean roomBean = new RoomBean();
            BeanUtils.copyProperties(r, roomBean);
            result.add(roomBean);
        });

        return new SuccessBean<>(result);
    }
}

