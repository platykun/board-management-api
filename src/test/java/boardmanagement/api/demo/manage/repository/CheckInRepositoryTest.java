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
        testEntityManager.persist( new CheckInEntity(0, 2, "10", new Date(), true));
        testEntityManager.persist( new CheckInEntity(0, 1, "10", new Date(), true));
        testEntityManager.persist( new CheckInEntity(0, 1, "10", new Date(), false));
        testEntityManager.persist( new CheckInEntity(0, 2, "10", new Date(), true));

        Pageable limit = PageRequest.of(0,1);
        List<CheckInEntity> actual = checkInRepository.findByUserIdOrderByIdDesc(limit, 1);

        assertThat(actual.size(), is(1));
        assertCheckInEntityWithoutDate(actual.get(0), new CheckInEntity(3, 1, "10", null, false));
    }

    /**
     * 日付以外のCheckInEntityをアサーションする.
     * @param actual 実際のEnitty
     * @param expected 予想のEntity
     */
    private void assertCheckInEntityWithoutDate(CheckInEntity actual, CheckInEntity expected) {
        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getUserId(), is(expected.getUserId()));
        assertThat(actual.getPlaceName(), is(expected.getPlaceName()));
        assertThat(actual.isCheckedOut(), is(expected.isCheckedOut()));
    }

}