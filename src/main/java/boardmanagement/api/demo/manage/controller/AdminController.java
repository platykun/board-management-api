package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.manage.bean.Room;
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
    public List<Room> findAll(@PathVariable int page) {
        List<RegisteredRoomDto> registerdRoomDtoList = roomService.findAll(page);

        List<Room> result = new ArrayList<>();
        registerdRoomDtoList.forEach(r -> {
            Room room = new Room();
            BeanUtils.copyProperties(r, room);
            result.add(room);
        });
        return result;
    }
}

