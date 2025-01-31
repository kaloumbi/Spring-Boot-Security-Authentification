package com.abs.SpringSecurityJWT.enums;

public enum CotisationError {

    // Succès
    OK("Succès", 200),
    CREATED("Ressource créée avec succès", 201),
    NO_CONTENT("Aucun contenu disponible", 204),

    // Erreurs client (4xx)
    BAD_REQUEST("Syntaxe de requête invalide", 400),
    UNAUTHORIZED("Accès non autorisé", 401),
    FORBIDDEN("Accès interdit", 403),
    NOT_FOUND("Ressource non trouvée", 404),
    METHOD_NOT_ALLOWED("Méthode HTTP non autorisée", 405),
    CONFLICT("Conflit avec l'état actuel de la ressource", 409),
    PAYLOAD_TOO_LARGE("Charge utile de la requête trop grande", 413),
    UNSUPPORTED_MEDIA_TYPE("Type de média non supporté", 415),

    // Erreurs serveur (5xx)
    INTERNAL_SERVER_ERROR("Une erreur interne du serveur est survenue !", 500),
    NOT_IMPLEMENTED("Fonctionnalité non implémentée", 501),
    BAD_GATEWAY("Réponse invalide du passerelle", 502),
    SERVICE_UNAVAILABLE("Service temporairement indisponible", 503),
    GATEWAY_TIMEOUT("Délai d'attente de passerelle dépassé", 504);


    private final String message;
    private final Integer code;


    CotisationError(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage(){
        return message;
    }

    public Integer getCode(){
        return code;
    }

}
