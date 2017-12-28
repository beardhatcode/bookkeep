package be.beardhatcode.bookeep.dbo;


import be.beardhatcode.bookeep.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
@Service
public class OrganisationDAO extends DAO<Organisation> {
    @Autowired
    public OrganisationDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate,"invoices");
    }

    protected Organisation buildFromResultSet(ResultSet rs) throws SQLException {
        return new Organisation(
                rs.getLong("id"),
                rs.getString("name")
        );
    }
}