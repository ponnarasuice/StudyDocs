package study.javafeatures.Streams;

public class Demo {
    public static void main(String[] args) {
        // System Property for Chrome Driver
//        System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\chromedriver.exe");
//
//        // Instantiate a ChromeDriver class.
//        WebDriver driver=new ChromeDriver();
//
//        // Launch Website
//        driver.navigate().to("http://www.javatpoint.com/");
//
//        //Maximize the browser
//        driver.manage().window().maximize();
//
//        //Scroll down the webpage by 5000 pixels
//        JavascriptExecutor js = (JavascriptExecutor)driver;
//        js.executeScript("scrollBy(0, 5000)");
//
//        // Click on the Search button
//        driver.findElement(By.linkText("Core Java")).click();
        String input = "AAABCCDDDD";
        System.out.println("Compressed String: " + compress(input));

    }

    public static String compress(String str) {
        if (str.isEmpty()) return str;
        StringBuilder compressed = new StringBuilder();
        int count = 1;

        char last = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == last) {
                count++;
            } else {
                compressed.append(last);
                if (count > 1)
                    compressed.append(count);
                last = str.charAt(i);
                count = 1;
            }
        }
        compressed.append(last);
        if (count > 1)
            compressed.append(count);

        return compressed.length() < str.length() ? compressed.toString() : str;
    }
}
