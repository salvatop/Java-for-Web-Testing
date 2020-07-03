private static final long DEFAULT_WAIT_TIMEOUT = Integer.parseInt(System.getProperty("timeout", "30"));

public FluentWait<WebDriver> newWait() {
       return new WebDriverWait(this, DEFAULT_WAIT_TIMEOUT)
           .ignoring(StaleElementReferenceException.class);
   }

newWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[]")));
