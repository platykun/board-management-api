package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.CheckInEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * チェックインリポジトリクラスのテスト.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CheckInRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CheckInRepository checkInRepository;

    @Before
    public void テストデータの初期化() {
        testEntityManager.clear();
    }

    @Test
    public void findByUserIdOrderByIdDescが最新のものを適切に取得できていることの確認() throws Exception {
        testEntityManager.persist( new CheckInEntity(0, "2", 10, new Date(), true));
        testEntityManager.persist( new CheckInEntity(0, "1", 10, new Date(), true));
        testEntityManager.persist( new CheckInEntity(0, "1", 10, new Date(), false));
        testEntityManager.persist( new CheckInEntity(0, "2", 10, new Date(), true));

        Pageable limit = PageRequest.of(0,1);
        List<CheckInEntity> actual = checkInRepository.findByUserIdOrderByIdDesc(limit, "1");

        assertThat(actual.size(), is(1));
        assertCheckInEntityWithoutDate(actual.get(0), new CheckInEntity(3, "1", 10, null, false));
    }

    @Test
    public void チェックアウトメソッド_対象機能のみチェックアウト_対象外もののがチェックアウトされていないこと() {
        testEntityManager.persist( new CheckInEntity(0, "1", 10, new Date(), false));
        testEntityManager.persist( new CheckInEntity(0, "1", 10, new Date(), false));
        testEntityManager.persist( new CheckInEntity(0, "1", 10, new Date(), true));
        testEntityManager.persist( new CheckInEntity(0, "2", 10, new Date(), false));
        testEntityManager.persist( new CheckInEntity(0, "2", 10, new Date(), true));

        Integer resultCount = checkInRepository.checkOutByUserId("1");

        // 更新件数はユーザID=1かつチェックアウトフラグがfalseの2件
        assertThat(resultCount, is(2));

        Pageable limit = PageRequest.of(0,4);
        List<CheckInEntity> actual = checkInRepository.findByUserIdOrderByIdDesc(limit, "1");

        // 更新後、すべてのデータがチェックアウト済になっていることを確認する.
        assertThat(actual.size(), is(3));
        actual.forEach(a -> assertThat(a.isCheckedOut(), is(true)));
    }

    /**
     * 日付以外のCheckInEntityをアサーションする.
     * @param actual 実際のEnitty
     * @param expected 予想のEntity
     */
    private void assertCheckInEntityWithoutDate(CheckInEntity actual, CheckInEntity expected) {
        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getUserId(), is(expected.getUserId()));
        assertThat(actual.getPlaceId(), is(expected.getPlaceId()));
        assertThat(actual.isCheckedOut(), is(expected.isCheckedOut()));
    }
}