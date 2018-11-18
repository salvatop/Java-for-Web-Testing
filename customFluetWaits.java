 
   
   protected WebElement waitForElementToBeClickable(int timeoutInSeconds, By by) {
        return wait.withTimeout(timeoutInSeconds, SECONDS).until(ExpectedConditions.elementToBeClickable(by));
    }

    
    public boolean waitUntilElementAppears(int timeoutInSeconds, By... locators) {
        try {
            return wait.withTimeout(timeoutInSeconds, SECONDS).until(presentAndVisible(locators));
        } catch (TimeoutException te) {
            return false;
        }
}
