package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.entity.RoomEntityBean;
import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.manage.service.base.RoomService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public SuccessBean<List<RoomEntityBean>> findAll(@PathVariable int page) {
        List<RoomEntityBean> roomEntityBeanList = roomService.findAll(page);

        return new SuccessBean<>(roomEntityBeanList);
    }
}

