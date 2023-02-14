package com.cgr.App.repository;

import com.cgr.App.entity.File;

import java.io.InputStream;

public interface IFileService {
    public String uploadFileAzure(String filename, InputStream content, long length, Integer idComplaint);

}
