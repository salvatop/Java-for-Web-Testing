public void actionSendKeys(By by, String text, Keys... keys) {
        Actions actions = new Actions(driverProvider.get()).moveToElement(waitForElementToBeClickable(by)).click();
        if (text != null) {
            actions.sendKeys(text);
        }
        if (keys != null) {
            actions.sendKeys(keys);
        }
        actions.build().perform();
    }

     public void waitMillis(long milliseconds) {
        sleepToWait(milliseconds);
    }


public void clearAndEnterText(WebElement element, String value) {
        new Actions(driverProvider.get()).moveToElement(
            element).doubleClick().sendKeys(Keys.chord(Keys.CONTROL, "a"), value, Keys.ENTER).build().perform();
    }


    public void moveToMultipleElementsAndClick(Consumer<Actions> actionsConsumer, WebElement... elements) {
        Actions actions = new Actions(driverProvider.get());
        Arrays.asList(elements).forEach(element -> moveToAnElement(actions, element));
        actionsConsumer.accept(actions);
    }

    public void clickElementWithActionpoint(WebElement element) {
        new Actions(driverProvider.get()).moveToElement(element).click().perform();
    }
