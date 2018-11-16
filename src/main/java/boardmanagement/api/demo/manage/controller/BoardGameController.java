package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.BoardGameEntityBean;
import boardmanagement.api.demo.manage.bean.request.BoardGameRequestBean;
import boardmanagement.api.demo.manage.service.base.BoardGameService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ボードゲームコントローラー
 */
@RestController
@CrossOrigin
@RequestMapping("/boardgame")
public class BoardGameController {

    /**
     * ボードゲームサービスクラス.
     */
    @NonNull
    private final
    BoardGameService boardGameService;

    @Autowired
    public BoardGameController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    /**
     * ボードゲームを作成する.
     *
     * @param requestBean 作成ボードゲーム情報
     * @return 作成後のボードゲーム情報
     */
    @PutMapping("")
    public SuccessBean<BoardGameEntityBean> create(@RequestBody BoardGameRequestBean requestBean) {
        BoardGameEntityBean result = boardGameService.register(requestBean.toEntityBean());
        return new SuccessBean<>(result);
    }

    /**
     * ボードゲームを更新する.
     * @param requestBean 更新情報
     * @param boardGameId 更新対象のID
     * @return 更新後のボードゲーム情報
     */
    @PutMapping("{boardGameId:^[0-9]+$}")
    public SuccessBean<BoardGameEntityBean> update(@RequestBody BoardGameRequestBean requestBean, @PathVariable int boardGameId) {
        BoardGameEntityBean result = boardGameService.update(requestBean.toEntityBeanWithId(boardGameId));
        return new SuccessBean<>(result);
    }

    /**
     * ボードゲームを削除する.
     * @param boardGameId 削除対象ID
     * @return 削除結果
     */
    @DeleteMapping("{boardGameId:^[0-9]+$}")
    public SuccessBean<Boolean> delete(@PathVariable int boardGameId) {
        Boolean result = boardGameService.delete(boardGameId);
        return new SuccessBean<>(result);
    }


    /**
     * ボードゲームを検索する.
     * @param page ページ番号
     * @return ルーム情報
     */
    @GetMapping("find_all/{page:^[a-z0-9]+$}")
    public SuccessBean<List<BoardGameEntityBean>> findAll(@PathVariable int page) {
        List<BoardGameEntityBean> boardGameEntityBeanList = boardGameService.findAll(page);

        return new SuccessBean<>(boardGameEntityBeanList);
    }

    /**
     * キーワードに合致したボードゲームを検索する.
     * @param keyword キーワード
     * @return ボードゲームリスト
     */
    @GetMapping("find/{keyword}")
    public SuccessBean<List<BoardGameEntityBean>> find(@PathVariable String keyword) {
        List<BoardGameEntityBean> boardGameEntityBeanList = boardGameService.findByName(keyword);

        return new SuccessBean<>(boardGameEntityBeanList);
    }
}

