package br.com.people.api.db;

import br.com.people.api.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataBase {

    // DB Operations https://mkyong.com/spring-boot/spring-boot-jdbc-examples/

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void insert(Person person) {
        jdbcTemplate.update(
                "INSERT INTO PERSON (NAME, AGE) VALUES (?,?)",
                person.getName(),
                person.getAge()
        );
    }

    public Person find(long id) {
        return jdbcTemplate.queryForObject("SELECT ID, NAME, AGE FROM PERSON WHERE ID = ?",
                (rs, rowNum) -> {
                    var person = new Person();
                    person.setId(rs.getLong("ID"));
                    person.setName(rs.getString("NAME"));
                    person.setAge(rs.getInt("AGE"));
                    return person;
                },
                id);
    }

    public void deleteById(long id) {
        jdbcTemplate.update(
                "DELETE INTO PERSON WHERE ID = ?", id

        );
    }
}
