package com.abs.SpringSecurityJWT.dto.GlobalException;

import com.abs.SpringSecurityJWT.enums.CotisationError;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DataApp {

    private final Integer status; // Code HTTP de la réponse (ex: 200, 400, 500)

    private final String message; // Message décrivant le statut de la requête


    // 🔹 Constructeur classique permettant d'initialiser un code et un message personnalisés
    public DataApp(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    // 🔹 Constructeur prenant directement une valeur de l'Enum CotisationError
    public DataApp(CotisationError error) {
        this.status = error.getCode();      // Récupère le code HTTP défini dans l'Enum
        this.message = error.getMessage();  // Récupère le message associé
    }

    // 🔹 Constructeur prenant des paramètres dynamiques à insérer dans le message
    public DataApp(Integer status, String message, Object... params) {
        this.status = status;
        this.message = String.format(message, params);  // Formate dynamiquement le message
    }



}
