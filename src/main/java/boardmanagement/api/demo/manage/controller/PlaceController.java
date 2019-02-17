package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.PlaceEntityBean;
import boardmanagement.api.demo.manage.bean.request.PlaceRequestBean;
import boardmanagement.api.demo.manage.service.base.PlaceService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 場所コントローラー
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class PlaceController {

    /**
     * 場所サービスクラス.
     */
    @NonNull
    private final
    PlaceService placeService;


    /**
     * 場所を作成する.
     *
     * @param placeRequestBean 場所情報.
     * @return 作成済場所情報.
     */
    @PutMapping("/user/places")
    public SuccessBean<PlaceEntityBean> createPlace(@RequestBody PlaceRequestBean placeRequestBean) {
        PlaceEntityBean result = placeService.register(placeRequestBean.toEntityBean());

        return new SuccessBean<>(result);
    }

    /**
     * 場所を更新する.
     *
     * @param placeUpdateRequestBean 更新情報
     * @param placeId                場所ID
     * @return 更新結果情報
     */
    @PutMapping("/user/places/{placeId:^[0-9]+$}")
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
    @DeleteMapping("/user/places/{placeId:^[0-9]+$}")
    public SuccessBean<Boolean> deletePlace(@PathVariable int placeId) {
        Boolean result = placeService.delete(placeId);

        return new SuccessBean<>(result);
    }

    /**
     * 場所を取得する.
     *
     * @param placeId 場所ID
     * @return 更新結果情報
     */
    @GetMapping("/places/{placeId:^[0-9]+$}")
    public SuccessBean<PlaceEntityBean> getPlace(@PathVariable int placeId) {
        PlaceEntityBean result = placeService.findById(placeId);

        return new SuccessBean<>(result);
    }

    @GetMapping("/places")
    public SuccessBean<List<PlaceEntityBean>> find(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "q", required = false) String query
    ) {
        if (query != null) {
            List<PlaceEntityBean> placeEntityBeanList = placeService.findByName(query);
            return new SuccessBean<>(placeEntityBeanList);
        } else {
            int findPage = page < 0 ? 0 : page;
            List<PlaceEntityBean> placeEntityBeanList = placeService.findAll(findPage);
            return new SuccessBean<>(placeEntityBeanList);
        }
    }

}

