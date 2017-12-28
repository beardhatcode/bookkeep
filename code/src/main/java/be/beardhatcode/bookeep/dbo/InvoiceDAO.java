package be.beardhatcode.bookeep.dbo;


import be.beardhatcode.bookeep.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
@Service
public class InvoiceDAO extends DAO<Invoice> {
    @Autowired
    public InvoiceDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate,"invoices");
    }

    protected Invoice buildFromResultSet(ResultSet rs) throws SQLException {
        return new Invoice(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("organisation_id"),
                rs.getLong("type_id"),
                rs.getDouble("amount"),
                rs.getTime("date"),
                rs.getString("note"),
                rs.getString("filename"),
                rs.getString("content"),
                rs.getLong("paid"),
                rs.getString("raw")
        );
    }
}