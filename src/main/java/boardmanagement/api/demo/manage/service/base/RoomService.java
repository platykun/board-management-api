package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.RoomEntityBean;
import boardmanagement.api.demo.manage.dto.RoomDto;
import boardmanagement.api.demo.manage.entity.RoomEntity;
import boardmanagement.api.demo.manage.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
     * @param registerDto ルーム情報
     * @return 登録済ルーム情報
     */
    public RoomEntityBean register(RoomDto registerDto) {
        if(registerDto.getId() != 0) throw new IllegalArgumentException();

        RoomEntity target = registerDto.toRoomEntity(new Date());
        RoomEntity registedRoom = roomRepository.save(target);

        return new RoomEntityBean(registedRoom);
    }

    /**
     * ルームを更新する.
     * @param updateDto ルーム情報
     * @return 更新済ルーム情報
     */
    public RoomEntityBean update(RoomDto updateDto) {
        if(updateDto.getId() == 0) throw new IllegalArgumentException();

        RoomEntity target = updateDto.toRoomEntity(null);
        RoomEntity updatedRoom = roomRepository.save(target);

        return new RoomEntityBean(updatedRoom);
    }

    /**
     * ルームを削除する.
     * @param roomId ルームID
     * @return 削除結果
     */
    public Boolean delete(int roomId) {
        roomRepository.deleteById(roomId);

        return true;
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

    /**
     * ルームIDからルーム情報を取得する.
     * @param roomId ルームID
     * @return ルーム情報
     */
    public Optional<RoomEntityBean> findById(int roomId) {
        return  roomRepository.findById(roomId).map(r -> new RoomEntityBean(r));
        }
}
