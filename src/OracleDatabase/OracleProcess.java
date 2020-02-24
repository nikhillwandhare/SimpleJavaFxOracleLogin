package OracleDatabase;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import org.json.JSONArray;
import org.json.JSONObject;

import com.UserInformationService;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class OracleProcess {

	OracleConnection cc = new OracleConnection();
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	JSONArray jsonTable = new JSONArray();
	
	public JSONArray executeQuery(String str1) throws Exception
	{
		try 
		{
			con = cc.Connect();
			pst = con.prepareStatement(str1);
			rs = pst.executeQuery();		
			ResultSetMetaData meta = rs.getMetaData();
			while(rs.next())
			{
				JSONObject record = new JSONObject();
				
				for(int i = 1;i < (Integer)meta.getColumnCount();i++)
				{
					record.put(meta.getColumnName(i), rs.getObject(i));
				}
				jsonTable.put(record);
			}
		}
		catch(Exception e) {e.printStackTrace();}
		
		return jsonTable;
	}
	
	@SuppressWarnings("unused")
	public UserInformationService checkUserLogin(String username,String password) throws Exception
	{
		int userId = 0;
		UserInformationService info = null;
		try {
				con = cc.Connect();
				CallableStatement statement = con.prepareCall("{call PR_CHECK_USER_LOGIN(?,?,?,?,?)}");
				
				statement.setString(1, username);
				statement.setString(2, password);
				statement.registerOutParameter(3, OracleTypes.VARCHAR);
				statement.registerOutParameter(4, OracleTypes.NUMBER);
				statement.registerOutParameter(5, OracleTypes.CURSOR);
				statement.executeUpdate();
				
				rs = ((OracleCallableStatement)statement).getCursor(5);
				userId = statement.getInt(4);
				
				while(rs.next())
				{
					info = new UserInformationService();
					info.setADUM_COMPANY(rs.getInt(1));
					info.setADCM_NAME(rs.getString(2));
					info.setADCM_ADD1(rs.getString(3));
					info.setADCM_ADD2(rs.getString(4));
					info.setADCM_ADD3(rs.getString(5));
					info.setADCM_ADD4(rs.getString(6));
					info.setADUM_USER_ID(rs.getInt(7));
					info.setADUM_USER_CODE(rs.getString(8));
					info.setADUM_USER_NAME(rs.getString(9));
					info.setADUM_LEVEL(rs.getInt(10));
					info.setADUM_FRDT(rs.getDate(11));
					info.setADUM_TODT(rs.getDate(12));
					info.setADCM_STATE_ID(rs.getInt(13));
					info.setGSTIN_STATE_NAME(rs.getString(14));
					
				}
				
				
			}
		catch(Exception e) 
			{e.printStackTrace();}
		return info;
	}
}
