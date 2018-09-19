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
        testEntityManager.persist(new RoomEntity(0, "後方一致_abcdefg", "boardTitle", "placeName", 1, "remark"));
        testEntityManager.persist(new RoomEntity(0, "部分一致_defghij", "boardTitle", "placeName", 1, "remark"));
        testEntityManager.persist(new RoomEntity(0, "efg前方一致_dhij", "boardTitle", "placeName", 1, "remark"));
        testEntityManager.persist(new RoomEntity(0, "一致無し_klmnopq", "boardTitle", "placeName", 1, "remark"));


        Pageable limit = PageRequest.of(0, 100);
        List<RoomEntity> actual = roomRepository.findByRoomName("efg", limit).getContent();


        assertThat(actual.size(), is(3));
        assertRoomEntity(actual.get(0), new RoomEntity(3, "efg前方一致_dhij", "boardTitle", "placeName", 1, "remark"));
        assertRoomEntity(actual.get(1), new RoomEntity(2, "部分一致_defghij", "boardTitle", "placeName", 1, "remark"));
        assertRoomEntity(actual.get(2), new RoomEntity(1, "後方一致_abcdefg", "boardTitle", "placeName", 1, "remark"));
    }

    /**
     * 日付以外のCheckInEntityをアサーションする.
     *
     * @param actual   実際のEnitty
     * @param expected 予想のEntity
     */
    private void assertRoomEntity(RoomEntity actual, RoomEntity expected) {
        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getRoomName(), is(expected.getRoomName()));
        assertThat(actual.getBoardTitle(), is(expected.getBoardTitle()));
        assertThat(actual.getPlaceName(), is(expected.getPlaceName()));
        assertThat(actual.getPlayer(), is(expected.getPlayer()));
        assertThat(actual.getRemark(), is(expected.getRemark()));
    }

}