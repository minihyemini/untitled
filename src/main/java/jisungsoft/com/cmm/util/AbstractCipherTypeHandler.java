package jisungsoft.com.cmm.util;

import egovframework.com.cmm.service.Globals;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mybatis custom Type Handler for Cipher
 */
public abstract class AbstractCipherTypeHandler implements TypeHandler<String> {

    /**
     *
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        // 암호화 여부 확인
        if(isCipher()){
            parameter = encode(parameter);
        }
        ps.setString(i, parameter);
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        // 암호화 여부 확인
        if(isCipher()){
            value = decode(value);
        }
        return value;
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        // 암호화 여부 확인
        if(isCipher()){
            value = decode(value);
        }
        return value;
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        // 암호화 여부 확인
        if(isCipher()){
            value = decode(value);
        }
        return value;
    }

    /**
     * 암호화 여부
     * @return 암호화 여부
     */
    protected abstract boolean isCipher();

    /**
     * 암호화
     * @param value 변환 문자
     * @return 암호화된 문자
     */
    protected String encode(String value){
        try{
            value = CommonUtil.encryptAES256(value, Globals.ENCRYP_KEY);
        }catch(Exception e){}

        return value;
    }

    /**
     * 복호화
     * @param value 변환 문자
     * @return 복호화된 문자
     */
    protected String decode(String value){
        try{
            value = CommonUtil.decryptAES256(value, Globals.ENCRYP_KEY);
        }catch(Exception e){}

        return value;
    }
}
