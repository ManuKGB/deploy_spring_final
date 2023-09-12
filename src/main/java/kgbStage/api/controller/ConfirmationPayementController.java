package kgbStage.api.controller;

import kgbStage.api.entity.ConfirmationPayement;
import kgbStage.api.services.ConfirmationPayementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/confirmation")
public class ConfirmationPayementController {
    @Autowired
    private ConfirmationPayementService confirmationPayementService;

    @GetMapping("/all")
    ResponseEntity<HashMap<Object,Object>> getAllConfirmation(){
        return confirmationPayementService.getAllConfirmation();
    }

    @GetMapping("/{id}")
    ResponseEntity<HashMap<Object,Object>> getOneConfirmation(@PathVariable("id") String idConfirmation  ){
        return confirmationPayementService.getOneConfirmation(idConfirmation);
    }

    @PostMapping("/save")
    ResponseEntity<HashMap<Object,Object>> saveConfirmation(@RequestBody ConfirmationPayement confirmationPayement){
        return confirmationPayementService.saveConfirmation(confirmationPayement);
    }

}
