public Object executeScript(String s, Object... args) {
        return ((JavascriptExecutor)this.driverProvider.get()).executeScript(s, args);
    }

    public void openLinkInANewBrowserTab(String link) {
        String scriptToExecute = "var dom = document, aTag = dom.createElement('a');"
            + "aTag.target = '_blank';"
            + "aTag.href = '%s';"
            + "aTag.innerHTML = '.';"
            + "dom.body.appendChild(aTag);"
            + "return aTag";
        Object element = executeScript(String.format(scriptToExecute, link));
        if (element instanceof WebElement) {
            WebElement linkToOpen = (WebElement) element;
            linkToOpen.click();
            executeScript("var aTag = arguments[0]; aTag.parentNode.removeChild(aTag);", linkToOpen);
        } else {
            throw new JavaScriptException(element, "Couldn't open the link in a new tab", 1);
        }
    }
