public void acceptAlert() {
        try {
            new WebDriverWait(driverProvider.get(), 100).until(ExpectedConditions.alertIsPresent());
            switchTo().alert().accept();
        } catch (Exception e) {
            LOG.error("Alert error message is " + e.getMessage());
        }
    }
