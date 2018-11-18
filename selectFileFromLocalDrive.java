public void selectFileFromLocalDrive(By attachInput, String fileName) {
        File file = new File("src/main/resources/net/test/dataImport/" + fileName);
        String path = file.getAbsolutePath();
        WebElement element = findElement(attachInput);
        element.sendKeys(path);
    }

   protected String getResourceFileAbsolutePath(String fileSourcePath) throws FileNotFoundException {
        try {
            return new File(getClass().getClassLoader().getResource(fileSourcePath).toURI()).getPath();
        } catch (URISyntaxException e) {
            throw new FileNotFoundException("Could not find the file " + fileSourcePath);
        }
    }
