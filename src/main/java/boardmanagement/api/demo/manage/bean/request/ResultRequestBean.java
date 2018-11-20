package boardmanagement.api.demo.manage.bean.request;

import boardmanagement.api.demo.manage.dto.ResultRegistDto;
import boardmanagement.api.demo.manage.dto.UserResultRegistDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 結果登録用Beanクラス.
 */
@Data
public class ResultRequestBean {
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
     * ユーザごとの結果情報.
     */
    private List<UserResultRequestBean> userList;

    /**
     * ResultDtoに変換する.
     * @return ResultRegistDto
     */
    public ResultRegistDto toResultDto() {
        List<UserResultRegistDto> userDtoList = userList.stream().map(u -> u.toResultDto()).collect(Collectors.toList());

        return new ResultRegistDto(
                this.boardGameId,
                this.boardGameTitle,
                this.placeId,
                this.placeName,
                userDtoList
        );
    }
}
