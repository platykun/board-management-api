package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.ResultEntityBean;
import boardmanagement.api.demo.manage.dto.ResultDto;
import boardmanagement.api.demo.manage.entity.ResultEntity;
import boardmanagement.api.demo.manage.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 結果情報サービスクラス.
 */
@Service
public class ResultService {
    private static final int FIND_LIMIT = 20;

    @Autowired
    ResultRepository resultRepository;

    /**
     * 結果を登録する.
     *
     * @param resultDto 結果情報
     * @return 結果登録結果
     */
    public ResultEntityBean register(ResultDto resultDto) {
        // TODO: userIDからroomIDを取得する処理

        ResultEntity entity = new ResultEntity();
        entity.setParentId(resultDto.getParentId());
        entity.setUserId(resultDto.getUserId());
        entity.setBoardGameId(resultDto.getBoardGameId());
        entity.setBoardGameTitle(resultDto.getBoardGameTitle());
        entity.setPlaceId(resultDto.getPlaceId());
        entity.setPlaceName(resultDto.getPlaceName());
        entity.setScore(resultDto.getScore());
        entity.setComment(resultDto.getComment());
        entity.setCreate(new Date());

        ResultEntity result = resultRepository.save(entity);

        return new ResultEntityBean(result);
    }

    /**
     * 場所IDから結果を取得する.
     *
     * @param page ページング
     * @return 結果のリスト
     */
    public List<ResultEntityBean> findResults(int page) {
        Pageable limit = PageRequest.of(page, FIND_LIMIT);
        Page<ResultEntity> joinRoomEntities = resultRepository.findAll(limit);
        return joinRoomEntities.stream().map(ResultEntityBean::new).collect(Collectors.toList());
    }

    /**
     * ユーザIDから結果を取得する.
     *
     * @param userId ユーザID
     * @param page ページング
     * @return 結果のリスト
     */
    public List<ResultEntityBean> findResultsByUserId(String userId, int page) {
        Pageable limit = PageRequest.of(page, FIND_LIMIT);
        Page<ResultEntity> joinRoomEntities = resultRepository.findByUserIdOrderByCreateDesc(limit, userId);
        return joinRoomEntities.stream().map(ResultEntityBean::new).collect(Collectors.toList());
    }

    /**
     * 場所IDから結果を取得する.
     *
     * @param placeId 場所ID
     * @param page ページング
     * @return 結果のリスト
     */
    public List<ResultEntityBean> findResultsByPlaceId(int placeId, int page) {
        Pageable limit = PageRequest.of(page, FIND_LIMIT);
        Page<ResultEntity> joinRoomEntities = resultRepository.findByPlaceIdOrderByCreateDesc(limit, placeId);
        return joinRoomEntities.stream().map(ResultEntityBean::new).collect(Collectors.toList());
    }

    /**
     * IDに紐付いた結果情報を取得する.
     *
     * @param id 結果ID
     * @return 結果情報
     */
    public ResultEntityBean findResultsById(int id) {
        return new ResultEntityBean(Objects.requireNonNull(resultRepository.findById(id).orElse(null)));
    }

    /**
     * 指定された親IDを持つ結果情報を取得する.
     * @param parentId 親ID
     * @return 結果情報リスト
     */
    public List<ResultEntityBean> findChildResultById(int parentId) {
        Pageable limit = PageRequest.of(0, FIND_LIMIT);
        Page<ResultEntity> children = resultRepository.findByParentId(limit, parentId);
        return children.stream().map(ResultEntityBean::new).collect(Collectors.toList());
    }
}
