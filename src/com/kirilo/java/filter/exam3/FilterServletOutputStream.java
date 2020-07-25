package com.kirilo.java.filter.exam3;

import javax.servlet.ServletOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FilterServletOutputStream extends ServletOutputStream {
    private DataOutputStream stream;

    public FilterServletOutputStream(OutputStream outputStream) {
        stream = new DataOutputStream(outputStream);
    }

    @Override
    public void write(int b) throws IOException {
        stream.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        stream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        stream.write(b, off, len);
    }
}
