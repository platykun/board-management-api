package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.ResultEntityBean;
import boardmanagement.api.demo.manage.dto.ResultDto;
import boardmanagement.api.demo.manage.entity.ResultEntity;
import boardmanagement.api.demo.manage.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 結果情報サービスクラス.
 */
@Service
public class ResultService {

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
        entity.setUserId(resultDto.getUserId());
        entity.setRoomId(0);
        entity.setRank(resultDto.getRank());
        entity.setScore(resultDto.getScore());
        entity.setComment(resultDto.getComment());

        ResultEntity result = resultRepository.save(entity);

        return new ResultEntityBean(result);
    }

}
