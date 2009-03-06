/**
 * Created on: 2004-6-23 10:26:50
 * Author:     zhoufan
 */
package uncertain.datatype;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * 
 */
public class DoubleType extends AbstractDataType implements DataType {

	/**
	 * Constructor for IntegerType.
	 */
	public DoubleType() {
	}

	/**
	 * @see uncertain.datatype.DataType#getJavaType()
	 */
	public Class getJavaType() {
		return Double.class;
	}

	/**
	 * @see uncertain.datatype.DataType#getSqlType()
	 */
	public int getSqlType() {
		return Types.DOUBLE;
	}

	/**
	 * @see uncertain.datatype.DataType#getObject(CallableStatement, int)
	 */
	public Object getObject(CallableStatement stmt, int id)
		throws SQLException {
		return new Double(stmt.getDouble(id));
	}

	/**
	 * @see uncertain.datatype.DataType#getObject(ResultSet, int)
	 */
	public Object getObject(ResultSet rs, int id) throws SQLException {
		return new Double(rs.getDouble(id));
	}

	/**
	 * @see uncertain.datatype.DataType#registerParameter(CallableStatement, int)
	 */
	public void registerParameter(CallableStatement stmt, int id)
		throws SQLException {
		stmt.registerOutParameter(id, Types.DOUBLE);
	}

    public void setParameter (PreparedStatement stmt, int id, Object value ) throws SQLException{
        if(value==null)
            stmt.setNull(id, getSqlType());
        else
            stmt.setDouble(id, ((Number)value).doubleValue());
    }    
    
	/**
	 * @see uncertain.datatype.DataType#convert(Object)
	 */
	public Object convert(Object value) 
        throws ConvertionException
    {
		if( value instanceof String){
            if(((String)value).length()==0)
                return null;            
    		try{
    			return new Double((String)value);
    		}catch(NumberFormatException ex){
    			throw new ConvertionException("Can't convert from string to double", ex);
    		}		
        }
		else if( value instanceof Number)
			return new Double(((Number)value).doubleValue());
		else
			return null;
	}

}
