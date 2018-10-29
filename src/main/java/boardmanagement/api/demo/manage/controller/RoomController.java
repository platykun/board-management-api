package boardmanagement.api.demo.manage.controller;

import org.springframework.web.bind.annotation.*;

/**
 * ルームコントローラー
 */
@RestController
@CrossOrigin
public class RoomController {

//    /**
//     * ルームサービスクラス.
//     */
//    @NonNull
//    private final
//    RoomService roomService;
//
//    @Autowired
//    public RoomController(RoomService roomService) {
//        this.roomService = roomService;
//    }
//
//    /**
//     * ルームを検索する.
//     * @param page ページ番号
//     * @return ルーム情報
//     */
//    @GetMapping("/room/find_all/{page:^[a-z0-9]+$}")
//    public SuccessBean<List<RoomEntityBean>> findAll(@PathVariable int page) {
//        List<RoomEntityBean> roomEntityBeanList = roomService.findAll(page);
//
//        return new SuccessBean<>(roomEntityBeanList);
//    }
//
//
//    /**
//     * キーワードをもとにルームを検索する.
//     * @param keyword キーワード
//     * @return 検索結果
//     */
//    @GetMapping("/room/find/{keyword}")
//    public SuccessBean<List<RoomEntityBean>> find(@PathVariable String keyword) {
//        List<RoomEntityBean> roomEntityBeanList = roomService.findByRoomName(keyword);
//
//        return new SuccessBean<>(roomEntityBeanList);
//    }
}

