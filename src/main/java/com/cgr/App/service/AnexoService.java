package com.cgr.App.service;


import com.cgr.App.entity.Anexo;
import com.cgr.App.repository.IAnexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnexoService {
    @Autowired
    private IAnexoService iAnexoService;

    public void saveAnexo(Integer idComplaint, String name){
         iAnexoService.saveAnexo(idComplaint,name);
    }


}
