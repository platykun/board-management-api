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
import java.util.Optional;
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
     *
     * @return ログインユーザのステータス情報
     */
    public UserStatusResponseDto getLoginUserStatus() {

        // ユーザが見つからなかった場合Optionalでexceptionが送出される
        UserEntityBean loginUserBean = userService.getLoginUser().get();

        // ログイン者のチェックイン情報.取得できなかった場合、空文字を返却する.
        Optional<CheckInEntityBean> checkInEntityBean = checkInService.findLatestCheckin(loginUserBean.getId());
        String placeName = checkInEntityBean.map(CheckInEntityBean::getPlaceName).orElse("");
        // ログイン者の参加情報.
        JoinRoomEntityBean joiningRoom = joinRoomService.findLatestJoinRoomByUserId(loginUserBean.getId());
        Optional<RoomEntityBean> room = roomService.findById(joiningRoom.getRoomId());

        String roomName = "";
        String boardTitle = "";
        int player = 0;
        String remark = "";
        if (room.isPresent()) {
            RoomEntityBean r = room.get();
            roomName = r.getRoomName();
            boardTitle = r.getBoardTitle();
            player = r.getPlayer();
            remark = r.getRemark();
        }

        // チェックインで参加しているユーザ一覧
        List<JoinRoomEntityBean> joiningJoinRoom = joinRoomService.findJoiningUser(joiningRoom.getRoomId());
        List<String> joiningUserList = joiningJoinRoom.stream().map(
                j -> userService.findByUserId(j.getUserId()).map(UserEntityBean::getName).orElse("")).collect(Collectors.toList());

        return new UserStatusResponseDto(
                placeName,
                roomName,
                boardTitle,
                player,
                remark,
                joiningUserList
        );
    }
}
