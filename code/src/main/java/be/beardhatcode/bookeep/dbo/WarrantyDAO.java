package be.beardhatcode.bookeep.dbo;


import be.beardhatcode.bookeep.Tag;
import be.beardhatcode.bookeep.Warranty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
@Service
public class WarrantyDAO extends DAO<Warranty> {
    @Autowired
    public WarrantyDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate,"statements");
    }

    protected Warranty buildFromResultSet(ResultSet rs) throws SQLException {
        return new Warranty(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("receipt"),
                rs.getString("note")
        );
    }
}
