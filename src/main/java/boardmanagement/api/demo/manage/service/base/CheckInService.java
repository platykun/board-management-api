package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.CheckInEntityBean;
import boardmanagement.api.demo.manage.entity.CheckInEntity;
import boardmanagement.api.demo.manage.repository.CheckInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public CheckInEntityBean register(int userId, String placeName) {
        CheckInEntity entity = new CheckInEntity(0, userId, placeName, new Date(), false);

        CheckInEntity registerdEntity = checkInRepository.save(entity);

        return new CheckInEntityBean(registerdEntity);
    }

    /**
     * 最新のチェックイン情報を取得する.
     *
     * @param userId ユーザID
     * @return 最新のチェックイン情報
     */
    public CheckInEntityBean findLatestCheckin(int userId) {
        Pageable limit = PageRequest.of(0, 1);
        Page<CheckInEntity> checkInEntity = checkInRepository.findByUserIdOrderByTimestampDesc(limit, userId);
        return new CheckInEntityBean(checkInEntity.getContent().get(0));
    }

}
