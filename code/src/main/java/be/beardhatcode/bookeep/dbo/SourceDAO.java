package be.beardhatcode.bookeep.dbo;


import be.beardhatcode.bookeep.sources.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
@Service
public class SourceDAO extends DAO<Source> {
    @Autowired
    public SourceDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate,"invoices");
    }

    protected Source buildFromResultSet(ResultSet rs) throws SQLException {
        try {
            return new Source(
                    rs.getLong("id"),
                    rs.getString("name"),
                    Source.ImportType.valueOf(rs.getString("type")),
                    Class.forName(rs.getString("class"))
                    );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}