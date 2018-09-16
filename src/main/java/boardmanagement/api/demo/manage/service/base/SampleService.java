package boardmanagement.api.demo.manage.service.base;

import boardmanagement.api.demo.manage.entity.*;
import boardmanagement.api.demo.manage.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * サンプルのサービスクラス.
 */
@Service
public class SampleService {

    @Autowired
    BoardGameRepository boardGameRepository;

    @Autowired
    JoinRoomRepository joinRoomRepository;

    @Autowired
    PasswordRepository passwordRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    public void callRepositorySample(){
        BoardGameEntity boardGameEntity = boardGameRepository.findByTitle("ボードゲーム0");
        JoinRoomEntity joinRoomEntity = joinRoomRepository.findByUserIdAndRoomId(0, 0);
        PasswordEntity passwordEntity = passwordRepository.findById(0);
        PlaceEntity placeEntity = placeRepository.findByName("ボドゲショップ1");
        ResultEntity resultEntity = resultRepository.findByUserIdAndRoomId(0, 0);
        RoomEntity roomEntity = roomRepository.findById(0);
        UserEntity userEntity = userRepository.findById(0);

        System.out.println("test");

        return;
    }
}
