package kgbStage.api.controller;


import kgbStage.api.entity.Notification;
import kgbStage.api.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private  NotificationService notificationServices;



    @GetMapping("/all")
    public ResponseEntity<HashMap<Object, Object>> getAllNotifications() {
        return notificationServices.getAllNotifications();
    }
    @GetMapping("/concours/{concoursId}")
    public ResponseEntity<HashMap<Object, Object>> getNotificationsByConcoursId(@PathVariable UUID concoursId) {
       return  notificationServices.getNotificationsByConcoursId(concoursId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashMap<Object, Object>> getNotificationById(@PathVariable UUID id) {
        return notificationServices.getNotificationById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<Object, Object>> createNotification(@RequestBody Notification notification) {
        return notificationServices.createNotification(notification);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HashMap<Object, Object>> updateNotification(@PathVariable UUID id, @RequestBody Notification updatedNotification) {
        return notificationServices.updateNotification(id, updatedNotification);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<Object, Object>> deleteNotification(@PathVariable UUID id) {
        return notificationServices.deleteNotification(id);
    }
}
