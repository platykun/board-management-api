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
    @PutMapping("/user/boardgames")
    public SuccessBean<BoardGameEntityBean> create(@RequestBody BoardGameRequestBean requestBean) {
        BoardGameEntityBean result = boardGameService.register(requestBean.toEntityBean());
        return new SuccessBean<>(result);
    }

    /**
     * ボードゲームを更新する.
     *
     * @param requestBean 更新情報
     * @param boardGameId 更新対象のID
     * @return 更新後のボードゲーム情報
     */
    @PutMapping("/user/boardgames/{boardGameId:^[0-9]+$}")
    public SuccessBean<BoardGameEntityBean> update(@RequestBody BoardGameRequestBean requestBean, @PathVariable int boardGameId) {
        BoardGameEntityBean result = boardGameService.update(requestBean.toEntityBeanWithId(boardGameId));
        return new SuccessBean<>(result);
    }

    /**
     * ボードゲームを削除する.
     *
     * @param boardGameId 削除対象ID
     * @return 削除結果
     */
    @DeleteMapping("/admin/boardgames/{boardGameId:^[0-9]+$}")
    public SuccessBean<Boolean> delete(@PathVariable int boardGameId) {
        Boolean result = boardGameService.delete(boardGameId);
        return new SuccessBean<>(result);
    }


    /**
     * ボードゲームを検索する.
     * queryが指定されている場合、キーワードに合致しているかどうかの判定を行う
     * queryが指定されていない場合全ての値を取得する
     *
     * @param page [任意]ページ番号 指定なしの場合:0 負の値の場合:0
     * @return ボードゲーム情報
     */
    @GetMapping("/boardgames")
    public SuccessBean<List<BoardGameEntityBean>> find(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "q", required = false) String query
    ) {
        if (query != null) {
            List<BoardGameEntityBean> boardGameEntityBeanList = boardGameService.findByName(query);
            return new SuccessBean<>(boardGameEntityBeanList);
        } else {
            int findPage = page < 0 ? 0 : page;
            List<BoardGameEntityBean> boardGameEntityBeanList = boardGameService.findAll(findPage);
            return new SuccessBean<>(boardGameEntityBeanList);
        }
    }
}

