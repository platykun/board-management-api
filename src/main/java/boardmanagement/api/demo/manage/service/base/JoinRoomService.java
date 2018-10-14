package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.JoinRoomEntityBean;
import boardmanagement.api.demo.manage.dto.RegisterJoinRoomDto;
import boardmanagement.api.demo.manage.entity.JoinRoomEntity;
import boardmanagement.api.demo.manage.repository.JoinRoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ルーム参加サービスクラス.
 */
@Service
public class JoinRoomService {
    private static final int FIND_LIMIT = 20;

    /**
     * ルーム参加リポジトリ.
     */
    private final
    JoinRoomRepository joinRoomRepository;

    @Autowired
    public JoinRoomService(JoinRoomRepository joinRoomRepository) {
        this.joinRoomRepository = joinRoomRepository;
    }

    /**
     * ルームを登録する.
     * @param dto ルーム情報
     * @return 登録済ルーム情報
     */
    public JoinRoomEntityBean register(RegisterJoinRoomDto dto) {
        JoinRoomEntity joinRoomEntity = new JoinRoomEntity();
        BeanUtils.copyProperties(dto, joinRoomEntity);
        joinRoomEntity.setCreate(new Date());
        JoinRoomEntity savedEntity = joinRoomRepository.save(joinRoomEntity);

        return new JoinRoomEntityBean(savedEntity);
    }


    /** ルームIDから参加情報を取得する */
    public List<JoinRoomEntityBean> findJoiningUser(int roomId) {
        List<JoinRoomEntity> result = joinRoomRepository.findByRoomId(roomId);

        return result.stream().map(JoinRoomEntityBean::new).collect(Collectors.toList());
    }

    /**
     * 最新のルーム参加情報を取得する.
     * @param userId ユーザID
     * @return 最新のルーム情報
     */
    public JoinRoomEntityBean findLatestJoinRoomByUserId(String userId) {
        Pageable limit = PageRequest.of(0, 1);
        Page<JoinRoomEntity> joinRoomEntities = joinRoomRepository.findByUserIdOrderByCreateDesc(limit, userId);
        return new JoinRoomEntityBean(joinRoomEntities.getContent().get(0));
    }

    /**
     * ユーザIDに紐付いた参加情報を取得する.
     *
     * @param userId ユーザID
     * @param page ページ番号
     * @return 参加情報のリスト.
     */
    public List<JoinRoomEntityBean> findJoinRoomByUserId(String userId, int page) {
        Pageable limit = PageRequest.of(page, FIND_LIMIT);
        Page<JoinRoomEntity> joinRoomEntities = joinRoomRepository.findByUserIdOrderByCreateDesc(limit, userId);

        return joinRoomEntities.stream().map(JoinRoomEntityBean::new).collect(Collectors.toList());
    }
}
