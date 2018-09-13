package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.RoomEntityBean;
import boardmanagement.api.demo.manage.dto.RegisteredRoomDto;
import boardmanagement.api.demo.manage.service.RoomService;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    RoomService roomService;

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

