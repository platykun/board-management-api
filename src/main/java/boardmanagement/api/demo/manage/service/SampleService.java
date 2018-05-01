package boardmanagement.api.demo.manage.service;

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
    PasswordRepository passwordRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    UserReposiotry userReposiotry;

    public void callRepositorySample(){
        BoardGameEntity boardGameEntity = boardGameRepository.findByTitle("ボードゲーム0");
        PasswordEntity passwordEntity = passwordRepository.findById(0);
        PlaceEntity placeEntity = placeRepository.findByName("ボドゲショップ1");
        RecordEntity recordEntity = recordRepository.findById(0);
        UserEntity userEntity = userReposiotry.findById(0);

        System.out.println("test");

        return;
    }
}
