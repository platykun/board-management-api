package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.CheckInEntityBean;
import boardmanagement.api.demo.common.bean.entity.RoomEntityBean;
import boardmanagement.api.demo.manage.dto.RegisterRoomDto;
import boardmanagement.api.demo.manage.entity.CheckInEntity;
import boardmanagement.api.demo.manage.entity.RoomEntity;
import boardmanagement.api.demo.manage.repository.CheckInRepository;
import boardmanagement.api.demo.manage.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * チェックインサービスクラス.
 */
@Service
public class CheckInService {

    @Autowired
    CheckInRepository checkInRepository;

    /**
     * チェックインする.
     * @param userId ユーザID
     * @return 登録済チェックイン情報
     */
    public CheckInEntityBean register(int userId, int placeId) {
        CheckInEntity entity = new CheckInEntity(0, userId, placeId, new Date(), false);

        CheckInEntity registerdEntity = checkInRepository.save(entity);

        return new CheckInEntityBean(registerdEntity);
    }

    public CheckInEntityBean findLatestCheckin(int userId) {
//        CheckInEntity checkInEntity = checkInRepository.findByUserIdOrderById(userId);
//        return new CheckInEntityBean(checkInEntity);
        return null;
    }

}
