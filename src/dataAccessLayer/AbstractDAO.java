package dataAccessLayer;

import java.util.List;

import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * this class implements the generic methods that can be implemented by all the other DAO 
 * classes. As attribute, in has a Class type, which represents a class in a program at runtime. 
 * @author tomi
 *
 * @param <T> generic parameter
 */
public class AbstractDAO<T> {
	private final Class<T> type;
	/**
	 * Its constructor simply uses the getClass() method, which returns the reference to the 
	 * Class object of the class of the object.
	 */
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	/**
	 * this method creates a basic select query which selects all the fields of a table; it 
	 * uses StringBuilder to build the desired query
	 * @return a String which represents the query
	 */
	protected String createSelectQuery() {//(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("* ");
		sb.append("FROM ");
		sb.append("bank1." + type.getSimpleName().toLowerCase());
		System.out.println(sb);
		return sb.toString();
		
	}
	/**
	 * this method also creates a select query,  but this one is specific to just one field, 
	 * given as parameter
	 * @param field parameter on which the query is based
	 * @return query
	 */
	protected String createSelectQuery(String field) {//(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("* ");
		sb.append("FROM ");
		sb.append("bank1." + type.getSimpleName().toLowerCase());
		sb.append(" WHERE " + field + " =? ");
		System.out.println(sb);
		return sb.toString();
		
	}
	/**
	 * this method created a delete query with regard to a specific field
	 * @param field parameter on which the query is based
	 * @return query
	 */
	protected String createDeleteQuery(String field) {
		StringBuilder query = new StringBuilder();
		query.append(" delete from ");
		query.append(type.getSimpleName().toLowerCase());
		query.append(" where " + field + "=? ");
		//System.out.println(query.toString());
		return query.toString();
	}
	/**
	 * this method creates an update query. It sets the record of the String field according to 
	 * the field2.
	 * @param field 
	 * @param field2
	 * @return query
	 */
	protected String createUpdateQuery(String field, String field2) {
		StringBuilder query = new StringBuilder();
		query.append(" update ");
		query.append(type.getSimpleName().toLowerCase());
		query.append(" set " + field + "=? ");
		query.append("where " + field2 + "=? ");
		return query.toString();
	}
	/**
	 * this generic method can find a row in a table by identifying one of its fields 
	 * (specified by field) by the value of the given id. It uses the createSelectQuery
	 * (String field) to prepare a PreparedStatement.
	 * @param id
	 * @param field
	 * @return the found record
	 */
	public T findById(int id, String field) {
		Connection con = null;
		PreparedStatement st = null;
		String query = createSelectQuery(field);
		try {
			con = ConnectionFactory.getConnection();
			st = con.prepareStatement(query);
			st.setInt(1, id);
			st.execute();
			return createObjects().get(id - 1);
		} catch(SQLException e) {
			e.getMessage();
		}
		finally {
			ConnectionFactory.close(con);
			//System.out.println(createObjects().get(0).toString());
		}
		return null;
	}
	/**
	 * this one, a generic method as well, deletes a record from a table identifying it by a 
	 * field and the value in it, represented by the id parameter
	 * @param field
	 * @param id
	 */
	public String delete(String field, String id) {
		Connection con = null;
		PreparedStatement st = null;
		String query = createDeleteQuery(field);
		
		try {
			con = ConnectionFactory.getConnection();
			st = con.prepareStatement(query);
			st.setString(1, id);
			st.execute();
			return "User deleted successfuly!";
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.close(con);
		}
		return "An Error occured while trying to delete the user, please try again.";
	}
	/**
	 * this  generic method works by the same principle as the previous ones: it updates a 
	 * field and its new_val (new value) by identifying the desired row by any field (field2) 
	 * and its id.
	 * @param field
	 * @param field2
	 * @param new_val
	 * @param id
	 */
	public void update(String field, String field2, String new_val, String id) {
		Connection con = null;
		PreparedStatement st = null;
		String query = createUpdateQuery(field, field2);
		
		try {
			con = ConnectionFactory.getConnection();
			st = con.prepareStatement(query);
			st.setString(1, new_val);
			st.setString(2, id);
			st.execute();
		} catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			ConnectionFactory.close(con);
		}
	}
	/**
	 * this method creates a list of generic objects with all the contents in a table, including 
	 * the fields’ names and all the data recorded.
	 * @return the list with all contents of a table
	 */
	public List<T> createObjects() {
		List<T> list = new ArrayList<T>();
		ResultSet rs = null;
		PreparedStatement st = null;
		Connection con = null;
		String q = createSelectQuery();
		try {
			con = ConnectionFactory.getConnection();
			st = con.prepareStatement(q);
			rs = st.executeQuery();
			while (rs.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					Object value = rs.getObject(field.getName());
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(), type);
					Method method = pd.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*for(T t : list) {
			t.toString();
			System.out.println(t);
		}*/
		return list;
	}
	/**
	 * this method creates a vector of String objects which holds the names of the fields of 
	 * a table.
	 * @return a vector of Strings which contains the fields
	 */
	public String[] getFields(){
		String[] cols = new String[100];
		Field[] field = type.getDeclaredFields();
		for(int i = 0 ;i < field.length; i++) {
			cols[i] = field[i].getName();
		}
		for(int i = 0; i < field.length; i++) {
			System.out.println(cols[i]);
		}
		return cols;
	}
} 
