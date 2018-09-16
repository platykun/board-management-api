package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.BoardGameEntityBean;
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
     * ボードゲームを検索する.
     * @param page ページ番号
     * @return ルーム情報
     */
    @GetMapping("find_all/{page:^[a-z0-9]+$}")
    public SuccessBean<List<BoardGameEntityBean>> findAll(@PathVariable int page) {
        List<BoardGameEntityBean> boardGameEntityBeanList = boardGameService.findAll(page);

        return new SuccessBean<>(boardGameEntityBeanList);
    }

}

