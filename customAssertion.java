
    public static void customAssertion(long timeout, long pause, Runnable junitAssertion) {
        final long end = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(timeout);
        while (System.currentTimeMillis() <= end) {
            try {
                junitAssertion.run();
                return;
            } catch (WebDriverException | AssertionError e) {
                LOG.debug("Assertion attempt failed: {}", e.getMessage());
            }
            try {
                Thread.sleep(pause);
            } catch (InterruptedException re) {
                throw new RuntimeException(re);
            }
        }
        junitAssertion.run();
    }
