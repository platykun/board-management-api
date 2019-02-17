package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.ResultEntityBean;
import boardmanagement.api.demo.manage.bean.response.ResultResponseBean;
import boardmanagement.api.demo.manage.bean.response.UserResultResponseBean;
import boardmanagement.api.demo.manage.dto.ResultRegistDto;
import boardmanagement.api.demo.manage.dto.UserResultRegistDto;
import boardmanagement.api.demo.manage.entity.ResultEntity;
import boardmanagement.api.demo.manage.entity.UserResultEntity;
import boardmanagement.api.demo.manage.repository.ResultRepository;
import boardmanagement.api.demo.manage.repository.UserResultRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 結果情報サービスクラス.
 */
@Service
@RequiredArgsConstructor
public class ResultService {
    private static final int FIND_LIMIT = 20;

    @NonNull
    ResultRepository resultRepository;

    @NonNull
    UserResultRepository userResultRepository;

    /**
     * 結果を新規登録する.
     *
     * @param resultRegistDto 結果情報
     * @return 結果登録結果
     */
    public ResultResponseBean register(ResultRegistDto resultRegistDto) {
        // TODO: userIDからroomIDを取得する処理

        ResultEntity entity = new ResultEntity(
                0,
                resultRegistDto.getBoardGameId(),
                resultRegistDto.getBoardGameTitle(),
                resultRegistDto.getPlaceId(),
                resultRegistDto.getPlaceName(),
                resultRegistDto.getEventId(),
                new Date()
        );

        ResultEntity result = resultRepository.save(entity);
        int resultId = result.getId();

        List<UserResultRegistDto> userResultList = resultRegistDto.getUserResultList();

        List<UserResultEntity> userResultResultList = new ArrayList<>();
        userResultList.forEach(u -> {
            UserResultEntity userResultEntity = new UserResultEntity(
                    0,
                    resultId,
                    u.getUserId(),
                    u.getScore(),
                    u.getComment()
            );
            UserResultEntity r = userResultRepository.save(userResultEntity);
            userResultResultList.add(r);
        });

        return new ResultResponseBean(result, userResultResultList);
    }

    /**
     * 結果を取得する.
     *
     * @param page ページング
     * @return 結果のリスト
     */
    public List<ResultEntity> findResults(int page) {
        Pageable limit = PageRequest.of(page, FIND_LIMIT);
        return resultRepository.findAll(limit)
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * ユーザIDから結果を取得する.
     *
     * @param userId ユーザID
     * @param page ページング
     * @return 結果のリスト
     */
    public List<ResultResponseBean> findResultsByUserId(String userId, int page) {
        Pageable limit = PageRequest.of(page, FIND_LIMIT);

        List<UserResultEntity> userResultList = userResultRepository.findByUserId(limit, userId);

        List<ResultEntity> joinedResults = userResultList
                .stream()
                .map(u -> resultRepository.findById(u.getResultId()).get())
                .collect(Collectors.toList());

        return joinedResults
                .stream()
                .map(j -> {
                    // ユーザIDに紐づく結果ごとに、関連するユーザごとの結果を取得する.
                    int resultId = j.getId();
                    List<UserResultEntity> userResultEntityList = userResultRepository.findByResultId(resultId);
                    return new ResultResponseBean(
                            j,
                            userResultEntityList
                    );
                })
                .collect(Collectors.toList());
    }

    /**
     * IDに紐付いた結果情報を取得する.
     *
     * @param id 結果ID
     * @return 結果情報
     */
    public ResultResponseBean findResultsByResultId(int id) {

        Pageable limit = PageRequest.of(0, FIND_LIMIT);
        Optional<ResultEntity> result = resultRepository.findById(id);

        List<UserResultEntity> userResultEntityList = userResultRepository.findByResultId(id);

        return new ResultResponseBean(result.get(), userResultEntityList);
    }


    /**
     * ユーザごとの結果を登録する.
     *
     * @param resultId 結果ID
     * @param userResultRegistDto ユーザごとの結果情報
     * @return 登録結果
     */
    public UserResultResponseBean registUserResult(int resultId, UserResultRegistDto userResultRegistDto) {
        UserResultEntity data = new UserResultEntity(
                0,
                resultId,
                userResultRegistDto.getUserId(),
                userResultRegistDto.getScore(),
                userResultRegistDto.getComment()
        );

        UserResultEntity result = userResultRepository.save(data);

        return new UserResultResponseBean(result);
    }

    /**
     * ユーザごとの結果を更新する.
     *
     * @param resultId 更新ID
     * @param userResultRegistDto ユーザごとの結果情報
     * @return 登録結果
     */
    public UserResultResponseBean updateUserResult(int resultId, String userId, UserResultRegistDto userResultRegistDto) {
        List<UserResultEntity> targets = userResultRepository.findByResultIdAndUserId(resultId, userId);
        if(targets.size() != 1) {
            return null;
        }

        UserResultEntity updatedUserResult = targets.stream().map(
                t-> {
                    UserResultEntity data = new UserResultEntity(
                            t.getId(),
                            resultId,
                            userResultRegistDto.getUserId(),
                            userResultRegistDto.getScore(),
                            userResultRegistDto.getComment()
                    );
                    return userResultRepository.save(data);
                }
        ).findFirst().get();

        return new UserResultResponseBean(updatedUserResult);
    }

    /**
     * ユーザごとの結果を削除する.
     *
     * @return 削除結果
     */
    public Boolean deleteUserResult(int resultId, String userId) {
        List<UserResultEntity> targets = userResultRepository.findByResultIdAndUserId(resultId, userId);
        if(targets.size() != 1){
            return false;
        }

        targets.forEach(
          target -> userResultRepository.delete(target)
        );
        return true;
    }

    /**
     * ユーザIDに紐付いた記録概要一覧を取得.
     * @param userId ユーザID
     * @return 記録概要
     */
    public List<ResultEntity> findResultOutlineByUserId(String userId) {
        Pageable limit = PageRequest.of(0, FIND_LIMIT);
        List<UserResultEntity> userResultList = userResultRepository.findByUserId(limit, userId);
        return userResultList
                .stream()
                .map(u -> resultRepository.findById(u.getResultId()).get())
                .collect(Collectors.toList());
    }

    /**
     * 場所IDに紐づく記録概要一覧を取得する.
     * @param placeId 場所ID
     * @return 記録概要
     */
    public List<ResultEntity> findResultOutlineByPlaceId(int placeId) {
        Pageable limit = PageRequest.of(0, FIND_LIMIT);
        return resultRepository.findByPlaceIdOrderByCreateDesc(limit, placeId)
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * イベントIDに紐づく記録一覧を取得する.
     * @param page ページ番号
     * @param eventId イベントID
     * @return 記録一覧
     */
    public List<ResultEntityBean> findResultsByEventId(int page, int eventId) {
        Pageable limit = PageRequest.of(page, FIND_LIMIT);
        Page<ResultEntity> resultEntityList = resultRepository.findAllByEventId(limit, eventId);

        return resultEntityList.stream().map(ResultEntityBean::new).collect(Collectors.toList());
    }
}
