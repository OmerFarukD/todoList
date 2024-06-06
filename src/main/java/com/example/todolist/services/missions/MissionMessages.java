package com.example.todolist.services.missions;

public final class MissionMessages {
    private MissionMessages() {
    }

    public static  String missionNotFoundMessage(long id){

        String message = "İd : " + id + " olan görev bulunamadı.";
        return message;
    }

    public static  String missionNotFoundByTitleMessage(String message){
        String response = "Başlık : " + message + " olan görev bulunamadı.";
        return response;
    }

    public static final String missionDeletedMessage = "Görev Silindi.";
    public static final String missionAddedMessage = "Görev Eklendi.";
    public static final String missionUpdatedMessage = "Görev Güncellendi.";

}
