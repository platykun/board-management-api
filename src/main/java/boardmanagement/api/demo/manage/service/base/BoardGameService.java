package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.BoardGameEntityBean;
import boardmanagement.api.demo.manage.entity.BoardGameEntity;
import boardmanagement.api.demo.manage.repository.BoardGameRepository;
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
    private BoardGameRepository boardGameRepository;

    public BoardGameService(BoardGameRepository boardGameRepository) {
        this.boardGameRepository = boardGameRepository;
    }

    /**
     * ボードゲームを登録する.
     * @param boardGameEntityBean ボードゲーム登録情報
     * @return 登録済ボードゲーム情報
     */
    public BoardGameEntityBean register(BoardGameEntityBean boardGameEntityBean) {
        if(boardGameEntityBean.getId() != 0) throw new IllegalArgumentException();

        BoardGameEntity entity = boardGameEntityBean.toEntity();
        BoardGameEntity result = boardGameRepository.save(entity);

        return new BoardGameEntityBean(result);
    }

    /**
     * ボードゲームを更新する.
     * @param boardGameEntityBean ボードゲーム更新情報
     * @return 更新後のボードゲーム情報
     */
    public BoardGameEntityBean update(BoardGameEntityBean boardGameEntityBean) {
        if(boardGameEntityBean.getId() == 0) throw new IllegalArgumentException();

        BoardGameEntity entity = boardGameEntityBean.toEntity();
        BoardGameEntity result = boardGameRepository.save(entity);

        return new BoardGameEntityBean(result);
    }

    /**
     * ボードゲームを削除する.
     * @param boardGameId ボードゲーム削除ID
     * @return 成功可否
     */
    public Boolean delete(int boardGameId) {
        boardGameRepository.deleteById(boardGameId);
        return true;
    }

    /**
     * ボードゲーム一覧を取得する.
     * @param page ページID
     * @return ボードゲーム  一覧
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
