package com.abs.SpringSecurityJWT.dto.GlobalException;

import com.abs.SpringSecurityJWT.enums.CotisationError;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DataResponse <T> extends DataApp {

    private final Boolean success;  // Indique si la requête a réussi (true) ou échoué (false)

    private final T data;           // Données retournées si l'opération est un succès


    // 🔹 Constructeur utilisant l'Enum CotisationError pour les erreurs/success
    public DataResponse(CotisationError error, Boolean success, T data) {
        super(error);       // Appelle le constructeur de DataApp en utilisant l'Enum
        this.success = success;
        this.data = data;
    }


    // 🔹 Constructeur classique permettant une personnalisation totale
    public DataResponse(Integer status, String message, Boolean success, T data) {
        super(status, message);     // Appelle le constructeur de DataApp
        this.success = success;
        this.data = data;
    }


}
