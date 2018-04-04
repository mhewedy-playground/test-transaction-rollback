package com.example.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaApplication.class)
public class MyServiceIntTest {

    @Autowired
    private Entity1Repo entity1Repo;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void create() throws Exception {

        MyService2 mock = mock(MyService2.class);
        doThrow(new RuntimeException("bla bla")).when(mock).create(anyLong());


        MyService myService = new MyService(entity1Repo, mock);

        try {
            myService.create("some name");
            fail("should never fails");
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("bla bla");        // success
        }

        Entity1 dbEntity1 = (Entity1) entityManager.createQuery("from Entity1 where name = 'some name'").getSingleResult();

        assertThat(dbEntity1).isNull();                             // fail, however it should be null as it is rolled-back
    }
}
