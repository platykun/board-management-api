package boardmanagement.api.demo.manage.bean.response;

import boardmanagement.api.demo.manage.entity.ResultEntity;
import boardmanagement.api.demo.manage.entity.UserResultEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 結果登録用Beanクラス.
 */
@Data
public class ResultResponseBean {
    /**
     * 結果ID.
     */
    private int id;

    /**
     * ボードゲームID.
     */
    private int boardGameId;

    /**
     * ボードゲームタイトル.
     */
    private String boardGameTitle;

    /**
     * 場所ID.
     */
    private int placeId;

    /**
     * 場所名.
     */
    private String placeName;

    /**
     * 作成日.
     */
    private Date create;

    /**
     * ユーザごとの結果情報.
     */
    private List<UserResultResponseBean> userList;

    public ResultResponseBean(ResultEntity result, List<UserResultEntity> userResultResultList) {

        this.id = result.getId();
        this.boardGameId = result.getBoardGameId();
        this.boardGameTitle = result.getBoardGameTitle();
        this.placeId = result.getPlaceId();
        this.placeName = result.getPlaceName();
        this.create = result.getCreate();
        this.userList = userResultResultList
                .stream()
                .map(UserResultResponseBean::new)
                .collect(Collectors.toList());
    }
}
