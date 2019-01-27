package boardmanagement.api.demo.manage.repository;

import boardmanagement.api.demo.manage.entity.EventEntity;
import boardmanagement.api.demo.manage.entity.JoinEventEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * EventRepositoryのテスト
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class EventRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void 参加中判定が正しく動いていること() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

        // targetDateを時間で挟みうちにしたパターン。参加中判定.
        EventEntity test1 = testEntityManager.persist(new EventEntity(0, 1, "場所名1", sdf.parse("2000/01/05 12:00:00"), sdf.parse("2000/01/05 18:00:00"), "コメント", null));
        // targetDateを日付で挟みうちにしたパターン。参加中判定.
        EventEntity test2 = testEntityManager.persist(new EventEntity(0, 1, "場所名1", sdf.parse("2000/01/04 10:00:00"), sdf.parse("2000/01/06 10:00:00"), "コメント", null));
        // targetDateがfromもtoも前。不参加判定.
        EventEntity test3 = testEntityManager.persist(new EventEntity(0, 1, "場所名1", sdf.parse("2000/01/04 10:00:00"), sdf.parse("2000/01/04 20:00:00"), "コメント", null));
        // targetDateがfromもtoも後。不参加判定.
        EventEntity test4 = testEntityManager.persist(new EventEntity(0, 1, "場所名1", sdf.parse("2000/01/06 10:00:00"), sdf.parse("2000/01/06 20:00:00"), "コメント", null));
        // targetDateを時間で挟みうちにしたパターン。ユーザと紐づかないため参加中判定ではない.
        EventEntity test5 = testEntityManager.persist(new EventEntity(0, 1, "場所名1", sdf.parse("2000/01/05 12:00:00"), sdf.parse("2000/01/05 18:00:00"), "コメント", null));

        testEntityManager.persist(new JoinEventEntity(0, test1.getId(), "target"));
        testEntityManager.persist(new JoinEventEntity(0, test2.getId(), "target"));
        testEntityManager.persist(new JoinEventEntity(0, test3.getId(), "target"));
        testEntityManager.persist(new JoinEventEntity(0, test4.getId(), "target"));
        testEntityManager.persist(new JoinEventEntity(0, test5.getId(), "other"));

        Date targetDate = sdf.parse("2000/01/05 15:00:00");
        List<EventEntity> result = eventRepository.findAllValidEvent("target", targetDate);

        // 二件選択される
        assertThat(result.size(), is(2));
    }
}