package com.cgr.App.controller;

import com.cgr.App.entity.Anexo;
import com.cgr.App.entity.Complaint;
import com.cgr.App.entity.File;
import com.cgr.App.repository.IAnexoService;
import com.cgr.App.repository.IFileService;
import com.cgr.App.service.AnexoService;
import com.cgr.App.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/file")
@CrossOrigin({"*"})
public class FileController {
    @Autowired
    private IFileService iFileService;
    @Autowired
    private AnexoService anexoService;
    @Autowired
    private ComplaintService complaintService;
    @PostMapping("/upload/{idComplaint}")
    public ResponseEntity<String> uploadFile(@RequestParam("files") MultipartFile[] files, @PathVariable Integer idComplaint){
        String message = "";
        try {
        List<String> fileNames = new ArrayList<>();
        Arrays.asList(files).stream().forEach(file -> {
            try {
                String nameFile = iFileService.uploadFileAzure(file.getOriginalFilename(),file.getInputStream(), file.getSize(), idComplaint);
                fileNames.add(file.getOriginalFilename());
                anexoService.saveAnexo(idComplaint,nameFile);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
            message="Se subieron los archivos correctamente";
            return new ResponseEntity<String>(message,HttpStatus.OK);
        } catch (Exception e){
            message="Fallo al subir los archivos";
            return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/complaint/{idComplaint}")
    public ResponseEntity<Complaint> findByIdComplaint(@PathVariable Long idComplaint){
        Complaint complaint = complaintService.findById(idComplaint);
        return new ResponseEntity<Complaint>(complaint,HttpStatus.OK);
    }
    @GetMapping("/complaintstatus/{idComplaint}")
    public ResponseEntity<Complaint> updateComplaint(@PathVariable Integer idComplaint){
        complaintService.updateComplaintById(idComplaint);
        return new ResponseEntity<Complaint>(HttpStatus.OK);
    }
}
