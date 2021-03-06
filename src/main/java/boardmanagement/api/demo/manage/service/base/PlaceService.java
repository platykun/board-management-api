package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.PlaceEntityBean;
import boardmanagement.api.demo.manage.entity.PlaceEntity;
import boardmanagement.api.demo.manage.repository.PlaceRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 場所サービスクラス.
 */
@Service
@RequiredArgsConstructor
public class PlaceService {
    private static final int FIND_LIMIT = 100;

    @NonNull
    private PlaceRepository placeRepository;

    /**
     * 場所を登録する.
     * @param placeEntityBean 場所登録情報
     * @return 登録済ルーム情報
     */
    public PlaceEntityBean register(PlaceEntityBean placeEntityBean) {
        if(placeEntityBean.getId() != 0)throw new IllegalArgumentException();

        PlaceEntity entity = placeEntityBean.toEntity();
        PlaceEntity result = placeRepository.save(entity);

        return new PlaceEntityBean(result);
    }

    /**
     * 場所を更新する.
     * @param placeEntityBean 場所更新情報
     * @return 更新済ルーム情報
     */
    public PlaceEntityBean update(PlaceEntityBean placeEntityBean) {
        if(placeEntityBean.getId() == 0)throw new IllegalArgumentException();

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

//        List<PlaceEntityBean> placeEntityBeanList = new ArrayList<>();
//        placeEntityPage.forEach(r -> placeEntityBeanList.add(new PlaceEntityBean(r)));
        return placeEntityPage.stream().map(PlaceEntityBean::new).collect(Collectors.toList());
//        return placeEntityBeanList;
    }

    /**
     * キーワードをもとに場所を検索する.
     * @param keyword 検索キーワード
     * @return 条件に合致する場所リスト
     */
    public List<PlaceEntityBean> findByName(String keyword) {
        Pageable limit = PageRequest.of(0, FIND_LIMIT);

        Page<PlaceEntity> resultEntityPage;
        resultEntityPage = placeRepository.findByName(keyword, limit);

        List<PlaceEntityBean> resultPlaces = new ArrayList<>();
        resultEntityPage.forEach(r -> resultPlaces.add(new PlaceEntityBean(r)));

        return resultPlaces;
    }

    /**
     * IDから場所情報を取得する.
     *
     * @param placeId 場所ID
     * @return 場所情報
     */
    public PlaceEntityBean findById(int placeId) {
        Optional<PlaceEntity> result = placeRepository.findById(placeId);
        return new PlaceEntityBean(result.get());
    }

    /**
     * IDから場所を削除する.
     * @param placeId 場所ID
     * @return 場所情報.
     */
    public Boolean delete(int placeId) {
        placeRepository.deleteById(placeId);
        return true;
    }
}
