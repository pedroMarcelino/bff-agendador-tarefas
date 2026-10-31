package com.marcelino.bffagendadortarefas.business;


import com.marcelino.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.marcelino.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviaEmail(TarefasDTOResponse dto){
        emailClient.enviarEmail(dto);
    }
}
