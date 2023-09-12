package kgbStage.api.services;

import kgbStage.api.entity.Concours;
import kgbStage.api.entity.Notification;
import kgbStage.api.repository.ConcoursRepository;
import kgbStage.api.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private  NotificationRepository notificationRepository;
    @Autowired
    private  ConcoursRepository concoursRepository;



    public HashMap<Object, Object> reponseModel(int statusCode, Object result) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("statusCode", statusCode);
        if (statusCode == 201) {
            map.put("message", "Notification créée avec succès");
            map.put("ok", true);
        } else if (statusCode == 200) {
            map.put("message", "Toutes les notifications récupérées");
            map.put("ok", true);
        } else if (statusCode == 404) {
            map.put("message", "Cette notification n'existe pas !");
            map.put("ok", false);
        } else if (statusCode == 204) {
            map.put("message", "Notification supprimée avec succès");
            map.put("ok", true);
        } else if (statusCode == 205) {
            map.put("message", "Notification restaurée avec succès");
            map.put("ok", true);
        }
        map.put("response", result);
        return map;
    }

    public ResponseEntity<HashMap<Object, Object>> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        var response = this.reponseModel(HttpStatus.OK.value(), notifications);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<HashMap<Object, Object>> createNotification(Notification notification) {
        Concours concours = concoursRepository.findById(notification.getConcours().getIdConcours()).orElse(null);
        if (concours == null) {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        notification.setConcours(concours);
        notificationRepository.save(notification);
        var response = this.reponseModel(HttpStatus.CREATED.value(), notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<HashMap<Object, Object>> getNotificationById(UUID idNotification) {
        Optional<Notification> notificationOptional = notificationRepository.findById(idNotification);

        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            var response = this.reponseModel(HttpStatus.OK.value(), notification);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public ResponseEntity<HashMap<Object, Object>> updateNotification(UUID idNotification, Notification updatedNotification) {
        Optional<Notification> notificationOptional = notificationRepository.findById(idNotification);

        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            Concours concours = concoursRepository.findById(updatedNotification.getConcours().getIdConcours()).orElse(null);
            if (concours == null) {
                var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            notification.setLibelle(updatedNotification.getLibelle());
            notification.setDescription(updatedNotification.getDescription());
            notification.setConcours(concours);
            notification.setDate(LocalDateTime.now());
            notificationRepository.save(notification);

            var response = this.reponseModel(HttpStatus.OK.value(), notification);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    public ResponseEntity<HashMap<Object, Object>> deleteNotification(UUID idNotification) {
        Optional<Notification> notificationOptional = notificationRepository.findById(idNotification);
        if (notificationOptional.isPresent()) {
            notificationRepository.deleteById(idNotification);
            var response = this.reponseModel(HttpStatus.NO_CONTENT.value(), null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    public ResponseEntity<HashMap<Object, Object>> getNotificationsByConcoursId(UUID concoursId) {
        Optional<Concours> concoursOptional = concoursRepository.findById(concoursId);
        if (!concoursOptional.isPresent()) {
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), "Le concours avec l'ID spécifié n'existe pas");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        Concours concours = concoursOptional.get();
        List<Notification> notifications = notificationRepository.findByConcours(concours);
        if (notifications.isEmpty()){
            var response = this.reponseModel(HttpStatus.NOT_FOUND.value(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            var response = this.reponseModel(HttpStatus.OK.value(), notifications);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

}
