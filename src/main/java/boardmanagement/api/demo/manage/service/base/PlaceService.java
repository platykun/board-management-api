package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.BoardGameEntityBean;
import boardmanagement.api.demo.common.bean.entity.PlaceEntityBean;
import boardmanagement.api.demo.manage.entity.BoardGameEntity;
import boardmanagement.api.demo.manage.entity.PlaceEntity;
import boardmanagement.api.demo.manage.repository.BoardGameRepository;
import boardmanagement.api.demo.manage.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 場所サービスクラス.
 */
@Service
public class PlaceService {
    private static final int FIND_LIMIT = 100;

    @Autowired
    private PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    /**
     * 場所を登録する.
     * @param placeEntityBean 場所登録情報
     * @return 登録済ルーム情報
     */
    public PlaceEntityBean register(PlaceEntityBean placeEntityBean) {
        PlaceEntity entity = placeEntityBean.toEntity();
        PlaceEntity result = placeRepository.save(entity);

        return new PlaceEntityBean(result);
    }

    /**
     * 場所一覧を取得する.
     * @param page ページID
     * @return 場所一覧
     */
    public List<PlaceEntityBean> findAll(int page) {
        Pageable limit = PageRequest.of(page,FIND_LIMIT);
        Page<PlaceEntity> placeEntityPage = placeRepository.findAll(limit);

        List<PlaceEntityBean> placeEntityBeanList = new ArrayList<>();
        placeEntityPage.forEach(r -> {
            placeEntityBeanList.add(new PlaceEntityBean(r));
        });
        return placeEntityBeanList;
    }


}
