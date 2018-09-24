package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.JoinRoomEntityBean;
import boardmanagement.api.demo.manage.dto.RegisterJoinRoomDto;
import boardmanagement.api.demo.manage.entity.JoinRoomEntity;
import boardmanagement.api.demo.manage.repository.JoinRoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ルーム参加サービスクラス.
 */
@Service
public class JoinRoomService {

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
     * @return 最新のルーム情報
     */
    public JoinRoomEntityBean findLatestJoinRoomByUserId(int userId) {
        // TODO: 最新情報を取得するロジックが必要.
        List<JoinRoomEntity> joinRoomEntities = joinRoomRepository.findByUserId(userId);
        return new JoinRoomEntityBean(joinRoomEntities.get(0));
    }
}
