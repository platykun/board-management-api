package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.RoomEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * ルームリポジトリクラスのテスト.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RoomRepository roomRepository;

    @Before
    public void テストデータの初期化() {
        testEntityManager.clear();
    }

    @Test
    public void findByRoomNameで部分一致した値を取得できること() throws Exception {
        Date date = new Date();
        testEntityManager.persist(new RoomEntity(0, "後方一致_abcdefg", 0, "boardGameTitle", 0, "placeName", 1, "remark", 0, date));
        testEntityManager.persist(new RoomEntity(0, "部分一致_defghij", 0, "boardGameTitle", 0, "placeName", 1, "remark", 0, date));
        testEntityManager.persist(new RoomEntity(0, "efg前方一致_dhij", 0, "boardGameTitle", 0, "placeName", 1, "remark", 0, date));
        testEntityManager.persist(new RoomEntity(0, "一致無し_klmnopq", 0, "boardGameTitle", 0, "placeName", 1, "remark", 0, date));


        Pageable limit = PageRequest.of(0, 100);
        List<RoomEntity> actual = roomRepository.findByRoomName("efg", limit).getContent();


        assertThat(actual.size(), is(3));
        assertRoomEntity(actual.get(0), new RoomEntity(3, "efg前方一致_dhij", 0, "boardGameTitle", 0, "placeName", 1, "remark", 0, date));
        assertRoomEntity(actual.get(1), new RoomEntity(2, "部分一致_defghij", 0, "boardGameTitle", 0, "placeName", 1, "remark", 0, date));
        assertRoomEntity(actual.get(2), new RoomEntity(1, "後方一致_abcdefg", 0, "boardGameTitle", 0, "placeName", 1, "remark", 0, date));
    }

    /**
     * 日付以外のCheckInEntityをアサーションする.
     *
     * @param actual   実際のEnitty
     * @param expected 予想のEntity
     */
    private void assertRoomEntity(RoomEntity actual, RoomEntity expected) {
        // auto incrementのIDはアサーション対象外
        // assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getRoomName(), is(expected.getRoomName()));
        assertThat(actual.getBoardGameId(), is(expected.getBoardGameId()));
        assertThat(actual.getBoardGameTitle(), is(expected.getBoardGameTitle()));
        assertThat(actual.getPlaceId(), is(expected.getPlaceId()));
        assertThat(actual.getPlaceName(), is(expected.getPlaceName()));
        assertThat(actual.getPlayer(), is(expected.getPlayer()));
        assertThat(actual.getRemark(), is(expected.getRemark()));
        assertThat(actual.getStatus(), is(expected.getStatus()));
        assertThat(actual.getCreate(), is(expected.getCreate()));
    }

}