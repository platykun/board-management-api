package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.CheckInEntityBean;
import boardmanagement.api.demo.manage.entity.CheckInEntity;
import boardmanagement.api.demo.manage.repository.CheckInRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * チェックインサービスクラス.
 */
@Service
@RequiredArgsConstructor
public class CheckInService {
    private static final int FIND_LIMIT = 20;

    @NonNull
    CheckInRepository checkInRepository;

    /**
     * チェックインする.チェックイン時、チェックイン状態もののがあればチェックアウトさせる.
     * @param userId ユーザID
     * @return 登録済チェックイン情報
     */
    public CheckInEntityBean checkIn(String userId, int placeId) {
        CheckInEntity entity = new CheckInEntity(0, userId, placeId, null, new Date(), false);

        checkInRepository.checkOutByUserId(userId);
        CheckInEntity registeredEntity = checkInRepository.save(entity);

        return new CheckInEntityBean(registeredEntity);
    }

    /**
     * チェックインする.宅ボドゲの場合を想定.
     * @param userId チェックインユーザID
     * @param checkInUserId チェックイン先ユーザID.
     * @return 登録済みチェックイン情報
     */
    public CheckInEntityBean checkInAtUserHome(String userId, String checkInUserId) {
        CheckInEntity entity = new CheckInEntity(0, userId, -1, checkInUserId, new Date(), false);

        checkInRepository.checkOutByUserId(userId);
        CheckInEntity registeredEntity = checkInRepository.save(entity);

        return new CheckInEntityBean(registeredEntity);
    }

    /**
     * 最新のチェックイン情報を取得する.
     *
     * @param userId ユーザID
     * @return 最新のチェックイン情報
     */
    public Optional<CheckInEntityBean> findLatestCheckin(String userId) {
        Pageable limit = PageRequest.of(0, 1);
        Page<CheckInEntity> checkInEntity = checkInRepository.findByUserIdOrderByTimestampValueDesc(limit, userId);

        if(!checkInEntity.hasContent()){
            return Optional.empty();
        }

        CheckInEntity checkInInfoEntity = checkInEntity.getContent().get(0);
        return Optional.of(new CheckInEntityBean(checkInInfoEntity));
    }

    /**
     * ユーザIDに紐付いたチェックイン情報を取得する.
     *
     * @param id ユーザID
     * @param page ページ番号
     * @return チェックイン情報のリスト
     */
    public List<CheckInEntityBean> findCheckInByUserId(String id, int page) {
        Pageable limit = PageRequest.of(page, FIND_LIMIT);
        Page<CheckInEntity> checkInEntities = checkInRepository.findByUserIdOrderByTimestampValueDesc(limit, id);

        return checkInEntities.stream().map(CheckInEntityBean::new).collect(Collectors.toList());
    }

    /**
     * 引数に対応するカラムをチェックアウト状態に更新する.
     *
     * @param userId チェックアウト対象のユーザID
     * @return 更新後のチェックアウトEntity情報
     */
    public Integer checkout(String userId) {
        return checkInRepository.checkOutByUserId(userId);
    }
}
