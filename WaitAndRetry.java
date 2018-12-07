
public void waitAndRetry(long attepmts, long pause, Runnable action) {
    for (; attepmts > 0; attepmts--) {
        try {
            sleepToWait(pause);
            action.run();
            return;
        } catch ( WebDriverException e) {
            LOG.debug("Attempt failed: {}", e.getMessage());
        }
    }
}

waitAndRetry(3, 500, () -> clickOnElement(By.xpath("//*[contains()]")));
