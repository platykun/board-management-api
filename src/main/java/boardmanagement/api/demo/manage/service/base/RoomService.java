package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.RoomEntityBean;
import boardmanagement.api.demo.manage.dto.RegisterRoomDto;
import boardmanagement.api.demo.manage.dto.RegisteredRoomDto;
import boardmanagement.api.demo.manage.entity.RoomEntity;
import boardmanagement.api.demo.manage.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ルームサービスクラス.
 */
@Service
public class RoomService {
    private static final int FIND_LIMIT = 100;

    @Autowired
    RoomRepository roomRepository;

    /**
     * ルームを登録する.
     *
     * @param dto ルーム情報
     * @return 登録済ルーム情報
     */
    public RoomEntityBean register(RegisterRoomDto dto) {
        RoomEntity roomEntity = new RoomEntity();
        BeanUtils.copyProperties(dto, roomEntity);
        RoomEntity registedRoom = roomRepository.save(roomEntity);

        return new RoomEntityBean(registedRoom);
    }

    /**
     * ルーム一覧を取得する.
     *
     * @param page ページID
     * @return ルーム一覧
     */
    public List<RoomEntityBean> findAll(int page) {
        Pageable limit = PageRequest.of(page, FIND_LIMIT);

        Page<RoomEntity> roomEntityPage = roomRepository.findAll(limit);

        List<RoomEntityBean> resultRooms = new ArrayList<>();
        roomEntityPage.forEach(r -> resultRooms.add(new RoomEntityBean(r)));

        return resultRooms;
    }

    /**
     * キーワードをもとにルームを検索する.
     * @param keyword 検索キーワード
     * @return 条件に合致するルームリスト
     */
    public List<RoomEntityBean> findByRoomName(String keyword) {
        Pageable limit = PageRequest.of(0, FIND_LIMIT);

        Page<RoomEntity> roomEntityPage = roomRepository.findByRoomName(keyword, limit);

        List<RoomEntityBean> resultRooms = new ArrayList<>();
        roomEntityPage.forEach(r -> resultRooms.add(new RoomEntityBean(r)));

        return resultRooms;
    }


}
