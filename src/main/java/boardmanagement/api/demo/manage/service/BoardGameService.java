package boardmanagement.api.demo.manage.service;

import boardmanagement.api.demo.common.bean.entity.BoardGameEntityBean;
import boardmanagement.api.demo.common.bean.entity.RoomEntityBean;
import boardmanagement.api.demo.manage.dto.RegisterRoomDto;
import boardmanagement.api.demo.manage.entity.BoardGameEntity;
import boardmanagement.api.demo.manage.entity.RoomEntity;
import boardmanagement.api.demo.manage.repository.BoardGameRepository;
import boardmanagement.api.demo.manage.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ボードゲームサービスクラス.
 */
@Service
public class BoardGameService {
    private static final int FIND_LIMIT = 100;

    @Autowired
    BoardGameRepository boardGameRepository;

    /**
     * ボードゲームを登録する.
     * @param boardGameEntityBean ボードゲーム登録情報
     * @return 登録済ルーム情報
     */
    public BoardGameEntityBean register(BoardGameEntityBean boardGameEntityBean) {
        BoardGameEntity entity = boardGameEntityBean.toEntity();
        BoardGameEntity result = boardGameRepository.save(entity);

        return new BoardGameEntityBean(result);
    }

    /**
     * ルーム一覧を取得する.
     * @param page ページID
     * @return ルーム一覧
     */
    public List<BoardGameEntityBean> findAll(int page) {
        Pageable limit = PageRequest.of(page,FIND_LIMIT);
        Page<BoardGameEntity> boardGameEntityPage = boardGameRepository.findAll(limit);

        List<BoardGameEntityBean> boardGameEntityBeanList = new ArrayList<>();
        boardGameEntityPage.forEach(r -> {
            boardGameEntityBeanList.add(new BoardGameEntityBean(r));
        });
        return boardGameEntityBeanList;
    }


}
