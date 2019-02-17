package boardmanagement.api.demo.manage.controller;

import boardmanagement.api.demo.common.bean.SuccessBean;
import boardmanagement.api.demo.common.bean.entity.EventEntityBean;
import boardmanagement.api.demo.common.bean.entity.JoinEventEntityBean;
import boardmanagement.api.demo.manage.dto.EventDetailDto;
import boardmanagement.api.demo.manage.service.base.AppUserService;
import boardmanagement.api.demo.manage.service.base.EventService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * イベント管理用コントローラー
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class EventController {
    @NonNull
    EventService eventService;

    @NonNull
    AppUserService appUserService;

    /**
     * イベントの一覧を取得する.
     *
     * @return イベント一覧情報
     */
    @GetMapping("/events")
    public SuccessBean<List<EventEntityBean>> getEvents(
            @RequestParam(name = "page", required = false) Integer paramPage
    ) {
        int page = paramPage != null ? paramPage : 0;
        var result = eventService.findEventList(page);

        return new SuccessBean<>(result);
    }

    /**
     * イベントの詳細情報を取得する.
     *
     * @param eventId イベントID
     * @return イベントの詳細情報
     */
    @GetMapping("/events/{eventId}")
    public SuccessBean<EventDetailDto> getEventDetail(@PathVariable int eventId) {
        var result = eventService.findEntityDetail(eventId);

        return new SuccessBean<>(result);
    }

    /**
     * イベントを作成する.
     *
     * @param eventEntityBean イベント作成情報
     * @return 作成結果.
     */
    @PutMapping("/users/events")
    public SuccessBean<EventEntityBean> create(@RequestBody EventEntityBean eventEntityBean) {
        var result = eventService.createEvent(eventEntityBean);

        return new SuccessBean<>(result);
    }

    /**
     * イベントを更新する.
     *
     * @param eventId         イベントID
     * @param eventEntityBean イベント更新情報
     * @return 更新結果.
     */
    @PutMapping("users/events/{eventId}")
    public SuccessBean<EventEntityBean> update(
            @PathVariable int eventId,
            @RequestBody EventEntityBean eventEntityBean) {
        var result = eventService.updateEvent(eventId, eventEntityBean);
        return new SuccessBean<>(result);
    }

    /**
     * イベントに参加する.
     *
     * @param eventId イベントID
     * @return 結果
     */
    @PutMapping("/users/events/{eventId}/join")
    public SuccessBean<JoinEventEntityBean> joinEvent(@PathVariable int eventId) {
        var userEntityBean = appUserService.findLoginUserFronSession();
        var userId = userEntityBean.getId();

        var result = eventService.joinEvent(eventId, userId);
        return new SuccessBean<>(result);
    }

    /**
     * ログインユーザの参加中の判定となるイベントを取得する.
     *
     * @return 参加中のイベント情報
     */
    @GetMapping("/users/events/joining")
    public SuccessBean<EventEntityBean> joiningEvent() {
        var userEntityBean = appUserService.findLoginUserFronSession();
        var userId = userEntityBean.getId();
        var result = eventService.findJoiningEvent(userId);
        return new SuccessBean<>(result);
    }
}
