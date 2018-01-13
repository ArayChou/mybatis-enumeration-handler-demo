package com.gmail.aray.chou.mybatis.enumeration.type.bug.demo;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * Created by zhouhongyang@zbj.com on 1/10/2018.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        //test(EnumOrdinalTypeHandler.class);
        test(CodedEnumHandler.class);
    }

    private static <T extends BlogMapper> void test(Class<?> handler) {
        JdbcDataSource dataSource = getJdbcDataSource();

        createTable(dataSource);


        TransactionFactory transactionFactory =
                new JdbcTransactionFactory();

        Environment environment =
                new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);


        configuration.getTypeHandlerRegistry().register(BlogStatus.class, handler);
        configuration.getTypeHandlerRegistry().register(BlogType.class, handler);


        configuration.addMapper(BlogMapper.class);

        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(configuration);


        try (SqlSession session = sqlSessionFactory.openSession();) {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            try {
                mapper.insertBlog(new Blog(1, "Blog Title", BlogStatus.NORMAL, BlogType.TECH));
                System.out.println("insertBlog successfully.");
            } catch (Exception e) {
                System.out.println("insertBlog Fail. ");
                e.printStackTrace();
            }
            try {
                mapper.updateBlog(BlogStatus.HIDDEN, BlogType.TECH);
                System.out.println("updateBlog successfully.");
            } catch (Exception e) {
                System.out.println("updateBlog Fail. ");
                e.printStackTrace();
            }

            try {
                List<Blog> list = mapper.select(BlogType.TECH);
                list.forEach(e -> System.out.println(e));
                System.out.println("select successfully. ");
            } catch (Exception e) {
                System.out.println("select Fail. ");
                e.printStackTrace();
            }
            try {
                mapper.delete(BlogType.TECH);
                System.out.println("delete successfully. ");
            } catch (Exception e) {
                System.out.println("delete Fail. ");
                e.printStackTrace();
            }
        }
    }

    private static void createTable(JdbcDataSource dataSource) {
        try (
                Connection conn = dataSource.getConnection();
                Statement statement = conn.createStatement()
        ) {
            statement.execute("create table  IF NOT EXISTS blog (id int, title varchar(128),status INT, type INT )");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JdbcDataSource getJdbcDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:./demoDb");
        dataSource.setUser("user");
        dataSource.setPassword("password");
        return dataSource;
    }
}
