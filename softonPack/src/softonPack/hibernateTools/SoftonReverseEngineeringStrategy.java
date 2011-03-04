package softonPack.hibernateTools;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringRuntimeInfo;
import org.hibernate.cfg.reveng.ReverseEngineeringSettings;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;
import org.hibernate.mapping.ForeignKey;
import org.hibernate.mapping.Table;

public class SoftonReverseEngineeringStrategy extends
		DelegatingReverseEngineeringStrategy {

	Properties prop = new Properties();

	/*
	 * Retorna o nome da classe de ID 
	 */
	@Override
	public String classNameToCompositeIdName(String className) {
		return super.classNameToCompositeIdName(className);
	}

	@Override
	public void close() {

		super.close();
	}

	@Override
	public String columnToHibernateTypeName(TableIdentifier table,
			String columnName, int sqlType, int length, int precision,
			int scale, boolean nullable, boolean generatedIdentifier) {

		return super.columnToHibernateTypeName(table, columnName, sqlType, length,
				precision, scale, nullable, generatedIdentifier);
	}

	@Override
	public Map columnToMetaAttributes(TableIdentifier identifier, String column) {

		return super.columnToMetaAttributes(identifier, column);
	}

	/*
	 * Retorna o nome da propriedade da classe
	 */
	@Override
	public String columnToPropertyName(TableIdentifier table, String column) {

		return super.columnToPropertyName(table, replaceName(column));
	}

	@Override
	public void configure(ReverseEngineeringRuntimeInfo runtimeInfo) {

		super.configure(runtimeInfo);
	}

	@Override
	public boolean excludeColumn(TableIdentifier identifier, String columnName) {

		return super.excludeColumn(identifier, columnName);
	}

	@Override
	public boolean excludeForeignKeyAsCollection(String keyname,
			TableIdentifier fromTable, List fromColumns,
			TableIdentifier referencedTable, List referencedColumns) {


		return super.excludeForeignKeyAsCollection(keyname, fromTable, fromColumns,
				referencedTable, referencedColumns);
	}

	@Override
	public boolean excludeForeignKeyAsManytoOne(String keyname,
			TableIdentifier fromTable, List fromColumns,
			TableIdentifier referencedTable, List referencedColumns) {

		return super.excludeForeignKeyAsManytoOne(keyname, fromTable, fromColumns,
				referencedTable, referencedColumns);
	}

	@Override
	public boolean excludeTable(TableIdentifier ti) {

		return super.excludeTable(ti);
	}

	/*
	 * Retorna o nome do metodo que tras a collection de FK. 
	 */
	@Override
	public String foreignKeyToCollectionName(String keyname,
			TableIdentifier fromTable, List fromColumns,
			TableIdentifier referencedTable, List referencedColumns,
			boolean uniqueReference) {

		return super.foreignKeyToCollectionName(keyname, fromTable, fromColumns,
				referencedTable, referencedColumns, uniqueReference);
	}

	/*
	 * Retorna o nome do método de uma chave estrangeira
	 */
	@Override
	public String foreignKeyToEntityName(String keyname,
			TableIdentifier fromTable, List fromColumnNames,
			TableIdentifier referencedTable, List referencedColumnNames,
			boolean uniqueReference) {

		return super.foreignKeyToEntityName(keyname, fromTable, fromColumnNames,
				referencedTable, referencedColumnNames, uniqueReference);
	}

	@Override
	public String foreignKeyToManyToManyName(ForeignKey fromKey,
			TableIdentifier middleTable, ForeignKey toKey,
			boolean uniqueReference) {

		return super.foreignKeyToManyToManyName(fromKey, middleTable, toKey,
				uniqueReference);
	}

	@Override
	public List getForeignKeys(TableIdentifier referencedTable) {

		return super.getForeignKeys(referencedTable);
	}

	@Override
	public String getOptimisticLockColumnName(TableIdentifier identifier) {

		return super.getOptimisticLockColumnName(identifier);
	}

	@Override
	public List getPrimaryKeyColumnNames(TableIdentifier identifier) {

		return super.getPrimaryKeyColumnNames(identifier);
	}

	@Override
	public List getSchemaSelections() {

		return super.getSchemaSelections();
	}

	@Override
	public Properties getTableIdentifierProperties(TableIdentifier identifier) {

		return super.getTableIdentifierProperties(identifier);
	}

	@Override
	public String getTableIdentifierStrategyName(TableIdentifier tableIdentifier) {

		return super.getTableIdentifierStrategyName(tableIdentifier);
	}

	@Override
	public boolean isForeignKeyCollectionInverse(String name,
			TableIdentifier foreignKeyTable, List columns,
			TableIdentifier foreignKeyReferencedTable, List referencedColumns) {

		return super.isForeignKeyCollectionInverse(name, foreignKeyTable, columns,
				foreignKeyReferencedTable, referencedColumns);
	}

	@Override
	public boolean isForeignKeyCollectionLazy(String name,
			TableIdentifier foreignKeyTable, List columns,
			TableIdentifier foreignKeyReferencedTable, List referencedColumns) {

		return super.isForeignKeyCollectionLazy(name, foreignKeyTable, columns,
				foreignKeyReferencedTable, referencedColumns);
	}

	@Override
	public boolean isManyToManyTable(Table table) {

		return super.isManyToManyTable(table);
	}

	@Override
	public void setSettings(ReverseEngineeringSettings settings) {

		super.setSettings(settings);
	}

	@Override
	public String tableToClassName(TableIdentifier tableIdentifier) {


		String className = replaceName(tableIdentifier.getName());
		return super.tableToClassName(
				new TableIdentifier(tableIdentifier.getCatalog(),
						tableIdentifier.getSchema(),
						className));
	}

	@Override
	public String tableToCompositeIdName(TableIdentifier identifier) {

		return super.tableToCompositeIdName(identifier);
	}

	@Override
	public String tableToIdentifierPropertyName(TableIdentifier tableIdentifier) {

		return super.tableToIdentifierPropertyName(tableIdentifier);
	}

	@Override
	public Map tableToMetaAttributes(TableIdentifier tableIdentifier) {

		return super.tableToMetaAttributes(tableIdentifier);
	}

	@Override
	public boolean useColumnForOptimisticLock(TableIdentifier identifier,
			String column) {

		return super.useColumnForOptimisticLock(identifier, column);
	}

	public SoftonReverseEngineeringStrategy(ReverseEngineeringStrategy delegate) {
		super(delegate);
		
		URL confUrl = SoftonReverseEngineeringStrategy.class.getResource("db_dictionary.properties");
		try {
			prop.load(confUrl.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String replaceName(String name){
		String newName = new String(name.toLowerCase());
		
		for (Iterator iterator = prop.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			
			if (newName.indexOf(key + "_") > -1) {
				newName = newName.replaceAll(key, prop.getProperty(key));
			}
			
			if (newName.endsWith(key)) {
				newName = newName.replaceAll(key, prop.getProperty(key));
			}
		}
		
		return newName;
	}
}
