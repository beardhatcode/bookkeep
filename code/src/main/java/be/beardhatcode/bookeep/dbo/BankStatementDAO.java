package be.beardhatcode.bookeep.dbo;


import be.beardhatcode.bookeep.BankStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
@Service
public class BankStatementDAO extends DAO<BankStatement> {
    @Autowired
    public BankStatementDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate,"statements");
    }

    protected BankStatement buildFromResultSet(ResultSet rs) throws SQLException {
        return new BankStatement(
                rs.getLong("id"),
                rs.getDouble("amount"),
                rs.getString("sender"),
                rs.getString("receiver"),
                rs.getString("comment"),
                rs.getTimestamp("date"),
                rs.getString("note"),
                rs.getString("raw")
        );
    }
}
