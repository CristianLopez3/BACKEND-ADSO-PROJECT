package edu.menueasy.adso.domain.menu.image;

public class ImageResponse {

    private String fileName;
    private String message;

    public ImageResponse(String fileName, String message) {
        this.fileName = fileName;
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public String getMessage() {
        return message;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
