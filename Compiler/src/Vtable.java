import java.io.*;
import java.util.*;

public class Vtable {
	
	protected String tableName;
	protected ArrayList<ArrayList<String>> table;
	
	public Vtable(String name){
		tableName = name;
		table = new ArrayList<ArrayList<String>>();
	}
	
	public void put(String k, String v) {
		for (int i = 0; i < table.size(); i++) {
			if (table.get(i).get(0) == k) {
				table.get(i).set(1,v);
				return;
			}
		}
		table.add( new ArrayList<String>() );
		table.get(table.size()-1).add(k);
		table.get(table.size()-1).add(v);
	}
	
	public String getTableName(){
		return tableName;
	}
	
	public void inherit(Vtable o){
		for (int i = 0; i < o.table.size(); i++){
			put(o.table.get(i).get(0),o.table.get(i).get(1));
		}
	}
	
	public int locate(String methodName) {
		for (int i = 0; i < table.size(); i++ ) {
			if ( table.get(i).get(0).equals("."+methodName)) 
				return i+1;
		}
		return -1;
	}
	
	public String toString() {
		String s = "\n";
		s += tableName+"_vtable:\n";
		s += "\t.word " + tableName + "..new\n";
		for (int i = 0; i < table.size(); i++) {
			s += "\t.word " + table.get(i).get(1) + table.get(i).get(0) + "\n";
		}
		return s;
	}
}