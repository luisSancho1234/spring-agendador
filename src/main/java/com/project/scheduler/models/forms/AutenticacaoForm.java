package com.project.scheduler.models.forms;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutenticacaoForm {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
