package com.ap1.crudap1.service;

import java.io.IOError;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

@Service
public class AzureStoreAccountSevice {
    public String uploadFileToAzure(MultipartFile file) throws IOException{
        String connectionString = "DefaultEndpointsProtocol=https;AccountName=ap2blobbl;AccountKey=WU086qtLFhUTvW81j21oi74uZ3DPvEQJsP+WVlz7+3J1q+lBTi9jq+XgcUcDMdob3thZMOsXvX8n+ASteoE/rw==;EndpointSuffix=core.windows.net";
    
        BlobContainerClient client = new BlobContainerClientBuilder()
            .connectionString(connectionString)
            .containerName("imagens")
            .buildClient();
        
        BlobClient blob = client.getBlobClient(file.getOriginalFilename());

        blob.upload(file.getInputStream(), file.getSize(),true);

        return "https://ap2blobbl.blob.core.windows.net/imagens/" + file.getOriginalFilename();
    
    }
    
}
