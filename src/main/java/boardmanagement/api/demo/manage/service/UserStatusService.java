package boardmanagement.api.demo.manage.service;

import boardmanagement.api.demo.common.bean.entity.CheckInEntityBean;
import boardmanagement.api.demo.common.bean.entity.JoinRoomEntityBean;
import boardmanagement.api.demo.common.bean.entity.RoomEntityBean;
import boardmanagement.api.demo.common.bean.entity.UserEntityBean;
import boardmanagement.api.demo.manage.dto.UserStatusResponseDto;
import boardmanagement.api.demo.manage.service.base.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ユーザステータス情報取得サービスクラス.
 */
@Service
public class UserStatusService {

    @NonNull
    private CheckInService checkInService;

    @Autowired
    private UserService userService;

    @Autowired
    private JoinRoomService joinRoomService;

    @Autowired
    private RoomService roomService;

    @Autowired
    public UserStatusService(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    /**
     * ログインユーザのステータス情報を取得する.
     * @return ログインユーザのステータス情報
     */
    public UserStatusResponseDto getLoginUserStatus() {

        UserEntityBean loginUserBean = userService.getLoginUser();

        // ログイン者のチェックイン情報.
        CheckInEntityBean checkInEntityBean = checkInService.findLatestCheckin(loginUserBean.getId());
        // ログイン者の参加情報.
        JoinRoomEntityBean joiningRoom = joinRoomService.findLatestJoinRoomByUserId(loginUserBean.getId());
        RoomEntityBean room = roomService.findById(joiningRoom.getRoomId());

        // チェックインで参加しているユーザ一覧
        List<JoinRoomEntityBean> joiningJoinRoom = joinRoomService.findJoiningUser(joiningRoom.getRoomId());
        List<String> joiningUserList = joiningJoinRoom.stream().map(j -> userService.findByUserId(j.getUserId()).getName()).collect(Collectors.toList());

        return new UserStatusResponseDto(
                "placeName",// TODO: 正しく取得できていない.
                room.getRoomName(),
                room.getBoardTitle(),
                room.getPlayer(),
                room.getRemark(),
                joiningUserList
                );
    }
}
