package boardmanagement.api.demo.manage.service;

import boardmanagement.api.demo.manage.dto.RegisterJoinRoomDto;
import boardmanagement.api.demo.manage.entity.JoinRoomEntity;
import boardmanagement.api.demo.manage.repository.JoinRoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public RegisterJoinRoomDto register(RegisterJoinRoomDto dto) {
        JoinRoomEntity joinRoomEntity = new JoinRoomEntity();
        BeanUtils.copyProperties(dto, joinRoomEntity);
        JoinRoomEntity savedEntity = joinRoomRepository.save(joinRoomEntity);

        RegisterJoinRoomDto registeredRoomDto = new RegisterJoinRoomDto();
        BeanUtils.copyProperties(savedEntity, registeredRoomDto);

        return registeredRoomDto;
    }
}
