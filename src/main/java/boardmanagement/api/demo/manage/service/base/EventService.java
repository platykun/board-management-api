package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.common.bean.entity.EventEntityBean;
import boardmanagement.api.demo.common.bean.entity.JoinEventEntityBean;
import boardmanagement.api.demo.common.bean.entity.ResultEntityBean;
import boardmanagement.api.demo.common.bean.entity.UserEntityBean;
import boardmanagement.api.demo.manage.dto.EventDetailDto;
import boardmanagement.api.demo.manage.entity.EventEntity;
import boardmanagement.api.demo.manage.entity.JoinEventEntity;
import boardmanagement.api.demo.manage.repository.EventRepository;
import boardmanagement.api.demo.manage.repository.JoinEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * イベント管理サービスクラス.
 */
@Service
public class EventService {
    private static final int FIND_LIMIT = 20;

    private final
    EventRepository eventRepository;

    private final
    JoinEventRepository joinEventRepository;

    private final
    ResultService resultService;

    private final
    AppUserService appUserService;

    @Autowired
    public EventService(EventRepository eventRepository, JoinEventRepository joinEventRepository, ResultService resultService, AppUserService appUserService) {
        this.eventRepository = eventRepository;
        this.joinEventRepository = joinEventRepository;
        this.resultService = resultService;
        this.appUserService = appUserService;
    }

    /**
     * イベントの一覧を取得する.
     *
     * @param page ページ番号
     * @return イベントの一覧
     */
    public List<EventEntityBean> findEventList(int page) {
        Pageable limit = PageRequest.of(page, FIND_LIMIT);
        Page<EventEntity> eventList = eventRepository.findAll(limit);

        return eventList.stream().map(EventEntityBean::new).collect(Collectors.toList());
    }

    /**
     * イベントを作成する.
     *
     * @param event イベント情報
     * @return 作成結果
     */
    public EventEntityBean createEvent(EventEntityBean event) {
        // idが0では無い場合、例外を発砲する.
        if(event.getId() != 0) throw new IllegalArgumentException();

        EventEntity result = eventRepository.save(event.toEntity());

        return new EventEntityBean(result);
    }

    /**
     * イベントを更新する.
     * @param event イベント情報
     * @return 更新結果
     */
    public EventEntityBean updateEvent(int eventId, EventEntityBean event) {
        // eventIdとEventEntityBeanのIDが一致しない場合例外を発砲する.
        if(eventId != event.getId()) throw new IllegalArgumentException();

        EventEntity result = eventRepository.save(event.toEntity());
        return new EventEntityBean(result);
    }

    /**
     * イベントに参加する.
     *
     * @param eventId イベントiD
     * @param userId  ユーザID
     * @return イベント情報
     */
    public JoinEventEntityBean joinEvent(int eventId, String userId) {
        JoinEventEntity joinEventEntity = new JoinEventEntity(0, eventId, userId);
        JoinEventEntity result = joinEventRepository.save(joinEventEntity);

        return new JoinEventEntityBean(result);
    }

    /**
     * イベントの詳細情報を取得する.
     *
     * @param eventId イベントID
     * @return イベントの詳細情報.
     */
    public EventDetailDto findEntityDetail(int eventId) {

        // イベント情報を取得する.
        EventEntity eventEntity = eventRepository.findById(eventId);
        EventEntityBean eventEntityBean = new EventEntityBean(eventEntity);

        // 参加ユーザを取得する.
        List<JoinEventEntity> joinEventEntityList = joinEventRepository.findAllByEventId(eventId);
        List<JoinEventEntityBean> joinEventEntityBeanList =
                joinEventEntityList.stream().map(JoinEventEntityBean::new).collect(Collectors.toList());
        List<UserEntityBean> joinUserList =
                joinEventEntityBeanList.stream().map(j -> {
                    String userId = j.getUserId();
                    return appUserService.findByUserId(userId).get();
                }).collect(Collectors.toList());

        // 結果情報を取得する.
        List<ResultEntityBean> resultEntityList = resultService.findResultsByEventId(0, eventId);

        return new EventDetailDto(eventEntityBean, joinUserList, resultEntityList);
    }

    /**
     * 引数のユーザが現在参加になっているイベントを検索する.
     * 多重登録になっている場合、どちらかが反映される.
     * @param userId ユーザID
     * @return 参加中のイベント情報
     */
    public EventEntityBean findJoiningEvent(String userId) {
        List<EventEntity> results = eventRepository.findAllValidEvent(userId, new Date());

        // 存在しない場合は空を返却
        if(results.isEmpty())return null;

        // 検索された値のうち、一つ目を返却
        return new EventEntityBean(results.get(0));
    }

}
