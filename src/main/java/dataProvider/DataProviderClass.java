package dataProvider;
import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name="testdata")
    public Object[][] passTestData()
    {
        Object [][] inputData=new Object[1][2];
        inputData[0][0]= "BULLY STICK";
        inputData[0][1]= 2;
        return inputData;
    }
}
