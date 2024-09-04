package com.abs.SpringSecurityJWT.controller;


import com.abs.SpringSecurityJWT.dto.UserGetDTO;
import com.abs.SpringSecurityJWT.dto.UserReqResDTO;
import com.abs.SpringSecurityJWT.notFoundExceptionClass.MyNotFoundExceptionClass;
import com.abs.SpringSecurityJWT.service.gestionCotisationService.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("admin")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * ************* LISTER TOUS LES UTILISATEURS *********************
     */
    @GetMapping("/users")
    ResponseEntity<List<UserGetDTO>> getAllUsers() throws Exception {
        // Appeler le service pour obtenir la liste des utilisateurs convertis en DTO
        List<UserGetDTO> userListDto = userService.listUsers();

        // Retourner la liste des DTO dans la réponse HTTP
        return ResponseEntity.ok(userListDto);
    }


    /**
     * ************* METRE À JOUR UN UTILISATEUR *********************
     */
    @PutMapping("/user/{id}/updated")
    public ResponseEntity<String> updatedUser (@PathVariable Long id, @RequestBody UserReqResDTO userReqResDTO){

        try {
            UserReqResDTO updatedUserRequest = userService.updateUser(id, userReqResDTO);
            ResponseEntity.ok(updatedUserRequest);
            return ResponseEntity.ok("Utilisateur mise à jour avec succès !!!");
        }catch (MyNotFoundExceptionClass e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun utilisateur d'indentifiant " +id+ " à mettre à jour");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" Erreur lors de la mise à jour de l'utilisateur ! ");
        }


    }


    /**
     * ************* SUPPRIMER UN UTILISATEUR PAR SON IDENTIFIANT *********************
     */
    @DeleteMapping("/user/{id}/deleted")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){

        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Utilisateur supprimé avec succès.");
        }catch (MyNotFoundExceptionClass e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé !");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression de l'utilisateur !");
        }

    }


    /**
     * ************* DETAIL D'UN UTILISATEUR *********************
     */
    @GetMapping("/user/{id}/detail")
    public ResponseEntity<UserReqResDTO> detailUser (@PathVariable Long id){

        try {
            UserReqResDTO getUserDetail = userService.detailUser(id);
            return ResponseEntity.ok(getUserDetail);
        }catch (MyNotFoundExceptionClass e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    /**
     * LA RECHERCHE DES UTILISATEURS
     */

    @GetMapping("/users/list/search")
    public ResponseEntity<List<UserReqResDTO>> listUsersSearched (@RequestParam String prenom){

        List<UserReqResDTO> listUsersByPrenomDto = userService.searcheUsers(prenom);

        return ResponseEntity.ok(listUsersByPrenomDto);
    }
    

}
