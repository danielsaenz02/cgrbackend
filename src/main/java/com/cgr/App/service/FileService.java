package com.cgr.App.service;

import com.cgr.App.entity.File;
import com.cgr.App.repository.IFileService;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Base64;

@Service
public class FileService implements IFileService {


    @Override
    public String uploadFileAzure(String filename, InputStream content, long length, Integer idComplaint) {
        String resultService = "";
        String storageConnectionAzure = "DefaultEndpointsProtocol=https;AccountName=cgrdenuncias;AccountKey=ULUkrE6s1hxoK2nZea4LbHss9OQ4K/Fvx/0sC01/aFnnKngUiSrylyrjeEaS379JzHxCm/Kdw98f+AStWHCkWA==;EndpointSuffix=core.windows.net";
        String nameContainer= "denuncias";
        String otherName = "";

        try {
            CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionAzure);
            CloudBlobClient serviceClient = account.createCloudBlobClient();
            CloudBlobContainer container = serviceClient.getContainerReference(nameContainer);
            CloudBlob blob = container.getBlockBlobReference(filename);
            if(blob.exists()){

                String[] parts = filename.split("\\.");
                otherName = parts[0] + String.valueOf(idComplaint) + "." + parts[1];
                CloudBlob blobOtherFile = container.getBlockBlobReference(otherName);
                blobOtherFile.upload(content,length);
                resultService=otherName;
            }else {
                blob.upload(content,length);
                resultService=filename;
            }

        }catch (Exception e){
            resultService = e.getMessage();

        }
        return resultService;
    }
}
