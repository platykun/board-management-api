package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.PlaceEntityBean;
import boardmanagement.api.demo.manage.bean.request.PlaceRequestBean;
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
     * 場所を作成する.
     *
     * @param placeRequestBean 場所情報.
     * @return 作成済場所情報.
     */
    @PutMapping("")
    public SuccessBean<PlaceEntityBean> createPlace(@RequestBody PlaceRequestBean placeRequestBean) {
        PlaceEntityBean result = placeService.register(placeRequestBean.toEntityBean());

        return new SuccessBean<>(result);
    }

    /**
     * 場所を更新する.
     *
     * @param placeUpdateRequestBean 更新情報
     * @param placeId 場所ID
     * @return 更新結果情報
     */
    @PutMapping("{placeId:^[0-9]+$}")
    public SuccessBean<PlaceEntityBean> updatePlace(@RequestBody PlaceRequestBean placeUpdateRequestBean, @PathVariable int placeId) {
        PlaceEntityBean result = placeService.update(placeUpdateRequestBean.toEntityBeanWithId(placeId));

        return new SuccessBean<>(result);
    }

    /**
     * 場所を削除する.
     *
     * @param placeId 場所ID
     * @return 削除成功可否
     */
    @DeleteMapping("{placeId:^[0-9]+$}")
    public SuccessBean<Boolean> deletePlace(@PathVariable int placeId) {
        Boolean result = placeService.delete(placeId);

        return new SuccessBean<>(result);
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

