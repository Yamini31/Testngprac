
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;


import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TeatPrac {

	
	String colorofbox2=null;
	WebDriver driver=new ChromeDriver();
	String driverPath = "/home/qainfotech/Downloads/chromedriver";

	@BeforeTest
	  public void  LaunchBrowser() throws InterruptedException, IOException, DocumentException {
	          
		Properties obj = new Properties();
		FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "/application.properties");
		obj.load(objfile);
		     driver.get(obj.getProperty("url"));
			 driver.manage().window().maximize();
			 Thread.sleep(2000);
			 
			 driver.findElement(By.xpath("//a[@href=\\\"/tatoc/basic\\\"]")).click();
			 Thread.sleep(2000);
	  
	  
	  }

	
	
  @Test
  public void Test01TitleMatch() {
	  
	  String headng=driver.findElement(By.cssSelector("div.title")).getText();
	  String heading="T.A.T.O.C";
	  if(headng.equals(heading))
	  {
		  System.out.print("Title match");
		  
	  }
	  else
	  {
		  System.out.print("Title not match");
		  
	  }
	  
	  
  }
  
   @Test
  public void Test02FrameSwitch() throws InterruptedException{
	  driver.findElement(By.xpath("//div[@onclick=\"passthru();\"]")).click();
		 Thread.sleep(2000);
		 
        driver.switchTo().frame("main");
	  
  }
  
  @Test
  public void Test03BoxColor()
  {
	  
	  String colorofbox1=driver.findElement(By.id("answer")).getAttribute("class");
		 System.out.print(colorofbox1);
	     
		 
		 do
		 {
			 driver.findElement(By.xpath("/html/body/center/a[1]")).click();
			 driver.switchTo().frame("child");
			 colorofbox2=driver.findElement(By.id("answer")).getAttribute("class");
			 System.out.println(colorofbox2);
			 driver.switchTo().parentFrame();
			 
			
			 
	     }while(!(colorofbox1.equals(colorofbox2)));
		 
	  
  }
  @Test
  public void Test04LaunchPopUp() throws InterruptedException
  {
	  driver.findElement(By.linkText("Proceed")).click();
		 Thread.sleep(2000);
		 WebElement drag=driver.findElement(By.xpath("//*[@id=\"dragbox\"]"));
		 WebElement drop=driver.findElement(By.xpath("//*[@id=\"dropbox\"]"));
		 Actions a = new Actions(driver);
		 a.dragAndDrop(drag,drop).build().perform();
		 driver.findElement(By.xpath("/html/body/div/div[2]/a")).click();	
		 Thread.sleep(1000);
		 driver.findElement(By.linkText("Launch Popup Window")).click();	
		 Thread.sleep(1000);
	
  }
  
  
  @Test
  public void Test05SendValue() throws InterruptedException
  {

	  String MainWindow=driver.getWindowHandle();		
		
	    Set<String> s1=driver.getWindowHandles();		
        Iterator<String> i1=s1.iterator();		

        while(i1.hasNext())			
        {		
            String ChildWindow=i1.next();		
            		
            if(!MainWindow.equalsIgnoreCase(ChildWindow))			
            {    		
                 
                  driver.switchTo().window(ChildWindow);	
        
      	 driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("yaminigupta");
	      Thread.sleep(3000);
	       driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
            }
	  
  }
        driver.switchTo().window(MainWindow);
  }

  @Test
  public void Test06GenerateToken() throws InterruptedException
  {
	  
	  
      driver.findElement(By.linkText("Proceed")).click();	
		 Thread.sleep(1000);
		 
		  driver.findElement(By.linkText("Generate Token")).click();	
			 Thread.sleep(1000);
	
  }
  
  @Test
  public void Test07CookieGeneration()
  {

		 String token =driver.findElement(By.id("token")).getText();
		System.out.println(token);
		 Cookie name =new Cookie("mycookie",token.substring(7));
		 driver.manage().addCookie(name);
		
		Set<org.openqa.selenium.Cookie> cookiesList =  driver.manage().getCookies();
		for(Cookie getcookies :cookiesList) {
		    System.out.println(getcookies );
		}
		
		
		
		
		
  }
  
  
  
	
  @AfterTest
  public void afterTest() throws InterruptedException {
   
	  driver.findElement(By.linkText("Proceed")).click();
		
		 
  }

	
	
}
