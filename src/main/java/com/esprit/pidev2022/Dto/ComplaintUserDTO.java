package com.esprit.pidev2022.Dto;

import lombok.Data;

@Data
public class ComplaintUserDTO {
    private Long idComplaint;
    private String Subject;
    private String Message;
    private String Status;
    private Long idUser;
    private String username;

}
