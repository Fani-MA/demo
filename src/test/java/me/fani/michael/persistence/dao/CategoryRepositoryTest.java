package me.fani.michael.persistence.dao;


import me.fani.michael.App;
import me.fani.michael.BaseDbTest;
import me.fani.michael.persistence.entity.Category;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = App.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-it.properties")
public class CategoryRepositoryTest extends BaseDbTest {

    @Autowired
    CategoryRepository sut;

    @Test
    public void testFindAll() {
        var res1 = sut.findAll();

        var newCategory = new Category();
        newCategory.setName("testCategory1");
        newCategory.setParentId(1L);
        var savedCategory = sut.save(newCategory);

        var res2 = sut.findAll();

        Assert.assertEquals(res2.size(), res1.size() + 1);

        var selectedCategory = sut.findById(savedCategory.getId());
        Assert.assertTrue(selectedCategory.isPresent());
        Assert.assertEquals(selectedCategory.get().getName(), "testCategory1");

    }
}
