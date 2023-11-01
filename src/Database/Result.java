package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ResultContainer for the result set and the preparedStatement 
 * 
 * @author Michael
 */
public class Result {

    private final ResultSet resultSet;
    private final PreparedStatement preparedStatement;
    
    public Result(ResultSet resultSet, PreparedStatement preparedStatement) {
        this.resultSet = resultSet;
        this.preparedStatement = preparedStatement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }
    
    public void closeResult() throws SQLException {
        this.resultSet.close();
        this.preparedStatement.close(); 
    }
}
