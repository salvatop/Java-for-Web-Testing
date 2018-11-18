    public boolean IsElementExists(By locator) {
        return !findElements(locator).isEmpty();
    }
    
      public boolean isUrlExisting(String link) {
        InputStream input = null;
        try {
            URL url = new URL(link);
            input = url.openStream();
        } catch (IOException exception) {
            LOG.debug("Invalid URL {}", link);
        }
        return input != null;
    }


     public boolean found(List<String> texts) {
        return texts.forEach(text -> found(text));
    }
