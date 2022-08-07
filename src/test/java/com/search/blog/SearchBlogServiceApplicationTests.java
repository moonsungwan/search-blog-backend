package com.search.blog;

import org.junit.BeforeClass;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
class SearchBlogServiceApplicationTests {

    @BeforeClass
    @Sql({"classpath:schema.sql", "classpath:/data.sql"})
    public static void setUpBeforeClass() throws Exception {
    	
    }
    
}
