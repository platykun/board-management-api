package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.PlaceEntityBean;
import boardmanagement.api.demo.manage.service.base.PlaceService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 場所コントローラー
 */
@RestController
@CrossOrigin
@RequestMapping("/place")
public class PlaceController {

    /**
     * 場所サービスクラス.
     */
    @NonNull
    private final
    PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    /**
     * 場所を検索する.
     * @param page ページ番号
     * @return 場所情報リスト
     */
    @GetMapping("find_all/{page:^[a-z0-9]+$}")
    public SuccessBean<List<PlaceEntityBean>> findAll(@PathVariable int page) {
        List<PlaceEntityBean> placeEntityBeanList = placeService.findAll(page);

        return new SuccessBean<>(placeEntityBeanList);
    }

    /**
     * キーワードに合致した場所を検索する.
     * @param keyword キーワード
     * @return 場所情報リスト
     */
    @GetMapping("find/{keyword}")
    public SuccessBean<List<PlaceEntityBean>> find(@PathVariable String keyword) {
        List<PlaceEntityBean> placeEntityBeanList = placeService.findByName(keyword);

        return new SuccessBean<>(placeEntityBeanList);
    }

}

