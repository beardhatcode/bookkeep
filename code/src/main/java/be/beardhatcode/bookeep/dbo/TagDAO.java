package be.beardhatcode.bookeep.dbo;


import be.beardhatcode.bookeep.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
@Service
public class TagDAO extends DAO<Tag> {
    @Autowired
    public TagDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate,"statements");
    }

    protected Tag buildFromResultSet(ResultSet rs) throws SQLException {
        return new Tag(
                rs.getLong("id"),
                rs.getString("name")
        );
    }
}
