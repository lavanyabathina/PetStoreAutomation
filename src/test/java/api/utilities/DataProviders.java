package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name="Data")
	public  String[][] getAllData(){
		
      String path= System.getProperty("user.dir") + "//testData//userdata.xlsx";
   		
      int rowCount= XLUtility.getRowCount(path, "Sheet1");
      int columnCount = XLUtility.getCellCount(path, "Sheet1", 1);
      String [][] data = new String [rowCount] [columnCount];
      
      for(int i=1; i <= rowCount ; i++ )
      {
    	  for(int j=0; j < columnCount ; j++)
    	  {
    		  String cellValue = XLUtility.getCellData(path, "Sheet1", i, j);
    		  System.out.println("Cell value is:" + cellValue);
    		  data[i-1][j] = cellValue;
    	  }
	}
	
	 return data;

}
	
	@DataProvider(name="Usernames")
	public String[] getUserNames() {
		String path= System.getProperty("user.dir")+"//testData//userdata.xlsx";
		int rowCount = XLUtility.getRowCount(path, "Sheet1");
		
		String [] userNames = new String [rowCount];
		for (int i=1 ; i<= rowCount; i++) {
			
			String name = XLUtility.getCellData(path, "Sheet1", i, 1);
			System.out.println("Username is:" + name);
			userNames[i-1] = name;
		}
		
		return userNames;
		
	}
	
	
}
