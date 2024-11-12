package fr.aplose.aploseframework.model.dolibarr;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author oandrade
 */
public class DocumentFile {
    String filename;
    @JsonProperty("content-type")
    String contentType;
    Integer filesize;
    String content;
    String encoding;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
    
            }
