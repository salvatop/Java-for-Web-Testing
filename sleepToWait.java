public void sleepToWait(long millis) {
        try {
            LOG.debug("Going to sleep for {} ms", millis);
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
