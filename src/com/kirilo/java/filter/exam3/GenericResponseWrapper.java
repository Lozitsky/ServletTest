package com.kirilo.java.filter.exam3;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class GenericResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream outputStream;

    private int contentLength;

    private String contentType;

    public GenericResponseWrapper(HttpServletResponse response) {
        super(response);
        outputStream = new ByteArrayOutputStream();
    }

    public byte[] getData() {
        return outputStream.toByteArray();
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return new FilterServletOutputStream(outputStream);
    }

    @Override
    public PrintWriter getWriter() {
        return new PrintWriter(getOutputStream(), true);
    }

    @Override
    public void setContentLength(int length) {
        contentLength = length;
        super.setContentLength(length);
    }

    public int getContentLength() {
        return contentLength;
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
        super.setContentType(contentType);
    }

    @Override
    public String getContentType() {
        return contentType;
    }

}
