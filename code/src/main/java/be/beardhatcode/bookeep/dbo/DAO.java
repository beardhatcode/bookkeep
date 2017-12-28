package be.beardhatcode.bookeep.dbo;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

abstract class DAO<T> {
    protected final JdbcTemplate jdbc;
    protected final String table;

    public DAO(JdbcTemplate jdbc, String table) {
        this.jdbc = jdbc;
        this.table = table;
    }

    protected T extractor(ResultSet rs) throws SQLException {
        if(rs.next()){
            return this.buildFromResultSet(rs);
        }
        else
        {
            return null;
        }
    }


    protected T rowMapper(ResultSet rs,int i) throws SQLException {
        return this.buildFromResultSet(rs);
    }

    protected abstract T buildFromResultSet(ResultSet rs) throws SQLException ;


    public List<T> getAll(){
        return this.jdbc.query(
                "SELECT * FROM "+this.table,
                this::rowMapper
        );
    }

    public T getbyId(long id) {
        return this.jdbc.query(
                "SELECT * FROM "+this.table +" WHERE id = ?",
                new Object[]{id},
                this::extractor
        );

    }

    public List<T> getbyId(long[] ids) {
        if(ids.length == 0) { return new ArrayList<>(); }

        return this.jdbc.<T>query((Connection connection) -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "SELECT * FROM "+ this.table +" WHERE id IN (" + DAO.questionmarks(ids.length) + ")"
                    );
                    for (int i = 0; i < ids.length; i++) {
                        ps.setLong(i+1, ids[i]);
                    }
                    return ps;
                },
                this::rowMapper
        );

    }

    static String questionmarks(int length){
        StringBuilder sb = new StringBuilder(length*2-1);
        for (int i = 1; i < length; i++) {
            sb.append("?,");
        }
        sb.append("?");
        return sb.toString();
    }
}
